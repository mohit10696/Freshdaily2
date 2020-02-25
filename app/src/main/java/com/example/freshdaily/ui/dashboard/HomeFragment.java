package com.example.freshdaily.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.smarteist.autoimageslider.SliderLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    SliderLayout sliderLayout;
    View view;
    EditText search;
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
        search = view.findViewById(R.id.searchTextbox);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), searchActivity.class));
            }
        });

        productLoad();

//        List<modelProduct> modelProducts = new ArrayList<>();
//        modelProducts.add(new modelProduct());
//        modelProducts.add(new modelProduct());
//        modelProducts.add(new modelProduct());
//        modelProducts.add(new modelProduct());
//        modelProducts.add(new modelProduct());
//        modelProducts.add(new modelProduct());
//        modelProducts.add(new modelProduct());
//        modelProducts.add(new modelProduct());
//        modelProducts.add(new modelProduct());
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

    private void productLoad() {
        apinterface api = retrofit.getapi();
        Call<Object> call = api.getAllProduct();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(getContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                try {
                    JSONObject myResponse = new JSONObject(response.body().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSliderViews() {
        for (int i = 0; i <= 2; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            sliderView.setImageDrawable(R.drawable.sliderdump);
            sliderLayout.addSliderView(sliderView);
        }
    }
}
