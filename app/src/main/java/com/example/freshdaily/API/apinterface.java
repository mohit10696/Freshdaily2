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

    @Multipart
    @POST("updateprofile.php")
    Call<String> updateaddress(
            @Part("users_id") RequestBody user_id,
            @Part("landmark") RequestBody landmark,
            @Part("address") RequestBody address
    );

    @Multipart
    @POST("addsubscription.php")
    Call<Object> addSubscription(
            @Part("users_id") RequestBody users_id,
            @Part("product_id") RequestBody product_id,
            @Part("quantity") RequestBody quantity,
            @Part("type") RequestBody type,
            @Part("sdate") RequestBody sdate
    );

    @Multipart
    @POST("checkvacation.php")
    Call<String> checkvaction(
            @Part("users_id") RequestBody users_id
    );

    @Multipart
    @POST("checkresumedate.php")
    Call<String> checkResumeDate(
            @Part("userid") RequestBody userid
    );

    @Multipart
    @POST("pausedate.php")
    Call<Object> pausedate(
            @Part("users_id") RequestBody users_id,
            @Part("startdate") RequestBody startdate
    );


    @Multipart
    @POST("addpausedate.php")
    Call<Object> addpausedate(
            @Part("users_id") RequestBody users_id,
            @Part("pdate") RequestBody pdate
    );
}
