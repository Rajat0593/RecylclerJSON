package com.f.rajat.demotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText editText_getId;
    private Button getDetails_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_getId = findViewById(R.id.editText_getId);
        getDetails_button = findViewById(R.id.getDetails_button);

        getDetails_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, EmployeeDetails.class);
                startActivity(i);
            }
        });
    }
}