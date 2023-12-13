package com.example.nossoappe.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.nossoappe.R;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Crie um Intent para a LoginActivity
        Intent intent = new Intent(this, LoginActivity.class);

        // Inicie a LoginActivity
        startActivity(intent);

        // Finalize a MainActivity para que ela não fique na pilha de atividades
        finish();

    }
}
