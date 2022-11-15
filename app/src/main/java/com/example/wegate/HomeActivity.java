package com.example.wegate;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wegate.fragments.HomeFragment;
import com.example.wegate.fragments.MessageListFragment;
import com.example.wegate.fragments.ProfileFragment;
import com.example.wegate.fragments.RequestFragment;
import com.example.wegate.fragments.SecurityFragment;
import com.example.wegate.fragments.SettingsFragment;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    MaterialToolbar materialToolbar;

    Button button;
    private RewardedAd rewardedAd;
    boolean isLoading;
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";
    private final String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        View inflatedView = getLayoutInflater().inflate(R.layout.fragment_home, null);
        materialToolbar = findViewById(R.id.materaltoolbar);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, securityFragment).commit();
                materialToolbar.setTitle("Security");
                bottomNavigationView.setSelected(false);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomnavview);
        bottomNavigationView.setSelectedItemId(R.id.home);
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, homeFragment).commit();
                        materialToolbar.setTitle("Home");
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, profileFragment).commit();
                        materialToolbar.setTitle("Profile");
                        return true;
                    case R.id.request:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, requestFragment).commit();
                        materialToolbar.setTitle("Requests");
                        return true;
                    case R.id.message:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, messagelistFragment).commit();
                        materialToolbar.setTitle("Messages");
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, settingsFragment).commit();
                        materialToolbar.setTitle("Settings");
                        return true;
                }
                return false;
            }
        });

    }
    HomeFragment homeFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    RequestFragment requestFragment = new RequestFragment();
    MessageListFragment messagelistFragment = new MessageListFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    SecurityFragment securityFragment = new SecurityFragment();
}