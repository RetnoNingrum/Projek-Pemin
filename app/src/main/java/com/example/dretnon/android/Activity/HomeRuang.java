package com.example.dretnon.android.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dretnon.android.Model.Ruang;
import com.example.dretnon.android.R;
import com.example.dretnon.android.Rest.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRuang extends AppCompatActivity {

    TextView idRuang, ruang;
    Button btnHapus, btnEdit;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ruang);

        bundle = getIntent().getExtras();

        idRuang = (TextView) findViewById(R.id.tvIdRuang);
        ruang = (TextView) findViewById(R.id.tvRuang);

        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnHapus = (Button) findViewById(R.id.btnHapus);

        idRuang.setText(bundle.getString("idRuang"));
        ruang.setText(bundle.getString("ruang"));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeRuang.this, EditRuang.class);
                Bundle bundle2 = new Bundle();

                bundle2.putString("id ruang", bundle.getString("idRuang"));
                bundle2.putString("ruang", bundle.getString("ruang"));

                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapusRuang();
            }
        });
    }

    private void hapusRuang() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeRuang.this);
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                deleteRuang();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteRuang() {
        Call<Ruang> call = ApiClient.getInstance().getApi().deleteRuang(bundle.getString("idRuang"));

        call.enqueue(new Callback<Ruang>() {
            @Override
            public void onResponse(Call<Ruang> call, Response<Ruang> response) {
                Toast.makeText(HomeRuang.this, "Ruang berhasil dihapus", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Ruang> call, Throwable t) {
                Toast.makeText(HomeRuang.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
