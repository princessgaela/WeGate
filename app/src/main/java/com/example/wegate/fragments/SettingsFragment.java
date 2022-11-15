package com.example.wegate.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.wegate.HomeActivityPremium;
import com.example.wegate.R;
import com.example.wegate.SplashScreen;
import com.example.wegate.WelcomeActivity;

public class SettingsFragment extends Fragment {

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog,alertDialogs;
    private Button upgradebutton,cancelbutton,cancelpayment,upgradeaccount,cancelmatch,save,logout,delete;
    private ToggleButton goldbutton,silverbutton,gcash,maya,card;
    private TextView accounttype,features,reason;

    SeekBar seekBar;
    TextView textView,textView2;
    int progressChangedValue = 0;
    Switch aSwitch;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        save = view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
        delete = view.findViewById(R.id.deleteaccount);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                                //Yes button clicked
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
        aSwitch = view.findViewById(R.id.switchbutton);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    aSwitch.setChecked(false);
                    createDialog();
                }
            }
        });
        RangeBar rangeBar = (RangeBar) view.findViewById(R.id.seekBar2);
        textView2 = view.findViewById(R.id.distance2);
        rangeBar.setMinVal(18);
        rangeBar.setMaxVal(100);
        rangeBar.create(textView2);
        textView = view.findViewById(R.id.distance);
        seekBar = view.findViewById(R.id.seekBar);
        seekBar.setMax(100);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                textView.setText(String.valueOf(progressChangedValue)+" mi.");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

    public void createDialog(){
        builder = new AlertDialog.Builder(getContext());
        final View view = getLayoutInflater().inflate(R.layout.popup_upgrade,null);
        upgradebutton = view.findViewById(R.id.button);
        cancelbutton = view.findViewById(R.id.button1);
        goldbutton = view.findViewById(R.id.gold);
        silverbutton = view.findViewById(R.id.silver);
        reason = view.findViewById(R.id.reason);
        reason.setText(R.string.location);

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