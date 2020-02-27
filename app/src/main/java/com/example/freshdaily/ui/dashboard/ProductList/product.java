package com.example.freshdaily.ui.dashboard.ProductList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.freshdaily.API.apinterface;
import com.example.freshdaily.API.retrofit;
import com.example.freshdaily.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class product extends AppCompatActivity {

    String category;
    RecyclerView recyclerView;
    TextView head;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        recyclerView = findViewById(R.id.productRecycleview);
        lottieAnimationView = findViewById(R.id.animation_view);
        lottieAnimationView.setVisibility(View.VISIBLE);
        category = getIntent().getStringExtra("category");
        apinterface api = retrofit.getapi();
        RequestBody cat = RequestBody.create(MediaType.parse("multipart/form-data"),category.replace('_',' '));
        Call<Object> call = api.getproduct(cat);
        head = findViewById(R.id.head);
        head.setText(category);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                //Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response.body().toString());
                    List<modelProduct> modelProducts = new ArrayList<>();
                    for(int i = 0 ; i< jsonArray.length();i++)
                    {

                        Log.d(jsonArray.getJSONObject(i).getString("photo"),"mohit");
                        modelProducts.add(new modelProduct(
                                jsonArray.getJSONObject(i).getString("product_id"),
                                jsonArray.getJSONObject(i).getString("photo"),
                                jsonArray.getJSONObject(i).getString("product_name"),
                                jsonArray.getJSONObject(i).getString("price"),
                                jsonArray.getJSONObject(i).getString("quantity"),
                                jsonArray.getJSONObject(i).getString("company_name")));
                    }
                    adpaterProduct adapter = new adpaterProduct(modelProducts,getApplicationContext(),getParent());
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                    recyclerView.setAdapter(adapter);
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                } catch (JSONException e) {
                   Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });



    }
}
