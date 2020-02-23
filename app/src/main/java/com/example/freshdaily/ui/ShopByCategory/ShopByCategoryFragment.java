package com.example.freshdaily.ui.ShopByCategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.R;
import com.example.freshdaily.ui.dashboard.verticalProductList.adpaterCat;
import com.example.freshdaily.ui.dashboard.verticalProductList.modelCat;

import java.util.ArrayList;
import java.util.List;

public class ShopByCategoryFragment extends Fragment {

    private ShopByCategoryViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(ShopByCategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        List<modelCat> modelCats = new ArrayList<>();
        modelCats.add(new modelCat());
        modelCats.add(new modelCat());
        modelCats.add(new modelCat());
        modelCats.add(new modelCat());
        modelCats.add(new modelCat());
        modelCats.add(new modelCat());
        modelCats.add(new modelCat());
        modelCats.add(new modelCat());
        modelCats.add(new modelCat());
        adpaterCat adapter2 = new adpaterCat(modelCats);
        RecyclerView recyclerView2 = root.findViewById(R.id.gridproduct);
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView2.setAdapter(adapter2);
        return root;
    }
}