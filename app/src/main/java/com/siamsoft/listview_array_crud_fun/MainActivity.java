package com.siamsoft.listview_array_crud_fun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.NameList;

import java.util.ArrayList;

import static com.siamsoft.listview_array_crud_fun.R.id.tv;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> name;
    ArrayAdapter<String> adp;
    SearchView sv;
    ListView lstv;
    EditText e1,e2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstv = findViewById(R.id.lv);
        b1 = findViewById(R.id.bt);
        e1 = findViewById(R.id.edt);
        sv = findViewById(R.id.srcv);
        name = new ArrayList<String>();

        getAnimalName();

        adp = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, name);
        lstv.setAdapter(adp);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adp.getFilter().filter(newText);
                return false;
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newitem = e1.getText().toString();
                name.add(newitem);
                adp.notifyDataSetChanged();

            }
        });

        lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                updateitem(name.get(position), position);
            }


        });
    }
        private void updateitem(String old, int index) {


        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Title..");
        dialog.setContentView(R.layout.activity_editpage);
        TextView text = (TextView)dialog.findViewById(tv);
        text.setText("Update Item");

        text.setTextColor(Color.parseColor(("#ff8822")));
        e2 = dialog.findViewById(R.id.et2);
        e2.setText(old);
        Button ddb = dialog.findViewById(R.id.b2);

        ddb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String ent = e2.getText().toString();
                name.set(index, ent);
                adp.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }

        });

        dialog.show();

            lstv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {


                    SparseBooleanArray checkedPositions =lstv.getCheckedItemPositions();

                    int count = lstv.getCount();
                    for (int item=count-1;item>=0;item--){

                        if(checkedPositions.get(item))

                        {
                            adp.remove(name.get(item));
                            Toast.makeText(getApplicationContext(),"deleteed", Toast.LENGTH_SHORT).show();

                        }

                        checkedPositions.clear();
                        adp.notifyDataSetChanged();
                    }

                    return false;
                }
            });

        }





    void getAnimalName()
    {
        name.add("DOG");
        name.add("CAT");
        name.add("TIGER");
        name.add("SNAKE");

    }


    /*public void update(String old, final int index)
    {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Title...");
        dialog.setContentView(R.layout.activity_editpage);
        TextView txt = dialog.findViewById(R.id.tv);
        txt.setText("Update Item");
        txt.setTextColor(Color.parseColor("#ff882"));
        e2 = dialog.findViewById(R.id.et2);
        e2.setText(old);

        Button db = dialog.findViewById(R.id.b2);

        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ent = e2.getText().toString();
                name.set(index,ent);
                adp.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

           dialog.show();


    }
*/

}