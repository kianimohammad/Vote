package com.w20.vote;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class VoteModel {

    public interface OnVoteStateListener {
        void stateChanged();
    }

    private static VoteModel mInstance;
    private OnVoteStateListener mListener;
    private int[] votes = new int[3];
    private List<String> ids = new ArrayList<>();

    public VoteModel() {}

    public static VoteModel getInstance() {
        if (mInstance == null)
            mInstance = new VoteModel();
        return mInstance;
    }

    public void setListener(OnVoteStateListener listener) {
        mListener = listener;
    }

    public void changeState(String candidate) {
        if(mListener != null) {
            switch (candidate) {
                case "candidate 1":
                    votes[0]++;
                    break;
                case "candidate 2":
                    votes[1]++;
                    break;
                case "candidate 3":
                    votes[2]++;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + candidate);
            }
            notifyStateChange();
        }
    }


    public int[] getVotes() {
        return votes;
    }

    private void notifyStateChange() {
        mListener.stateChanged();
    }

    public List<String> getIds() {
        return ids;
    }
}
