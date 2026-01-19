package com.miapp.agentegamer.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.miapp.agentegamer.R;
import com.miapp.agentegamer.ui.main.MainActivity;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private EditText etEmail, etPassword, etNombre, etPresupuesto;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etNombre = findViewById(R.id.etNombre);
        etPresupuesto = findViewById(R.id.etPresupuesto);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> registrarUsusario());
    }

    private void registrarUsusario() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String nombre = etNombre.getText().toString().trim();
        String presupuestoTxt = etPresupuesto.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || nombre.isEmpty() || presupuestoTxt.isEmpty()){
            Toast.makeText(this, "Es necesario completar todos los campos", Toast.LENGTH_SHORT).show();
        }

        double presupuesto;
        try{
            presupuesto = Double.parseDouble(presupuestoTxt);
        } catch (NumberFormatException nfc) {
            Toast.makeText(this, "Presupuesto inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(result -> {

                    String uid = result.getUser().getUid();

                    Map<String, Object> userData = new HashMap<>();
                    userData.put("email", email);
                    userData.put("nombre", nombre);
                    userData.put("presupuesto", presupuesto);
                    userData.put("fechaCreacion", FieldValue.serverTimestamp());

                    db.collection("usuarios")
                            .document(uid)
                            .set(userData)
                            .addOnSuccessListener(unused -> {
                                startActivity(new Intent(this, MainActivity.class));
                                finish();
                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(this, "Error guardando perfil", Toast.LENGTH_SHORT).show()
                            );
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}
