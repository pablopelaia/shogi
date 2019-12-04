package Shogi.Shogi.servicios;

import Shogi.Shogi.entidades.Alfil;
import Shogi.Shogi.entidades.Rey;
import Shogi.Shogi.entidades.Tablero;
import Shogi.Shogi.enumeraciones.Jugador;
import static Shogi.Shogi.enumeraciones.Jugador.blanco;
import static Shogi.Shogi.enumeraciones.Jugador.negro;
import java.util.ArrayList;
import java.util.HashSet;


/**
     * Creo dos alfiles, 1 y 2, con un "if - else" distingo si es negro o blanco.    
    */ 
public class AlfilServicio {
    
    public void Crear (Alfil alfil, HashSet<Integer> piezas_mov, HashSet<Integer> casillas_color, int id, Jugador jugador){
        
        ArrayList<Integer> posibles_movimientos = new ArrayList<>();
        String nombre;        
    
        alfil.setTipo("Alf");
        alfil.setJugador(jugador);
        alfil.setId(id);
        
        if (jugador.equals(negro)){
            
            alfil.setPos_tablero(82);
            piezas_mov.add(81);
            posibles_movimientos.add(81);
            piezas_mov.add(82); 
            posibles_movimientos.add(82);            
    }else{
            
            alfil.setPos_tablero(28);
            piezas_mov.add(27);
            posibles_movimientos.add(27);
            piezas_mov.add(29);
            posibles_movimientos.add(29);
    }
        casillas_color.add(alfil.getPos_tablero());
        
        if (alfil.getJugador().equals(negro)){
            nombre="▲☻_"+alfil.getTipo();
        }else{
            nombre="▼☺_"+alfil.getTipo();
        }
        alfil.setNombre(nombre);        
        alfil.setPosibles_movimientos(posibles_movimientos);
        alfil.setCoronado(false);
        alfil.setCapturado(false);
    }
    
