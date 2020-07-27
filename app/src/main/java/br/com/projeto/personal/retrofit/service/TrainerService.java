package br.com.projeto.personal.retrofit.service;


import java.util.List;

import br.com.projeto.personal.model.Professor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TrainerService {

    @GET("/v1/trainers")
    Call<List<Professor>> buscaProfessores();

    @GET("/v1/trainers/{id}")
    Call<Professor> buscaProfessorPorId(@Path("id") String id);

    @DELETE("/v1/trainers/{id}")
    Call<Void> removeProfessorPorId(@Path("id") String id);

    @PUT("/v1/trainers/{id}")
    Call<Professor> altera(@Path("id") String id, @Body Professor professor);

    @POST("/v1/trainers")
    Call<Professor> salvaTrainer(@Body Professor professor);
}
