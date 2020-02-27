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

    @Multipart
    @POST("getProduct.php")
    Call<Object> getproduct(
            @Part("category") RequestBody category
    );

    @Multipart
    @POST("getsingleproduct.php")
    Call<Object> getsingleproduct(
            @Part("product_id") RequestBody productid
    );

    @POST("getAllproduct.php")
    Call<Object> getAllProduct();

    @POST("getAllcat.php")
    Call<Object> getcatProduct();

    @Multipart
    @POST("adduser.php")
    Call<String> adduser(
            @Part("fname") RequestBody fname,
            @Part("lname") RequestBody lanme,
            @Part("number") RequestBody number,
            @Part("email") RequestBody email
    );

}
