
package com.example.pieski;

import retrofit2.Call;
import retrofit2.http.GET;


public interface DogApi {

    @GET("api/breeds/image/random")
    Call<DogImage> getRandomDog();



}