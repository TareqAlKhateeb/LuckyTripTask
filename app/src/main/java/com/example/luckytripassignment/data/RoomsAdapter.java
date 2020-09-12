package com.example.luckytripassignment.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luckytripassignment.R;

import java.util.ArrayList;
import java.util.Collections;

//The main recycler view adapter for the list of rooms
public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ItemViewHolder> {

    private ArrayList<Rooms.Room> rooms;
    private RoomsAdapter.OnItemClickListener onItemClickListener;
    private ArrayList<ItemViewHolder> roomsHolder;
    private Context context;

    public RoomsAdapter(Context context) {

        this.context = context;
        this.rooms = new ArrayList<>();
        this.roomsHolder = new ArrayList<>();

    }

    @NonNull
    @Override
    public RoomsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);

    }

    //Fetching data into views
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RoomsAdapter.ItemViewHolder holder, final int position) {

        Rooms.Room room = rooms.get(position);

        String maxOccupancy = context.getString(R.string.max_occupancy);

        holder.roomName.setText(room.name);
        holder.roomPrice.setText((room.price.price_value) + " " + room.price.currency);
        holder.roomDesc.setText(room.room_description);
        holder.roomMaxOccupancy.setText(maxOccupancy + " " + room.max_occupancy);
        holder.bedConfiguration.setText(room.bed_configurations.get(0).count + " " + room.bed_configurations.get(0).name);
        holder.cardView.setOnClickListener(v -> onItemClickListener.onClick(room));

        roomsHolder.add(holder);

    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    //Adding/updating list of rooms
    public void addItems(ArrayList<Rooms.Room> rooms) {

        this.rooms.clear();

        Collections.sort(rooms);

        this.rooms.addAll(rooms);

        notifyDataSetChanged();

    }

    public void setOnItemClickListener(RoomsAdapter.OnItemClickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;

    }

    public interface OnItemClickListener {

        void onClick(Rooms.Room room);

    }

    //Initiating views
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView roomName, roomPrice, roomDesc, roomMaxOccupancy, bedConfiguration;
        CardView cardView;

        public ItemViewHolder(View itemView) {
            super(itemView);

            roomName = itemView.findViewById(R.id.roomName);
            roomPrice = itemView.findViewById(R.id.roomPrice);
            roomDesc = itemView.findViewById(R.id.roomDesc);
            roomMaxOccupancy = itemView.findViewById(R.id.roomMaxOccupancy);
            bedConfiguration = itemView.findViewById(R.id.bedConfiguration);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

}
