package com.epicodus.annatimofeeva.myfitnesshelperversion1.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.annatimofeeva.myfitnesshelperversion1.Constants;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity  implements OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;

    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mfindGymsButton;
    private Button maboutAppButon;
    private EditText mLocationEditText;
    private TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();




        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/Windsong.ttf");
        mAppNameTextView.setTypeface(ostrichFont);
        mLocationEditText = (EditText) findViewById(R.id.zipCode);
        mfindGymsButton = (Button) findViewById(R.id.findGymsButton);
        maboutAppButon = (Button) findViewById(R.id.aboutAppButon);

        mfindGymsButton.setOnClickListener(this);

        maboutAppButon.setOnClickListener(this);


    } //on Create

    @Override

    public void onClick(View view) {

        //Toast.makeText(MainActivity.this, "FirstActivity", Toast.LENGTH_SHORT).show();
        if (view == mfindGymsButton) {

            String location = mLocationEditText.getText().toString();
//            if(!(location).equals("")) {
//                addToSharedPreferences(location);
//            }
            saveLocationToFirebase(location);

            Intent intent = new Intent(MainActivity.this, GymsActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
        if (view == maboutAppButon) {

            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            intent.putExtra("about", "The information about application will be provide later");
            startActivity(intent);
        }
    }

    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.setValue(location);
    }

//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }

}
//@Override
//    public void onClick(View view) {
//
//        //Toast.makeText(MainActivity.this, "SecondActivity", Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
//        intent.putExtra("about", "The information about application will be provide later");
//        startActivity(intent);
//
//    }



