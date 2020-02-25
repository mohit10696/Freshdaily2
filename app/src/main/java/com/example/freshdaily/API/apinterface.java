package com.example.freshdaily.API;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface apinterface {
    String JSONURL = "http://18.213.183.26/webservice/";
    @Multipart
    @POST("login.php")
    Call<Object> getUserLogin(
            @Part("number") RequestBody number
    );

    @POST("getAllproduct.php")
    Call<Object> getAllProduct();

}
