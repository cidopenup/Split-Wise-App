package com.example.billapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast; // Import Toast for showing messages
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText amountEdt, peopleEdt, tipEdt;
    private TextView amtTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing variables with ids.
        amountEdt = findViewById(R.id.idEdtAMount);
        peopleEdt = findViewById(R.id.idEdtPeople);
        tipEdt = findViewById(R.id.idEdtTip);
        Button amtBtn = findViewById(R.id.idBtnGetAmount);
        Button resetBtn = findViewById(R.id.idBtnReset);
        amtTV = findViewById(R.id.idTVIndividualAmount);

        // adding click listener for amount button.
        amtBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onClick(View v) {
                // Validate inputs first
                String amountStr = Objects.requireNonNull(amountEdt.getText()).toString().trim();
                String peopleStr = Objects.requireNonNull(peopleEdt.getText()).toString().trim();
                String tipStr = Objects.requireNonNull(tipEdt.getText()).toString().trim();

                if (amountStr.isEmpty() || peopleStr.isEmpty() || tipStr.isEmpty()) {
                    // Display error message if any field is empty
                    Toast.makeText(MainActivity.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Parsing inputs as integers or floats
                    float amount = Float.parseFloat(amountStr);
                    int people = Integer.parseInt(peopleStr);
                    float tip = Float.parseFloat(tipStr);

                    if (people == 0) {
                        // Prevent division by zero
                        Toast.makeText(MainActivity.this, "Number of people cannot be zero.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Calculating individual amount
                    float individualAmt = (amount + tip) / people;

                    // Display result with formatted value
                    amtTV.setText("Individual Amount : \n" + String.format("%.2f", individualAmt));

                } catch (NumberFormatException e) {
                    // Handle invalid number format
                    Toast.makeText(MainActivity.this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // Catch any other unforeseen errors
                    Toast.makeText(MainActivity.this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // adding click listener for reset button.
        resetBtn.setOnClickListener(v -> {
            // Resetting input fields and result
            amountEdt.setText("");
            peopleEdt.setText("");
            tipEdt.setText("");
            amtTV.setText("");
        });
    }
}
