package com.miapp.agentegamer.ui.gastos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.viewmodel.GastoViewModel;

public class ListaGastosActivity extends AppCompatActivity {

    private GastoViewModel gastoViewModel;
    private RecyclerView recyclerView;
    private GastoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gastos);

        recyclerView = findViewById(R.id.recyclerGastos);
        adapter = new GastoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        gastoViewModel = new ViewModelProvider(this).get(GastoViewModel.class);

        gastoViewModel.getListaGastos().observe(this, gastos -> {
            adapter.setLista(gastos);
        });
    }
}
