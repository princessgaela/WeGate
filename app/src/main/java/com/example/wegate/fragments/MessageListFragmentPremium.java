package com.example.wegate.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wegate.R;

public class MessageListFragmentPremium extends Fragment {
    CardView cardView,cardView2;

    public MessageListFragmentPremium() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message_list, container, false);
        cardView = view.findViewById(R.id.messagecard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageFragmentPremium messageFragmentPremium = new MessageFragmentPremium();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, messageFragmentPremium).commit();
            }
        });
        return view;
    }
}