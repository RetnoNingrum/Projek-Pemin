package com.example.dretnon.android.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dretnon.android.Model.Ruang;
import com.example.dretnon.android.R;
import com.example.dretnon.android.Rest.ApiClient;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahRuang extends AppCompatActivity {
    EditText tbhIdRuang, tbhRuang;
    Button btnTambahRuang, btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_ruang);

        btnTambahRuang = (Button) findViewById(R.id.btnTambahFilm);
        btnKembali = (Button) findViewById(R.id.btnKembali);
        tbhIdRuang = (EditText) findViewById(R.id.tbhIdRuang);
        tbhRuang = (EditText) findViewById(R.id.tbhRuang);

        btnTambahRuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahRuang();
            }
        });

//        btnKembali.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HomeActivity.onRefresh();
//                finish();
//            }
//        });
    }

    private void tambahRuang(){
        btnTambahRuang.setVisibility(View.INVISIBLE);

        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();

        Call<Ruang> call = ApiClient.getInstance().getApi().createRuang(tbhIdRuang.getText().toString()
        , tbhRuang.getText().toString());

        call.enqueue(new Callback<Ruang>() {
            @Override
            public void onResponse(Call<Ruang> call, Response<Ruang> response) {
                btnTambahRuang.setVisibility(View.VISIBLE);
                Toast.makeText(TambahRuang.this, "Data berhasil bitambahkan", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Ruang> call, Throwable t) {
                btnTambahRuang.setVisibility(View.INVISIBLE);
                Toast.makeText(TambahRuang.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
