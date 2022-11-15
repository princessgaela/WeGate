package com.example.wegate.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wegate.R;

import java.util.Random;

public class GameFragmentBingo extends Fragment {
     int max = 3;
     int min = 1;
     int random = new Random().nextInt((max - min) + 1) + 1;
    ImageView imageView;
    Button submit;
    TextView wait;
    ProgressBar progressBar;

    public GameFragmentBingo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_game_bingo, container, false);
        imageView = view.findViewById(R.id.bingoimage);
        if (random==1){
            imageView.setImageResource(R.drawable.bingo1);
        }
        if (random==2){
            imageView.setImageResource(R.drawable.bingo2);
        }
        if (random==3){
            imageView.setImageResource(R.drawable.bingo3);
        }
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
                        GameResultBingoFragment gameResultBingoFragment = new GameResultBingoFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("random", random);
                        gameResultBingoFragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, gameResultBingoFragment).commit();
                    }
                },3000);
            }
        });
        return view;
    }
}