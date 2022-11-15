package com.example.wegate.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wegate.R;

public class GameResultAgainFragment extends Fragment {

    Button closebutton,viewanswer;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog,alertDialogs;

    public GameResultAgainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_result_again, container, false);
        closebutton = view.findViewById(R.id.closebutton);
        viewanswer = view.findViewById(R.id.viewanswer);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageFragmentPremium messageFragmentPremium = new MessageFragmentPremium();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, messageFragmentPremium).commit();
            }
        });
        viewanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(getContext());
                final View view = getLayoutInflater().inflate(R.layout.popup_thisorthat,null);
                closebutton = view.findViewById(R.id.closebutton);
                closebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                builder.setView(view);
                alertDialog = builder.create();
                alertDialog.show();


            }
        });
        return view;
    }
}