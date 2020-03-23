package com.adeeva.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.github.florent37.shapeofview.shapes.CircleView;

public class HomeActivity extends AppCompatActivity {

    LinearLayout btn_ticket_pisa;
    LinearLayout btn_ticket_torri;
    LinearLayout btn_ticket_pagoda;
    LinearLayout btn_ticket_candi;
    LinearLayout btn_ticket_sphinx;
    LinearLayout btn_ticket_monas;
    CircleView btn_to_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_ticket_pisa = findViewById(R.id.btn_ticket_pisa);
        btn_to_profile = findViewById(R.id.btn_to_profile);

        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoprofile = new Intent(HomeActivity.this, MyProfileActivity.class);
                startActivity(gotoprofile);
            }
        });

        btn_ticket_pisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotopisaticket = new Intent(HomeActivity.this, TicketDetailActivity.class);
                startActivity(gotopisaticket);
            }
        });

    }
}
