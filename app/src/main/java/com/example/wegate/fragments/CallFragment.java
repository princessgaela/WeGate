package com.example.wegate.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wegate.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class CallFragment extends Fragment {
    FloatingActionButton endcall;
    Boolean premium;

    public CallFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        premium = getArguments().getBoolean("premium");
        View view = inflater.inflate(R.layout.fragment_call, container, false);
        endcall = view.findViewById(R.id.endcall);
        endcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(premium){
                    MessageFragmentPremium messageFragmentPremium = new MessageFragmentPremium();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, messageFragmentPremium).commit();
                }else {
                    MessageFragment messageFragment = new MessageFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, messageFragment).commit();
                }

            }
        });
        return view;
    }
}