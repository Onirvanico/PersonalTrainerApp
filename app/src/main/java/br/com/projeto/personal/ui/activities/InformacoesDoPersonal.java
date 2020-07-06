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

    public static final String APP_BAR_INFORMACOES_PERSONAL = "Informações do Personal Trainer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_do_personal);

        setTitle(APP_BAR_INFORMACOES_PERSONAL);
        TextView nome = findViewById(R.id.informacoes_personal_input_nome);
        TextView experiencia = findViewById(R.id.informacoes_personal_input_experiencia);

        buscaPersonalTrainer(nome, experiencia);

        TextView btAgendar = findViewById(R.id.informacoes_personal_botao_agendar);

        btAgendar.setOnClickListener(this);
    }

    private void buscaPersonalTrainer(TextView nome, TextView experiencia) {
        if(getIntent().hasExtra("professor")) {
            preencheCamposPersonal(nome, experiencia);
        }
    }

    private void preencheCamposPersonal(TextView nome, TextView experiencia) {
        Professor professor = getIntent().getParcelableExtra("professor");
        nome.setText(professor.getNome());
        experiencia.setText(professor.getExperiencia());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.informacoes_personal_botao_agendar) {
            startActivity(new Intent(InformacoesDoPersonal.this,
                    AgendamentoActivity.class));
        }
    }
}
