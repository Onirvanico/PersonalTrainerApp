package br.com.projeto.personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        adapter.setOnTouchItemList((position) -> Log.i("clique",
                "view tocada" + " na posição " + position));
    }
}
