package com.w20.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class VoteActivity extends AppCompatActivity {
    private static final String TAG = "VoteActivity";

    Spinner spinner;
    EditText etId;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        spinner = findViewById(R.id.candidates);
        etId = findViewById(R.id.et_id);
        toggleButton = findViewById(R.id.toggle);
    }

    public void vote(View v) {
        List<String> ids = VoteModel.getInstance().getIds();
        if (!etId.getText().toString().isEmpty()) {
            if (toggleButton.isChecked()) {
                if (!ids.contains(etId.getText().toString())) {
                    Log.d(TAG, "vote: " + ids);
                    ids.add(etId.getText().toString());
                    Log.d(TAG, "vote: " + ids);
                    VoteModel.getInstance().changeState(spinner.getSelectedItem().toString());
                }
            }
        }
    }
}
