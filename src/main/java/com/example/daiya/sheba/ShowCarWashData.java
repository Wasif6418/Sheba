package com.example.daiya.sheba;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowCarWashData extends AppCompatActivity implements View.OnClickListener {
    private int id;
    private String homech,workshopch,interiorch,exteriorch,enginech,name,email,phone;
    private TextView home,workshop,interior,exterior,engine,Name,Phone,Email;
    private Button call;
    private static final int REQUEST_CALL =1;
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car_wash_data);

        home=findViewById(R.id.homeId);
        workshop=findViewById(R.id.workshopId);
        interior=findViewById(R.id.interiorId);
        exterior=findViewById(R.id.exteriorId);
        engine=findViewById(R.id.engineId);
        call=findViewById(R.id.callButtonId);
        Name=findViewById(R.id.nameId);
        Email=findViewById(R.id.emailId);
        Phone=findViewById(R.id.phoneId);



        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            id=bundle.getInt("id");
        }
        id=id+1;
        myDatabaseHelper =new MyDatabaseHelper(this);
        Cursor data=myDatabaseHelper.getDatasofCarWash();
        int ID;
        String IDs;
        if(data.getCount()==0)
        {
            Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (data.moveToNext())
            {
                IDs=data.getString(0);
                ID = Integer.parseInt(IDs);
                if(ID==id)
                    break;
            }

        }
        name=data.getString(1);
        email=data.getString(2);
        phone=data.getString(8);
        homech=data.getString(7);
        workshopch=data.getString(3);
        interiorch=data.getString(4);
        enginech=data.getString(6);
        exteriorch=data.getString(5);

        Name.setText(name);
        Email.setText(email);
        Phone.setText(phone);
        home.setText(homech+" BDT");
        workshop.setText(workshopch+" BDT");
        engine.setText(enginech+" BDT");
        interior.setText(interiorch+" BDT");
        exterior.setText(exteriorch+" BDT");



        call.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.callButtonId)
        {
            makePhoneCall();

        }
    }

    private void makePhoneCall(){
        String number = phone;
        if(ContextCompat.checkSelfPermission(ShowCarWashData.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
        {
            String dial = "tel:"+number;
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
        }
        else
        {
            ActivityCompat.requestPermissions(ShowCarWashData.this,new String[] { Manifest.permission.CALL_PHONE},REQUEST_CALL);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode== REQUEST_CALL)
        {
            if(grantResults.length >0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else {
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
