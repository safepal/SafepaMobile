package com.lecodesoft.safepalmobile.ReferralPathway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lecodesoft.safepalmobile.MainActivity;
import com.lecodesoft.safepalmobile.R;

public class ReferralPathway extends AppCompatActivity {
    Button bgoHome, bContinue, bHealth, bLegal, bSupport, bPolice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral_pathway);
        bgoHome = (Button)findViewById(R.id.button_home);

        bgoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
