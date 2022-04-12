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
import com.example.sharemarket.course;
import com.example.sharemarket.model.CourseModel;
import com.example.sharemarket.model.EbookModel;
import com.example.sharemarket.pdfviewer;
import com.example.sharemarket.youtubePlaylist;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.annotations.NotNull;
import com.squareup.picasso.Picasso;

public class EbookAdapter extends FirebaseRecyclerAdapter<EbookModel,EbookAdapter.MyViewHolder> {

    public EbookAdapter(@NonNull FirebaseRecyclerOptions<EbookModel> options) { super(options);




    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull EbookModel model)
    {
        holder.ebookname.setText(model.getEbookname());
        holder.authorname.setText(model.getAuthorname());
        Picasso.get().load(model.getEbookimage()).into(holder.ebookimage);
        holder.itemView.setOnClickListener(v->
        {
            Intent intent=new Intent(holder.itemView.getContext(), pdfviewer.class);
//            intent.putExtra("bookid",model.getBookid());
//            intent.putExtra("authorname",model.getAuthorname());
            intent.putExtra("ebookurl",model.getEbookurl());
            holder.itemView.getContext().startActivity(intent);
            holder.authorname.getContext().startActivity(intent);
        });
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new EbookAdapter.MyViewHolder(v);
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView ebookname;
        ImageView ebookimage;
        TextView authorname;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.ebookname=itemView.findViewById(R.id.coursename);
            this.ebookimage=itemView.findViewById(R.id.image);
            this.authorname=itemView.findViewById(R.id.authorname);
        }
    }


}


