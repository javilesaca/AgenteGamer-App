package com.miapp.agentegamer.ui.lanzamientos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.viewmodel.LanzamientosViewModel;

public class LanzamientosActivity extends AppCompatActivity {

    private LanzamientosViewModel viewModel;
    private LanzamientosAdapter adapter;

    private static final String API_KEY = "65370d96089f4bf7bb853f14e14f4fd8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanzamientos);

        RecyclerView rv = findViewById(R.id.recyclerLanzamientos);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LanzamientosAdapter();
        rv.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(LanzamientosViewModel.class);

        viewModel.getLanzamientos().observe(this, adapter::setLista);

        viewModel.precargaProximos15Dias(API_KEY);
    }
}
