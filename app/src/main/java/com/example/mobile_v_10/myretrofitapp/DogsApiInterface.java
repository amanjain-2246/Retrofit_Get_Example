package com.example.mobile_v_10.myretrofitapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by MOBILE-V-10 on 8/19/2017.
 */

public interface DogsApiInterface {
    @GET("api/breeds/list")
    public Call<DogName> getDogs();

    @GET("api/breed/{breedname}/images/random")
    public Call<DogImage> getRandomBreedImage(@Path("breedname") String name);
}
