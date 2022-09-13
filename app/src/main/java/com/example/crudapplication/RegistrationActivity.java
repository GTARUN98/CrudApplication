package com.example.crudapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
public class RegistrationActivity extends AppCompatActivity {

    EditText name;
    EditText pass;
    EditText repass;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name=findViewById(R.id.name);
        pass=findViewById(R.id.pass);
        repass=findViewById(R.id.repass);
        btn=findViewById(R.id.Submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> vi =new HashMap<>();

                vi.put("name",name.getText().toString());
                vi.put("pass",pass.getText().toString());
                FirebaseFirestore.getInstance().collection("Vendor").add(vi).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {

                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(getApplicationContext(), "Details are Inserted", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });

    }
}