package br.com.senac.appeventos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.senac.appeventos.dao.EventosDAO;
import br.com.senac.appeventos.modelo.Evento;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
        Intent intent = getIntent();
        Evento evento = (Evento) intent.getSerializableExtra("remedio");

        if(evento != null){
            helper.alterform(evento);
            Toast.makeText(FormularioActivity.this,"Está tudo certo", Toast.LENGTH_LONG).show();
        }

        Button botaosalvar = findViewById(R.id.botaosalvar);
        botaosalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Evento evento = helper.getEvento();
                EventosDAO dao = new EventosDAO(FormularioActivity.this);

                if (evento.getId() != null) {
                    dao.altera(evento);
                    Toast.makeText(FormularioActivity.this, "O evento " + evento.getEvento() + " foi alterado", Toast.LENGTH_SHORT).show();
                } else {
                    dao.inserirEvento(evento);
                    Toast.makeText(FormularioActivity.this, "O evento " + evento.getEvento() + " foi salvo", Toast.LENGTH_SHORT).show();
                }

                dao.close();
            }
        });

        Button botaolista = (Button) findViewById(R.id.botaolista);
        botaolista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linklista = new Intent(FormularioActivity.this, ListaActivity.class);
                startActivity(linklista);
            }
        });

    }
}
