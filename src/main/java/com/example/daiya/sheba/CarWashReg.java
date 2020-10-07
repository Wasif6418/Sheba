package com.example.daiya.sheba;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CarWashReg extends AppCompatActivity implements View.OnClickListener {
    private String name,email,password,phone;
    private String homech,workshopch,interiorch,exteriorch,enginech;
    private EditText home,workshop,interior,exterior,engine;
    private Button signup;
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash_reg);

        home=findViewById(R.id.homeId);
        workshop=findViewById(R.id.workshopId);
        interior=findViewById(R.id.interiorId);
        exterior=findViewById(R.id.exteriorId);
        engine=findViewById(R.id.engineId);
        signup=findViewById(R.id.signUpButtonId);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            name=bundle.getString("name");
            email=bundle.getString("email");
            password=bundle.getString("pass");
            phone=bundle.getString("phone");
        }

        myDatabaseHelper =new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        homech=home.getText().toString();
        workshopch=workshop.getText().toString();
        interiorch=interior.getText().toString();
        exteriorch=exterior.getText().toString();
        enginech=engine.getText().toString();

        boolean ck=false;

        if(!homech.isEmpty() && !workshopch.isEmpty() && !interiorch.isEmpty() && !enginech.isEmpty() && !exteriorch.isEmpty()) {
            ck = true;
        }

        if(v.getId()==R.id.signUpButtonId)
        {
            long rowId1,rowid2;
            if(ck)
            {
                rowid2= myDatabaseHelper.insertDataCarWash(name,email,phone,homech,workshopch,interiorch,exteriorch,enginech);
                if ( rowid2 !=-1) {
                    rowId1 = myDatabaseHelper.insertDataUser(name, email, password, phone);
                    if(rowId1!=-1)
                    {
                        Toast.makeText(this, "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(CarWashReg.this,Login.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(this, "REGISTRATION FAILED", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Enter Data properly", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
