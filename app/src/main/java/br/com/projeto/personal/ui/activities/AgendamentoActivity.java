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

    private Object itemSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

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

           /* new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.alert_light_frame)
                    .setTitle("Detalhes do agendamento")
                    .setMessage("Verifique se todas as informações são as desejadas")
                    .setPositiveButton("Ok", (dialog, which) -> {
                    });*/
            Log.i("TipoAtividade ", tipoAtividade);
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.opcoes_marcadas);
            dialog.setTitle("Verifique se tudo está de acordo com o esperado!");
            ListView lista = dialog.findViewById(R.id.lista_opcoes_marcas);
            String informacoes [] = {"Data: " + dataEscolhida,"Hora: " + hora,
                    "Tipo de atividade: " + tipoAtividade};
            
            lista.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    informacoes));
            dialog.show();
        });
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
