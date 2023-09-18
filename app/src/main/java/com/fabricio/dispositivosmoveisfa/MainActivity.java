package com.fabricio.dispositivosmoveisfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bnt_registrar = findViewById(R.id.bnt_registrar), bnt_registros = findViewById(R.id.bnt_registros);


        bnt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaR = new Intent(MainActivity.this, registrar.class);
                startActivity(telaR);
            }
        });
        bnt_registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tel = new Intent(MainActivity.this, list_registre.class);
                startActivity(tel);
            }
        });
    }
}