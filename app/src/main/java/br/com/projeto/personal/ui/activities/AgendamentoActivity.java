package br.com.projeto.personal.ui.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import br.com.projeto.personal.R;

public class AgendamentoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String APP_BAR_AGENDAR = "Agendar Atividade";
    private Object itemSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        setTitle(APP_BAR_AGENDAR);
        Button botaoConcluir = findViewById(R.id.agendamento_botao_concluir);
        Spinner spinner = findViewById(R.id.agendamento_spinner_hora);
        EditText campoData = findViewById(R.id.agendamento_input_data);
        RadioGroup radioGroup = findViewById(R.id.agendamento_radio_group);


        configuraSpinner(spinner);
        spinner.setOnItemSelectedListener(this);


        botaoConcluir.setOnClickListener(view -> {
            String dataEscolhida = campoData.getText().toString();
            String hora = itemSelecionado.toString();
            String tipoAtividade = "";

            tipoAtividade = buscaAtividadeEscolhida(radioGroup, tipoAtividade);

            String informacoes [] = {"Data: " + dataEscolhida,"Hora: " + hora,
                    "Tipo de atividade: " + tipoAtividade};

            configuraDialogoDeInformacoes(informacoes);
        });
    }

    private String buscaAtividadeEscolhida(RadioGroup radioGroup, String tipoAtividade) {
        switch(radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_funcionais:
                tipoAtividade = "Atividades funcionais";
                break;

            case R.id.radio_aerobicos:
                tipoAtividade = "Atividades aeróbicas";
                break;

            case R.id.radio_anaerobicos:
                tipoAtividade = "Atividades anaeróbicas";
        }
        return tipoAtividade;
    }

    private void configuraDialogoDeInformacoes(String[] informacoes) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.opcoes_marcadas);
        dialog.setTitle("Verifique se tudo está de acordo com o esperado!");
        ListView lista = dialog.findViewById(R.id.lista_opcoes_marcas);


        configuraListaDeInformacoesAdapter(informacoes, lista);
        dialog.show();
    }

    private void configuraListaDeInformacoesAdapter(String[] informacoes, ListView lista) {
        lista.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                informacoes));
    }

    private void configuraSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.opcao_horario,
                android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        itemSelecionado = parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
