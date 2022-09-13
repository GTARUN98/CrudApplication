package com.example.crudapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class show extends AppCompatActivity {
    ListView list;
    Button btn;
    List<String> namelist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        list = findViewById(R.id.list);
        btn=findViewById(R.id.show_data_realtime);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseFirestore.getInstance().collection("Vendor").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        namelist.clear();
                        for(DocumentSnapshot s:value){
                            namelist.add(s.getString("name")+" : "+s.getString("pass"));

                        }
                        Toast.makeText(getApplicationContext(), "Real Time Data is being Showed", Toast.LENGTH_SHORT).show();
                        ArrayAdapter adapter = new ArrayAdapter<String>(show.this, android.R.layout.simple_selectable_list_item,namelist);
                        adapter.notifyDataSetChanged();
                        list.setAdapter(adapter);
                    }
                });
            }
        });

    }
}