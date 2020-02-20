package com.example.freshdaily.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.freshdaily.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class mydashboard extends Fragment {
    BottomNavigationView BottomNavigation;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       root  = inflater.inflate(R.layout.fragment_maindashboard, container, false);
        BottomNavigation = root.findViewById(R.id.navigation);
        BottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);



        return root;
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            openFragment(HomeFragment.newInstance("", ""));
                            return true;

                        case R.id.chat:
                            openFragment(ContactFragment.newInstance("", ""));
                            return true;



                    }
                    return false;
                }
            };
}
