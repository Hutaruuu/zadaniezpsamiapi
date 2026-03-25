package com.example.pieski;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView dogImage;
    Button btnNext, btnPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dogImage = findViewById(R.id.dogImage);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);

        loadDog(); // pierwszy pies

        btnNext.setOnClickListener(v -> loadDog());
        btnPrev.setOnClickListener(v -> loadDog());
    }

    private void loadDog() {
        DogApi dogApi = RetrofitClass.RetrofitClient
                .getClient()
                .create(DogApi.class);

        Call<DogImage> call = dogApi.getRandomDog();

        call.enqueue(new Callback<DogImage>() {
            @Override
            public void onResponse(Call<DogImage> call, Response<DogImage> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String imageUrl = response.body().getMessage();

                    Glide.with(MainActivity.this)
                            .load(imageUrl)
                            .into(dogImage);

                    Log.d("DogAPI", "URL: " + imageUrl);
                }
            }

            @Override
            public void onFailure(Call<DogImage> call, Throwable t) {
                Log.e("DogAPI", "Błąd", t);
            }
        });
    }
}