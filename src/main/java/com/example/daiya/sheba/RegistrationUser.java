package com.example.daiya.sheba;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;
import android.util.Patterns;

public class RegistrationUser extends AppCompatActivity implements View.OnClickListener {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 4 characters
                    "$");


    private EditText userName,pass1,pass2,phone,email;
    private Button signUp;
    private Cursor data;

    MyDatabaseHelper myDatabaseHelper;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_user);

    userName=findViewById(R.id.userNameId);
    pass1=findViewById(R.id.passwordId);
    pass2=findViewById(R.id.passwordId2);
    phone=findViewById(R.id.phoneId);
    email=findViewById(R.id.emailId);
    signUp=findViewById(R.id.signUpButtonId);

    myDatabaseHelper =new MyDatabaseHelper(this);
    SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
    signUp.setOnClickListener(this);

}

    @Override
    public void onClick(View v) {
        String name = userName.getText().toString();
        String Email = email.getText().toString();
        String Pass1 = pass1.getText().toString();
        String Pass2 = pass2.getText().toString();
        String Phone = phone.getText().toString();
        String emailchk;
        int matched=0;
        boolean check = true;

        if(v.getId()==R.id.signUpButtonId)
        {

            data = myDatabaseHelper.getDatasofUser();
            if(data.getCount()==0)
            {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
            }
            else
            {
                while ((data.moveToNext()))
                {
                    emailchk=data.getString(1);
                    if(Email.equalsIgnoreCase(emailchk))
                    {
                        matched=1;
                        break;
                    }
                }
            }





            long rowId;

            if(name.isEmpty()){
                Toast.makeText(this, "Invalid user name!", Toast.LENGTH_SHORT).show();
                check=false;
            }else
            if (!(Patterns.EMAIL_ADDRESS.matcher(Email).matches())) {
                Toast.makeText(this, "Invalid Email!", Toast.LENGTH_SHORT).show();
                check = false;
            } else if (!(PASSWORD_PATTERN.matcher(Pass1).matches())) {
                Toast.makeText(this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                check = false;
            } else if(Pass1.equals(Pass2)==false) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                check = false;

            }else if(Phone.length()!=11)
            {
                Toast.makeText(this, "Invalid mobile number!", Toast.LENGTH_SHORT).show();
                check = false;
            }

            if(check==true) {
                if(matched==0)
                {
                    rowId = myDatabaseHelper.insertDataUser(name, Email, Pass1, Phone);

                    if (rowId == -1) {
                        Toast.makeText(this, "REGISTRATION FAILED", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegistrationUser.this,Login.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(this, "ALREADY REGISTERED EMAIL!", Toast.LENGTH_SHORT).show();
                }


            }

        }

    }
}