package com.example.wegate.fragments;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.CountDownTimer;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.wegate.HomeActivityPremium;
import com.example.wegate.MessageChatAdapter;
import com.example.wegate.MessageChatModel;
import com.example.wegate.R;
import com.example.wegate.SplashScreen;
import com.example.wegate.WelcomeActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MessageFragment extends Fragment {

    Calendar calendar;

    List<MessageChatModel> messageChatModelList =  new ArrayList<>();
    RecyclerView recyclerView;
    MessageChatAdapter adapter ;

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog,alertDialogs;
    private Button upgradebutton,cancelbutton,cancelpayment,upgradeaccount,cancelmatch;
    private ToggleButton goldbutton,silverbutton,gcash,maya,card;

    private TextView accounttype,features,reason;
    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();
    RelativeLayout relativeLayout;
    EditText messageET;
    ImageView sendBtn,backbutton,callbutton,videocall,gamebutton,block;

    long startTimer,endTimer,screenOnTime,TIME_ERROR,times;

    public MessageFragment() {
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
                createDialog(1);
            }
        });
        gamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(0);
            }
        });
        videocall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideocallFragment videocallFragment = new VideocallFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("premium", false);
                videocallFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, videocallFragment).commit();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageListFragment messageListFragment = new MessageListFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, messageListFragment).commit();
            }
        });
        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallFragment callFragment = new CallFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("premium", false);
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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isAdded() && isVisible() && isResumed()) {
                    createDialog(2);
                }
                else {
                    //Whatever
                }
                messageET.setFocusable(false);
                messageET.setInputType(InputType.TYPE_NULL);
                messageET.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createDialog(2);
                    }
                });
                messageET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            createDialog(2);
                        }
                    }
                });
                sendBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createDialog(2);
                    }
                });
                callbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createDialog(2);
                    }
                });
                videocall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createDialog(2);
                    }
                });
            }
        }, 10000);
        return  view;
    }

    public void createDialog(int i){
        builder = new AlertDialog.Builder(getContext());
        final View view = getLayoutInflater().inflate(R.layout.popup_upgrade,null);
        upgradebutton = view.findViewById(R.id.button);
        cancelbutton = view.findViewById(R.id.button1);
        goldbutton = view.findViewById(R.id.gold);
        silverbutton = view.findViewById(R.id.silver);
        reason = view.findViewById(R.id.reason);
        if(i==0){
            reason.setText(R.string.game);
        }else if(i==1){
            reason.setText(R.string.undo);
        }else if(i==2){
            reason.setText(R.string.message);
        }

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        upgradebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goldbutton.isChecked() || silverbutton.isChecked()){
                    if(goldbutton.isChecked()){
                        builder = new AlertDialog.Builder(getContext());
                        final View view = getLayoutInflater().inflate(R.layout.popup_payment,null);
                        cancelpayment = view.findViewById(R.id.cancelpayment);
                        upgradeaccount = view.findViewById(R.id.upgradeaccount);
                        gcash = view.findViewById(R.id.gcash);
                        maya = view.findViewById(R.id.maya);
                        card = view.findViewById(R.id.card);
                        accounttype = view.findViewById(R.id.accounttype);
                        features = view.findViewById(R.id.features);
                        accounttype.setText(R.string.goldname);
                        features.setText(R.string.gold);
                        upgradeaccount = view.findViewById(R.id.upgradeaccount);
                        builder.setView(view);
                        alertDialogs = builder.create();
                        alertDialogs.show();
                        cancelpayment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialogs.dismiss();
                            }
                        });
                        gcash.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (gcash.isChecked()){
                                    maya.setChecked(false);
                                    card.setChecked(false);
                                }
                            }
                        });
                        maya.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (maya.isChecked()){
                                    gcash.setChecked(false);
                                    card.setChecked(false);
                                }
                            }
                        });
                        card.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (card.isChecked()){
                                    gcash.setChecked(false);
                                    maya.setChecked(false);
                                }
                            }
                        });
                        upgradeaccount.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (gcash.isChecked() || maya.isChecked() ||card.isChecked()){
                                    Toast.makeText(getContext(), "Your account has been upgraded.", Toast.LENGTH_SHORT).show();
                                    getActivity().finish();
                                    Intent intent = new Intent(getContext(), HomeActivityPremium.class);
                                    alertDialogs.dismiss();
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(getContext(), "Select which mode of payment would like to use.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        builder = new AlertDialog.Builder(getContext());
                        final View view = getLayoutInflater().inflate(R.layout.popup_payment,null);
                        cancelpayment = view.findViewById(R.id.cancelpayment);
                        upgradeaccount = view.findViewById(R.id.upgradeaccount);
                        gcash = view.findViewById(R.id.gcash);
                        maya = view.findViewById(R.id.maya);
                        card = view.findViewById(R.id.card);
                        accounttype = view.findViewById(R.id.accounttype);
                        features = view.findViewById(R.id.features);
                        accounttype.setText(R.string.silvername);
                        features.setText(R.string.silver);
                        upgradeaccount = view.findViewById(R.id.upgradeaccount);
                        builder.setView(view);
                        alertDialogs = builder.create();
                        alertDialogs.show();
                        cancelpayment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialogs.dismiss();
                            }
                        });
                        gcash.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (gcash.isChecked()){
                                    maya.setChecked(false);
                                    card.setChecked(false);
                                }
                            }
                        });
                        maya.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (maya.isChecked()){
                                    gcash.setChecked(false);
                                    card.setChecked(false);
                                }
                            }
                        });
                        card.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (card.isChecked()){
                                    gcash.setChecked(false);
                                    maya.setChecked(false);
                                }
                            }
                        });
                        upgradeaccount.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (gcash.isChecked() || maya.isChecked() ||card.isChecked()){
                                    Toast.makeText(getContext(), "Your account has been upgraded.", Toast.LENGTH_SHORT).show();
                                    getActivity().finish();
                                    Intent intent = new Intent(getContext(), HomeActivityPremium.class);
                                    alertDialogs.dismiss();
                                    startActivity(intent);


                                }else {
                                    Toast.makeText(getContext(), "Select which mode of payment would like to use.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }else{
                    Toast.makeText(getContext(), "Select which account you want to avail.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        goldbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goldbutton.isChecked()){
                    silverbutton.setChecked(false);
                }
            }
        });
        silverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (silverbutton.isChecked()){
                    goldbutton.setChecked(false);
                }
            }
        });
    }
}