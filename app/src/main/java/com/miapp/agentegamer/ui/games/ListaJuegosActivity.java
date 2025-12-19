package com.miapp.agentegamer.ui.games;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.viewmodel.GamesViewModel;

public class ListaJuegosActivity extends AppCompatActivity {

    private GamesViewModel viewModel;
    private RecyclerView recyclerView;
    private JuegosAdapter adapter;

    private static final String API_KEY = "65370d96089f4bf7bb853f14e14f4fd8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_juegos);

        recyclerView = findViewById(R.id.recyclerJuegos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new JuegosAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(GamesViewModel.class);

        viewModel.obtenerJuegos(API_KEY).observe(this, juegos -> {
            if (juegos != null) {
                adapter.setLista(juegos);
            } else {
                Toast.makeText(this, "Error cargando juegos", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

