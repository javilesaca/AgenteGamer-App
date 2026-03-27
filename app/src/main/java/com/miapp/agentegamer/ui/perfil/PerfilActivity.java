package com.miapp.agentegamer.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.miapp.agentegamer.R;
import com.miapp.agentegamer.data.model.UsuarioEntity;
import com.miapp.agentegamer.data.repository.UserRepository;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@AndroidEntryPoint
public class PerfilActivity extends AppCompatActivity {

    private TextView tvAvatar, tvEmail, tvPresupuesto, tvNombre, tvFechaCreacion, tvRol;
    private MaterialButton btnEditPerfil;
    private MaterialToolbar toolbar;
    
    @Inject
    UserRepository userRepo;

    private final ActivityResultLauncher<Intent> editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String nuevoNombre = data.getStringExtra("nombre");
                            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                                actualizarNombre(nuevoNombre);
                            }
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        tvAvatar = findViewById(R.id.tvAvatar);
        tvEmail = findViewById(R.id.tvEmail);
        tvNombre = findViewById(R.id.tvNombre);
        tvFechaCreacion = findViewById(R.id.tvFechaCreacion);
        tvRol = findViewById(R.id.tvRol);
        tvPresupuesto = findViewById(R.id.tvPresupuesto);
        btnEditPerfil = findViewById(R.id.btnEditPerfil);

        // Setup toolbar
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Setup edit button
        btnEditPerfil.setOnClickListener(v -> {
            String currentNombre = tvNombre.getText().toString();
            Intent intent = new Intent(PerfilActivity.this, EditProfileActivity.class);
            intent.putExtra("nombre", currentNombre);
            editProfileLauncher.launch(intent);
        });

        cargarPerfil();
    }

    private void cargarPerfil() {
        userRepo.obtenerUsuario(new UserRepository.OnUsuarioCallback() {
            @Override
            public void onSuccess(UsuarioEntity usuario) {
                runOnUiThread(() -> {
                    // Avatar initials
                    String nombre = usuario.getNombre();
                    if (nombre != null && !nombre.isEmpty()) {
                        String[] parts = nombre.trim().split("\\s+");
                        String initials = "";
                        if (parts.length >= 2) {
                            initials = (parts[0].substring(0, 1) + parts[parts.length - 1].substring(0, 1)).toUpperCase();
                        } else {
                            initials = parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
                        }
                        tvAvatar.setText(initials);
                        tvNombre.setText(nombre);
                    }

                    tvEmail.setText(usuario.getEmail());
                    
                    // Format presupuesto with currency
                    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.SPAIN);
                    tvPresupuesto.setText(nf.format(usuario.getPresupuestoMensual()));
                    
                    // Format fechaCreacion
                    if (usuario.getFechaCreacion() != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy", new Locale("es", "ES"));
                        String fechaStr = sdf.format(new Date(usuario.getFechaCreacion().getSeconds() * 1000));
                        tvFechaCreacion.setText(fechaStr);
                    } else {
                        tvFechaCreacion.setText("-");
                    }
                    
                    tvRol.setText(usuario.getRol());
                });
            }

            @Override
            public void onError() {
                runOnUiThread(() -> 
                    Toast.makeText(PerfilActivity.this, "No se pudo cargar el perfil", Toast.LENGTH_SHORT).show()
                );
            }
        });

        // Reactive presupuesto
        userRepo.getPresupuestoLiveData().observe(this, presupuesto -> {
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.SPAIN);
            tvPresupuesto.setText(nf.format(presupuesto));
        });
    }

    private void actualizarNombre(String nombre) {
        userRepo.actualizarNombre(nombre, new UserRepository.OnActualizarNombreCallback() {
            @Override
            public void onSuccess() {
                runOnUiThread(() -> {
                    // Update name and avatar initials
                    tvNombre.setText(nombre);
                    String[] parts = nombre.trim().split("\\s+");
                    String initials = "";
                    if (parts.length >= 2) {
                        initials = (parts[0].substring(0, 1) + parts[parts.length - 1].substring(0, 1)).toUpperCase();
                    } else {
                        initials = parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
                    }
                    tvAvatar.setText(initials);
                    
                    Toast.makeText(PerfilActivity.this, getString(R.string.nombre_actualizado), Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onError() {
                runOnUiThread(() -> 
                    Toast.makeText(PerfilActivity.this, getString(R.string.error_actualizar_nombre), Toast.LENGTH_SHORT).show()
                );
            }
        });
    }
}
