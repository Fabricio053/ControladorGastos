package com.fabricio.dispositivosmoveisfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class registrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        Button bnt_voltar2 = findViewById(R.id.bnt_voltar2),buttonR = findViewById(R.id.buttonR) ;
        EditText nomeAlu = findViewById(R.id.nomeAlu) , n1 = findViewById(R.id.n1),n2 = findViewById(R.id.n2),n3 = findViewById(R.id.n3);

        SharedPreferences sharedPreferences = getSharedPreferences("diario_de_notas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        bnt_voltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaR = new Intent(registrar.this, MainActivity.class);
                startActivity(telaR);
            }
        });

        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aluno = nomeAlu.getText().toString();
                String nota1 = n1.getText().toString();
                String nota2 = n2.getText().toString();
                String nota3 = n3.getText().toString();
                JSONObject notas = new JSONObject();
                try {
                    notas.put("1nota", (nota1));
                    notas.put("2nota", (nota2));
                    notas.put("3nota", (nota3));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                editor.putString(aluno, notas.toString());
                editor.apply();
                nomeAlu.setText("");
                n1.setText("");
                n2.setText("");
                n3.setText("");
            }

        });

    }
}