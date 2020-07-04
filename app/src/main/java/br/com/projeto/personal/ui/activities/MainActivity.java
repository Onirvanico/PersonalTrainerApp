package br.com.projeto.personal.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.projeto.personal.R;
import br.com.projeto.personal.model.Professor;
import br.com.projeto.personal.recyclerview.ListaDosPersonal;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_personal);

        List<Professor> professores = new ArrayList<>(Arrays.asList(
                new Professor("Cristiano", "70"),
                new Professor("Welinton", "50"),
                new Professor("Cactóide", "interminável")
        ));

        ListaDosPersonal adapter = new ListaDosPersonal(this, professores);
        recyclerView.setAdapter(adapter);
        adapter.setOnTouchItemList((position) -> {
            startActivity(new Intent(MainActivity.this,
                    InformacoesDoPersonal.class));

        });
    }
}
