package com.adeeva.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class RegisterTwoActivity extends AppCompatActivity {

    LinearLayout btn_back;
    Button btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        btn_back = findViewById(R.id.btn_back);
        btn_continue = findViewById(R.id.btn_continue);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoprev = new Intent(RegisterTwoActivity.this, RegisterOneActivity.class);
                startActivity(backtoprev);
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotosuccess = new Intent(RegisterTwoActivity.this, SuccessRegisterActivity.class);
                startActivity(gotosuccess);
            }
        });
    }
}
