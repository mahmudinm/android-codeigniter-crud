package com.example.mahmudinm.androidcodeignitercrud;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mahmudinm.androidcodeignitercrud.adapter.AdapterItem;
import com.example.mahmudinm.androidcodeignitercrud.api.ApiRequest;
import com.example.mahmudinm.androidcodeignitercrud.api.Retroserver;
import com.example.mahmudinm.androidcodeignitercrud.api.response.ItemListResponse;
import com.example.mahmudinm.androidcodeignitercrud.model.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {

    private RecyclerView mRecycler;
    private AdapterItem mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<Item> mItems = new ArrayList<>();
    ProgressDialog progressDialog;

    private static final int REQUEST_CODE_ADD = 1;
    private static final int REQUEST_CODE_EDIT = 2;

    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.fragment_item, container, false);


        progressDialog = new ProgressDialog(getActivity());
        mRecycler = (RecyclerView) x.findViewById(R.id.itemRecycler);
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);

        load();

        return x ;

    }

    private void load() {
        progressDialog.setMessage("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
        Call<ItemListResponse> getItem = api.getItem();
        getItem.enqueue(new Callback<ItemListResponse>() {
            @Override
            public void onResponse(Call<ItemListResponse> call, Response<ItemListResponse> response) {
                progressDialog.hide();
                Log.d("Response", "onResponse: " + response.body().getData());

                Toast.makeText(getActivity(), "berhasil ambil data", Toast.LENGTH_SHORT).show();

                mItems = response.body().getData();

                mAdapter = new AdapterItem(mItems);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ItemListResponse> call, Throwable t) {
                progressDialog.hide();
                Toast.makeText(getActivity(), "gagal ambil data", Toast.LENGTH_SHORT).show();
                Log.d("Error", "onFailure: " );

            }
        });


    }


}
