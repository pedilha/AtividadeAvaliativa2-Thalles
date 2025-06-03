package com.seuapp.trabalhoavaliativo2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Historico extends AppCompatActivity {

    private ListView listViewCeps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listViewCeps = findViewById(R.id.listViewCeps);

        // Cria adaptador com a lista da MainActivity
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.historicoCeps
        );
        listViewCeps.setAdapter(adapter);

        // Clique em item da lista
        listViewCeps.setOnItemClickListener((parent, view, position, id) -> {
            String cepSelecionado = MainActivity.historicoCeps.get(position);
            String url = "https://viacep.com.br/ws/" + cepSelecionado + "/json/";

            Intent intent = new Intent(Historico.this, Resultado.class);
            intent.putExtra("url", url);
            startActivity(intent);
        });
    }
}
