package com.example.wegate.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.daprlabs.cardstack.SwipeDeck;
import com.example.wegate.CourseModal;
import com.example.wegate.DeckAdapter;
import com.example.wegate.DeckAdapterPremium;
import com.example.wegate.HomeActivity;
import com.example.wegate.HomeActivityPremium;
import com.example.wegate.LoginActivity;
import com.example.wegate.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragmentPremium extends Fragment {
    private SwipeDeck cardStack;
    private ArrayList<CourseModal> courseModalArrayList;
    private static final String TAG = HomeFragment.class.getSimpleName();
    private AlertDialog.Builder builder;
    private int swipecounter,swiperight=0;
    private AlertDialog alertDialog,alertDialogs;
    private Button upgradebutton,cancelbutton,cancelpayment,upgradeaccount,seecompatibility;
    private ToggleButton goldbutton,silverbutton,gcash,maya,card;
    private TextView accounttype,features,reason;
    private FloatingActionButton like,dislike;
    public HomeFragmentPremium() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        init(root);
        return root;
    }

    private void init(View root){

        courseModalArrayList = new ArrayList<>();
        cardStack = (SwipeDeck) root.findViewById(R.id.swipe_deck);
        like = root.findViewById(R.id.floatingActionButton3);
        dislike = root.findViewById(R.id.floatingActionButton2);
        // on below line we are adding data to our array list.
        courseModalArrayList.add(new CourseModal("HoYeon ♀", "28", "Seoul", "Cancer", R.drawable.hoyeon));
        courseModalArrayList.add(new CourseModal("Alexandra ♀", "36", "New York", "Pisces", R.drawable.alexandra));
        courseModalArrayList.add(new CourseModal("Channing ♂", "42", "Alabama", "Taurus", R.drawable.channing));
        courseModalArrayList.add(new CourseModal("Simu ♂", "33", "Harbin", "Aries", R.drawable.simu));
        courseModalArrayList.add(new CourseModal("Chris ♂", "39", "Melbourne", "Leo", R.drawable.chris));
        courseModalArrayList.add(new CourseModal("Elizabeth ♀", "33", "California", "Aquarius", R.drawable.elizabeth));
        courseModalArrayList.add(new CourseModal("Jeremy ♂", "51", "California", "Capricorn", R.drawable.jeremy));
        courseModalArrayList.add(new CourseModal("Johnny ♂", "59", "Kentucky", "Gemini", R.drawable.johnny));
        courseModalArrayList.add(new CourseModal("Anthony ♂", "44", "Louisiana", "Libra", R.drawable.anthony));
        courseModalArrayList.add(new CourseModal("Scarlett ♀", "37", "New York", "Sagittarius", R.drawable.scarlett));
        courseModalArrayList.add(new CourseModal("Ryan ♂", "45", "Vancouver", "Scorpio", R.drawable.ryan));
        courseModalArrayList.add(new CourseModal("Zendaya ♀", "26", "California", "Virgo", R.drawable.zendaya));

        // on below line we are creating a variable for our adapter class and passing array list to it.
        final DeckAdapterPremium adapter = new DeckAdapterPremium(courseModalArrayList, getContext());

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);

        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                dislike.setPressed(true);
                dislike.setPressed(false);
            }

            @Override
            public void cardSwipedRight(int position) {
                like.setPressed(true);
                like.setPressed(false);

            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present

                courseModalArrayList.add(new CourseModal("HoYeon ♀", "28", "Seoul", "Cancer", R.drawable.hoyeon));
                courseModalArrayList.add(new CourseModal("Alexandra ♀", "36", "New York", "Pisces", R.drawable.alexandra));
                courseModalArrayList.add(new CourseModal("Channing ♂", "42", "Alabama", "Taurus", R.drawable.channing));
                courseModalArrayList.add(new CourseModal("Simu ♂", "33", "Harbin", "Aries", R.drawable.simu));
                courseModalArrayList.add(new CourseModal("Chris ♂", "39", "Melbourne", "Leo", R.drawable.chris));
                courseModalArrayList.add(new CourseModal("Elizabeth ♀", "33", "California", "Aquarius", R.drawable.elizabeth));
                courseModalArrayList.add(new CourseModal("Jeremy ♂", "51", "California", "Capricorn", R.drawable.jeremy));
                courseModalArrayList.add(new CourseModal("Johnny ♂", "59", "Kentucky", "Gemini", R.drawable.johnny));
                courseModalArrayList.add(new CourseModal("Anthony ♂", "44", "Louisiana", "Libra", R.drawable.anthony));
                courseModalArrayList.add(new CourseModal("Scarlett ♀", "37", "New York", "Sagittarius", R.drawable.scarlett));
                courseModalArrayList.add(new CourseModal("Ryan ♂", "45", "Vancouver", "Scorpio", R.drawable.ryan));
                courseModalArrayList.add(new CourseModal("Zendaya ♀", "26", "California", "Virgo", R.drawable.zendaya));
                // on below line we are creating a variable for our adapter class and passing array list to it.
                final DeckAdapterPremium adapter = new DeckAdapterPremium(courseModalArrayList, getContext());

                // on below line we are setting adapter to our card stack.
                cardStack.setAdapter(adapter);
            }

            @Override
            public void cardActionDown() {
                // this method is called when card is swiped down.
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
                Log.i("TAG", "CARDS MOVED UP");
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardRight(10000);
            }
        });
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardLeft(10000);
            }
        });


    }

}
