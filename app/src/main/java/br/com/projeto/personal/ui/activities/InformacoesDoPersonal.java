package br.com.projeto.personal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import br.com.projeto.personal.R;
import br.com.projeto.personal.model.Professor;

public class InformacoesDoPersonal extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_do_personal);

        TextView nome = findViewById(R.id.informacoes_personal_input_nome);
        TextView experiencia = findViewById(R.id.informacoes_personal_input_experiencia);

        if(getIntent().hasExtra("professor")) {
            Professor professor = getIntent().getParcelableExtra("professor");
            nome.setText(professor.getNome());
            experiencia.setText(professor.getExperiencia());
        }

        TextView btAgendar = findViewById(R.id.informacoes_personal_botao_agendar);

        btAgendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.informacoes_personal_botao_agendar) {
            startActivity(new Intent(InformacoesDoPersonal.this,
                    AgendamentoActivity.class));
        }
    }
}
