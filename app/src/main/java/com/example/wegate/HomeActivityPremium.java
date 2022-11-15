package com.example.wegate;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wegate.fragments.HomeFragmentPremium;
import com.example.wegate.fragments.MessageFragment;
import com.example.wegate.fragments.MessageListFragmentPremium;
import com.example.wegate.fragments.ProfileFragment;
import com.example.wegate.fragments.RequestFragment;
import com.example.wegate.fragments.SecurityFragment;
import com.example.wegate.fragments.SettingsFragment;
import com.example.wegate.fragments.SettingsFragmentPremium;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class HomeActivityPremium extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    MaterialToolbar materialToolbar;

    Button button;
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, messageListFragmentPremium).commit();
                        materialToolbar.setTitle("Messages");
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, settingsFragmentPremium).commit();
                        materialToolbar.setTitle("Settings");
                        return true;
                }
                return false;
            }
        });

    }
    HomeFragmentPremium homeFragment = new HomeFragmentPremium();
    ProfileFragment profileFragment = new ProfileFragment();
    RequestFragment requestFragment = new RequestFragment();
    MessageListFragmentPremium messageListFragmentPremium = new MessageListFragmentPremium();
    SettingsFragmentPremium settingsFragmentPremium = new SettingsFragmentPremium();
    SecurityFragment securityFragment = new SecurityFragment();
}