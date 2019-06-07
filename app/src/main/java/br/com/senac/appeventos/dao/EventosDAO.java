package br.com.senac.appeventos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.senac.appeventos.modelo.Evento;

public class EventosDAO extends SQLiteOpenHelper {

    public EventosDAO(Context context) {
        super(context, "bdevento", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tbevento(id INTEGER PRIMARY KEY,evento TEXT NOT NULL,dias TEXT,horas TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tbeventos";
        db.execSQL(sql);
    }

    public void inserirEvento(Evento evento) {
        SQLiteDatabase inserir = getWritableDatabase();
        ContentValues dados = gtdados(evento);

        inserir.insert("tbevento", null, dados);

    }

    public List<Evento> listaevento() {
        String sql = "Select * From tbevento;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Evento> eventos = new ArrayList<Evento>();

        while (c.moveToNext()) {
            Evento evento = new Evento();

            evento.setId(c.getLong(c.getColumnIndex("id")));
            evento.setEvento(c.getString(c.getColumnIndex("evento")));
            evento.setDias(c.getString(c.getColumnIndex("dias")));
            evento.setHoras(c.getString(c.getColumnIndex("horas")));
            evento.setLocal(c.getString(c.getColumnIndex("local")));

            eventos.add(evento);
        }
        c.close();
        return eventos;
    }

    public void deleta(Evento evento) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {evento.getId().toString()};
        db.delete("tbevento", "id = ?", params);
    }

    public void altera(Evento evento) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = gtdados(evento);
        String[] params = {evento.getId().toString()};
        db.update("tbevento", dados, "id = ?", params);
    }

    private ContentValues gtdados(Evento evento) {
        ContentValues dados = new ContentValues();
        dados.put("evento", evento.getEvento());
        dados.put("dias", evento.getDias());
        dados.put("horas", evento.getHoras());
        dados.put("local",evento.getLocal());

        return dados;

    }
}
