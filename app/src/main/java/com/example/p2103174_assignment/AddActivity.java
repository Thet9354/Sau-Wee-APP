package com.example.p2103174_assignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddActivity extends AppCompatActivity {

    Addhelper addhelper;
    Addhelper db;

    public int PICK_IMAGE = 1;
    private Uri selectedImageUri = null;
    private Bitmap bitmap = null;

    ImageView userimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText datebtn = (EditText) findViewById(R.id.datebtn);
        EditText countrybtn = (EditText) findViewById(R.id.countrybtn);
        Button locationbtn = (Button) findViewById(R.id.locationbtn);
        Button picturebtn = (Button) findViewById(R.id.picturebtn);
        EditText latitideview = (EditText) findViewById(R.id.latitudeview);
        EditText longitudeview = (EditText) findViewById(R.id.longitudeview);
        Button savebtn = (Button) findViewById(R.id.savebtn);
        userimage = (ImageView) findViewById(R.id.userImage);

        addhelper = new Addhelper(this);
        db = new Addhelper(this);

        final String[] userlatitude = new String[1];
        final String[] userlongitude = new String[1];

        locationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 =new Intent(AddActivity.this, MapActivity2.class);
                startActivity(intent3);
            }
        });

        userlatitude[0] = getIntent().getStringExtra("latitude");
        userlongitude[0] = getIntent().getStringExtra("longitude");

        latitideview.setText(userlatitude[0]);
        longitudeview.setText(userlongitude[0]);

        picturebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose Picture"), PICK_IMAGE);
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String userdate =  datebtn.getText().toString();
               String usercountry =  countrybtn.getText().toString();
               String userlat = latitideview.getText().toString();
               String userlong = longitudeview.getText().toString();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                if (bitmap != null) {

                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                    byte[] bArray = bos.toByteArray();

                    if(bArray != null) {

                        if(!userdate.equals("")&& !usercountry.equals("") && !userlat.equals("") && !userlong.equals("")) {

//                            addhelper.insert(userdate, usercountry, userlat, userlong, bArray);
                            Intent intent = new Intent(AddActivity.this, HistoryActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getApplicationContext(), "Entry not saved, one or more inputs are empty", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Toast.makeText(getApplicationContext(), "Entry not saved, image too large", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Entry not saved, Please put in an image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                selectedImageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    bitmap = Bitmap.createScaledBitmap(bitmap,  600,392,true);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (selectedImageUri != null) {

                    userimage.setImageURI(selectedImageUri);
                    userimage.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(getApplicationContext(), "photo is too large", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}