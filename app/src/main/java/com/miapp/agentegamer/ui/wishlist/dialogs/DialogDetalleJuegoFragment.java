package com.miapp.agentegamer.ui.wishlist.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.miapp.agentegamer.R;
import com.miapp.agentegamer.data.model.WishlistEntity;
import com.miapp.agentegamer.viewmodel.WishlistViewModel;

public class DialogDetalleJuegoFragment extends DialogFragment {

    private static final String ARG_JUEGO = "juego";

    private WishlistEntity juego;
    private WishlistViewModel wishlistViewModel;

    public static DialogDetalleJuegoFragment newInstance(WishlistEntity juego) {
        DialogDetalleJuegoFragment fragment = new DialogDetalleJuegoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_JUEGO, juego);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        juego = (WishlistEntity) getArguments().getSerializable(ARG_JUEGO);
        wishlistViewModel = new ViewModelProvider(requireActivity()).get(WishlistViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_detalle_juego, null);

        ImageView img = view.findViewById(R.id.imgJuego);
        TextView nombre = view.findViewById(R.id.tvNombreJuego);
        TextView precio = view.findViewById(R.id.tvPrecio);

        Button btnEditar = view.findViewById(R.id.btnEditar);
        Button btnComprar = view.findViewById(R.id.btnComprar);
        Button btnCancelar = view.findViewById(R.id.btnCancelar);

        nombre.setText(juego.getNombre());
        precio.setText("Precio estimado: " + juego.getPrecioEstimado() + " €");

        Glide.with(requireContext()).load(juego.getImagenUrl()).into(img);

        btnEditar.setOnClickListener(v -> {
            DialogEditarPrecioFragment dialog = DialogEditarPrecioFragment.newInstance(juego);

            dialog.setOnPrecioEditadoListener(() -> {
                dismiss();
            });

            dialog.show(getParentFragmentManager(), "editarPrecio");
        });

        btnComprar.setOnClickListener(v -> {
            DialogConfirmarCompraFragment.newInstance(juego, juego.getPrecioEstimado()).show(getParentFragmentManager(), "confirmarCompra");
            dismiss();
        });

        btnCancelar.setOnClickListener( v ->
            dismiss());

        return new AlertDialog.Builder(requireContext()).setView(view).create();

    }
}
