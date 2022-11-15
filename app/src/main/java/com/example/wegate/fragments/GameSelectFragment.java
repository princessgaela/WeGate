package com.example.wegate.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wegate.R;

public class GameSelectFragment extends Fragment {

    Button doyoulike,thisorthat,bingo;

    public GameSelectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_select, container, false);
        bingo = view.findViewById(R.id.bingo);
        bingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameFragmentBingo gameFragmentBingo = new GameFragmentBingo();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, gameFragmentBingo).commit();
            }
        });
        doyoulike = view.findViewById(R.id.submit);
        doyoulike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameFragmentDoYouLike gameFragmentDoYouLike = new GameFragmentDoYouLike();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, gameFragmentDoYouLike).commit();
            }
        });
        thisorthat = view.findViewById(R.id.thisorthat);
        thisorthat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameFragmentThisOrThat gameFragmentThisOrThat = new GameFragmentThisOrThat();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, gameFragmentThisOrThat).commit();
            }
        });
        return view;
    }
}