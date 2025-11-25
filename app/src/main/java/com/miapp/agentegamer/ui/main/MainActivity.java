package com.miapp.agentegamer.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.miapp.agentegamer.R;

public class MainActivity extends AppCompatActivity {

    private Button btnVerJuegos;
    private Button btnWishlist;
    private Button btnGastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Vincula el layout

        // Referencia a los botones
        btnVerJuegos = findViewById(R.id.btnVerJuegos);
        btnWishlist = findViewById(R.id.btnWishlist);
        btnGastos = findViewById(R.id.btnGastos);

        // Listeners simples
        btnVerJuegos.setOnClickListener(v ->
                Toast.makeText(this, "Toca para ver Juegos", Toast.LENGTH_SHORT).show()
        );

        btnWishlist.setOnClickListener(v ->
                Toast.makeText(this, "Toca para ver Wishlist", Toast.LENGTH_SHORT).show()
        );

        btnGastos.setOnClickListener(v ->
                Toast.makeText(this, "Toca para ver Gastos", Toast.LENGTH_SHORT).show()
        );
    }
}

