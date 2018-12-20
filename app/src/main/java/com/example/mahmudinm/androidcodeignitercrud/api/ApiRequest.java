package com.example.mahmudinm.androidcodeignitercrud.api;

import com.example.mahmudinm.androidcodeignitercrud.api.response.ItemListResponse;
import com.example.mahmudinm.androidcodeignitercrud.api.response.StatusResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Mahmudinm on 20/12/2018.
 */

public interface ApiRequest {

    @GET("item")
    Call<ItemListResponse> getItem();

    @FormUrlEncoded
    @POST("item")
    Call<StatusResponse> postItem(@Field("nama") String nama,
                                  @Field("harga") String harga);

    @FormUrlEncoded
    @PUT("item")
    Call<StatusResponse> putItem(@Field("id") String id,
                                 @Field("nama") String nama,
                                 @Field("harga") String harga);
    @FormUrlEncoded
    @DELETE("item")
    Call<StatusResponse> deleteItem(@Field("id") String id);

}
