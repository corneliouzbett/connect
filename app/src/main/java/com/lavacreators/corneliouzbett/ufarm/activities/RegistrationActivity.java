package com.lavacreators.corneliouzbett.ufarm.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lavacreators.corneliouzbett.ufarm.R;
import com.lavacreators.corneliouzbett.ufarm.model.User;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmpasswordEditText;
    private Button submitButton;
    private FirebaseAuth auth;
    private Spinner accounttypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Account Registration");
        }
        auth = FirebaseAuth.getInstance();

        accounttypeSpinner = findViewById(R.id.account_spinner);
        List<String> account = new ArrayList<String>();
        account.add("Farmer");
        account.add("Extension Officer");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,account
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accounttypeSpinner.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("AGRI-CONNECT APP");
        progressDialog.setMessage("Please wait creating account ...");
        progressDialog.setCancelable(false);

        emailEditText = findViewById(R.id.email_edt);
        passwordEditText = findViewById(R.id.password_edt);
        confirmpasswordEditText= findViewById(R.id.confirm_password_edt);
        submitButton= findViewById(R.id.btn_submit);

        submitButton.setOnClickListener(submit ->{

            if (passwordEditText.getText().toString().trim().equals(confirmpasswordEditText.getText().toString().trim())){
                createUserWithEmail(
                        emailEditText.getText().toString().trim(),
                        passwordEditText.getText().toString().trim()
                );
            } else {
                Toast.makeText(RegistrationActivity.this,"Password does not match",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void createUserWithEmail(String email,String password){

        progressDialog.show();
        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegistrationActivity.this, "Registration completed :" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            if (accounttypeSpinner.getSelectedItem().toString().trim().equals("Farmer")){
                                startActivity(new Intent(RegistrationActivity.this,FarmerRegistrationActivity.class));
                                finish();
                            } else {
                                startActivity(new Intent(RegistrationActivity.this,SpecialistRegistrationActivity.class));
                                finish();
                            }
                        }
                    }
                });
    }

}
