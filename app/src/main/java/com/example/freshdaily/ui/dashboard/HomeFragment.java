package com.example.freshdaily.ui.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.freshdaily.API.apinterface;
import com.example.freshdaily.API.retrofit;
import com.example.freshdaily.R;
import com.example.freshdaily.searchActivity;
import com.example.freshdaily.ui.dashboard.verticalProductList.adpaterCat;
import com.example.freshdaily.ui.dashboard.verticalProductList.adpaterProduct;
import com.example.freshdaily.ui.dashboard.verticalProductList.modelCat;
import com.example.freshdaily.ui.dashboard.verticalProductList.modelProduct;
import com.google.gson.JsonObject;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.freshdaily.ui.dashboard.verticalProductList.adpaterProduct.dburl;

public class HomeFragment extends Fragment {
    SliderLayout sliderLayout;
    View view;
    EditText search;
    LottieAnimationView lottieAnimationView;
    public static List<modelCat> modelCats;
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        sliderLayout = view.findViewById(R.id.imageSliderHome);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1);
        setSliderViews();
        lottieAnimationView = view.findViewById(R.id.animation_view);
        search = view.findViewById(R.id.searchTextbox);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), searchActivity.class));
            }
        });
        lottieAnimationView.setVisibility(View.VISIBLE);
        productLoad();
        catLoad();
//        List<modelProduct> modelProducts = new ArrayList<>();
//         modelProducts.add(new modelProduct("chaishaktibig.jpg","TAZAAA","200","50ml","amul"));
////        modelProducts.add(new modelProduct());
////        modelProducts.add(new modelProduct());
////        modelProducts.add(new modelProduct());
////        modelProducts.add(new modelProduct());
////        modelProducts.add(new modelProduct());
////        modelProducts.add(new modelProduct());
////        modelProducts.add(new modelProduct());
////        modelProducts.add(new modelProduct());
//        adpaterProduct adapter = new adpaterProduct(modelProducts);
//        RecyclerView recyclerView = view.findViewById(R.id.Verticalproduct);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
//        recyclerView.setAdapter(adapter);


//        List<modelCat> modelCats = new ArrayList<>();
//        modelCats.add(new modelCat());
//        modelCats.add(new modelCat());
//        modelCats.add(new modelCat());
//        modelCats.add(new modelCat());
//        modelCats.add(new modelCat());
//        modelCats.add(new modelCat());
//        modelCats.add(new modelCat());
//        modelCats.add(new modelCat());
//        modelCats.add(new modelCat());
//        adpaterCat adapter2 = new adpaterCat(modelCats);
//        RecyclerView recyclerView2 = view.findViewById(R.id.gridproduct);
//        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(),3));
//        recyclerView2.setAdapter(adapter2);
        return view;
    }

    private void catLoad() {
        apinterface api = retrofit.getapi();
        RequestBody cat = RequestBody.create(MediaType.parse("multipart/form-data"),"milk");
        Call<Object> call = api.getproduct(cat);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                try {
                    JSONArray jsonArray = new JSONArray(response.body().toString());
                    modelCats = new ArrayList<>();
                    for(int i = 0 ; i< jsonArray.length();i++)
                    {
                        modelCats.add(new modelCat(jsonArray.getJSONObject(i).getString("category_image"),
                                jsonArray.getJSONObject(i).getString("Category_name")
                                ));
                    }
                    adpaterCat adapter2 = new adpaterCat(modelCats,getContext());
                    RecyclerView recyclerView2 = view.findViewById(R.id.gridproduct);
                    recyclerView2.setLayoutManager(new GridLayoutManager(getContext(),3));
                    recyclerView2.setAdapter(adapter2);
                    lottieAnimationView.setVisibility(View.INVISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    private void productLoad() {
        apinterface api = retrofit.getapi();
        Call<Object> call = api.getAllProduct();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                try {
                    JSONArray jsonArray = new JSONArray(response.body().toString());
                    List<modelProduct> modelProducts = new ArrayList<>();
                    for(int i = 0 ; i< jsonArray.length();i++)
                    {
                        Log.d(jsonArray.getJSONObject(i).getString("photo"),"mohit");
                        modelProducts.add(new modelProduct(
                                jsonArray.getJSONObject(i).getString("photo"),
                                jsonArray.getJSONObject(i).getString("product_name"),
                                jsonArray.getJSONObject(i).getString("price"),
                                jsonArray.getJSONObject(i).getString("quantity"),
                                jsonArray.getJSONObject(i).getString("company_name")));
                    }
                    adpaterProduct adapter = new adpaterProduct(modelProducts,getContext());
                    RecyclerView recyclerView = view.findViewById(R.id.Verticalproduct);
                    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
                    recyclerView.setAdapter(adapter);
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                } catch (JSONException e) {
                    Toast.makeText(getContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                }


            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                lottieAnimationView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setSliderViews() {
        for (int i = 0; i <= 2; i++) {


            DefaultSliderView sliderView = new DefaultSliderView(getContext());


            switch (i) {
                case 0:
                    sliderView.setImageUrl("http://18.213.183.26/assets/images/products/chaishaktibig.jpg");
                    break;
                case 1:
                    sliderView.setImageUrl("http://18.213.183.26/assets/images/products/gold.png");
                    break;
                case 2:
                    sliderView.setImageUrl("http://18.213.183.26/assets/images/products/amulslimandtrim.jpg");
                    break;

            }
            sliderLayout.addSliderView(sliderView);

        }
    }
}
