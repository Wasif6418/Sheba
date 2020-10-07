package com.example.daiya.sheba;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowList extends AppCompatActivity {
    private ListView listView;
    Cursor data;
    private int track;
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        listView=findViewById(R.id.listViewId);
        myDatabaseHelper=new MyDatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            track=bundle.getInt("id");
        }

        ArrayList<String> theList = new ArrayList<>();
        if(track==1) {
            data = myDatabaseHelper.getDatasofCarWash();
        }
        if(track==2) {
            data = myDatabaseHelper.getDatasofCarRent();
        }
        if(track==3) {
            data = myDatabaseHelper.getDatasofDriver();
        }
        if(track==4) {
            data = myDatabaseHelper.getDatasofShifting();
        }
        if(track==5) {
            data = myDatabaseHelper.getDatasofTutor();
        }



        if(data.getCount()==0)
        {
            Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while ((data.moveToNext()))
            {
                theList.add(data.getString(1));


            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(ShowList.this,android.R.layout.simple_list_item_1,theList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               if(track==1)
               {
                   Intent intent = new Intent(ShowList.this, ShowCarWashData.class);
                   intent.putExtra("id", position);
                   startActivity(intent);
               }

               if(track==2)
               {
                   Intent intent = new Intent(ShowList.this, ShowCarRentData.class);
                   intent.putExtra("id", position);
                   startActivity(intent);
               }

               if(track==3)
               {

                   Intent intent = new Intent(ShowList.this, ShowDriverData.class);
                   intent.putExtra("id", position);
                   startActivity(intent);
               }

               if(track==4)
               {

                   Intent intent = new Intent(ShowList.this, ShowShiftingData.class);
                   intent.putExtra("id", position);
                   startActivity(intent);
               }

               if(track==5)
               {

                   Intent intent = new Intent(ShowList.this, ShowTutorData.class);
                   intent.putExtra("id", position);
                   startActivity(intent);
               }



            }
        });
    }

}
