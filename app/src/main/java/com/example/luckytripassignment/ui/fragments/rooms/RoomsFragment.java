package com.example.luckytripassignment.ui.fragments.rooms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luckytripassignment.R;
import com.example.luckytripassignment.data.IApiCaller;
import com.example.luckytripassignment.data.Rooms;
import com.example.luckytripassignment.data.RoomsAdapter;
import com.example.luckytripassignment.ui.base.BaseFragment;
import com.example.luckytripassignment.util.SharedPreferences;

import java.util.Objects;

import static com.example.luckytripassignment.util.SharedPreferences.IS_ROOM_PRESSED;

public class RoomsFragment extends BaseFragment implements IApiCaller.Listener {

    RoomsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IApiCaller caller = new IApiCaller();

        caller.callApiGet(getActivity(),this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rooms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupAdapter();

        setupListeners();

    }

    private void setupListeners() {

        adapter.setOnItemClickListener((room) -> {

            SharedPreferences.saveRoomObject(Objects.requireNonNull(getActivity()),room);

            SharedPreferences.setBooleanPrefs(Objects.requireNonNull(getActivity()),IS_ROOM_PRESSED,true);

            getActivity().onBackPressed();

        });

    }

    @Override
    public void onSuccessfulResponse(Rooms.Root rooms) {

        adapter.addItems(rooms.rooms);

    }

    private void setupAdapter() {

        RecyclerView recyclerView = Objects.requireNonNull(getActivity()).findViewById(R.id.roomsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RoomsAdapter(getActivity());
        recyclerView.setAdapter(adapter);

    }
}