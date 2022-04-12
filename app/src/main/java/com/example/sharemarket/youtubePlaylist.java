package com.example.sharemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sharemarket.adapter.CourseAdapter;
import com.example.sharemarket.adapter.PlaylistAdapter;
import com.example.sharemarket.model.CourseModel;
import com.example.sharemarket.model.playlistmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class youtubePlaylist extends AppCompatActivity {

    String courseid;
    RecyclerView courseplaylistview;
    PlaylistAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_playlist);

        getSupportActionBar().setTitle("SHARE MARKET");
        getSupportActionBar().setSubtitle("Playlist");

        courseid=getIntent().getExtras().get("courseid").toString();
        courseplaylistview =(RecyclerView) findViewById(R.id.courseplaylist);
        courseplaylistview.setLayoutManager(new WrapContentLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        FirebaseRecyclerOptions<playlistmodel> options=
                new FirebaseRecyclerOptions.Builder<playlistmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ourseplaylist").child(courseid), playlistmodel.class)
                        .build();
       adapter = new PlaylistAdapter(options,courseid);
       adapter.startListening();
       courseplaylistview.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), course.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
       startActivity(intent);
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView textview;
        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img=itemView.findViewById(R.id.image);
            this.textview=itemView.findViewById(R.id.coursename);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    private class WrapContentLinearLayoutManager extends LinearLayoutManager {


        public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try{
                super.onLayoutChildren(recycler, state);
            }
            catch (IndexOutOfBoundsException e){

            }
        }
    }
}