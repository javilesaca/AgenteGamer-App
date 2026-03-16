package com.miapp.agentegamer.ui.perfil;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.data.model.UsuarioEntity;
import com.miapp.agentegamer.data.repository.UserRepository;

public class PerfilActivity extends AppCompatActivity {

    private TextView tvEmail, tvPresupuesto;
    private UserRepository userRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tvEmail = findViewById(R.id.tvEmail);
        tvPresupuesto = findViewById(R.id.tvPresupuesto);

        userRepo = new UserRepository();
        cargarPerfil();
    }

    private void cargarPerfil() {
        userRepo.obtenerUsuario(new UserRepository.OnUsuarioCallback() {
            @Override
            public void onSuccess(UsuarioEntity usuario) {
                tvEmail.setText(usuario.getEmail());
            }

            @Override
            public void onError() {
                Toast.makeText(PerfilActivity.this, "No se pudo cargar el perfil", Toast.LENGTH_SHORT).show();
            }
        });

        userRepo.getPresupuestoLiveData().observe(this, presupuesto -> {
            tvPresupuesto.setText(String.valueOf(presupuesto));
        });
    }
}
