package com.example.luckytripassignment.ui.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.luckytripassignment.R;
import com.example.luckytripassignment.data.Rooms;

import java.util.Objects;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);

    }

    //Room details popup
    @SuppressLint("SetTextI18n")
    public PopupWindow showRoomDetailsPopup(View view, Rooms.Room room) {

        // Inflate the popup_layout.xml
        LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(getActivity()).getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.room_details_popup, null), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        View layout = pw.getContentView();

        setupPopupViews(layout,room,pw);

        pw.showAtLocation(view, Gravity.CENTER, 0, 0);

        return pw;

    }

    //Rooms details popup views and data handler method
    private void setupPopupViews(View layout, Rooms.Room room, PopupWindow pw) {

        layout.findViewById(R.id.outsideLayout).setOnClickListener(v -> pw.dismiss());

        ImageView roomImageView = layout.findViewById(R.id.roomIv);
        ((TextView) layout.findViewById(R.id.roomTitle)).setText(room.name);
        ((TextView) layout.findViewById(R.id.roomPrice)).setText((room.price.price_value) + " " + room.price.currency);

        RequestOptions myOptions = new RequestOptions()
                .override(roomImageView.getWidth(), roomImageView.getHeight()).centerCrop();

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {
                Glide.with(BaseFragment.this).asBitmap()
                        .apply(myOptions).load(room.photos.get(i)).into(roomImageView);
                i++;
                if (i > room.photos.size() - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 0);

    }

}