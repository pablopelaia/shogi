package Shogi.Shogi.servicios;

import Shogi.Shogi.entidades.Peon;
import Shogi.Shogi.entidades.Rey;
import Shogi.Shogi.entidades.Tablero;
import Shogi.Shogi.enumeraciones.Jugador;
import static Shogi.Shogi.enumeraciones.Jugador.blanco;
import static Shogi.Shogi.enumeraciones.Jugador.negro;
import java.util.ArrayList;
import java.util.HashSet;

public class PeonServicio {
    
    /**
     * Se crean 18 peones del 17 al 34, se distingue si es blanco o negro y la posición se le calcula de acuerdo a su
     * Id, el mismo llegará como parámetro.    
    */ 
    public void Crear (Peon peon, HashSet<Integer> piezas_mov, HashSet<Integer> casillas_color, int id, Jugador jugador){
        
        ArrayList<Integer> posibles_movimientos = new ArrayList<>();
        String nombre;
        
        peon.setId(id);
        peon.setTipo("Peo");
        peon.setJugador(jugador);
        
        if (jugador.equals(negro)){
            peon.setPos_tablero(peon.getId()+54);            
            posibles_movimientos.add(peon.getId()+44);
            piezas_mov.add(peon.getId()+44);
        }else{
            peon.setPos_tablero(peon.getId()-5); 
            posibles_movimientos.add(peon.getId()+5);
            piezas_mov.add(peon.getId()+5);
        }
        
        casillas_color.add(peon.getPos_tablero());
                        
        if (peon.getJugador().equals(negro)){
            nombre="N  "+peon.getTipo();
        }else{
            nombre="B  "+peon.getTipo();
        }
        
        peon.setNombre(nombre);
        peon.setPosibles_movimientos(posibles_movimientos);
        peon.setCapturado(false);
        peon.setCoronado(false);
        
    }
    
     /**
     * Devuelve un arreglo con las casillas de los posibles movimientos que tendrá la pieza en el siguiente turno (Si
     * bien un peón puede tener un sólo movimiento posible, si está coronado podrá más de uno).
     * Recibe un peón y defimos como variable casilla para la posición a ocupar.
     
     1° Primero se llama al método que determinará si es una pieza clavada o no (En ajedrez se le dice pieza clavada a 
      la pieza que no puede moverse de su casilla por estar protegiendo a su rey). En caso de ser pieza clavada, tendrá
      solamente movimeintos en el espacio que haya entre su rey y la pieza que lo amenaza.
     
     2° Primero analizamos si está o no coronada la pieza, de ser así se llama al método correspondiente, de lo
        contrario se analizo primero si es blanco o negro y luego vemos si es posible agregar un movimiento.
     */
    public ArrayList<Integer> verMovimientos (Peon peon, Tablero tablero){
        
        ArrayList<Integer> movimientos = new ArrayList<>();
        ArrayList<Integer> espacio_entre_rey_amenaza = new ArrayList<>();
        PartidaServicio partidaServicio = new PartidaServicio();
        TableroServicio tableroservicio = new TableroServicio();
        Rey rey = new Rey();
        
        if (peon.getJugador().equals(negro)){
            rey=tablero.getRey_negro();
        }else{
            rey=tablero.getRey_blanco();
        }
        
        int casilla=peon.getPos_tablero();
        boolean clavada=false;
        
        partidaServicio.EspacioReyAmenaza(espacio_entre_rey_amenaza, "clavada", 200, rey.getPos_tablero(), tablero, peon.getJugador(), clavada, peon.getId());
        
        if (clavada){
            movimientos=espacio_entre_rey_amenaza;
        }else{
            if (peon.isCoronado()){
                GeneralDeOroServicio generaldeoroservicio = new GeneralDeOroServicio();
                movimientos=(generaldeoroservicio.verMovimientos(peon.getJugador(), casilla, tablero));
            }else{
                if (peon.getJugador().equals(negro)){
                    if ((casilla-10)>10 && !tableroservicio.posicionOcupada(tablero, casilla-10, negro)){
                        movimientos.add(casilla-10);
                    }
                }else{
                    if ((casilla+10)<100 && !tableroservicio.posicionOcupada(tablero, casilla+10, blanco)){
                        movimientos.add(casilla+10);
                    }
                }
            }
        }
        return movimientos;
    }

    /**Recibe un peón y busca en el arreglo de posibles movimientos si conincide con el propuesto por el jugador*/
    public boolean consultaMovimiento(Peon peon, int movimiento) {
        
        boolean verificador=false;
        
            for (Object movimiento_legal : peon.getPosibles_movimientos()) {
                
                if (movimiento_legal.equals(movimiento)){
                    verificador=true;
                    break;
                }
            }
            
            return verificador;
    }
}
