package br.com.projeto.personal.retrofit;

import br.com.projeto.personal.retrofit.service.TrainerService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TrainerRetrofit {

    private final TrainerService trainerService;

    public TrainerRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.7:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        trainerService = retrofit.create(TrainerService.class);
    }

   public TrainerService getTrainerService() {
        return trainerService;
   }
}
