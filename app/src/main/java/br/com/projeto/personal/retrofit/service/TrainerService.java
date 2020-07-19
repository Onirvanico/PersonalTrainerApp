package br.com.projeto.personal.retrofit.service;


import java.util.List;

import br.com.projeto.personal.model.Professor;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TrainerService {

    @GET("/v1/trainers")
    Call<List<Professor>> buscaProfessores();
}
