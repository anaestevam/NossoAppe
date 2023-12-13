package com.example.nossoappe.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.nossoappe.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private SharedPreferences sharedPreferences;
    private TextView esqueci_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("LoginActivity", "onCreate");

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        esqueci_senha = findViewById(R.id.textViewEsqueci);

        sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);

        btnLogin.setOnClickListener(v -> loginUser());
        esqueci_senha.setOnClickListener(v -> recuperar());

    }

    private void recuperar() {
        Intent intent = new Intent(LoginActivity.this, MudarSenhaActivity.class);
        startActivityForResult(intent, 1); // Usa o requestCode 1 para identificar o resultado
    }

    // Método chamado quando a MudarSenhaActivity é concluída
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            loginUser();
        }
    }

    private void loginUser() {
        String savedPassword = sharedPreferences.getString("password", ""); // Recebe a senha salva

        String entrouUsername = etUsername.getText().toString();
        String entrouPassword = etPassword.getText().toString();

        if (entrouUsername.equals("admin") && (entrouPassword.isEmpty() || !entrouPassword.equals(savedPassword))) {
            savedPassword = "admin";
        }

        // Se as credenciais estão corretas, realiza o login
        if (entrouUsername.equals("admin") && entrouPassword.equals(savedPassword)) {
            Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();

            finish();
            // Inicia a MenuActivity após o login
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);

        } else {
            Toast.makeText(this, "Nome de usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
        }
    }
}
