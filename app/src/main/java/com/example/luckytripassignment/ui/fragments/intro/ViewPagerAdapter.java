package com.example.luckytripassignment.ui.fragments.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luckytripassignment.R;
import com.example.luckytripassignment.ui.base.App;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Integer> arrayList;
    ArrayList<String> descriptionArrayList;

    public ViewPagerAdapter(Context context) {

        this.arrayList = new ArrayList<>();
        this.arrayList.add(R.drawable.ic_pager_1);
        this.arrayList.add(R.drawable.ic_pager_2);
        this.arrayList.add(R.drawable.ic_pager_3);

        this.descriptionArrayList = new ArrayList<>();
        this.descriptionArrayList.add(App.mApp.getString(R.string.ic_pager_1));
        this.descriptionArrayList.add(App.mApp.getString(R.string.ic_pager_2));
        this.descriptionArrayList.add(App.mApp.getString(R.string.ic_pager_3));

        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public ViewPagerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_pager_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.MyViewHolder holder, int position) {
        holder.imageView.setImageResource(arrayList.get(position));
        holder.mainTextView.setText(descriptionArrayList.get(position));

        holder.itemView.getWidth();
    }

    @Override
    public int getItemCount() {
        if(arrayList != null){
            return arrayList.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView imageCardView;
        TextView mainTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.viewPagerIV);
            mainTextView = itemView.findViewById(R.id.mainTV);
            imageCardView = itemView.findViewById(R.id.relativeBorder);

            imageCardView.getLayoutParams().width = ((int)(App.mApp.getResources().getDisplayMetrics().widthPixels * 0.5)) ;
            imageCardView.getLayoutParams().height = ((int)(App.mApp.getResources().getDisplayMetrics().widthPixels * 0.5)) ;

            imageView.getLayoutParams().width = ((int)(App.mApp.getResources().getDisplayMetrics().widthPixels * 0.5)) ;
            imageView.getLayoutParams().height = ((int)(App.mApp.getResources().getDisplayMetrics().widthPixels * 0.5)) ;

        }
    }
}
