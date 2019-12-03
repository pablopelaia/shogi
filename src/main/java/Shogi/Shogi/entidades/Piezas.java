package Shogi.Shogi.entidades;

import Shogi.Shogi.enumeraciones.Jugador;
import java.util.ArrayList;


public class Piezas {
    private int id;
    private String nombre;
    private Jugador jugador;
    private int pos_tablero;
    private String tipo;
    private ArrayList<Integer> posibles_movimientos;

    public Piezas() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getPos_tablero() {
        return pos_tablero;
    }

    public void setPos_tablero(int pos_tablero) {
        this.pos_tablero = pos_tablero;
    }

    public ArrayList<Integer> getPosibles_movimientos() {
        return posibles_movimientos;
    }

    public void setPosibles_movimientos(ArrayList<Integer> posibles_movimientos) {
        this.posibles_movimientos = posibles_movimientos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
