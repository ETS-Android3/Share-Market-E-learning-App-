package com.example.sharemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.sharemarket.adapter.CourseAdapter;
import com.example.sharemarket.adapter.EbookAdapter;
import com.example.sharemarket.model.CourseModel;
import com.example.sharemarket.model.EbookModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

public class Ebook extends AppCompatActivity {

    RecyclerView recyclerview;
    EbookAdapter adapter;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ebook);

        getSupportActionBar().setTitle("SHARE MARKET");
        getSupportActionBar().setSubtitle("Ebooks");

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.courses:
                        startActivity(new Intent(getApplicationContext(),course.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ebook:
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






        recyclerview = (RecyclerView) findViewById(R.id.e_recview);
        recyclerview.setLayoutManager(new Ebook.WrapContentLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        FirebaseRecyclerOptions<EbookModel> options=
                new FirebaseRecyclerOptions.Builder<EbookModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ebook"), EbookModel.class)
                        .build();
        adapter = new EbookAdapter(options);
        adapter.startListening();
        recyclerview.setAdapter(adapter);
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView ebookname;
        TextView authorname;
        ImageView img;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.ebookname=itemView.findViewById(R.id.coursename);
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
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(getApplicationContext(), course.class);
        startActivity(intent);
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

        getMenuInflater().inflate(R.menu.optionmenu,menu);

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
        FirebaseRecyclerOptions<EbookModel> options =
                new FirebaseRecyclerOptions.Builder<EbookModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ebook").orderByChild("ebookname").startAt(s).endAt(s+"\uf8ff"), EbookModel.class)
                        .build();

        adapter=new EbookAdapter(options);
        adapter.startListening();
        recyclerview.setAdapter(adapter);

    }



}