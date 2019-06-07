package br.com.senac.appeventos;

import android.widget.EditText;

import br.com.senac.appeventos.modelo.Evento;

public class FormularioHelper {
    private final EditText campoevento;
    private final EditText campodias;
    private final EditText campohoras;
    private final EditText campolocal;
    private Evento evento;

// classe auxiliar

    public FormularioHelper(FormularioActivity Activity) {

        campoevento = Activity.findViewById(R.id.nomeevento);
        campodias = Activity.findViewById(R.id.dias);
        campohoras = Activity.findViewById(R.id.horas);
        campolocal = Activity.findViewById(R.id.local);
        evento = new Evento();

    }

    public Evento getEvento(){
        evento.setEvento(campoevento.getText().toString());
        evento.setDias(campodias.getText().toString());
        evento.setHoras(campohoras.getText().toString());
        evento.setLocal(campolocal.getText().toString());

        return evento;
    }

    public void alterform(Evento evento) {
        campoevento.setText(evento.getEvento());
        campodias.setText(evento.getDias());
        campohoras.setText(evento.getHoras());
        campolocal.setText(evento.getLocal());
        this.evento = evento;
    }
}
