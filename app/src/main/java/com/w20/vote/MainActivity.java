package com.w20.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements VoteModel.OnVoteStateListener {

    private static final String TAG = "MainActivity";

    EditText etCan1, etCan2, etCan3, winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VoteModel.getInstance().setListener(this);

        etCan1 = findViewById(R.id.et_can1);
        etCan2 = findViewById(R.id.et_can2);
        etCan3 = findViewById(R.id.et_can3);
        winner = findViewById(R.id.et_winner);
    }

    public void takeVote(View v) {
//        startActivityForResult(new Intent(this, VoteActivity.class), 1);
        startActivity(new Intent(this, VoteActivity.class));
    }

    @Override
    public void stateChanged() {

        int[] votes = VoteModel.getInstance().getVotes();
        Log.i(TAG, "stateChanged: " + votes);

        etCan1.setText(String.valueOf(votes[0]));
        etCan2.setText(String.valueOf(votes[1]));
        etCan3.setText(String.valueOf(votes[2]));

        displayWinner(votes);
    }

    private void displayWinner(int[] votes) {
        int index = getIndexOfLargest(votes);
        switch (index) {
            case 0:
                winner.setText("Candidate 1");
                break;
            case 1:
                winner.setText("Candidate 2");
                break;
            case 2:
                winner.setText("Candidate 3");
                break;

            default:
                winner.setText("N/A");
                break;
        }
    }

    public int getIndexOfLargest(int[] array) {
        if (array == null || array.length == 0)
            return -1; // null or empty

        int maxAt = 0;

        for (int i = 0; i < array.length; i++) {
            maxAt = array[i] > array[maxAt] ? i : maxAt;
        }
        return maxAt; // position of the first largest found
    }
}
