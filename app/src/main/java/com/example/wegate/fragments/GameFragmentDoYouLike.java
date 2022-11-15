package com.example.wegate.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wegate.R;

public class GameFragmentDoYouLike extends Fragment {
    Button submit;
    TextView wait;
    ProgressBar progressBar;

    public GameFragmentDoYouLike() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_do_you_like, container, false);
        submit = view.findViewById(R.id.submit);
        wait = view.findViewById(R.id.waittext);
        progressBar = view.findViewById(R.id.progressBar);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wait.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                submit.setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GameResultFragment gameResultFragment = new GameResultFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, gameResultFragment).commit();
                    }
                },3000);
            }
        });
        return view;
    }
}