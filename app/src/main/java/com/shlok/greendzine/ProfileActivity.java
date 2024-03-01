package com.shlok.greendzine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private List<Employee> employeeList;
    private List<Employee> filteredList;
    private EmployeeAdapter adapter;
    private RecyclerView recyclerView;
    private SearchView searchView;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        // Initialize lists
        employeeList = new ArrayList<>();
        filteredList = new ArrayList<>();

        // Set up RecyclerView
        adapter = new EmployeeAdapter(employeeList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Load JSON data from assets
        loadJSONFromAsset();

        // Search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.profile) {
                adapter.filterList(employeeList);
                // Handle Home menu item click
                return true;
            } else if (itemId == R.id.home) {
                // Handle Our Team menu item click
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                finish();
                return true;
            }
            return false;
        });
    }

    // Load JSON data from assets folder
    private void loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("json_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            parseJson(json);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Parse JSON data and populate employeeList
    private void parseJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("employee");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject empObject = jsonArray.getJSONObject(i);
                int id = empObject.getInt("id");
                String name = empObject.getString("name");
                String dob = empObject.getString("dob");
                String role = empObject.getString("role");
                employeeList.add(new Employee(id, name, dob, role));
            }
            // Add all employees to filteredList initially
            filteredList.addAll(employeeList);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Filter employeeList based on search query
    private void filter(String query) {
        filteredList.clear();
        for (Employee employee : employeeList) {
            if (employee.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(employee);
            }
        }
        adapter.filterList(filteredList);
    }
}
