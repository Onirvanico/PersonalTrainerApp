package br.com.projeto.personal.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.projeto.personal.R;
import br.com.projeto.personal.model.Professor;
import br.com.projeto.personal.retrofit.TrainerRetrofit;
import br.com.projeto.personal.retrofit.service.TrainerService;
import br.com.projeto.personal.ui.activities.recyclerview.ListaDosPersonal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    public static final String PROFESSOR = "professor";
    private RecyclerView recyclerView;
    private List<Professor> professores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_personal);
        // professores = exemploDeLista();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathDadosUsuario = storageRef.child("DadosUsuario");
        StorageReference dadosUsuarioArquivo = storageRef.child("DadosUsuario/arquivo.txt");


      /*  try {

            InputStream fs = new FileInputStream(getFilesDir()+"/arquivo.txt");
            dadosUsuarioArquivo.putStream(fs).addOnFailureListener( exception -> {
                Toast.makeText(this, "Erro ao fazer upload", Toast.LENGTH_LONG).show();
            }).addOnSuccessListener(taskSnapshot -> {
                Toast.makeText(this, "Sucesso do uplado do arquivo", Toast.LENGTH_SHORT).show();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        TrainerService trainerService = new TrainerRetrofit().getTrainerService();

        Professor claudin = new Professor("Pudim", "infinit");

        carregaListaBackEnd(trainerService);

        //buscaProfessorPorId(trainerService);

        //removePorId(trainerService);

        //alteraProfessor(trainerService);
        //salvaTrainer(trainerService, claudin);

        Log.i("Caminho da pasta", pathDadosUsuario.getRoot().toString());
        Log.i("storage ", pathDadosUsuario.getPath());

        ListaDosPersonal adapter = configuraAdapter(professores);
        botaoAbrirDetalhesDoPersonal(adapter);
    }

    private void salvaTrainer(TrainerService trainerService, Professor professor) {
        Call<Professor> call = trainerService.salvaTrainer(professor);
        call.enqueue(new Callback<Professor>() {
            @Override
            public void onResponse(Call<Professor> call, Response<Professor> response) {
                
                if (response.isSuccessful()) {
                    Professor prof = response.body();
                    Toast.makeText(MainActivity.this,
                            "Tudo certo" + prof, LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Professor> call, Throwable t) {
                Log.i("erro ao salvar", t.getMessage());
                Toast.makeText(MainActivity.this, "Erro " + t.getMessage(),
                        LENGTH_LONG).show();
            }
        });
    }

    private void alteraProfessor(TrainerService trainerService) {
        Call<Professor> call = trainerService.altera("5f1cf15ca2d0ac3011f5245f", new Professor("Maroca",
                "100"));
        call.enqueue(new Callback<Professor>() {
            @Override
            public void onResponse(Call<Professor> call, Response<Professor> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Alterado com sucesso",
                            LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Professor> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Deu ruim",
                        LENGTH_LONG).show();
            }
        });
    }

    private void removePorId(TrainerService trainerService) {
        Call<Void> call = trainerService.removeProfessorPorId("5f1c934bdebbfa9c2c22a0c5");
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();
                Log.i("Resposta servidor ", "Código " + code);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha ao apagar trainer",
                        LENGTH_LONG)
                        .show();
            }
        });
    }

    private void buscaProfessorPorId(TrainerService trainerService) {
        Call<Professor> call = trainerService.buscaProfessorPorId("5f1c934bdebbfa9c2c22a0c5");
        call.enqueue(new Callback<Professor>() {
            @Override
            public void onResponse(Call<Professor> call, Response<Professor> response) {
                if (response.isSuccessful()) {

                    Log.i("Professor retornado ", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Professor> call, Throwable t) {

            }
        });
    }

    private void carregaListaBackEnd(TrainerService trainerService) {

        Call<List<Professor>> call = trainerService.buscaProfessores();

        call.enqueue(new Callback<List<Professor>>() {
            @Override
            public void onResponse(Call<List<Professor>> call, Response<List<Professor>> response) {
                professores = response.body();
                ListaDosPersonal adapter = configuraAdapter(professores);
                botaoAbrirDetalhesDoPersonal(adapter);
            }

            @Override
            public void onFailure(Call<List<Professor>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha ao carregar a lista de " +
                        "professores", LENGTH_LONG).show();
            }
        });
    }

    private void botaoAbrirDetalhesDoPersonal(ListaDosPersonal adapter) {
        adapter.setOnTouchItemList((position) -> {
            irParaTelaDetalhesDoPersonal(position);

        });
    }

    private void irParaTelaDetalhesDoPersonal(int position) {
        Intent intent = new Intent(MainActivity.this,
                InformacoesDoPersonal.class);
        intent.putExtra(PROFESSOR, professores.get(position));
        startActivity(intent);
    }

    private ListaDosPersonal configuraAdapter(List<Professor> professores) {
        ListaDosPersonal adapter = new ListaDosPersonal(this, professores);
        recyclerView.setAdapter(adapter);
        return adapter;
    }

    private List<Professor> exemploDeLista() {
        return new ArrayList<>(Arrays.asList(
                new Professor("Personal 1", "70"),
                new Professor("Personal 2", "50"),
                new Professor("Personal 3", "40"),
                new Professor("Personal 4", "65"),
                new Professor("Personal 5", "70"),
                new Professor("Personal 6", "80"),
                new Professor("Personal 7", "45"),
                new Professor("Personal 8", "25"),
                new Professor("Personal 9", "35"),
                new Professor("Personal 10", "80")
        ));
    }
}
