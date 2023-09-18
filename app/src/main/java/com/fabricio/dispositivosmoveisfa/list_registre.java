package com.fabricio.dispositivosmoveisfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class list_registre extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private List<String> diarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_registre);
        Button bnt_voltar = findViewById(R.id.bnt_voltar), button_show = findViewById(R.id.button_show);
        ListView mostrarReegistros = findViewById(R.id.mostrarReegistros);

        SharedPreferences sharedPreferences = getSharedPreferences("diario_de_notas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Map<String, ?> allEntries = sharedPreferences.getAll();
        diarios = new ArrayList<>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String chave = entry.getKey();
            String valor = entry.getValue().toString();
            Gson gson = new Gson();
            Type listType = new TypeToken<Map<String, String>>() {}.getType();
            Map<String, String> dados = gson.fromJson(valor, listType);
            int n = 1;
            String registro = "Nome Aluno: " + chave + "\n" ;
            for (Map.Entry<String, String> item : dados.entrySet()) {
                registro += "Nota "+ n+1 + ": " + item.getValue()  + "\n";
            }
            diarios.add(registro);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diarios);
            mostrarReegistros.setAdapter(adapter);
        }
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tel_ed = new Intent(list_registre.this, Editar.class);
                startActivity(tel_ed);
            }
        });

        bnt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaR = new Intent(list_registre.this, MainActivity.class);
                startActivity(telaR);
            }
        });
    }
}