    /**
     * Devuelve un arreglo con las casillas de los posibles movimientos que tendrá la pieza en el siguiente turno.
     * Recibe un Alfil y defino como variables casilla para la posición a ocupar, color y contrario para definir blanco
     * y negro el que defiende y el que ataca (esto último es porque a diferencia de otras piezas, los alfiles tienen
     * los mismos movimientos independientemente de su color; solamente necesitaremos disinguir blancas o negras para ver
     * si una casilla está ocupada y por quién).
     
     1° Primero se llama al método que determinará si es una pieza clavada o no (En ajedrez se le dice pieza clavada a 
      la pieza que no puede moverse de su casilla por estar protegiendo a su rey). En caso de ser pieza clavada, tendrá
      solamente movimeintos en el espacio que haya entre su rey y la pieza que lo amenaza.
     
     2° Con 4 "Do - While" determino las diagonales posibles, busco primero que las casillas de la misma no estén ocupadas
      por fichas propias o rivales (en el primer no se permite el movimiento y en el segundo si lo hace pero se trunca la
      diagonal.
     3° Si la pieza está coronada se analizan cuatro movimientos posibles más.
     */    
    public ArrayList<Integer> verMovimientos (Alfil alfil, Tablero tablero){
        
        ArrayList<Integer> movimientos = new ArrayList<>();
        ArrayList<Integer> espacio_entre_rey_amenaza = new ArrayList<>();
        PartidaServicio partidaServicio = new PartidaServicio();
        TableroServicio tableroservicio = new TableroServicio();
        Rey rey = new Rey();
        
        Jugador contrario, color=alfil.getJugador();
        int casilla;
        boolean clavada=false;
        
        if (color.equals(negro)){
            rey=tablero.getRey_negro();
        }else{
            rey=tablero.getRey_blanco();
        }
                
        partidaServicio.EspacioReyAmenaza(espacio_entre_rey_amenaza, "clavada", 200, rey.getPos_tablero(), tablero, color, clavada, alfil.getId());
        
        if (clavada){
            movimientos=espacio_entre_rey_amenaza;
        }else{
            if (color.equals(negro)){
                contrario=blanco;
            }else{
                contrario=negro;
            }

            casilla=alfil.getPos_tablero();
            do {
                casilla=casilla+9;
                if (casilla<99 && casilla%10!=0){
                    if (tableroservicio.posicionOcupada(tablero, casilla, color)){
                        break;
                    }else{
                        movimientos.add(casilla);
                        if (tableroservicio.posicionOcupada(tablero, casilla, contrario)){
                            break;
                        }
                    }
                }else{
                    break;
                }
            } while (casilla<90);

            casilla=alfil.getPos_tablero();
            do {
                casilla=casilla-9;
                if (casilla>11 && casilla%10!=0){
                    if (tableroservicio.posicionOcupada(tablero, casilla, color)){
                        break;
                    }else{
                        movimientos.add(casilla);
                        if (tableroservicio.posicionOcupada(tablero, casilla, contrario)){
                            break;
                        }
                    }
                }else{
                    break;
                }
            } while (casilla>20);

            casilla=alfil.getPos_tablero();
            do {
                casilla=casilla+11;
                if (casilla<100 && casilla%10!=0){
                    if (tableroservicio.posicionOcupada(tablero, casilla, color)){
                        break;
                    }else{
                        movimientos.add(casilla);
                        if (tableroservicio.posicionOcupada(tablero, casilla, contrario)){
                            break;
                        }
                    }
                }else{
                    break;
                }
            } while (casilla<89);

            casilla=alfil.getPos_tablero();
            do {
                casilla=casilla-11;
                if (casilla>10 && casilla%10!=0){
                    if (tableroservicio.posicionOcupada(tablero, casilla, color)){
                        break;
                    }else{
                        movimientos.add(casilla);
                        if (tableroservicio.posicionOcupada(tablero, casilla, contrario)){
                            break;
                        }
                    }
                }else{
                    break;
                }
            } while (casilla>21);

            if (alfil.isCoronado()){

                casilla=alfil.getPos_tablero()+10;
                if (casilla<100){
                    if (tableroservicio.posicionOcupada(tablero, casilla, negro)){
                        if (alfil.getJugador().equals(blanco)){
                            movimientos.add(casilla);
                        }
                    }else{
                        if (tableroservicio.posicionOcupada(tablero, casilla, blanco)){
                            if (alfil.getJugador().equals(negro)){
                                movimientos.add(casilla);                            
                            }
                        }else{
                            movimientos.add(casilla);
                        }
                    }
                }

                casilla=alfil.getPos_tablero()-10;
                if (casilla>10){
                    if (tableroservicio.posicionOcupada(tablero, casilla, negro)){
                        if (alfil.getJugador().equals(blanco)){
                            movimientos.add(casilla);
                        }
                    }else{
                        if (tableroservicio.posicionOcupada(tablero, casilla, blanco)){
                            if (alfil.getJugador().equals(negro)){
                                movimientos.add(casilla);                            
                            }
                        }else{
                            movimientos.add(casilla);
                        }
                    }
                }

                casilla=alfil.getPos_tablero()+1;
                if (casilla%10!=0){
                    if (tableroservicio.posicionOcupada(tablero, casilla, negro)){
                        if (alfil.getJugador().equals(blanco)){
                            movimientos.add(casilla);
                        }
                    }else{
                        if (tableroservicio.posicionOcupada(tablero, casilla, blanco)){
                            if (alfil.getJugador().equals(negro)){
                                movimientos.add(casilla);                            
                            }
                        }else{
                            movimientos.add(casilla);
                        }
                    }
                }

                casilla=alfil.getPos_tablero()-1;
                if (casilla%10!=0){
                    if (tableroservicio.posicionOcupada(tablero, casilla, negro)){
                        if (alfil.getJugador().equals(blanco)){
                            movimientos.add(casilla);
                        }
                    }else{
                        if (tableroservicio.posicionOcupada(tablero, casilla, blanco)){
                            if (alfil.getJugador().equals(negro)){
                                movimientos.add(casilla);                            
                            }
                        }else{
                            movimientos.add(casilla);
                        }
                    }
                }
            }
        }
        return movimientos;
    }
    
    /**Recibe un alfil y busca en el arreglo de posibles movimientos si conincide con el propuesto por el jugador*/
        public boolean consultaMovimiento(Alfil alfil, int movimiento){
            
            boolean verificador=false;
            
            for (Object movimiento_legal : alfil.getPosibles_movimientos()) {
                
                if (movimiento_legal.equals(movimiento)){
                    verificador=true;
                    break;
                }
            }
            
            return verificador;
        }
}
