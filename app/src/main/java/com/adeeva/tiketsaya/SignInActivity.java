package com.adeeva.tiketsaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    TextView btn_new_account;
    Button btn_sign_in;
    EditText xusername, xpassword;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btn_new_account = findViewById(R.id.btn_new_account);
        btn_sign_in = findViewById(R.id.btn_sign_in);

        xusername = findViewById(R.id.xusername);
        xpassword = findViewById(R.id.xpassword);

        btn_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoregisterone = new Intent(SignInActivity.this, RegisterOneActivity.class);
                startActivity(gotoregisterone);
            }
        });

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ubah state menjadi loading
                btn_sign_in.setEnabled(false);
                btn_sign_in.setText("Loading ...");

                final String username = xusername.getText().toString();
                final String password = xpassword.getText().toString();

                reference = FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(username);

                //reference = FirebaseDatabase.getInstance().getReference()
                //        .child("Users").child(password);

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            // ambil data password dari  firebase
                            String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();

                            // validasi password dengan pasword firebase
                            if (password.equals(passwordFromFirebase)) {

                                // simpan username (key) pada local
                                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(username_key, xusername.getText().toString());
                                editor.apply();

                                // berpindah activity
                                Intent gotohome = new Intent(SignInActivity.this, HomeActivity.class);
                                startActivity(gotohome);

                                Toast.makeText(getApplicationContext(), "Login Success.", Toast.LENGTH_SHORT).show();

                            } else {
                                btn_sign_in.setEnabled(true);
                                btn_sign_in.setText(getText(R.string.sign_in));
                                Toast.makeText(getApplicationContext(), "Username atau password tidak terdaftar.", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            btn_sign_in.setEnabled(true);
                            btn_sign_in.setText(getText(R.string.sign_in));
                            Toast.makeText(getApplicationContext(), "Username atau password tidak terdaftar.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Database Error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
