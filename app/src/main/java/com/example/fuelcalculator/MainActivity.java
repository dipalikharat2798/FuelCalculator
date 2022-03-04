package com.example.fuelcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.fuelcalculator.Fragments.CalculateDistanceFragment;
import com.example.fuelcalculator.Fragments.CalculateFuelFragment;

public class MainActivity extends AppCompatActivity {

    Button fuel_btn,distance_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fuel_btn=findViewById(R.id.fuel_btn);
        distance_btn=findViewById(R.id.distance_btn);

        CalculateFuelFragment calculateFuelFragment=new CalculateFuelFragment();
        replaceFragment(calculateFuelFragment);

        fuel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(calculateFuelFragment);

            }
        });

        distance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new CalculateDistanceFragment());

            }
        });




    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }


}