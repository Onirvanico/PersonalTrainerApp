package br.com.projeto.personal.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.projeto.personal.retrofit.service.TrainerService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TrainerRetrofit {

    private final TrainerService trainerService;


    public TrainerRetrofit() {
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.10:3000")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        trainerService = retrofit.create(TrainerService.class);
    }

   public TrainerService getTrainerService() {
        return trainerService;
   }
}
