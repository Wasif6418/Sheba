package com.example.daiya.sheba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button takeService,beService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        takeService=findViewById(R.id.takeServiceId);
        beService=findViewById(R.id.beServiceId);

        takeService.setOnClickListener(this);
        beService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.takeServiceId)
        {
            Intent intent=new Intent(SignUp.this,RegistrationUser.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.beServiceId)
        {
            Intent intent=new Intent(SignUp.this,RegistrationService.class);
            startActivity(intent);
        }
    }
}
