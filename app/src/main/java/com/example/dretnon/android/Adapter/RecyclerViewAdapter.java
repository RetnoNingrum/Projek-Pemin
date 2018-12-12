package com.example.dretnon.android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dretnon.android.Activity.HomeRuang;
import com.example.dretnon.android.Model.Ruang;
import com.example.dretnon.android.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    Context mContext;
    List<Ruang> mList;


    public RecyclerViewAdapter(Context mContext, List<Ruang> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.ruang_list,parent, false);
        final MyViewHolder vHolder = new MyViewHolder(view);


        vHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, HomeRuang.class);
                Bundle bundle = new Bundle();
                bundle.putString("idRuang", mList.get(vHolder.getAdapterPosition()).getIdRuang());
                bundle.putString("ruang", mList.get(vHolder.getAdapterPosition()).getRuang());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.idRuang.setText(mList.get(position).getIdRuang());
        holder.ruang.setText(mList.get(position).getRuang());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView idRuang, ruang;

        public MyViewHolder(View itemView) {
            super(itemView);

            idRuang = (TextView) itemView.findViewById(R.id.tvIdRuang);
            ruang = (TextView) itemView.findViewById(R.id.tvRuang);

        }
    }
}
