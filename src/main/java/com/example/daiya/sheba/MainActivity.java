package com.example.daiya.sheba;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView carWashView,carRentView,tutorView,shiftingView,driverView;
    private String email,name;
    private TextView Name,Email;
    private Button signoutButton;
    AlertDialog.Builder alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carWashView=findViewById(R.id.carWashId);
        carRentView=findViewById(R.id.carRentalId);
        tutorView=findViewById(R.id.tutorId);
        shiftingView=findViewById(R.id.shiftingId);
        driverView=findViewById(R.id.driverId);
        signoutButton=findViewById(R.id.signoutId);

        Name=findViewById(R.id.name2Id);
        Email=findViewById(R.id.email2Id);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {

            email=bundle.getString("email");
            name=bundle.getString("name");

        }

        Name.setText("NAME: "+name);
        Email.setText("EMAIL: "+email);


        carWashView.setOnClickListener(this);
        carRentView.setOnClickListener(this);
        tutorView.setOnClickListener(this);
        driverView.setOnClickListener(this);
        shiftingView.setOnClickListener(this);
        signoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.carWashId)
        {
            Intent intent=new Intent(MainActivity.this,ShowList.class);
            intent.putExtra("id", 1);
            startActivity(intent);
        }
        if(v.getId()==R.id.carRentalId)
        {
            Intent intent=new Intent(MainActivity.this,ShowList.class);
            intent.putExtra("id", 2);
            startActivity(intent);
        }
        if(v.getId()==R.id.tutorId)
        {
            Intent intent=new Intent(MainActivity.this,ShowList.class);
            intent.putExtra("id", 5);
            startActivity(intent);
        }
        if(v.getId()==R.id.driverId)
        {
            Intent intent=new Intent(MainActivity.this,ShowList.class);
            intent.putExtra("id", 3);
            startActivity(intent);
        }
        if(v.getId()==R.id.shiftingId)
        {
            Intent intent=new Intent(MainActivity.this,ShowList.class);
            intent.putExtra("id", 4);
            startActivity(intent);
        }
        if(v.getId()==R.id.signoutId)
        {



            alert =new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("ALERT");
            alert.setMessage("Are you sure you want to sign out?");
            alert.setIcon(R.drawable.alert);

            alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }
            });
            alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alert.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog=alert.create();
            alertDialog.show();
        }


    }

    public void onBackPressed() {
        alert =new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("ALERT");
        alert.setMessage("Do you want to exit?");
        alert.setIcon(R.drawable.alert);

        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);


            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog=alert.create();
        alertDialog.show();
    }
}
