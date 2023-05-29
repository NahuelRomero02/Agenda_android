package com.example.lista20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lista20.Db.DbTareas;
import com.example.lista20.Db.Dbhelper;
import com.example.lista20.entidades.tareas;

import java.util.ArrayList;

public class NuevoMainActivity extends AppCompatActivity {

    EditText txtTarea;
    Button btnAddTarea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_main);
        txtTarea=findViewById(R.id.txtTarea);
        btnAddTarea = findViewById(R.id.btnAddTarea);

        btnAddTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tarea = txtTarea.getText().toString().trim(); // Obtener el contenido del EditText sin espacios en blanco

                if (tarea.isEmpty()) {
                    Toast.makeText(NuevoMainActivity.this, "Debe escribir una tarea", Toast.LENGTH_LONG).show();
                } else {
                    DbTareas dbTareas = new DbTareas(NuevoMainActivity.this);
                    long id = dbTareas.insertarTarea(tarea);
                    if (id > 0) {
                        Toast.makeText(NuevoMainActivity.this, "Tarea agregada", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoMainActivity.this, "Error al guardar la tarea", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });



    }



    private void limpiar(){
        txtTarea.setText("");
    }

}