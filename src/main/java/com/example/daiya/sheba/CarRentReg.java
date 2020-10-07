package com.example.daiya.sheba;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CarRentReg extends AppCompatActivity implements View.OnClickListener {

    private String name,email,password,phone,sedan,noah,hiace;
    private Button signupButton;
    private EditText sedantxt,noahtxt,hiacetxt;

    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_rent_reg);

        signupButton=findViewById(R.id.signUpButtonId);
        sedantxt=findViewById(R.id.sedanId);
        noahtxt=findViewById(R.id.noahId);
        hiacetxt=findViewById(R.id.hiaceId);

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

        signupButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        sedan=sedantxt.getText().toString();
        hiace=hiacetxt.getText().toString();
        noah=noahtxt.getText().toString();
        boolean ck=false;

        if(!sedan.isEmpty() && !noah.isEmpty() && !hiace.isEmpty()) {
            ck = true;
        }

        if(v.getId()==R.id.signUpButtonId)
        {
            long rowId1,rowid2;
            if(ck)
            {
                rowid2= myDatabaseHelper.insertDataCarRent(name,email,phone,sedan,noah,hiace);
                if ( rowid2 !=-1) {
                    rowId1 = myDatabaseHelper.insertDataUser(name, email, password, phone);
                    if(rowId1!=-1)
                    {
                        Toast.makeText(this, "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(CarRentReg.this,Login.class);
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
