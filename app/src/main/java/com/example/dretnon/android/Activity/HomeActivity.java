package com.example.dretnon.android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dretnon.android.Adapter.RecyclerViewAdapter;
import com.example.dretnon.android.Model.Ruang;
import com.example.dretnon.android.R;
import com.example.dretnon.android.Rest.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerViewAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    List<Ruang> mList;

    ImageButton ib;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ib = (ImageButton) findViewById(R.id.btnTambah);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });


        Call<List<Ruang>> call = ApiClient.getInstance().getApi().getRuang();

        call.enqueue(new Callback<List<Ruang>>() {
            @Override
            public void onResponse(Call<List<Ruang>> call, Response<List<Ruang>> response) {
                mList = response.body();

                mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                adapter = new RecyclerViewAdapter(getApplicationContext(), mList);
                mRecyclerView.setLayoutManager(new GridLayoutManager(HomeActivity.this,3));
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Ruang>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TambahRuang.class);
                startActivity(intent);
            }
        });
    }

    void refreshItems() {
        Call<List<Ruang>> call = ApiClient.getInstance().getApi().getRuang();
        call.enqueue(new Callback<List<Ruang>>() {
            @Override
            public void onResponse(Call<List<Ruang>> call, Response<List<Ruang>> response) {
                mList = response.body();
                adapter = new RecyclerViewAdapter(getApplicationContext(), mList);
                mRecyclerView.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Ruang>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

}
