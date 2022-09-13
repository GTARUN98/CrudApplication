package com.example.crudapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

public class Delete extends AppCompatActivity {
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        btn= findViewById(R.id.delete_data_realtime);
        //This is for deletion through id
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseFirestore.getInstance().collection("Vendor").document(
//                        "personal details").delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(getApplicationContext(), "Deletion of data with id \n" +
//
//                                "personal details is successfull", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
        //This is for batch deletion
       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore.getInstance().collection("Vendor").whereEqualTo("pass","123").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        WriteBatch b=FirebaseFirestore.getInstance().batch();
                        List<DocumentSnapshot> s =  queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot snapshot:s){
                            b.delete(snapshot.getReference());
                        }
                        b.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Successfully deleted with password as 123", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
       });
    }}
