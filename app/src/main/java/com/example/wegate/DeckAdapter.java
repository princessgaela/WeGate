package com.example.wegate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class DeckAdapter extends BaseAdapter {

    // on below line we have created variables
    // for our array list and context.
    private ArrayList<CourseModal> courseData;
    private Context context;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog,alertDialogs;
    private Button upgradebutton,cancelbutton,cancelpayment,upgradeaccount,seecompatibility;
    private ToggleButton goldbutton,silverbutton,gcash,maya,card;
    private TextView accounttype,features,reason;

    // on below line we have created constructor for our variables.
    public DeckAdapter(ArrayList<CourseModal> courseData, Context context) {
        this.courseData = courseData;
        this.context = context;
    }

    @Override
    public int getCount() {
        // in get count method we are returning the size of our array list.
        return courseData.size();
    }

    @Override
    public Object getItem(int position) {
        // in get item method we are returning the item from our array list.
        return courseData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // in get item id we are returning the position.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // in get view method we are inflating our layout on below line.
        View v = convertView;
        if (v == null) {
            // on below line we are inflating our layout.
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        }
        // on below line we are initializing our variables and setting data to our variables.
        ((TextView) v.findViewById(R.id.idTVCourseName)).setText(courseData.get(position).getCourseName());
        ((TextView) v.findViewById(R.id.idTVCourseDescription)).setText(courseData.get(position).getCourseDescription());
        ((TextView) v.findViewById(R.id.idTVCourseDuration)).setText(courseData.get(position).getCourseDuration());
        ((TextView) v.findViewById(R.id.idTVCourseTracks)).setText(courseData.get(position).getCourseTracks());
        ((ImageView) v.findViewById(R.id.idIVCourse)).setImageResource(courseData.get(position).getImgId());
        v.findViewById(R.id.compatibilitybutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewDialog("compatibility",parent);
            }
        });
        return v;
    }
    public void createNewDialog(String reasons, ViewGroup parent){
        builder = new AlertDialog.Builder(context);
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_upgrade,null);
        upgradebutton = view.findViewById(R.id.button);
        cancelbutton = view.findViewById(R.id.button1);
        goldbutton = view.findViewById(R.id.gold);
        silverbutton = view.findViewById(R.id.silver);
        reason = view.findViewById(R.id.reason);
        if(reasons.equals("swipe")){
            reason.setText(R.string.swipe);
        }
        if(reasons.equals("compatibility")){
            reason.setText(R.string.compatibility);
        }
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();

        upgradebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goldbutton.isChecked() || silverbutton.isChecked()){
                    if(goldbutton.isChecked()){
                        builder = new AlertDialog.Builder(context);
                        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_payment,null);
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
                                    Toast.makeText(context, "Your account has been upgraded.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context,HomeActivityPremium.class);
                                    alertDialogs.dismiss();
                                    parent.getContext().startActivity(intent);
                                }else {
                                    Toast.makeText(context, "Select which mode of payment would like to use.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        builder = new AlertDialog.Builder(context);
                        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_payment,null);
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
                                    Toast.makeText(context, "Your account has been upgraded.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context,HomeActivityPremium.class);
                                    alertDialogs.dismiss();
                                    parent.getContext().startActivity(intent);


                                }else {
                                    Toast.makeText(context, "Select which mode of payment would like to use.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }else{
                    Toast.makeText(context, "Select which account you want to avail.", Toast.LENGTH_SHORT).show();
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


