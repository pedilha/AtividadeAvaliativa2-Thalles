package com.seuapp.trabalhoavaliativo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Resultado extends AppCompatActivity {

    private TextView logradouro, bairro, cidade, estado;
    private Button btnVoltar, btnHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String urlRecebida = getIntent().getStringExtra("url");

        logradouro = findViewById(R.id.Logradouro);
        bairro = findViewById(R.id.Bairro);
        cidade = findViewById(R.id.Cidade);
        estado = findViewById(R.id.Estado);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnHistorico = findViewById(R.id.btnHistotico);

        RequestQueue queue = Volley.newRequestQueue(this);

        if (urlRecebida != null && !urlRecebida.isEmpty()) {
            JsonObjectRequest jsonRequest = new JsonObjectRequest(
                    Request.Method.GET, urlRecebida, null,
                    response -> {
                        try {
                            logradouro.setText("Logradouro: " + response.optString("logradouro", "Não encontrado"));
                            bairro.setText("Bairro: " + response.optString("bairro", "Não encontrado"));
                            cidade.setText("Cidade: " + response.optString("localidade", "Não encontrado"));
                            estado.setText("Estado: " + response.optString("uf", "Não encontrado"));
                        } catch (Exception e) {
                            Toast.makeText(Resultado.this, "Erro ao processar dados", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
                        Toast.makeText(Resultado.this, "Erro na requisição: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
            );

            queue.add(jsonRequest);

        } else {
            Toast.makeText(this, "URL inválida ou não recebida", Toast.LENGTH_SHORT).show();
        }

        btnVoltar.setOnClickListener(v -> finish());

        btnHistorico.setOnClickListener(v -> {
            startActivity(new Intent(this, Historico.class));
        });
    }
}
