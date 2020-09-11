package com.flowz.gads2020praticetask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flowz.gads2020praticetask.R;
import com.flowz.gads2020praticetask.roomdb.skilliqdatabase.dbSkilliqModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SkillIqAdapter extends RecyclerView.Adapter<SkillIqAdapter.SkillsViewHolder> {

//    ArrayList<SkilliqModel> iqScores;
    List<dbSkilliqModel> iqScores;
    Context context;

    public SkillIqAdapter(List<dbSkilliqModel> iqScores, Context context) {
        this.iqScores = iqScores;
        this.context = context;
    }

    @NonNull
    @Override
    public SkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.skillitem, parent, false);
        return new SkillsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsViewHolder holder, int position) {

        String score = String.valueOf(iqScores.get(position).score) + " skill IQ Score,";

        holder.name.setText(iqScores.get(position).name);
        holder.score.setText(score);
        holder.country.setText(iqScores.get(position).country);

        Glide.with(context).load(iqScores.get(position).badgeUrl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return iqScores.size();
    }

    public class SkillsViewHolder extends RecyclerView.ViewHolder{
        TextView name, country, score;
        ImageView imageView;


        public SkillsViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            score = itemView.findViewById(R.id.skilliq_score);
            country = itemView.findViewById(R.id.country);
            imageView = itemView.findViewById(R.id.badge);



        }
    }

}
