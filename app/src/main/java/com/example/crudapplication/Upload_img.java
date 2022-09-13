package com.example.crudapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageRegistrar;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class Upload_img extends AppCompatActivity {
Button save;
Button upload;
ImageView image;

private Uri u;
FirebaseStorage storage;
StorageReference storageReference;
private  final int IMG_REQUEST_ID=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_img);
        save = findViewById(R.id.save);
        upload = findViewById(R.id.upload);
        image = findViewById(R.id.image);
        save.setEnabled(false);
        storage = FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i,"select picture"),IMG_REQUEST_ID);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog p = new ProgressDialog(Upload_img.this);
                p.setTitle("Uploading....");
                p.show();
            if(u!=null){
                StorageReference reference=storageReference.child("picture/"+ UUID.randomUUID().toString());
                reference.putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        p.dismiss();
                        Toast.makeText(getApplicationContext(), "saved the image into the database", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to save the image into the database", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            upload.setEnabled(true);
            save.setEnabled(false);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST_ID && resultCode==RESULT_OK && data !=null && data.getData() !=null){
            u=data.getData();

                try {
                    Bitmap bit = MediaStore.Images.Media.getBitmap(getContentResolver(),u);
                image.setImageBitmap(bit);
                upload.setEnabled(false);
                save.setEnabled(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }
}