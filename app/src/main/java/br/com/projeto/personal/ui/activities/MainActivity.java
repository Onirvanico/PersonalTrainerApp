package br.com.projeto.personal.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.projeto.personal.R;
import br.com.projeto.personal.model.Professor;
import br.com.projeto.personal.recyclerview.ListaDosPersonal;

public class MainActivity extends AppCompatActivity {

    public static final String PROFESSOR = "professor";
    private RecyclerView recyclerView;
    private List<Professor> professores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_personal);

        professores = exemploDeLista();

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
                    new Professor("Cristiano", "70"),
                    new Professor("Welinton", "50"),
                    new Professor("Cactóide", "interminável")
            ));
    }
}
