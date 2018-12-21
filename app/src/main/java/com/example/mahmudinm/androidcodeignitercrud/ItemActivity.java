package com.example.mahmudinm.androidcodeignitercrud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmudinm.androidcodeignitercrud.api.ApiRequest;
import com.example.mahmudinm.androidcodeignitercrud.api.Retroserver;
import com.example.mahmudinm.androidcodeignitercrud.api.response.StatusResponse;
import com.example.mahmudinm.androidcodeignitercrud.model.Item;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemActivity extends AppCompatActivity {

    EditText txtNama, txtHarga;
    Button save, update, delete;
    ProgressDialog progressDialog;

    private String refreshFlag = "0";
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNama = (EditText) findViewById(R.id.txtNama);
        txtHarga = (EditText) findViewById(R.id.txtHarga);
        save = (Button) findViewById(R.id.save);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);

        Intent intent = getIntent();
        if (intent.hasExtra("item")) {

            Item item = new Item();
            item = (Item) intent.getSerializableExtra("item");

            save.setVisibility(View.GONE);
            update.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);

            id = item.getId();
            txtNama.setText(item.getNama());
            txtHarga.setText(item.getHarga());

        }

        progressDialog = new ProgressDialog(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Loading ...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                refreshFlag = "1";

                String nama = txtNama.getText().toString();
                String harga = txtHarga.getText().toString();

                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                Call<StatusResponse> postItem = api.postItem(nama, harga);
                postItem.enqueue(new Callback<StatusResponse>() {
                    @Override
                    public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                        progressDialog.hide();
                        String status = response.body().getStatus();

                        if (status.equals("success")) {
                            Toast.makeText(ItemActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ItemActivity.this, "Data gagal disimpan", Toast
                                    .LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<StatusResponse> call, Throwable t) {

                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Loading ...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                refreshFlag = "1";
                String nama = txtNama.getText().toString();
                String harga = txtHarga.getText().toString();

                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                Call<StatusResponse> putItem = api.putItem(id, nama, harga);
                putItem.enqueue(new Callback<StatusResponse>() {
                    @Override
                    public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                        progressDialog.dismiss();
                        String status = response.body().getStatus();
                        if (status.equals("success")) {
                            Toast.makeText(ItemActivity.this, "Berhasil Update", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ItemActivity.this, "Gagal Update", Toast.LENGTH_SHORT)
                                    .show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(ItemActivity.this, "Error Update", Toast.LENGTH_SHORT)
                                .show();
                        finish();

                    }
                });

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Loading ...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                refreshFlag = "1";

                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                Call<StatusResponse> deleteItem = api.deleteItem(id);
                deleteItem.enqueue(new Callback<StatusResponse>() {
                    @Override
                    public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                        progressDialog.dismiss();
                        String status = response.body().getStatus();
                        if (status.equals("success")) {
                            Toast.makeText(ItemActivity.this, "Berhasil Delete", Toast.LENGTH_SHORT)
                                    .show();
                            finish();
                        } else {
                            Toast.makeText(ItemActivity.this, "Gagal Delete", Toast.LENGTH_SHORT)
                                    .show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(ItemActivity.this, "Error Delete", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

            }
        });

    }

    @Override
    public void finish() {
        System.gc();
        Intent data = new Intent();
        data.putExtra("refreshFlag", refreshFlag);

        setResult(RESULT_OK, data);

        super.finish();
    }
}
