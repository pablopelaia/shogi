
package Shogi.Shogi.entidades;

import Shogi.Shogi.enumeraciones.Jugador;

public class Participante {
    private int id;
    private String nombre;
    private Jugador color;

    public Participante() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Jugador getColor() {
        return color;
    }

    public void setColor(Jugador color) {
        this.color = color;
    }
    
    
}
