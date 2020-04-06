package com.adeeva.tiketsaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOneActivity extends AppCompatActivity {

    LinearLayout btn_back;
    Button btn_continue;
    EditText username, password, email_address;
    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);

        btn_back = findViewById(R.id.btn_back);
        btn_continue = findViewById(R.id.btn_continue);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email_address = findViewById(R.id.email_address);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtosignin = new Intent(RegisterOneActivity.this, SignInActivity.class);
                startActivity(backtosignin);
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ubah state menjadi loading
                btn_continue.setEnabled(false);
                btn_continue.setText("Loading ...");

                if (username.getText().toString().isEmpty()) {
                    btn_continue.setEnabled(true);
                    btn_continue.setText(R.string.continue_label);
                    Toast.makeText(getApplicationContext(), "Username kosong!", Toast.LENGTH_SHORT).show();

                } else {

                    if (password.getText().toString().isEmpty()) {
                        btn_continue.setEnabled(true);
                        btn_continue.setText(R.string.continue_label);
                        Toast.makeText(getApplicationContext(), "Password kosong!", Toast.LENGTH_SHORT).show();

                    } else if (email_address.getText().toString().isEmpty()) {
                        btn_continue.setEnabled(true);
                        btn_continue.setText(R.string.continue_label);
                        Toast.makeText(getApplicationContext(), "Email kosong!", Toast.LENGTH_SHORT).show();

                    } else {
                        // menyimpan data kepada local storage
                        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(username_key, username.getText().toString());
                        editor.apply();

                        //simpan kepada database
                        reference = FirebaseDatabase.getInstance().getReference()
                                .child("Users").child(username.getText().toString());
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                                dataSnapshot.getRef().child("password").setValue(password.getText().toString());
                                dataSnapshot.getRef().child("email_address").setValue(email_address.getText().toString());
                                dataSnapshot.getRef().child("user_balance").setValue(800);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        Intent gotonextregister = new Intent(RegisterOneActivity.this, RegisterTwoActivity.class);
                        startActivity(gotonextregister);

                    }

                }

            }
        });
    }
}
