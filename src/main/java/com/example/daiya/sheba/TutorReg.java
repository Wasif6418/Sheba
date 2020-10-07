package com.example.daiya.sheba;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TutorReg extends AppCompatActivity implements View.OnClickListener {
    private Button signupButton;
    private EditText studytxt,experiencetxt,classtxt,feestxt,subjecttxt;
    private String name,email,password,phone;
    String study,experience,clasS,subject,fees;

    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_reg);

        studytxt=findViewById(R.id.studyid);
        experiencetxt=findViewById(R.id.experienceid);
        classtxt=findViewById(R.id.classId);
        feestxt=findViewById(R.id.feesId);
        subjecttxt=findViewById(R.id.subjectId);
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
        clasS=classtxt.getText().toString();
        subject=subjecttxt.getText().toString();
        experience=experiencetxt.getText().toString();
        fees=feestxt.getText().toString();
        study=studytxt.getText().toString();

        boolean ck=false;

        if(!study.isEmpty() && !fees.isEmpty() && !experience.isEmpty() && !clasS.isEmpty() && !subject.isEmpty()) {
            ck = true;
        }

        if(v.getId()==R.id.signUpButtonId)
        {
            long rowId1,rowid2;
            if(ck)
            {

                rowid2= myDatabaseHelper.insertDataTutor(name,email,phone,study,experience,clasS,subject,fees);
                if ( rowid2 !=-1) {
                    rowId1 = myDatabaseHelper.insertDataUser(name, email, password, phone);
                    if(rowId1!=-1)
                    {
                        Toast.makeText(this, "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(TutorReg.this,Login.class);
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
