package com.example.fuelcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText trip_distance_edt, fuel_eff_edt, fuel_price_edt;
    Button calculate_btn, clear_btn;
    TextView result_tv;
    Spinner trip_distance_spinner, fuel_eff_spinner, fuel_price_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trip_distance_edt = findViewById(R.id.trip_distance_edt);
        fuel_eff_edt = findViewById(R.id.fuel_eff_edt);
        fuel_price_edt = findViewById(R.id.fuel_price_edt);
        calculate_btn = findViewById(R.id.calculate_btn);
        clear_btn = findViewById(R.id.clear_btn);
        result_tv = findViewById(R.id.result_tv);
        trip_distance_spinner = findViewById(R.id.trip_distance_spinner);
        fuel_eff_spinner = findViewById(R.id.fuel_eff_spinner);
        fuel_price_spinner = findViewById(R.id.fuel_price_spinner);

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double tripDistance = Double.parseDouble(trip_distance_edt.getText().toString());
                double fuelEfficiency = Double.parseDouble(fuel_eff_edt.getText().toString());
                double fuelPrice = Double.parseDouble(fuel_price_edt.getText().toString());
                int item1 = trip_distance_spinner.getSelectedItemPosition();
                int item2 = fuel_eff_spinner.getSelectedItemPosition();
                int item3 = fuel_price_spinner.getSelectedItemPosition();
                evaluate(item1, item2, item3, tripDistance, fuelEfficiency, fuelPrice);
                Log.d("TAG", "onClick: " + item1 + item2 + item3);
            }
        });

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trip_distance_edt.setText("");
                fuel_eff_edt.setText("");
                fuel_price_edt.setText("");
            }
        });
    }

    private void evaluate(int item1, int item2, int item3, double tripDistance, double fuelEfficiency, double fuelPrice) {
        int dist = item1;
        int eff = item2;
        int price = item3;
        if (dist == 0) {
            if (eff == 0) {
                if (item3 == 0) {
                    double required_ltr = tripDistance / fuelEfficiency; //gallon
                    double fuel_cost = required_ltr * fuelPrice;
                    result_tv.setText(required_ltr + " gallons" + " " + fuel_cost);
                } else {
                    double required_ltr = tripDistance / fuelEfficiency; //gallon
                    double fuel_cost = (required_ltr * 4.546092) * fuelPrice;
                    result_tv.setText((required_ltr * 4.546092) + " liters" + " " + fuel_cost);
                }
            } else {

                if (item3 == 0) {
                    double required_ltr = (tripDistance * fuelEfficiency * 1.609) / 100; //liters
                    double fuel_cost = required_ltr * 0.21996915 * fuelPrice;
                    result_tv.setText(required_ltr * 0.21996915 + " gallons" + " " + fuel_cost);
                } else {
                    double required_ltr = (tripDistance * fuelEfficiency * 1.609) / 100; //liters
                    double fuel_cost = (required_ltr) * fuelPrice;
                    result_tv.setText((required_ltr) + " liters" + " " + fuel_cost);
                }
            }
        } else {
            if (eff == 0) {
                if (item3 == 0) {
                    double required_ltr = tripDistance / (1.6093 * 20); //gallon
                    double fuel_cost = required_ltr * fuelPrice;
                    result_tv.setText(required_ltr + " gallons" + " " + fuel_cost);
                } else {
                    double required_ltr = tripDistance / (1.6093 * 20); //gallon
                    double fuel_cost = (required_ltr * 4.546092) * fuelPrice;
                    result_tv.setText((required_ltr * 4.546092) + " liters" + " " + fuel_cost);
                }
            } else {
                if (item3 == 0) {
                    double required_ltr = (tripDistance * fuelEfficiency) / 100; //liters
                    double fuel_cost = required_ltr * 0.21996915 * fuelPrice;
                    result_tv.setText(required_ltr * 0.21996915 + " gallons" + " " + fuel_cost);
                } else {
                    double required_ltr = (tripDistance * fuelEfficiency) / 100; //liters
                    double fuel_cost = (required_ltr) * fuelPrice;
                    result_tv.setText((required_ltr) + " liters" + " " + fuel_cost);
                }
            }
        }
    }


}