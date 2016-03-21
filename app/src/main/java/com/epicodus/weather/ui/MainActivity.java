package com.epicodus.weather.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.weather.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.cityEditText) EditText cityEditText;
    @Bind(R.id.submitButton) Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
        intent.putExtra("location", cityEditText.getText().toString());
        startActivity(intent);
    }
}
