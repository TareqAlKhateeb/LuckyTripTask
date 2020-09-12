package com.example.luckytripassignment.ui.activities.host;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.luckytripassignment.R;
import com.example.luckytripassignment.ui.fragments.intro.IntroFragment;

//The screen that holds both the intro and the rooms fragments
public class HostActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        //Navigates to needed fragment
        navigateToFragment(new IntroFragment());

    }

    public void navigateToFragment(Fragment fragment) {

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Replace the contents of the container with the new fragment
        ft.replace(R.id.container, fragment);

        // Complete the changes added above
        ft.commit();

    }

    @Override
    public void onBackPressed() {

        //Handles back button press
        handleBackPress();

    }

    public void handleBackPress() {

        //Gets the container that holds the fragments
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.container);

        //checks current fragment nullability
        if (visibleFragment == null) {

            return;

        } else if (visibleFragment instanceof IntroFragment) {

            //Handles exit app action
            if (doubleBackToExitPressedOnce) {

                super.onBackPressed();
                return;

            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getString(R.string.click_back_again_to_exit), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
            return;
        }

        super.onBackPressed();

    }
}