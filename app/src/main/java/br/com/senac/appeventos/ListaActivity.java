package br.com.senac.appeventos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.senac.appeventos.dao.EventosDAO;
import br.com.senac.appeventos.modelo.Evento;

public class ListaActivity extends Activity {
    private ListView listaevento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        EventosDAO dao = new EventosDAO(this);
        final List<Evento> eventos = dao.listaevento();

        listaevento = (ListView) findViewById(R.id.listaevento);

        listaevento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento evento = (Evento) listaevento.getItemAtPosition(position);
                Intent linkAlterar = new Intent(ListaActivity.this,FormularioActivity.class);
                linkAlterar.putExtra("evento",evento);
                startActivity(linkAlterar);
            }
        });
        ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(this, android.R.layout.simple_list_item_activated_1, eventos);
        listaevento.setAdapter(adapter);

        Button btback = findViewById(R.id.botaonovo);
        btback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent linkback = new Intent(ListaActivity.this,FormularioActivity.class);
                startActivity(linkback);
            }
        });
        registerForContextMenu(listaevento);
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
                Evento evento = (Evento) listaevento.getItemAtPosition(info.position);
                Toast.makeText(ListaActivity.this,evento.getEvento() + " - " + "foi deletado ", Toast.LENGTH_LONG).show();

                EventosDAO dao = new EventosDAO(ListaActivity.this);
                dao.deleta(evento);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void carregaLista(){
        EventosDAO dao = new EventosDAO(this);
        List<Evento> eventos = dao.listaevento();
        dao.close();

        ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(this,android.R.layout.simple_list_item_1, eventos);
        listaevento.setAdapter(adapter);
    }
    }
