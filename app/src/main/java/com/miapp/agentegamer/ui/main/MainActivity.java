package com.miapp.agentegamer.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.lifecycle.ViewModelProvider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.miapp.agentegamer.R;
import com.miapp.agentegamer.agent.AgenteFinanciero;
import com.miapp.agentegamer.data.model.GastoEntity;
import com.miapp.agentegamer.ui.games.ListaJuegosActivity;
import com.miapp.agentegamer.ui.gastos.ListaGastosActivity;
import com.miapp.agentegamer.ui.lanzamientos.LanzamientosActivity;
import com.miapp.agentegamer.ui.wishlist.ListaWishlistActivity;
import com.miapp.agentegamer.ui.worker.AgenteFinancieroWorker;
import com.miapp.agentegamer.viewmodel.GastoViewModel;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button btnVerJuegos;
    private Button btnWishlist;
    private Button btnGastos;
    private Button btnLanzamientos;
    public TextView tvRecomendacion;
    public TextView tvTotalGastos;
    private GastoViewModel viewModel;
    private View indicador;
    public PieChart pieChart;

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
        btnLanzamientos = findViewById(R.id.btnLanzamientos);


        indicador = findViewById(R.id.viewIndicador);
        pieChart = findViewById(R.id.pieChart);
        /*AgenteFinanciero agente = new AgenteFinanciero(100);*/

        viewModel.getListaGastos().observe(this, gastos -> {
            double total = 0;
            for (GastoEntity g : gastos) total += g.getPrecio();
            tvTotalGastos.setText("Total gastado: " + total + " €");
        });

        viewModel.getListaGastos().observe(this, gastos -> {
            List<PieEntry> entries = new ArrayList<>();

            for (GastoEntity g : gastos) {
                entries.add(new PieEntry((float) g.getPrecio(), g.getId()));
            }

            PieDataSet dataSet = new PieDataSet(entries, "Distribución de gastos");
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

            PieData data = new PieData(dataSet);
            pieChart.setData(data);
            pieChart.invalidate();
        });

        viewModel.getRecomendacion().observe(this, texto -> {
            tvRecomendacion.setText(texto);
        });

        viewModel.getEstadoFinanciero().observe(this, estado -> {
            int color;
            switch (estado) {
                case VERDE:
                    color = Color.GREEN;
                    break;
                case AMARILLO:
                    color = Color.YELLOW;
                    break;
                default:
                    color = Color.RED;
            }
            indicador.setBackgroundColor(color);
        });

        // Listeners simples
        btnVerJuegos.setOnClickListener(v ->
             startActivity(new Intent(this, ListaJuegosActivity.class))
        );

        btnWishlist.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    ListaWishlistActivity.class
            );
            startActivity(intent);

        });

        btnGastos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaGastosActivity.class);
            startActivity(intent);
        });

        btnLanzamientos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LanzamientosActivity.class);
            startActivity(intent);
        });


        //Boton implementado para uso exclusivo de desarrollo
        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(v -> {
            viewModel.borrarTodosLosGastos();
            Toast.makeText(this, "Gastos borrados", Toast.LENGTH_SHORT).show();
        });
    }
}

