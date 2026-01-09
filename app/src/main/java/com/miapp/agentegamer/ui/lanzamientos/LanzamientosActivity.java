package com.miapp.agentegamer.ui.lanzamientos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.viewmodel.LanzamientosViewModel;

public class LanzamientosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanzamientos);

        RecyclerView rv = findViewById(R.id.recyclerLanzamientos);
        rv.setLayoutManager(new LinearLayoutManager(this));

        LanzamientosAdapter adapter = new LanzamientosAdapter();
        rv.setAdapter(adapter);

        LanzamientosViewModel vm = new ViewModelProvider(this).get(LanzamientosViewModel.class);

        vm.getLanzamientos().observe(this, adapter::setLista);
    }
}
