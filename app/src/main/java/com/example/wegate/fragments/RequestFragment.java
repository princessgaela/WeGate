package com.example.wegate.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wegate.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class RequestFragment extends Fragment {

    FloatingActionButton accept1,accept,decline,decline1;

    public RequestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request, container, false);
        accept = view.findViewById(R.id.accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept.setVisibility(View.INVISIBLE);
                decline.setVisibility(View.INVISIBLE);
            }
        });
        accept1 = view.findViewById(R.id.accept1);
        accept1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept1.setVisibility(View.INVISIBLE);
                decline1.setVisibility(View.INVISIBLE);
            }
        });
        decline = view.findViewById(R.id.decline);
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept.setVisibility(View.INVISIBLE);
                decline.setVisibility(View.INVISIBLE);
            }
        });
        decline1 = view.findViewById(R.id.decline1);
        decline1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept1.setVisibility(View.INVISIBLE);
                decline1.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }
}