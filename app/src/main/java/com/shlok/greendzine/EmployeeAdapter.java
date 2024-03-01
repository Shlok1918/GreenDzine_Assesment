package com.shlok.greendzine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private List<Employee> employees;
    private List<Employee> filteredEmployees; // Add a separate list for filtered employees
    private Context context;

    public EmployeeAdapter(List<Employee> employees, Context context) {
        this.employees = employees;
        this.filteredEmployees = new ArrayList<>(employees); // Initialize filteredEmployees with all employees
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = filteredEmployees.get(position); // Use filteredEmployees list for binding
        holder.textName.setText(employee.getName());
        holder.textDob.setText( employee.getDob());
        holder.textRole.setText(employee.getRole());
    }

    @Override
    public int getItemCount() {
        return filteredEmployees.size(); // Return size of filteredEmployees list
    }

    // Method to filter the employeeList based on search query
    public void filterList(List<Employee> filteredList) {
        this.filteredEmployees = filteredList;
        notifyDataSetChanged(); // Notify RecyclerView to refresh with filtered data
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textDob, textRole;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textDob = itemView.findViewById(R.id.textDob);
            textRole = itemView.findViewById(R.id.textRole);
        }
    }
}
