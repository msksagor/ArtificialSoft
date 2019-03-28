package com.sabuj.artificialsoft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    String fullURL = "https://api.myjson.com/bins/15baeq";
    public static final String baseURL = "https://api.myjson.com/";
    private DataServiceAPI mDataServiceAPI;
    private ArrayList<DataModelforAdapter> dataModelforAdapters;
    private RecyclerView mRecyclerView;
    private RecAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataModelforAdapters = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recycleView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        mDataServiceAPI = retrofit.create(DataServiceAPI.class);


        Call<DataModel> arrayListCall = mDataServiceAPI.getResponse();

        arrayListCall.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.code() == 200) {
                    DataModel data_response = response.body();
                    int totalData = data_response.getSearchFound();

                    for (int i = 0; i < totalData; i++) {
                        dataModelforAdapters.add(new DataModelforAdapter(data_response.getSearchResult().get(i).getName(), data_response.getSearchResult().get(i).getImage()));

                    }
                    mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    adapter = new RecAdapter(dataModelforAdapters, getApplicationContext());

                    mRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });

    }
}
