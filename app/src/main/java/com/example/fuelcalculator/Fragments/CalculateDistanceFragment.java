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

public class CalculateDistanceFragment extends Fragment {
    EditText trip_fuel_edt, fuel_eff_edt;
    Button calculate_btn, clear_btn;
    TextView result_tv;
    Spinner trip_fuel_spinner, fuel_eff_spinner;

    public CalculateDistanceFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculate_distance, container, false);

        trip_fuel_edt = view.findViewById(R.id.trip_fuel_edt);
        fuel_eff_edt = view.findViewById(R.id.fuel_eff_edt);
        calculate_btn = view.findViewById(R.id.calculate_btn);
        clear_btn = view.findViewById(R.id.clear_btn);
        result_tv = view.findViewById(R.id.result_tv);
        trip_fuel_spinner = view.findViewById(R.id.trip_fuel_spinner);
        fuel_eff_spinner = view.findViewById(R.id.fuel_eff_spinner);

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trip_fuel_edt.getText().toString().matches("") || fuel_eff_edt.getText().toString().matches("") ) {
                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    double tripfuel = Double.parseDouble(trip_fuel_edt.getText().toString());
                    double fuelEfficiency = Double.parseDouble(fuel_eff_edt.getText().toString());
                    int item1 = trip_fuel_spinner.getSelectedItemPosition();
                    int item2 = fuel_eff_spinner.getSelectedItemPosition();
                    evaluate(item1, item2, tripfuel, fuelEfficiency);
                    Log.d("TAG", "onClick: " + item1 + item2 );
                }
            }
        });

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trip_fuel_edt.setText("");
                fuel_eff_edt.setText("");
                result_tv.setText("");
            }
        });

        return view;
    }

    private void evaluate(int item1, int item2,double tripfuel, double fuelEfficiency) {
        int fuel_req = item1;
        int eff = item2;
        if (eff == 0) {
            if (fuel_req == 0){
                double res=tripfuel*fuelEfficiency; //miles
                result_tv.setText((res*1.609)+" Kilometers or \n"+res+" Miles");
            }else {
                double res=tripfuel*(fuelEfficiency*0.26417); //miles
                result_tv.setText((res*1.609)+" Kilometers or \n"+res+" Miles");
            }
        }else{
            if (fuel_req == 0){
                double res=tripfuel*(fuelEfficiency/0.26417); //km
                result_tv.setText(res+" Kilometers or \n"+(res/1.609)+" Miles");
            }else {
                double res=tripfuel*fuelEfficiency; //km
                result_tv.setText(res+" Kilometers or \n"+(res/1.609)+" Miles");
            }
        }
    }
}