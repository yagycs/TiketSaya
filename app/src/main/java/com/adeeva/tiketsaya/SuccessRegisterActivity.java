package com.adeeva.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessRegisterActivity extends AppCompatActivity {

    Button btn_explore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        btn_explore = findViewById(R.id.btn_explore);

        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotohome = new Intent(SuccessRegisterActivity.this, HomeActivity.class);
                startActivity(gotohome);
            }
        });
    }
}
