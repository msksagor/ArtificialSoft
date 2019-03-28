package com.sabuj.artificialsoft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sabuj.artificialsoft.MainActivity.baseURL;

public class UserProfile extends AppCompatActivity {
    private DataServiceAPI mDataServiceAPI;
    private SearchResult mSearchResult;
    int idNumber;

    private TextView id,name,who,user;
    private ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        init();

        Call<DataModel> data_from_api = mDataServiceAPI.getResponse();

        data_from_api.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.code() == 200) {
                    DataModel data_response = response.body();

                    if(idNumber>-1){
                        Log.d("Dagorssgdg","recev : "+idNumber);
                        mSearchResult = data_response.getSearchResult().get(idNumber);
                        Log.d("Dagorssgdg","recev : "+mSearchResult.getName());
                        id.setText("ID : "+mSearchResult.getId());
                        user.setText("User : "+mSearchResult.getUser());
                        name.setText("Name : "+mSearchResult.getName());
                        who.setText("Who : "+mSearchResult.getWho());
                        Picasso.get().load(mSearchResult.getImage()).into(userImage);
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });
    }

    private void init(){
        idNumber =  getIntent().getIntExtra("key",-5);

        mSearchResult = new SearchResult();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        mDataServiceAPI = retrofit.create(DataServiceAPI.class);
        id= findViewById(R.id.id);
        user = findViewById(R.id.user);
        name = findViewById(R.id.name);
        who = findViewById(R.id.who);
        userImage = findViewById(R.id.imageView_user_profile);

    }
}
