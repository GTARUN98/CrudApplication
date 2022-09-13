package com.example.crudapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView reg;
    Button btn;
    Button btn_show;
    Button btn_delete;
    Button btn_upld_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg = findViewById(R.id.toRegister);
        btn_show = findViewById(R.id.show);
        btn_delete = findViewById(R.id.toDelete);
        btn_upld_img = findViewById(R.id.upload_img);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),RegistrationActivity.class);
                Toast.makeText(getApplicationContext(), "To Registration Activity", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),show.class);
                Toast.makeText(getApplicationContext(), "To show Activity", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),Delete.class);
                Toast.makeText(getApplicationContext(), "To Delete Activity", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        btn_upld_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),Upload_img.class);
                Toast.makeText(getApplicationContext(), "To Upload Image Activity", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }
}