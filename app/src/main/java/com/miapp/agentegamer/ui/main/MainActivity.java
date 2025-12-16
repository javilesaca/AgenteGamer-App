package com.miapp.agentegamer.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.lifecycle.ViewModelProvider;

import androidx.appcompat.app.AppCompatActivity;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.agent.AgenteFinanciero;
import com.miapp.agentegamer.data.model.GastoEntity;
import com.miapp.agentegamer.ui.gastos.ListaGastosActivity;
import com.miapp.agentegamer.viewmodel.GastoViewModel;

public class MainActivity extends AppCompatActivity {

    private Button btnVerJuegos;
    private Button btnWishlist;
    private Button btnGastos;
    public TextView tvRecomendacion;
    public TextView tvTotalGastos;
    private GastoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Vincula el layout
        viewModel = new ViewModelProvider(this).get(GastoViewModel.class);

        //Referencia al textview de total de gastos
        tvTotalGastos = findViewById(R.id.tvTotalGastos);
        //Referencia al textview
        tvRecomendacion = findViewById(R.id.tvRecomendacion);
        // Referencia a los botones
        btnVerJuegos = findViewById(R.id.btnVerJuegos);
        btnWishlist = findViewById(R.id.btnWishlist);
        btnGastos = findViewById(R.id.btnGastos);

        AgenteFinanciero agente = new AgenteFinanciero(100);

        viewModel.getListaGastos().observe(this, gastos -> {
            double total = 0;
            for (GastoEntity g : gastos) total += g.getPrecio();
            tvTotalGastos.setText("Total gastado: " + total + " €");
        });

        viewModel.getRecomendacion().observe(this, texto -> {
            tvRecomendacion.setText(texto);
        });
        // Listeners simples
        btnVerJuegos.setOnClickListener(v ->
                Toast.makeText(this, "Toca para ver Juegos", Toast.LENGTH_SHORT).show()
        );

        btnWishlist.setOnClickListener(v ->
                Toast.makeText(this, "Toca para ver Wishlist", Toast.LENGTH_SHORT).show()
        );

        btnGastos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaGastosActivity.class);
            startActivity(intent);
        });
    }
}

