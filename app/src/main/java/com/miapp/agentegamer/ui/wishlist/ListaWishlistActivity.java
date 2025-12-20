package com.miapp.agentegamer.ui.wishlist;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.data.model.WishlistEntity;
import com.miapp.agentegamer.viewmodel.WishlistViewModel;

public class ListaWishlistActivity extends AppCompatActivity {

    private WishlistViewModel viewModel;
    private WishlistAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_wishlist);

        RecyclerView recyclerView = findViewById(R.id.recyclerWishlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new WishlistAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(juego -> {
            mostrarDialogoEditarPrecio(juego);
        });

        viewModel = new ViewModelProvider(this).get(WishlistViewModel.class);

        viewModel.getWishlist().observe(this, adapter::setLista);
    }

    private void mostrarDialogoEditarPrecio(WishlistEntity juego) {

        EditText input = new EditText(this);
        input.setInputType(
                InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL
        );
        input.setText(String.valueOf(juego.getPrecioEstimado()));

        new AlertDialog.Builder(this)
                .setTitle("Editar precio")
                .setMessage("Precio estimado para " + juego.getNombre())
                .setView(input)
                .setPositiveButton("Guardar", (dialog, which) -> {

                    double nuevoPrecio = Double.parseDouble(input.getText().toString());

                    WishlistEntity actualizado = new WishlistEntity(
                            juego.getGameId(),
                            juego.getNombre(),
                            juego.getFechaLanzamiento(),
                            juego.getImagenUrl(),
                            nuevoPrecio
                    );

                    viewModel.actualizar(actualizado);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
