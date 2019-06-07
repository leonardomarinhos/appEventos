package br.com.senac.appeventos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botaocomecar = findViewById(R.id.botaocomecar);
        botaocomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botaocomecar = new Intent(MainActivity.this,FormularioActivity.class);
                startActivity(botaocomecar);
            }
        });

    }
}
