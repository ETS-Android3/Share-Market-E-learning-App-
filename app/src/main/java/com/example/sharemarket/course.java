package com.example.sharemarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.sharemarket.adapter.CourseAdapter;
import com.example.sharemarket.model.CourseModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class course extends AppCompatActivity{


    RecyclerView recyclerview;
    CourseAdapter adapter;
    BottomNavigationView bottomNavigationView;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.courses);

        getSupportActionBar().setTitle("SHARE MARKET");
        getSupportActionBar().setSubtitle("Courses");

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                                                           @Override
                                                           public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                                                               switch (item.getItemId()){

                                                                   case R.id.home:
                                                                       startActivity(new Intent(getApplicationContext(),Home.class));
                                                                       overridePendingTransition(0,0);
                                                                       return true;

                                                                   case R.id.courses:
                                                                       return true;

                                                                   case R.id.ebook:
                                                                       startActivity(new Intent(getApplicationContext(),Ebook.class));
                                                                       overridePendingTransition(0,0);
                                                                       return true;

                                                                       case R.id.nav_profile:
                                                                       startActivity(new Intent(getApplicationContext(),setting.class));
                                                                       overridePendingTransition(0,0);
                                                                       return true;

                                                                   case R.id.shortsss:
                                                                       startActivity(new Intent(getApplicationContext(),shorts.class));
                                                                       overridePendingTransition(0,0);
                                                                       return true;



                                                               }

                                                               return false;
                                                           }
                                                       });


                recyclerview = (RecyclerView) findViewById(R.id.c_recview);
        recyclerview.setLayoutManager(new WrapContentLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        FirebaseRecyclerOptions<CourseModel> options=
                new FirebaseRecyclerOptions.Builder<CourseModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("courses"), CourseModel.class)
                        .build();
        adapter = new CourseAdapter(options);
        adapter.startListening();
        recyclerview.setAdapter(adapter);

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
            this.authorname = itemView.findViewById(R.id.authorname);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchbar,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<CourseModel> options =
                new FirebaseRecyclerOptions.Builder<CourseModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("courses").orderByChild("coursename").startAt(s).endAt(s+"\uf8ff"), CourseModel.class)
                        .build();

        adapter=new CourseAdapter(options);
        adapter.startListening();
        recyclerview.setAdapter(adapter);

    }



}


