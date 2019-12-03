package Shogi.Shogi.servicios;

import Shogi.Shogi.entidades.Torre;
import Shogi.Shogi.enumeraciones.Jugador;
import static Shogi.Shogi.enumeraciones.Jugador.blanco;
import static Shogi.Shogi.enumeraciones.Jugador.negro;
import java.util.ArrayList;
import java.util.HashSet;

public class TorreServicio {
    
    /**
     * Se crean 4 torres del 35 al 38, se utiliza "swich" para ver si es blanco o negro y así determinar su posición.    
    */ 
    public  void Crear (Torre torre, HashSet<Integer> piezas_mov, HashSet<Integer> casillas_color, int id, Jugador jugador){
        
        ArrayList<Integer> posibles_movimientos = new ArrayList<>();
        String nombre;
        
        torre.setId(id);
        torre.setTipo("Tor");
        torre.setJugador(jugador);
        
        switch (id){
            case 35:
                torre.setPos_tablero(91);
                posibles_movimientos.add(81);
                piezas_mov.add(81);
                break;
            case 36:
                torre.setPos_tablero(99);
                posibles_movimientos.add(89);
                piezas_mov.add(89);
                break;
            case 37:
                torre.setPos_tablero(11);
                posibles_movimientos.add(21);
                piezas_mov.add(21);
                break;
            case 38:
                torre.setPos_tablero(19);
                posibles_movimientos.add(29);
                piezas_mov.add(29);
                break;
        }
        
        casillas_color.add(torre.getPos_tablero());
        
        if (torre.getJugador().equals(negro)){
            nombre="▲☻"+torre.getTipo();
        }else{
            nombre="▼☺"+torre.getTipo();
        }
    
        torre.setNombre(nombre);        
        torre.setPosibles_movimientos(posibles_movimientos);
        
    }
    
    /**
     * Devuelve un arreglo con las casillas de los posibles movimientos que tendrá la pieza en el siguiente turno.
     * Recibe una torre y definimos como variables, casilla para la posición a ocupar, y color y contrario para
     * definir blanco o negro, es decir el que ataca o defiende (esto último es porque a diferencia de otras piezas,
     * las torres tienen los mismos movimientos independientemente de su color, si se necesita diferenciar para saber
     * si una casilla está ocupada por pieza del mismo color).
     * Con 4 "Do - While" se determinan los movimientos horizontales o verticales correspondiente, busco primero que
     * las casillas de la misma no estén ocupadas por fichas propias o rivales (en el primer caso no se permite el
     * movimiento y en el segundo si lo hace pero se trunca la suceción de movimientos en esa dirección).
     * Si la pieza está coronada se analizan cuatro movimientos posibles más.
     */   
    public ArrayList<Integer> verMovimientos (Torre torre){
        
        ArrayList<Integer> arreglo = new ArrayList<>();
        TableroServicio tableroservicio = new TableroServicio();
        
        Jugador color=torre.getJugador(), contrario;
        if (color.equals(negro)){
            contrario=blanco;
        }else{
            contrario=negro;
        }
        
        int casilla=torre.getPos_tablero();
         do {
             casilla=casilla+10;
             if (casilla<100){
                 if (tableroservicio.posicionOcupada(casilla, color)){
                     break;
                 }else{
                     arreglo.add(casilla);
                     if (tableroservicio.posicionOcupada(casilla, contrario)){
                         break;
                     }
                 }
             }
         } while (casilla<90);
         
         casilla=torre.getPos_tablero();
         do {
             casilla=casilla-10;
             if (casilla>10){
                 if (tableroservicio.posicionOcupada(casilla, color)){
                     break;
                 }else{
                     arreglo.add(casilla);
                     if (tableroservicio.posicionOcupada(casilla, contrario)){
                         break;
                     }
                 }
             }
         } while (casilla>20);
         
         casilla=torre.getPos_tablero();
         do {
             casilla=casilla-1;
             if (casilla%10!=0){
                 if (tableroservicio.posicionOcupada(casilla, color)){
                     break;
                 }else{
                     arreglo.add(casilla);
                     if (tableroservicio.posicionOcupada(casilla, contrario)){
                         break;
                     }
                 }
             }
         } while (casilla%10!=0);
         
         casilla=torre.getPos_tablero();
         do {
             casilla=casilla+1;
             if (casilla%10!=0){
                 if (tableroservicio.posicionOcupada(casilla, color)){
                     break;
                 }else{
                     arreglo.add(casilla);
                     if (tableroservicio.posicionOcupada(casilla, contrario)){
                         break;
                     }
                 }
             }
         } while (casilla%10!=0);
         
         if (torre.isCoronado()){
             casilla=torre.getPos_tablero();
             if ((casilla<89) && (!tableroservicio.posicionOcupada(casilla+11, color))){
                 arreglo.add(casilla+11);
             }
             
             if ((casilla<90) && (!tableroservicio.posicionOcupada(casilla+9, color))){
                 arreglo.add(casilla+9);
             }
             
             if ((casilla>21) && (!tableroservicio.posicionOcupada(casilla-11, color))){
                 arreglo.add(casilla+11);
             }
             
             if ((casilla>20) && (!tableroservicio.posicionOcupada(casilla-9, color))){
                 arreglo.add(casilla+9);
             }
         }
                 
        return arreglo;
     }

    /**Recibe una torre y busca en el arreglo de posibles movimientos si conincide con el propuesto por el jugador*/
    public boolean consultaMovimiento(Torre torre, int movimiento) {
        boolean verificador=false;
            for (Object movimiento_legal : torre.getPosibles_movimientos()) {
                if (movimiento_legal.equals(movimiento)){
                    verificador=true;
                    break;
                }
            }
            
            return verificador;
    }
}
