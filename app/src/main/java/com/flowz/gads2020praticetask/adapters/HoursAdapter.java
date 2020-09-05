package com.flowz.gads2020praticetask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flowz.gads2020praticetask.R;
import com.flowz.gads2020praticetask.models.HoursModel;
import com.flowz.gads2020praticetask.models.SkilliqModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.HoursViewHolder> {

    ArrayList<HoursModel> hoursList;
    Context context;


    public HoursAdapter(ArrayList<HoursModel> hoursList, Context context) {
        this.hoursList = hoursList;
        this.context = context;
    }

    @NonNull
    @Override
    public HoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.hoursitem, parent, false);
        return new HoursViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder holder, int position) {

        String hours = String.valueOf(hoursList.get(position).hours) + " learning Hours,";

        holder.name.setText(hoursList.get(position).name);
        holder.hoursText.setText(hours);
        holder.country.setText(hoursList.get(position).country);

        Glide.with(context).load(hoursList.get(position).badgeUrl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return hoursList.size();
    }

    public class HoursViewHolder extends RecyclerView.ViewHolder{
        TextView name, country, hoursText;
        ImageView imageView;


        public HoursViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            hoursText = itemView.findViewById(R.id.hours_spent);
            country = itemView.findViewById(R.id.country);
            imageView = itemView.findViewById(R.id.badge);



        }
    }

}
