package com.example.fuelcalculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelcalculator.R;


public class CalculateFuelFragment extends Fragment {
    EditText trip_distance_edt, fuel_eff_edt;
    Button calculate_btn, clear_btn;
    TextView result_tv;
    Spinner trip_distance_spinner, fuel_eff_spinner;

    public CalculateFuelFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate_fuel, container, false);

        trip_distance_edt = view.findViewById(R.id.trip_distance_edt);
        fuel_eff_edt = view.findViewById(R.id.fuel_eff_edt);
        calculate_btn = view.findViewById(R.id.calculate_btn);
        clear_btn = view.findViewById(R.id.clear_btn);
        result_tv = view.findViewById(R.id.result_tv);
        trip_distance_spinner = view.findViewById(R.id.trip_distance_spinner);
        fuel_eff_spinner = view.findViewById(R.id.fuel_eff_spinner);

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trip_distance_edt.getText().toString().matches("") || fuel_eff_edt.getText().toString().matches("")
                ) {
                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    double tripDistance = Double.parseDouble(trip_distance_edt.getText().toString());
                    double fuelEfficiency = Double.parseDouble(fuel_eff_edt.getText().toString());
                    int item1 = trip_distance_spinner.getSelectedItemPosition();
                    int item2 = fuel_eff_spinner.getSelectedItemPosition();
                    evaluate(item1, item2, tripDistance, fuelEfficiency);
                    Log.d("TAG", "onClick: " + item1 + item2);
                }
            }
        });

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trip_distance_edt.setText("");
                fuel_eff_edt.setText("");
                result_tv.setText("");
            }
        });

        return view;
    }

    private void evaluate(int item1, int item2, double tripDistance, double fuelEfficiency) {
        int dist = item1;
        int eff = item2;
        if (dist == 0) {
            if (eff == 0) {
                double required_ltr = tripDistance / fuelEfficiency; //gallon
                result_tv.setText((required_ltr/0.26417) + " liters (or) \n"+required_ltr+" gallons");
            } else {
                double required_ltr = (tripDistance*1.609) / fuelEfficiency; //liters
                result_tv.setText(required_ltr + " liters (or) \n"+(required_ltr*0.26417)+" gallons");
            }
        } else {
            if (eff == 0) {
                double required_ltr = (tripDistance/1.609) / fuelEfficiency; //gallon
                result_tv.setText((required_ltr/0.26417) + " liters (or) \n"+required_ltr+" gallons");
            } else {
                double required_ltr = (tripDistance * fuelEfficiency); //liters
                result_tv.setText(required_ltr + " liters (or) \n"+(required_ltr*0.26417)+" gallons");
            }
        }
    }
}