package com.example.mahmudinm.androidcodeignitercrud.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mahmudinm on 20/12/2018.
 */

public class Retroserver {

//    public static final String base_url = "https://10a5d41b.ngrok.io/index" +
//            ".php/android_codeigniter_crud/";
    public static final String base_url = "https://b56f9618.ngrok.io/android_crud_retrofit/api/";
//   public static final String base_url = "https://10a5d41b.ngrok.io/hakiki95tutorial/";

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
