package com.example.daiya.sheba;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShiftingReg extends AppCompatActivity implements View.OnClickListener {

    EditText text1,text2,text3,text4,text5,text6;
    Button signupButton;
    private String name,email,password,phone;
    String T1,T2,T3,T4,T5,T6;

    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shifting_reg);

        text1=findViewById(R.id.vanId);
        text2=findViewById(R.id.pickupId);
        text3=findViewById(R.id.truckId);
        text4=findViewById(R.id.one23Id);
        text5=findViewById(R.id.four25id);
        text6=findViewById(R.id.six28Id);
        signupButton=findViewById(R.id.signUpButtonId);


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


        T1=text1.getText().toString();
        T2=text2.getText().toString();
        T3=text3.getText().toString();
        T4=text4.getText().toString();
        T5=text5.getText().toString();
        T6=text6.getText().toString();

        boolean ck=false;

        if(!T1.isEmpty() && !T2.isEmpty() && !T3.isEmpty() && !T4.isEmpty() && !T5.isEmpty() && !T6.isEmpty()) {
            ck = true;
        }

        if(v.getId()==R.id.signUpButtonId)
        {
            long rowId1,rowid2;
            if(ck)
            {
                rowid2= myDatabaseHelper.insertDataShifting(name,email,phone,T1,T2,T3,T4,T5,T6);
                if ( rowid2 !=-1) {
                    rowId1 = myDatabaseHelper.insertDataUser(name, email, password, phone);
                    if(rowId1!=-1)
                    {
                        Toast.makeText(this, "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ShiftingReg.this,Login.class);
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
