package com.example.appconsulta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public EditText idArvore;
    public Button botao_pesquisa;
    public TextView resultado;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idArvore = findViewById(R.id.idArvore);
        botao_pesquisa = findViewById(R.id.query_button);
        resultado = findViewById(R.id.result);

        botao_pesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //instancia do db
                DatabaseAccess databaseAccess= DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();

                String i=idArvore.getText().toString();
                String nomePop = DatabaseAccess.getNomePop(i);

                resultado.setText(nomePop);

                databaseAccess.close();

            }
        });
    }
}