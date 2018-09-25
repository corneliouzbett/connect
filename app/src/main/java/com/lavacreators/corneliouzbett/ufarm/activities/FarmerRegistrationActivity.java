package com.lavacreators.corneliouzbett.ufarm.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lavacreators.corneliouzbett.ufarm.R;
import com.lavacreators.corneliouzbett.ufarm.auth.LoginActivity;
import com.lavacreators.corneliouzbett.ufarm.model.Farmer;
import com.lavacreators.corneliouzbett.ufarm.model.User;

public class FarmerRegistrationActivity extends AppCompatActivity {
    private TextInputEditText firstnameEditText;
    private TextInputEditText lastnameEditText;
    private TextInputEditText phoneEditText;
    private TextInputEditText locationEditText;
    private TextInputEditText typeoffarmEditText;
    private TextInputEditText descriptionEditText;
    private TextInputEditText nearestTownEditText;
    private FloatingActionButton nextActionButton, backActionButton, doneActionButton;
    private ViewFlipper farmerFlipper;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(FarmerRegistrationActivity.this, LoginActivity.class));
            finish();
        }
        setContentView(R.layout.activity_farmer_registration);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Farmer Registration");
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Ufarm").child("users").child(firebaseAuth.getUid());

        firstnameEditText = findViewById(R.id.firstname_edt);
        lastnameEditText = findViewById(R.id.lastname_edt);
        phoneEditText = findViewById(R.id.phone_edt);
        locationEditText = findViewById(R.id.loaction_edt);
        typeoffarmEditText = findViewById(R.id.typeoffarming_edt);
        descriptionEditText = findViewById(R.id.desc_edt);
        nearestTownEditText = findViewById(R.id.town_edt);
        farmerFlipper = findViewById(R.id.farmer_viewflipper);

        nextActionButton = findViewById(R.id.next_fab);
        doneActionButton = findViewById(R.id.done_fab);
        backActionButton = findViewById(R.id.back_fab);

        nextActionButton.setOnClickListener( next ->farmerFlipper.showNext());
        backActionButton.setOnClickListener( next ->farmerFlipper.showPrevious());
        doneActionButton.setOnClickListener( next ->{
           valid_form();
           Farmer farmer = new Farmer();
           farmer.setFirstname(firstnameEditText.getText().toString().trim());
           farmer.setLastname(lastnameEditText.getText().toString().trim());
           farmer.setPhone(phoneEditText.getText().toString().trim());
           farmer.setLocation(locationEditText.getText().toString().trim());
           farmer.setTypeoffarming(typeoffarmEditText.getText().toString().trim());
           farmer.setNearestTown(nearestTownEditText.getText().toString().trim());
           farmer.setDescription(descriptionEditText.getText().toString().trim());
           farmer.setEmail(firebaseAuth.getCurrentUser().getEmail());
           registerFarmer(farmer);

        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("AGRI-CONNECT APP");
        progressDialog.setMessage("Please wait saving your data ...");
        progressDialog.setCancelable(false);

    }

    private void valid_form() {
        if (TextUtils.isEmpty(firstnameEditText.getText().toString())) {
            Toast.makeText(FarmerRegistrationActivity.this,
                    "Enter First name!", Toast.LENGTH_SHORT).show();
            firstnameEditText.setError("Enter your firstname!");
            return;
        }
        if (TextUtils.isEmpty(lastnameEditText.getText().toString())) {
            lastnameEditText.setError("Enter your last name ..!");
            return;
        }
        if (TextUtils.isEmpty(phoneEditText.getText().toString())) {
            Toast.makeText(FarmerRegistrationActivity.this,
                    "Enter Phone Number!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(locationEditText.getText().toString())) {
            Toast.makeText(FarmerRegistrationActivity.this,
                    "Enter Location!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(descriptionEditText.getText().toString())) {
            lastnameEditText.setError("Enter your description ..!");
            return;
        }
        if (TextUtils.isEmpty(nearestTownEditText.getText().toString())) {
            Toast.makeText(FarmerRegistrationActivity.this,
                    "Enter nearest town!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(typeoffarmEditText.getText().toString())) {
            Toast.makeText(FarmerRegistrationActivity.this,
                    "fill in here!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    private void registerFarmer(Farmer farmer) {

        progressDialog.show();

        databaseReference.setValue(farmer, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null){
                    progressDialog.dismiss();
                    Toast.makeText(FarmerRegistrationActivity.this,"Data saved succesfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(FarmerRegistrationActivity.this, MainActivity.class));
                    finish();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(FarmerRegistrationActivity.this,"Data not Saved, Try again"+databaseError,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
