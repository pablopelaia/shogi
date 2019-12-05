package Shogi.Shogi.servicios;

import Shogi.Shogi.entidades.Rey;
import Shogi.Shogi.entidades.Tablero;
import Shogi.Shogi.enumeraciones.Jugador;
import static Shogi.Shogi.enumeraciones.Jugador.negro;
import java.util.ArrayList;
import java.util.HashSet;

public class ReyServicio {
    
    public Rey Crear (Jugador jugador, HashSet<Integer> piezas_mov, HashSet<Integer> casillas_color, int id){
        
        ArrayList<Integer> posibles_movimientos = new ArrayList<>();
        
        Rey rey=new Rey();
        String nombre;
        
        rey.setId(id);
        rey.setTipo("Rey");
        rey.setJugador(jugador);
        
        if (jugador.equals(negro)){            
            rey.setPos_tablero(95);
            posibles_movimientos.add(84);
            posibles_movimientos.add(85);
            posibles_movimientos.add(86);
            piezas_mov.add(84);
            piezas_mov.add(85);
            piezas_mov.add(86);
        }else{
            rey.setPos_tablero(15);
            posibles_movimientos.add(24);
            posibles_movimientos.add(25);
            posibles_movimientos.add(26);
            piezas_mov.add(24);
            piezas_mov.add(25);
            piezas_mov.add(26);
        }
        
        casillas_color.add(rey.getPos_tablero());
               
        if (rey.getJugador().equals(negro)){
            nombre="N  "+rey.getTipo();
        }else{
            nombre="B  "+rey.getTipo();
        }
        
        rey.setNombre(nombre);        
        rey.setPosibles_movimientos(posibles_movimientos);
        rey.setJaque(false);
        
        return rey;
    }
    
/*El método devolverá el arreglo con todos los movimientos posibles del rey en casillas no ocupadas por piezas de su
color, luego en otros métodos se eliminan las casillas que provacan jaque.*/
    
    public ArrayList<Integer> verMovimientos (Tablero tablero, Rey rey){
        ArrayList<Integer> arreglo = new ArrayList<>();
        TableroServicio tableroservicio = new TableroServicio();        
                
        int casilla=rey.getPos_tablero()+11;
        if ((casilla<100) && (!tableroservicio.posicionOcupada(tablero, casilla, rey.getJugador()))){
            arreglo.add(casilla);
        }
        
        casilla=rey.getPos_tablero()+10;
        if ((casilla<100) && (!tableroservicio.posicionOcupada(tablero, casilla, rey.getJugador()))){
            arreglo.add(casilla);
        }
        
        casilla=rey.getPos_tablero()+9;
        if ((casilla<100) && (!tableroservicio.posicionOcupada(tablero, casilla, rey.getJugador()))){
            arreglo.add(casilla);
        }
        
        casilla=rey.getPos_tablero()+1;
        if ((casilla%10!=0) && (!tableroservicio.posicionOcupada(tablero, casilla, rey.getJugador()))){
            arreglo.add(casilla);
        }
        
        casilla=rey.getPos_tablero()-1;
        if ((casilla%10!=0) && (!tableroservicio.posicionOcupada(tablero, casilla, rey.getJugador()))){
            arreglo.add(casilla);
        }
        
        casilla=rey.getPos_tablero()-11;
        if ((casilla>10) && (!tableroservicio.posicionOcupada(tablero, casilla, rey.getJugador()))){
            arreglo.add(casilla);
        }
        
        casilla=rey.getPos_tablero()-10;
        if ((casilla>10) && (!tableroservicio.posicionOcupada(tablero, casilla, rey.getJugador()))){
            arreglo.add(casilla);
        }
        
        casilla=rey.getPos_tablero()-9;
        if ((casilla>10) && (!tableroservicio.posicionOcupada(tablero, casilla, rey.getJugador()))){
            arreglo.add(casilla);
        }
                
        return arreglo;
    }    

    /**Recibe un rey y busca en el arreglo de posibles movimientos si conincide con el propuesto por el jugador*/
    public boolean consultaMovimiento(Rey rey, int movimiento) {
        boolean verificador=false;
            for (Object movimiento_legal : rey.getPosibles_movimientos()) {
                if (movimiento_legal.equals(movimiento)){
                    verificador=true;
                    break;
                }
            }
            
            return verificador;               
    }
}
