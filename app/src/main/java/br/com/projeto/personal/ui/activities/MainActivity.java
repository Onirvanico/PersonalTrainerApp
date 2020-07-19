package br.com.projeto.personal.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.projeto.personal.R;
import br.com.projeto.personal.model.Professor;
import br.com.projeto.personal.recyclerview.ListaDosPersonal;
import br.com.projeto.personal.retrofit.TrainerRetrofit;
import br.com.projeto.personal.retrofit.service.TrainerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String PROFESSOR = "professor";
    private RecyclerView recyclerView;
    private List<Professor> professores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
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
        Call<List<Professor>> listaProfessores = trainerService.buscaProfessores();

        listaProfessores.enqueue(new Callback<List<Professor>>() {
            @Override
            public void onResponse(Call<List<Professor>> call, Response<List<Professor>> response) {
                professores = response.body();
                ListaDosPersonal adapter = configuraAdapter(professores);
                botaoAbrirDetalhesDoPersonal(adapter);
            }

            @Override
            public void onFailure(Call<List<Professor>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha ao carregar a lista de "+
                        "professores", Toast.LENGTH_LONG).show();
            }
        });


        Log.i("Caminho da pasta", pathDadosUsuario.getRoot().toString() );
        Log.i("storage ", pathDadosUsuario.getPath());

        ListaDosPersonal adapter = configuraAdapter(professores);
        botaoAbrirDetalhesDoPersonal(adapter);
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
