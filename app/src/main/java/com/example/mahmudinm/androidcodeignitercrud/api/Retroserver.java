package com.example.mahmudinm.androidcodeignitercrud.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mahmudinm on 20/12/2018.
 */

public class Retroserver {

    public static final String base_url = "https://e2762f8e.ngrok.io/android_codeigniter_crud/api/";

    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
