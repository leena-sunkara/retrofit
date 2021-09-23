package com.example.getlistofalbums;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<ResponseModel> responseList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        setRecyclerView();
        callApi();
    }

    private void callApi() {
        ApiService apiService = Network.getInstance().create(ApiService.class);
        apiService.getData().enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {
                if (response.body() != null) {
                    albumAdapter.updateData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to get the data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerView() {
        albumAdapter = new AlbumAdapter(responseList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(albumAdapter);
    }
}