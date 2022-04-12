package com.example.sharemarket.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharemarket.R;
import com.example.sharemarket.model.CourseModel;
import com.example.sharemarket.youtubePlaylist;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.annotations.NotNull;
import com.squareup.picasso.Picasso;

public class CourseAdapter extends FirebaseRecyclerAdapter<CourseModel,CourseAdapter.MyViewHolder> {


    public CourseAdapter(@NonNull FirebaseRecyclerOptions<CourseModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull CourseModel model)
    {
        holder.coursename.setText(model.getCoursename());
        holder.authorname.setText(model.getAuthorname());
        Picasso.get().load(model.getThumbnail()).into(holder.img);
        holder.itemView.setOnClickListener(v->
        {
            Intent intent=new Intent(holder.itemView.getContext(), youtubePlaylist.class);
            intent.putExtra("courseid",model.getId());
            holder.authorname.getContext().startActivity(intent);
            holder.coursename.getContext().startActivity(intent);
        });

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new MyViewHolder(v);
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView coursename;
        ImageView img;
        TextView authorname;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.coursename=itemView.findViewById(R.id.coursename);
            this.img=itemView.findViewById(R.id.image);
            this.authorname=itemView.findViewById(R.id.authorname);
        }
    }
}
