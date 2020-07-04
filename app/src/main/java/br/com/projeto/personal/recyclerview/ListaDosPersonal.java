package br.com.projeto.personal.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

import br.com.projeto.personal.R;
import br.com.projeto.personal.model.Professor;

public class ListaDosPersonal extends RecyclerView.Adapter<ListaDosPersonal.MyViewHolder> {


    private Context context;
    private List<Professor> professores;
    private OnClickItemList onClickItemList;

    public ListaDosPersonal(Context context, List<Professor> professores) {

        this.context = context;
        this.professores = professores;
    }

    public void setOnTouchItemList(OnClickItemList onClickItem) {
        onClickItemList = onClickItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_lista, parent,
                false);

        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.vincula(position);
    }

    @Override
    public int getItemCount() {
        return professores.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeProfessor;
        private final TextView experienciaProfessor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeProfessor = itemView.findViewById(R.id.item_lista_nome_professor);
            experienciaProfessor = itemView.findViewById(R.id.item_lista_experiencia_professor);

            itemView.setOnClickListener(view -> onClickItemList.OnItem(getBindingAdapterPosition()));
        }
         private void vincula(int position) {
             Professor professor = professores.get(position);
             nomeProfessor.setText(professor.getNome());
             experienciaProfessor.setText(professor.getExperiencia());

         }

    }
    public interface OnClickItemList {
        void OnItem(int position);
    }
}
