package com.example.daiya.sheba;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DriverReg extends AppCompatActivity implements View.OnClickListener {
    private String name,email,password,phone,category,value;
    private  int id;
    private String before,after;
    private RadioGroup radioGroup1;
    private RadioButton radioButton1;
    private EditText befor10,after10;
    private Button signupButton;
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_reg);

        radioGroup1=findViewById(R.id.radioGroup1Id);
        befor10=findViewById(R.id.before10Id);
        after10=findViewById(R.id.after10Id);
        signupButton=findViewById(R.id.signUpButtonId);

        //id=radioGroup1.getCheckedRadioButtonId();
       // radioButton1=findViewById(id);
        //category=radioButton1.getText().toString();
       // value=radioButton1.getText().toString();
       // category="Standard";



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

        before=befor10.getText().toString();
        after=after10.getText().toString();
        category="Premium";

        boolean ck=false;

        if(!before.isEmpty() && !after.isEmpty()) {
            ck = true;
        }

        if(v.getId()==R.id.signUpButtonId)
        {
            long rowId1,rowid2;
            if(ck)
            {
                rowid2= myDatabaseHelper.insertDataDriver(name,email,phone,before,after,category);
                if ( rowid2 !=-1) {
                    rowId1 = myDatabaseHelper.insertDataUser(name, email, password, phone);
                    if(rowId1!=-1)
                    {
                        Toast.makeText(this, "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(DriverReg.this,Login.class);
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
