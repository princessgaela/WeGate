package com.example.wegate;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class SignupActivity extends AppCompatActivity {

    TextView login;
    EditText location,birthdate;
    Button button;
    ImageView imageView;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    final Calendar myCalendar= Calendar.getInstance();
    int AUTOCOMPLETE_REQUEST_CODE = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_signup);

        login = (TextView) findViewById(R.id.textView4);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        birthdate=(EditText) findViewById(R.id.editTextDate);
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String my_zodiac= getZodiac(dayOfMonth,month);getZodiac(dayOfMonth,month);
                updateLabel(my_zodiac);
            }
        };
        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SignupActivity.this,dateSetListener,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        imageView=(ImageView) findViewById(R.id.imageView2);
        if(checkAndRequestPermissions(SignupActivity.this)){
            chooseImage(SignupActivity.this);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage(SignupActivity.this);
            }
        });


        location = (EditText) findViewById(R.id.location);
        String apiKey = getString(R.string.api_key);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the fields to specify which types of place data to
                // return after the user has made a selection.
                List<Place.Field> field = Arrays.asList(Place.Field.ID, Place.Field.ADDRESS);

                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, field)
                        .build(SignupActivity.this);
                //start activity result
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);

            }
        });






    }



    private void chooseImage(Context context){
        final CharSequence[] optionsMenu = {"Take Photo", "Choose from Gallery", "Exit" }; // create a menuOption Array
        // create a dialog for showing the optionsMenu
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // set the items in builder
        builder.setItems(optionsMenu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(optionsMenu[i].equals("Take Photo")){
                    // Open the camera and get the photo
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                }
                else if(optionsMenu[i].equals("Choose from Gallery")){
                    // choose from  external storage
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                }
                else if (optionsMenu[i].equals("Exit")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }
    // function to check permission
    public static boolean checkAndRequestPermissions(final Activity context) {
        int WEExtortPermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WEExtortPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                    .add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
    // Handled permission Result
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(SignupActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                                    "WeGate Requires Access to Camera.", Toast.LENGTH_SHORT)
                            .show();
                } else if (ContextCompat.checkSelfPermission(SignupActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            "WeGate Requires Access to Your Storage.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    chooseImage(SignupActivity.this);
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            location.setText("Calasiao");
            if (resultCode == RESULT_OK) {
                //When success initialize place
                Place place = Autocomplete.getPlaceFromIntent(data);
                //set address on edittext
                location.setText(place.getAddress());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(selectedImage);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                imageView.setImageURI(selectedImage);
                                cursor.close();
                            }
                        }
                    }
                    break;
            }
        }
    }


    private void updateLabel(String zodiac){
        String myFormat="MMM/dd/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        birthdate.setText(dateFormat.format(myCalendar.getTime())+" "+zodiac);
    }
    private String getZodiac(int day,int month){
        String zodiacSign="";
        if(month==0){
            if (day<20){
                zodiacSign="Capricorn";
            }else {
                zodiacSign="Aquarius";
            }
        }else if(month==1){
            if (day<19){
                zodiacSign="Aquarius";
            }else {
                zodiacSign="Pisces";
            }
        }else if(month==2){
            if (day<21){
                zodiacSign="Pisces";
            }else {
                zodiacSign="Aries";
            }
        }else if(month==3){
            if (day<20){
                zodiacSign="Aries";
            }else {
                zodiacSign="Taurus";
            }
        }else if(month==4){
            if (day<21){
                zodiacSign="Taurus";
            }else {
                zodiacSign="Gemini";
            }
        }else if(month==5){
            if (day<22){
                zodiacSign="Gemini";
            }else {
                zodiacSign="Cancer";
            }
        }else if(month==6){
            if (day<23){
                zodiacSign="Cancer";
            }else {
                zodiacSign="Leo";
            }
        }else if(month==7){
            if (day<23){
                zodiacSign="Leo";
            }else {
                zodiacSign="Virgo";
            }
        }else if(month==8){
            if (day<23){
                zodiacSign="Virgo";
            }else {
                zodiacSign="Libra";
            }
        }else if(month==9){
            if (day<24){
                zodiacSign="Libra";
            }else {
                zodiacSign="Scorpio";
            }
        }else if(month==10){
            if (day<23){
                zodiacSign="Scorpio";
            }else {
                zodiacSign="Sagittarius";
            }
        }else if(month==11){
            if (day<22){
                zodiacSign="Sagittarius";
            }else {
                zodiacSign="Capricorn";
            }
        }
        return zodiacSign;
    }
}