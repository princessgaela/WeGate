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

public class DeckAdapterPremium extends BaseAdapter {

    // on below line we have created variables
    // for our array list and context.
    private ArrayList<CourseModal> courseData;
    private Context context;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog,alertDialogs;
    private Button upgradebutton,cancelbutton,cancelpayment,upgradeaccount,seecompatibility;
    private ToggleButton goldbutton,silverbutton,gcash,maya,card;
    private TextView compatibilitytext,compatibilitypercent;
    private ImageView compatibityimage;
    String zodiacsign;

    // on below line we have created constructor for our variables.
    public DeckAdapterPremium(ArrayList<CourseModal> courseData, Context context) {
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
                zodiacsign = courseData.get(position).getCourseDescription();
                createNewDialog(parent,zodiacsign);
            }
        });
        return v;
    }
    public void createNewDialog(ViewGroup parent,String zodiacsign){
        builder = new AlertDialog.Builder(context);
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_compatibility,null);
        cancelbutton = view.findViewById(R.id.button1);
        compatibilitytext = view.findViewById(R.id.compatibilitytext);
        compatibityimage = view.findViewById(R.id.compatibilityimage);
        compatibilitypercent = view.findViewById(R.id.compatibilitypercent);
        if(zodiacsign.equals("Aries")){
            compatibityimage.setImageResource(R.drawable.aries);
            compatibilitytext.setText(R.string.aries);
            compatibilitypercent.setText("53%");
        }
        if(zodiacsign.equals("Aquarius")){
            compatibityimage.setImageResource(R.drawable.aquarius);
            compatibilitytext.setText(R.string.aquarius);
            compatibilitypercent.setText("15%");
        }
        if(zodiacsign.equals("Cancer")){
            compatibityimage.setImageResource(R.drawable.cancer);
            compatibilitytext.setText(R.string.cancer);
            compatibilitypercent.setText("86%");
        }
        if(zodiacsign.equals("Capricorn")){
            compatibityimage.setImageResource(R.drawable.capricorn);
            compatibilitytext.setText(R.string.capricorn);
            compatibilitypercent.setText("83%");
        }if(zodiacsign.equals("Gemini")){
            compatibityimage.setImageResource(R.drawable.gemini);
            compatibilitytext.setText(R.string.gemini);
            compatibilitypercent.setText("30%");
        }if(zodiacsign.equals("Leo")){
            compatibityimage.setImageResource(R.drawable.leo);
            compatibilitytext.setText(R.string.leo);
            compatibilitypercent.setText("41%");
        }
        if(zodiacsign.equals("Libra")){
            compatibityimage.setImageResource(R.drawable.libra);
            compatibilitytext.setText(R.string.libra);
            compatibilitypercent.setText("31%");
        }
        if(zodiacsign.equals("Pisces")){
            compatibityimage.setImageResource(R.drawable.pisces);
            compatibilitytext.setText(R.string.pisces);
            compatibilitypercent.setText("85%");
        }
        if(zodiacsign.equals("Sagittarius")){
            compatibityimage.setImageResource(R.drawable.sagittarius);
            compatibilitytext.setText(R.string.sagittarius);
            compatibilitypercent.setText("28%");
        }
        if(zodiacsign.equals("Scorpio")){
            compatibityimage.setImageResource(R.drawable.scorpio);
            compatibilitytext.setText(R.string.scorpio);
            compatibilitypercent.setText("82%");
        }
        if(zodiacsign.equals("Taurus")){
            compatibityimage.setImageResource(R.drawable.taurus);
            compatibilitytext.setText(R.string.taurus);
            compatibilitypercent.setText("78%");
        }
        if(zodiacsign.equals("Virgo")){
            compatibityimage.setImageResource(R.drawable.virgo);
            compatibilitytext.setText(R.string.virgo);
            compatibilitypercent.setText("80%");
        }
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

}


