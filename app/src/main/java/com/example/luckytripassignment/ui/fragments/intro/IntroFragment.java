package com.example.luckytripassignment.ui.fragments.intro;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.luckytripassignment.R;
import com.example.luckytripassignment.data.Rooms;
import com.example.luckytripassignment.ui.base.BaseFragment;
import com.example.luckytripassignment.ui.fragments.rooms.RoomsFragment;
import com.example.luckytripassignment.util.SharedPreferences;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

import static com.example.luckytripassignment.util.Constants.ShortProgressBarDuration;
import static com.example.luckytripassignment.util.SharedPreferences.IS_FIRST_LAUNCH;
import static com.example.luckytripassignment.util.SharedPreferences.IS_ROOM_PRESSED;

public class IntroFragment extends BaseFragment {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private CardView cardView;
    private CardView selectedRoomCardView;
    private FrameLayout rootView;
    private View shadowView;
    private Rooms.Room room;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews();

        setupConstraints();

        initImageAdapter();

        setupListeners();

        checkForSelectedRoom();

        checkForFirstAppLaunch();

    }

    private void checkForSelectedRoom() {

        Rooms.Room room = SharedPreferences.retrieveRoomObject(Objects.requireNonNull(getActivity()));

        if (room != null) {

            this.selectedRoomCardView.setVisibility(View.VISIBLE);

            this.room = room;

            if (SharedPreferences.getBooleanPrefs(Objects.requireNonNull(getActivity()),IS_ROOM_PRESSED,false)) {

                SharedPreferences.setBooleanPrefs(Objects.requireNonNull(getActivity()),IS_ROOM_PRESSED,false);

                showMask();

                new Handler().postDelayed(() -> showRoomDetailsPopup(rootView,this.room).setOnDismissListener(this::hideMask), ShortProgressBarDuration);

            }

        }

    }

    private void checkForFirstAppLaunch() {

        if (!SharedPreferences.getBooleanPrefs(Objects.requireNonNull(getActivity()),IS_FIRST_LAUNCH,true)) {

            SharedPreferences.setBooleanPrefs(Objects.requireNonNull(getActivity()),IS_FIRST_LAUNCH,false);

            showMask();

            new Handler().postDelayed(() -> showRoomDetailsPopup(rootView,this.room).setOnDismissListener(this::hideMask), ShortProgressBarDuration);

        }

    }

    private void setupListeners() {

        this.cardView.setOnClickListener(v -> navigateToFragment(new RoomsFragment()));

        this.selectedRoomCardView.setOnClickListener(v -> {

            showMask();

            new Handler().postDelayed(() -> showRoomDetailsPopup(rootView,this.room).setOnDismissListener(this::hideMask), ShortProgressBarDuration);

        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Hides the title bar
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false);

    }

    private void setupViews() {

        this.viewPager = Objects.requireNonNull(getActivity()).findViewById(R.id.view_pager);
        this.tabLayout = Objects.requireNonNull(getActivity()).findViewById(R.id.tabLayout);
        this.cardView = Objects.requireNonNull(getActivity()).findViewById(R.id.cardView);
        this.selectedRoomCardView = Objects.requireNonNull(getActivity()).findViewById(R.id.selectedRoomCardView);
        this.rootView = Objects.requireNonNull(getActivity()).findViewById(R.id.rootView);
        this.shadowView = getActivity().findViewById(R.id.alphaLayout);

    }

    private void initImageAdapter() {

        // init viewpager adapter and attach
        ViewPagerAdapter adapter = new ViewPagerAdapter(Objects.requireNonNull(getActivity()));
        viewPager.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, (tab, position) -> {});

        tabLayoutMediator.attach();

    }

    private void setupConstraints() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        this.viewPager.getLayoutParams().height = ((int) (displayMetrics.heightPixels * 0.6));
        this.cardView.getLayoutParams().width = ((int) (displayMetrics.heightPixels * 0.3));
        this.selectedRoomCardView.getLayoutParams().width = ((int) (displayMetrics.heightPixels * 0.3));

    }

    public void navigateToFragment(Fragment fragment) {

        // Begin the transaction
        FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();

        // Replace the contents of the container with the new fragment
        ft.replace(R.id.container, fragment);

        // Append this transaction to the back stack
        ft.addToBackStack(fragment.getTag());

        // Complete the changes added above
        ft.commit();

    }

    public void showMask() {

        this.shadowView.setVisibility(View.VISIBLE);

    }

    public void hideMask() {

        this.shadowView.setVisibility(View.GONE);

    }

}