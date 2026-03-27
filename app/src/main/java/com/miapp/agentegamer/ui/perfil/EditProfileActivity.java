package com.miapp.agentegamer.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.miapp.agentegamer.R;
import com.miapp.agentegamer.data.repository.UserRepository;
import dagger.hilt.AndroidEntryPoint;
import javax.inject.Inject;

@AndroidEntryPoint
public class EditProfileActivity extends AppCompatActivity {

    private TextInputEditText editTextNombre;
    private MaterialButton buttonSave;
    private View loadingContainer;
    private MaterialToolbar toolbar;
    private String originalNombre;
    
    @Inject
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        editTextNombre = findViewById(R.id.editTextNombre);
        buttonSave = findViewById(R.id.buttonSave);
        loadingContainer = findViewById(R.id.loadingContainer);

        // Load current nombre from intent
        originalNombre = getIntent().getStringExtra("nombre");
        if (originalNombre != null) {
            editTextNombre.setText(originalNombre);
        }

        // Setup toolbar
        toolbar.setNavigationOnClickListener(v -> finish());

        // Setup save button
        buttonSave.setOnClickListener(v -> guardarNombre());
    }

    private void guardarNombre() {
        String nuevoNombre = editTextNombre.getText().toString().trim();
        
        if (nuevoNombre.isEmpty()) {
            editTextNombre.setError("El nombre no puede estar vacío");
            return;
        }

        if (nuevoNombre.equals(originalNombre)) {
            // No changes, just close
            finish();
            return;
        }

        // Show loading
        setLoading(true);

        userRepository.actualizarNombre(nuevoNombre, new UserRepository.OnActualizarNombreCallback() {
            @Override
            public void onSuccess() {
                runOnUiThread(() -> {
                    setLoading(false);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("nombre", nuevoNombre);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                });
            }

            @Override
            public void onError() {
                runOnUiThread(() -> {
                    setLoading(false);
                    editTextNombre.setError(getString(R.string.error_actualizar_nombre));
                });
            }
        });
    }

    private void setLoading(boolean loading) {
        if (loading) {
            loadingContainer.setVisibility(View.VISIBLE);
            buttonSave.setEnabled(false);
            buttonSave.setText("Guardando...");
        } else {
            loadingContainer.setVisibility(View.GONE);
            buttonSave.setEnabled(true);
            buttonSave.setText("Guardar cambios");
        }
    }
}
