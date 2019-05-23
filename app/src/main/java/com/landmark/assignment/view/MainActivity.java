
package com.landmark.assignment.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import com.landmark.assignment.model.APIResponse;
import com.landmark.assignment.R;
import com.landmark.assignment.adapter.ProductAdapter;
import com.landmark.assignment.viewmodel.ProductViewModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ProductViewModel dataViewModel;
    private ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productAdapter = new ProductAdapter(this, new ArrayList<APIResponse.Products>());
//        RecyclerView recyclerView = findViewById(R.id.data_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), VERTICAL));
//        recyclerView.setAdapter(productAdapter);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(productAdapter);

        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getProducts().observe(this, (products) -> {
            Log.d("data changes in product", "ON_CHANGED");
            productAdapter.setListdata(products);
            productAdapter.notifyDataSetChanged();
        });
    }
}
