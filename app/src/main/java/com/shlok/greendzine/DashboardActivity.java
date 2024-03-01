package com.shlok.greendzine;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private List<Employee> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardactivity);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                // Handle Home menu item click
                // You can add your logic here for handling the home menu item click
                return true;
            } else if (itemId == R.id.profile) {
                // Handle Profile menu item click
                // Pass the employee list to ProfileActivity
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("employeeList", (Serializable) employeeList);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });

    }
}
