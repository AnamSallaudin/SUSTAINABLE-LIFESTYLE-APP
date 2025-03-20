package com.example.carbonfootprintcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputDistance, inputElectricity;
    private Spinner spinnerVehicle, spinnerElectricity, spinnerDiet;
    private Button btnCalculate, btnQuiz;
    private TextView textResult;
    private LinearLayout resultContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        inputDistance = findViewById(R.id.input_distance);
        inputElectricity = findViewById(R.id.input_electricity);
        spinnerVehicle = findViewById(R.id.spinner_vehicle);
        spinnerElectricity = findViewById(R.id.spinner_electricity);
        spinnerDiet = findViewById(R.id.spinner_diet);
        textResult = findViewById(R.id.text_result);  // Correct initialization
        resultContainer = findViewById(R.id.result_container);  // Initialize the box container
        btnCalculate = findViewById(R.id.btn_calculate);
        btnQuiz = findViewById(R.id.btn_quiz);

        // Button click listener for Calculate
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCarbonFootprint();
            }
        });

        // Button click listener for Quiz
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    private void calculateCarbonFootprint() {
        String distance = inputDistance.getText().toString();
        String electricityUsage = inputElectricity.getText().toString();

        if (distance.isEmpty() || electricityUsage.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Carbon footprint calculation
        double distanceValue = Double.parseDouble(distance);
        double electricityValue = Double.parseDouble(electricityUsage);

        double carbonFootprint = (distanceValue * 0.12) + (electricityValue * 0.6);

        // Show result in box
        textResult.setText(String.format("Your estimated carbon footprint: %.2f kg CO2/day", carbonFootprint));
        resultContainer.setVisibility(View.VISIBLE);
    }
}
