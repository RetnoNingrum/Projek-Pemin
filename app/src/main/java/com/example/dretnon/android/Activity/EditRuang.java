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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditRuang extends AppCompatActivity {
    Bundle bundle;
    EditText edtIdRuang, edtRuang;
    Button btnSimpan;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ruang);

        edtIdRuang = (EditText) findViewById(R.id.edtIdRuang);
        edtRuang = (EditText) findViewById(R.id.edtRuang);
        btnSimpan = (Button) findViewById(R.id.btnSimpanFilm);

        edtIdRuang.setText(bundle.getString("idRuang"));
        edtRuang.setText(bundle.getString("Ruang"));

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editRuang();
            }
        });
    }

    private void editRuang(){
        btnSimpan.setVisibility(View.INVISIBLE);

        Call<Ruang> call2 = ApiClient.getInstance().getApi().updateRuang(bundle.getString("idRuang"),
                edtRuang.getText().toString());

        call2.enqueue(new Callback<Ruang>(){

            @Override
            public void onResponse(Call<Ruang> call, Response<Ruang> response) {
                Toast.makeText(EditRuang.this, "Data berhasil diperbarui", Toast.LENGTH_LONG).show();
                btnSimpan.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Ruang> call, Throwable t) {
                btnSimpan.setVisibility(View.VISIBLE);
                Toast.makeText(EditRuang.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
