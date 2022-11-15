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

public class HomeFragment extends Fragment {
    private SwipeDeck cardStack;
    private ArrayList<CourseModal> courseModalArrayList;
    private static final String TAG = HomeFragment.class.getSimpleName();
    private RewardedAd rewardedAds;
    boolean isLoading;
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";
    private int swipecounter,swiperight=0;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog,alertDialogs;
    private Button upgradebutton,cancelbutton,cancelpayment,upgradeaccount,cancelmatch;
    private ToggleButton goldbutton,silverbutton,gcash,maya,card;
    private TextView accounttype,features,reason;
    private FloatingActionButton like,dislike;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });
        loadRewardedAd();
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
        final DeckAdapter adapter = new DeckAdapter(courseModalArrayList, getContext());

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);


        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {

            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                dislike.setPressed(true);
                swipecounter++;
                if(swipecounter==3){
                    showRewardedVideo();
                    swipecounter=0;

                }
                dislike.setPressed(false);
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swiped to right we are displaying a toast message.
                like.setPressed(true);
                swipecounter++;
                swiperight++;
                if(swipecounter==3){
                    showRewardedVideo();
                    swipecounter=0;
                }

                like.setPressed(false);
                if(swiperight==2){
                    builder = new AlertDialog.Builder(getContext());
                    final View view = getLayoutInflater().inflate(R.layout.popup_match,null);
                    cancelmatch = view.findViewById(R.id.cancelmatch);
                    cancelmatch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                    builder.setView(view);
                    alertDialog = builder.create();
                    alertDialog.show();
                }
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                createNewDialog("swipe");
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
    private void loadRewardedAd() {
        if (rewardedAds == null) {
            isLoading = true;
            AdRequest adRequest = new AdRequest.Builder().build();
            RewardedAd.load(
                    getContext(),
                    AD_UNIT_ID,
                    adRequest,
                    new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error.

                            Log.d(TAG, loadAdError.getMessage());
                            rewardedAds = null;
                            isLoading = false;

                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            rewardedAds = rewardedAd;
                            Log.d(TAG, "onAdLoaded");
                            isLoading = false;

                        }
                    });
        }
    }
    private void showRewardedVideo() {

        if (rewardedAds == null) {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
            return;
        }
        rewardedAds.setFullScreenContentCallback(
                new FullScreenContentCallback() {
                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(TAG, "onAdShowedFullScreenContent");

                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when ad fails to show.
                        Log.d(TAG, "onAdFailedToShowFullScreenContent");
                        // Don't forget to set the ad reference to null so you
                        // don't show the ad a second time.
                        rewardedAds = null;

                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        // Don't forget to set the ad reference to null so you
                        // don't show the ad a second time.
                        rewardedAds = null;
                        Log.d(TAG, "onAdDismissedFullScreenContent");

                        // Preload the next rewarded ad.
                        loadRewardedAd();
                    }
                });
        Activity activityContext = getActivity();
        rewardedAds.show(
                activityContext,
                new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        // Handle the reward.
                        Log.d("TAG", "The user earned the reward.");
                        int rewardAmount = rewardItem.getAmount();
                        String rewardType = rewardItem.getType();
                    }
                });
    }
    public void createNewDialog(String reasons){
        builder = new AlertDialog.Builder(getContext());
        final View view = getLayoutInflater().inflate(R.layout.popup_upgrade,null);
        upgradebutton = view.findViewById(R.id.button);
        cancelbutton = view.findViewById(R.id.button1);
        goldbutton = view.findViewById(R.id.gold);
        silverbutton = view.findViewById(R.id.silver);
        reason = view.findViewById(R.id.reason);
        if(reasons.equals("swipe")){
            reason.setText(R.string.swipe);
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
                                    Intent intent = new Intent(getContext(),HomeActivityPremium.class);
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
