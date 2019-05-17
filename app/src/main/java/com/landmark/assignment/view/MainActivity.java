
package com.landmark.assignment.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.landmark.assignment.R;
import com.landmark.assignment.helpers.ConfigHelper;
import com.landmark.assignment.services.DownloadConfigService;
import com.landmark.assignment.viewmodel.DataViewModel;
import com.landmark.assignment.databinding.ActivityMainBinding;

import org.json.JSONException;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;


public class MainActivity extends AppCompatActivity {
    private DataViewModel dataViewModel;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dataViewModel.setUp();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = bind();
        initRecyclerView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ConfigHelper.getConfig() == null) {
            if (!DownloadConfigService.isIntentServiceRunning) {
                Intent servIntent = new Intent(this, DownloadConfigService.class);
                servIntent.putExtra("messenger", new Messenger(handler));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(servIntent);
                } else {
                    startService(servIntent);
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataViewModel.tearDown();
    }

    private View bind() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dataViewModel = new DataViewModel();
        binding.setViewModel(dataViewModel);
        return binding.getRoot();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), VERTICAL));
    }
}
