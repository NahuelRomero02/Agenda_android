package com.example.lista20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lista20.Db.DbTareas;
import com.example.lista20.adaptadores.ListaTareasAdappter;
import com.example.lista20.entidades.tareas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaTareas;
    ArrayList<tareas> listaArrayTareas;
    ListaTareasAdappter adapter; // Declarar la variable adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaTareas = findViewById(R.id.listaTarea);
        listaTareas.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        listaTareas.addItemDecoration(itemDecoration);

        DbTareas dbTareas = new DbTareas(MainActivity.this);
        listaArrayTareas = new ArrayList<>();
        adapter = new ListaTareasAdappter(listaArrayTareas); // Inicializar el adapter

        adapter.setOnItemClickListener(new ListaTareasAdappter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                eliminarTarea(position);
            }
        });

        listaTareas.setAdapter(adapter);
        actualizarListaTareas();

        Button btnNuevaTarea = findViewById(R.id.btnIR);
        btnNuevaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NuevoMainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Actualizar la lista de tareas cada vez que la actividad se reanuda
        actualizarListaTareas();
    }

    private void actualizarListaTareas() {
        DbTareas dbTareas = new DbTareas(MainActivity.this);
        listaArrayTareas.clear();
        listaArrayTareas.addAll(dbTareas.mostrasTareas());
        adapter.notifyDataSetChanged();
    }

    private void eliminarTarea(int position) {
        DbTareas dbTareas = new DbTareas(MainActivity.this);
        tareas tarea = listaArrayTareas.get(position);
        dbTareas.eliminarTarea(tarea.getId());
        listaArrayTareas.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
