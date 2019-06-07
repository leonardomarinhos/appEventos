package br.com.senac.appeventos.modelo;

import java.io.Serializable;

public class Evento implements Serializable {
    private Long id;
    private String evento;
    private String dias;
    private String horas;
    private String local;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getEvento() { return evento; }

    public void setEvento(String evento) { this.evento = evento; }

    public String getDias() { return dias; }

    public void setDias(String dias) { this.dias = dias; }

    public String getHoras() { return horas; }

    public void setHoras(String horas) { this.horas = horas; }

    public String getLocal() { return local; }

    public void setLocal(String local) { this.local = local; }

    public String toString(){
        return getEvento() + " = " + getDias() + " - " + getHoras();
    }
}
