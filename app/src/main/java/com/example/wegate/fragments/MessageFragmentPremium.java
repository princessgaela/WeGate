package com.example.wegate.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wegate.MessageChatAdapter;
import com.example.wegate.MessageChatModel;
import com.example.wegate.R;

import java.util.ArrayList;
import java.util.List;

public class MessageFragmentPremium extends Fragment {
    List<MessageChatModel> messageChatModelList =  new ArrayList<>();
    RecyclerView recyclerView;
    MessageChatAdapter adapter ;

    EditText messageET;
    ImageView sendBtn,backbutton,callbutton,videocall,gamebutton,block;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog,alertDialogs;
    private Button upgradebutton,cancelbutton,cancelpayment,upgradeaccount,cancelmatch;

    public MessageFragmentPremium() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        messageET = (EditText)view.findViewById(R.id.messageET);
        sendBtn = (ImageView) view.findViewById(R.id.sendBtn);
        backbutton = view.findViewById(R.id.backmessage);
        callbutton = view.findViewById(R.id.callbutton);
        videocall = view.findViewById(R.id.videocall);
        gamebutton = view.findViewById(R.id.gamebutton);
        block = view.findViewById(R.id.block);
        block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(getContext());
                final View view = getLayoutInflater().inflate(R.layout.popup_redo,null);
                cancelbutton = view.findViewById(R.id.cancelmatch);
                builder.setView(view);
                alertDialog = builder.create();
                alertDialog.show();
                cancelbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
        gamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameFragment gameFragment = new GameFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, gameFragment).commit();
            }
        });
        videocall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideocallFragment videocallFragment = new VideocallFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("premium", true);
                videocallFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, videocallFragment).commit();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageListFragmentPremium messageListFragmentPremium = new MessageListFragmentPremium();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, messageListFragmentPremium).commit();
            }
        });
        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallFragment callFragment = new CallFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("premium", true);
                callFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, callFragment).commit();
            }
        });
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        MessageChatModel model1 = new MessageChatModel(
                "Hello",
                "10:00 PM",
                1
        );
        MessageChatModel model2 = new MessageChatModel(
                "Hi",
                "10:01 PM",
                0
        );
        MessageChatModel model3 = new MessageChatModel(
                "How are you?",
                "10:02 PM",
                1
        );
        MessageChatModel model4 = new MessageChatModel(
                "Fine. How about you?",
                "10:03 PM",
                0
        );
        MessageChatModel model5 = new MessageChatModel(
                "Feeling great.",
                "10:04 PM",
                1
        );

        messageChatModelList.add(model1);
        messageChatModelList.add(model2);
        messageChatModelList.add(model3);
        messageChatModelList.add(model4);
        messageChatModelList.add(model5);

        recyclerView.smoothScrollToPosition(messageChatModelList.size());
        adapter = new MessageChatAdapter(messageChatModelList, getContext());
        recyclerView.setAdapter(adapter);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = messageET.getText().toString();

                MessageChatModel model = new MessageChatModel(
                        msg,
                        "10:06 PM",
                        0
                );
                messageChatModelList.add(model);
                recyclerView.smoothScrollToPosition(messageChatModelList.size());
                adapter.notifyDataSetChanged();
                messageET.setText("");


            }
        });
        return view;
    }
}