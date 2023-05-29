package com.example.lista20.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista20.R;
import com.example.lista20.entidades.tareas;

import java.util.ArrayList;

public class ListaTareasAdappter extends RecyclerView.Adapter<ListaTareasAdappter.TareaViewHolder> {
    ArrayList<tareas> listaTareas;
    private OnItemClickListener onItemClickListener; // Declarar la variable onItemClickListener

    public ListaTareasAdappter(ArrayList<tareas> listaTareas){
        this.listaTareas=listaTareas;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_tareas,null,false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        holder.viewTarea.setText(listaTareas.get(position).getTarea());
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class TareaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView viewTarea;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            viewTarea=itemView.findViewById(R.id.viewTarea);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(position);
                }
            }
        }
    }
}
