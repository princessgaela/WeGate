package com.example.wegate.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wegate.R;
import com.example.wegate.SplashScreen;
import com.example.wegate.WelcomeActivity;

public class GameFragment extends Fragment {

    private static int timer = 3000;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GameSelectFragment gameSelectFragment = new GameSelectFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, gameSelectFragment).commit();
            }
        },timer);
        return view;
    }
}