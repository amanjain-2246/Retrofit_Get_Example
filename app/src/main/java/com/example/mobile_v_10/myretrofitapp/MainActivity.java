package com.example.mobile_v_10.myretrofitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity{
    Retrofit client;
    DogsApiInterface service;
    ImageView imageView;
    ListView namelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namelist=(ListView)findViewById(R.id.list);
        imageView = (ImageView) findViewById(R.id.imageView);
        client=RestClient.getClient();
        service=client.create(DogsApiInterface.class);
        service.getDogs().enqueue(new Callback<DogName>() {
            @Override
            public void onResponse(Call<DogName> call, Response<DogName> response) {
                //Toast.makeText(MainActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
               final ArrayList arr= (ArrayList) response.body().getMessage();
                ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arr);
                namelist.setAdapter(adapter);
                namelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        service.getRandomBreedImage(arr.get(position).toString()).enqueue(new Callback<DogImage>() {
                            @Override
                            public void onResponse(Call<DogImage> call, Response<DogImage> response) {
                               // Toast.makeText(MainActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                               Glide.with(MainActivity.this).load(response.body().getMessage()).into(imageView);
                            }

                            @Override
                            public void onFailure(Call<DogImage> call, Throwable t) {

                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(Call<DogName> call, Throwable t) {

            }
        });
      /*  service.getRandomBreedImage("boxer").enqueue(new Callback<DogImage>() {
            @Override
            public void onResponse(Call<DogImage> call, Response<DogImage> response) {
                Toast.makeText(MainActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DogImage> call, Throwable t) {

            }
        });     */
    }
}
