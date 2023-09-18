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

import java.util.Map;

public class Editar extends AppCompatActivity {

    String aluno = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        EditText nomeALuno = findViewById(R.id.nomeALuno), n1 = findViewById(R.id.n1), n2 = findViewById(R.id.n2), n3 = findViewById(R.id.n3);
        Button buttonSalvar = findViewById(R.id.buttonSalvar), buttonEcluir = findViewById(R.id.buttonEcluir), bnt_voltar2 = findViewById(R.id.bnt_voltar2), buscar = findViewById(R.id.buscar);

        SharedPreferences sharedPreferences = getSharedPreferences("diario_de_notas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Map<String, ?> diarios_de_notas = sharedPreferences.getAll();

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aluno = nomeALuno.getText().toString().trim();
                for (Map.Entry<String, ?> dado: diarios_de_notas.entrySet()) {
                    String chave = dado.getKey();
                    if (chave.equals(aluno)) {
                        String valor = dado.getValue().toString();
                        try {
                            JSONObject alunoJson = new JSONObject(valor);
                            String nota1 = alunoJson.getString("1nota");
                            String nota2 = alunoJson.getString("2nota");
                            String nota3 = alunoJson.getString("3nota");

                            n1.setText(nota1);
                            n2.setText(nota2);
                            n3.setText(nota3);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
            }
        }
        }
        });

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Map.Entry<String, ?> dado : diarios_de_notas.entrySet()) {
                    String chave = dado.getKey();
                    if (chave.equals(aluno)) {
                        editor.remove(chave);
                        String nome = nomeALuno.getText().toString();
                        String nota1AR = n1.getText().toString();
                        String nota2AR = n2.getText().toString();
                        String nota3AR = n3.getText().toString();

                        JSONObject notas = new JSONObject();
                        try {
                            notas.put("nota1", String.valueOf(nota1AR));
                            notas.put("nota2", String.valueOf(nota2AR));
                            notas.put("nota3", String.valueOf(nota3AR));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        editor.putString(nome, notas.toString());
                        editor.apply();
                        nomeALuno.setText("");
                        n1.setText("");
                        n2.setText("");
                        n3.setText("");
                    }
                }

            }
        });

        buttonEcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aluno = nomeALuno.getText().toString();

                editor.remove(aluno);
                editor.apply();
                n1.setText("");
                n2.setText("");
                n3.setText("");
                nomeALuno.setText("");

            }
        });

        bnt_voltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaR = new Intent(Editar.this, list_registre.class);
                startActivity(telaR);
            }
        });

    }
    }
