package com.example.daiya.sheba;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistrationService extends AppCompatActivity implements View.OnClickListener {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");

    private EditText userName, pass1, pass2, phone, email;
    private Button next;
    private RadioButton radioButton, carWash, carRent, shifting, tutor, driver;
    private RadioGroup radioGroup;
    private String value;
    private Cursor data;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_service);

        userName = findViewById(R.id.userNameId);
        pass1 = findViewById(R.id.passwordId);
        pass2 = findViewById(R.id.passwordId2);
        email = findViewById(R.id.emailId);
        phone = findViewById(R.id.phoneId);
        radioGroup = findViewById(R.id.radioGroupId);

        next = findViewById(R.id.nextButtonId);

        myDatabaseHelper = new MyDatabaseHelper(this);


        next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String Name = userName.getText().toString();
        String Email = email.getText().toString();
        String Pass1 = pass1.getText().toString();
        String Pass2 = pass2.getText().toString();
        String Phone = phone.getText().toString();
        String emailchk;
        int matched = 0;

        try {
            int id = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(id);
            value = radioButton.getText().toString();
        } catch (Exception e) {
            Toast.makeText(this, "Select a service!", Toast.LENGTH_SHORT).show();
        }


        boolean check = true;


        if (v.getId() == R.id.nextButtonId) {

            if(Name.isEmpty()){
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
            if (check) {

                data = myDatabaseHelper.getDatasofUser();
                if (data.getCount() == 0) {
                    Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
                } else {
                    while ((data.moveToNext())) {
                        emailchk = data.getString(1);
                        if (Email.equalsIgnoreCase(emailchk)) {
                            matched = 1;
                            break;
                        }
                    }
                }

                if (matched == 0) {
                    try {

                        if (value.equalsIgnoreCase("Car Wash")) {
                            Intent intent = new Intent(RegistrationService.this, CarWashReg.class);
                            intent.putExtra("name", Name);
                            intent.putExtra("email", Email);
                            intent.putExtra("pass", Pass1);
                            intent.putExtra("phone", Phone);
                            startActivity(intent);
                        }
                        if (value.equalsIgnoreCase("Car Rental")) {
                            Intent intent = new Intent(RegistrationService.this, CarRentReg.class);
                            intent.putExtra("name", Name);
                            intent.putExtra("email", Email);
                            intent.putExtra("pass", Pass1);
                            intent.putExtra("phone", Phone);
                            startActivity(intent);
                        }
                        if (value.equalsIgnoreCase("Tutor")) {
                            Intent intent = new Intent(RegistrationService.this, TutorReg.class);
                            intent.putExtra("name", Name);
                            intent.putExtra("email", Email);
                            intent.putExtra("pass", Pass1);
                            intent.putExtra("phone", Phone);
                            startActivity(intent);
                        }
                        if (value.equalsIgnoreCase("Shifting")) {
                            Intent intent = new Intent(RegistrationService.this, ShiftingReg.class);
                            intent.putExtra("name", Name);
                            intent.putExtra("email", Email);
                            intent.putExtra("pass", Pass1);
                            intent.putExtra("phone", Phone);
                            startActivity(intent);
                        }
                        if (value.equalsIgnoreCase("On Demand Driver")) {
                            Intent intent = new Intent(RegistrationService.this, DriverReg.class);
                            intent.putExtra("name", Name);
                            intent.putExtra("email", Email);
                            intent.putExtra("pass", Pass1);
                            intent.putExtra("phone", Phone);
                            startActivity(intent);
                        }


                    } catch (Exception e) {
                        Toast.makeText(this, "Select a service!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "ALREADY REGISTERED EMAIL!", Toast.LENGTH_SHORT).show();
                }


            }
        }

    }
}
