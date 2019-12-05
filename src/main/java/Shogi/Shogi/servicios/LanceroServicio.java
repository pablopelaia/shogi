package Shogi.Shogi.servicios;

import Shogi.Shogi.entidades.Lancero;
import Shogi.Shogi.entidades.Rey;
import Shogi.Shogi.entidades.Tablero;
import Shogi.Shogi.enumeraciones.Jugador;
import static Shogi.Shogi.enumeraciones.Jugador.blanco;
import static Shogi.Shogi.enumeraciones.Jugador.negro;
import java.util.ArrayList;
import java.util.HashSet;

public class LanceroServicio {
     
    /**
     * Se crean 2 lanceros, 15 y 16 con un "if- while" distingo si es negro o blanco y asigno posición, esta pieza no
     * tiene movimientos posibles en el inicio de la partida.    
    */ 
    public void Crear (Lancero lancero, int id, Jugador jugador, HashSet<Integer> casillas_color){
        
        String nombre;
        
        lancero.setId(id);
        lancero.setTipo("Lan");
        lancero.setJugador(jugador);   
    
        if (jugador.equals(negro)){
            lancero.setPos_tablero(88);            
        }else{
            lancero.setPos_tablero(22);
        }
                
        casillas_color.add(lancero.getPos_tablero());
        
        if (lancero.getJugador().equals(negro)){
            nombre="N  "+lancero.getTipo();
        }else{
            nombre="B  "+lancero.getTipo();
        }
        
        lancero.setNombre(nombre);
        lancero.setPosibles_movimientos(new ArrayList<>()); 
        lancero.setCapturado(false);
        lancero.setCoronado(false);
        
    }
    
    /**
     * Devuelve un arreglo con las casillas de los posibles movimientos que tendrá la pieza en el siguiente turno.
     * Recibe un Lancero y defino como variables casilla para la posición a ocupar. 
     1° Primero se llama al método que determinará si es una pieza clavada o no (En ajedrez se le dice pieza clavada a 
      la pieza que no puede moverse de su casilla por estar protegiendo a su rey). En caso de ser pieza clavada, tendrá
      solamente movimeintos en el espacio que haya entre su rey y la pieza que lo amenaza.
    
    2° En primer lugar se analiza si es o no coronada la pieza, de ser así se llama al método correspondiente, de lo 
    
    3° contrario, en primer lugar analizamos si es blanca o negra y en base a eso se le asignan los movimientos:
     * se busca primero que las casillas de la misma no estén ocupadas por fichas propias o rivales (en el primer caso no
     * se permite el movimiento y en el segundo si lo hace pero se trunca el avance).
     */  
    public ArrayList<Integer> verMovimientos (Lancero lancero, Tablero tablero){
        
        ArrayList<Integer> movimientos = new ArrayList<>();
        ArrayList<Integer> espacio_entre_rey_amenaza = new ArrayList<>();
        PartidaServicio partidaServicio = new PartidaServicio();
        TableroServicio tableroservicio = new TableroServicio();
        Rey rey = new Rey();
        
        int casilla=lancero.getPos_tablero();
        boolean clavada=false;
        
        if (lancero.getJugador().equals(negro)){
            rey=tablero.getRey_negro();
        }else{
            rey=tablero.getRey_blanco();
        }
        
        partidaServicio.EspacioReyAmenaza(espacio_entre_rey_amenaza, "clavada", 200, rey.getPos_tablero(), tablero, lancero.getJugador(), clavada, lancero.getId());
        
        if (clavada){
            movimientos=espacio_entre_rey_amenaza;
        }else{
            if (lancero.isCoronado()){
                GeneralDeOroServicio generaldeoroservicio = new GeneralDeOroServicio();
                movimientos=(generaldeoroservicio.verMovimientos(lancero.getJugador(), casilla, tablero));
            }else{
                if (lancero.getJugador().equals(negro)){
                    do {
                        casilla=casilla-10;
                        if(casilla>10){
                            if (lancero.getJugador().equals(negro)){
                                break;
                            }else{
                                movimientos.add(casilla);
                                if (tableroservicio.posicionOcupada(tablero, casilla, blanco)){
                                    break;
                                }
                            }
                        }
                    } while (casilla>20);                
                }else{
                     do {
                        casilla=casilla+10;
                        if(casilla<100){
                            if (lancero.getJugador().equals(negro)){
                                break;
                            }else{
                                movimientos.add(casilla);
                                if (tableroservicio.posicionOcupada(tablero, casilla, blanco)){
                                    break;
                                }
                            }
                        }
                    } while (casilla<90); 

                }
            }
        }
        return movimientos;
    }

    /**Recibe un lancero y busca en el arreglo de posibles movimientos si conincide con el propuesto por el jugador*/
    public boolean consultaMovimiento(Lancero lancero, int movimiento) {
        boolean verificador=false;
            for (Object movimiento_legal : lancero.getPosibles_movimientos()) {
                if (movimiento_legal.equals(movimiento)){
                    verificador=true;
                    break;
                }
            }
            
            return verificador;
    }
}