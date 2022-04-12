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
import com.example.sharemarket.model.playlistmodel;
import com.example.sharemarket.myvideoplayer;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class PlaylistAdapter  extends FirebaseRecyclerAdapter<playlistmodel,PlaylistAdapter.PlaylistViewHolder>
{

    String courseid;
    public PlaylistAdapter(@NonNull FirebaseRecyclerOptions<playlistmodel> options,String courseid) {
        super(options);
        this.courseid=courseid;
    }



    @Override
    protected void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position, @NonNull playlistmodel model) {

        holder.textview.setText(model.getVideoname());
        holder.authorname.setText(model.getAuthorname());
        Picasso.get().load(model.getPlaylistimage()).into(holder.img);
        holder.itemView.setOnClickListener(v->
        {
            Intent intent = new Intent(holder.textview.getContext(), myvideoplayer.class);
            intent.putExtra("videourl",model.getVideourl());
            intent.putExtra("courseid",courseid);
            holder.authorname.getContext().startActivity(intent);
            holder.textview.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new PlaylistViewHolder(v);
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView textview;
        TextView authorname;
        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img=itemView.findViewById(R.id.image);
            this.textview=itemView.findViewById(R.id.coursename);
            this.authorname=itemView.findViewById(R.id.authorname);

        }
    }
}
