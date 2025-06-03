package com.seuapp.trabalhoavaliativo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText CEP;
    private Button btn, btnHist;
    public static ArrayList<String> historicoCeps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CEP = findViewById(R.id.CEP);
        btn = findViewById(R.id.btn);
        btnHist = findViewById(R.id.btnHistotico);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String cepDigitado = CEP.getText().toString().trim();

                if (cepDigitado.length() == 8 && cepDigitado.matches("\\d+")) {
                    if (!historicoCeps.contains(cepDigitado)) {
                        historicoCeps.add(cepDigitado);
                    }

                    String url = "https://viacep.com.br/ws/" + cepDigitado + "/json/";
                    Intent intent = new Intent(MainActivity.this, Resultado.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "CEP deve conter 8 dígitos numéricos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Historico.class);
                startActivity(intent);
            }
        });

    }
}
