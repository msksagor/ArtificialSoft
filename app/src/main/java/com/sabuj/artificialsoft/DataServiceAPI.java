package com.sabuj.artificialsoft;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataServiceAPI {
    @GET("bins/15baeq")
    Call<DataModel>getResponse();
}
