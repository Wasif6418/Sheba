package com.example.daiya.sheba;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowShiftingData extends AppCompatActivity implements View.OnClickListener {
    private int id;
    private String one,four,six,van,pickup,truck,name,email,phone;
    private TextView One,Four,Six,Van,Pickup,Truck,Name1,Phone1,Email1;
    private Button call;
    private static final int REQUEST_CALL =1;
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shifting_data);


        One=findViewById(R.id.oneId);
        Four=findViewById(R.id.fourId);
        Six=findViewById(R.id.sixId);
        Van=findViewById(R.id.van1Id);
        Pickup=findViewById(R.id.pickup1Id);
        Truck=findViewById(R.id.truck1Id);
        call=findViewById(R.id.callButtonId);
        Name1=findViewById(R.id.name1Id);
        Email1=findViewById(R.id.email1Id);
        Phone1=findViewById(R.id.phone1Id);



        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            id=bundle.getInt("id");
        }
        id=id+1;
        myDatabaseHelper =new MyDatabaseHelper(this);
        Cursor data1=myDatabaseHelper.getDatasofShifting();
        int ID;
        String IDs;
        if(data1.getCount()==0)
        {
            Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (data1.moveToNext())
            {
                IDs=data1.getString(0);
                ID = Integer.parseInt(IDs);
                if(ID==id)
                    break;
            }

        }
        name=data1.getString(1);
        email=data1.getString(2);
        phone=data1.getString(9);
        van=data1.getString(3);
        pickup=data1.getString(8);
        truck=data1.getString(5);
        one=data1.getString(4);
        four=data1.getString(6);
        six=data1.getString(7);


        Name1.setText(name);
        Email1.setText(email);
        Phone1.setText(phone);
        One.setText(one+" BDT");
        Four.setText(four+" BDT");
        Six.setText(six+" BDT");
        Van.setText(van+" BDT");
        Pickup.setText(pickup+" BDT");
        Truck.setText(truck+" BDT");

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
        if(ContextCompat.checkSelfPermission(ShowShiftingData.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
        {
            String dial = "tel:"+number;
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
        }
        else
        {
            ActivityCompat.requestPermissions(ShowShiftingData.this,new String[] { Manifest.permission.CALL_PHONE},REQUEST_CALL);

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
