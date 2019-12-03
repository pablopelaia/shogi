package Shogi.Shogi.servicios;

import Shogi.Shogi.entidades.Caballo;
import Shogi.Shogi.entidades.Rey;
import Shogi.Shogi.entidades.Tablero;
import Shogi.Shogi.enumeraciones.Jugador;
import static Shogi.Shogi.enumeraciones.Jugador.negro;
import java.util.ArrayList;
import java.util.HashSet;

public class CaballoServicio {
    /**
     * Se crean cuatro caballos del 3 al 6, con un "switch" distingo si es negro o blanco y asigno posición.
    */     
    public void Crear (Caballo caballo, HashSet<Integer> piezas_mov, HashSet<Integer> casillas_color, int id, Jugador jugador) {
        
        ArrayList<Integer> posibles_movimientos = new ArrayList<>();
        String nombre;
        
        caballo.setId(id);
        caballo.setTipo("Cab");
        caballo.setJugador(jugador);
        
        switch (id){
            case 3:
                caballo.setPos_tablero(92);
                posibles_movimientos.add(71);
                piezas_mov.add(71);
                posibles_movimientos.add(73);
                piezas_mov.add(73);
                break;
            case 4:
                caballo.setPos_tablero(98);
                posibles_movimientos.add(67);
                piezas_mov.add(67);
                posibles_movimientos.add(69);
                piezas_mov.add(69);
                break;
            case 5:
                caballo.setPos_tablero(12);
                posibles_movimientos.add(31);
                piezas_mov.add(31);
                posibles_movimientos.add(33);
                piezas_mov.add(33);
                break;
            case 6:
                caballo.setPos_tablero(18);
                posibles_movimientos.add(37);
                piezas_mov.add(37);
                posibles_movimientos.add(39);
                piezas_mov.add(39);
                break;
        }
        
        casillas_color.add(caballo.getPos_tablero());
        
        if (caballo.getJugador().equals(negro)){
            nombre="▲☻"+caballo.getTipo();
        }else{
            nombre="▼☺"+caballo.getTipo();
        }
        
        caballo.setNombre(nombre);        
        caballo.setPosibles_movimientos(posibles_movimientos);
        
    }
    
    /**
     * Devuelve un arreglo con las casillas de los posibles movimientos que tendrá la pieza en el siguiente turno.
     * Recibe un caballo y defimos como variable casilla para la posición a ocupar. 
     
     1° Primero se llama al método que determinará si es una pieza clavada o no (En ajedrez se le dice pieza clavada a 
      la pieza que no puede moverse de su casilla por estar protegiendo a su rey). En caso de ser pieza clavada, tendrá
      solamente movimeintos en el espacio que haya entre su rey y la pieza que lo amenaza.
     
     2° Primero analizamos si está o no coronada la pieza, de ser así se llama al método correspondiente, de lo
      contrario se analizo primero si es blanco o negro y luego se buscan los posibles movimientos.
     */    
    public ArrayList<Integer> verMovimientos (Caballo caballo, Tablero tablero){
        
        ArrayList<Integer> movimiento = new ArrayList<>();
        ArrayList<Integer> espacio_entre_rey_amenaza = new ArrayList<>();
        PartidaServicio partidaServicio = new PartidaServicio();
        TableroServicio tableroservicio = new TableroServicio();
        Rey rey = new Rey();
        int casilla=caballo.getPos_tablero();
        boolean clavada=false;
        
        if (caballo.getJugador().equals(negro)){
            rey=tablero.getRey_negro();
        }else{
            rey=tablero.getRey_blanco();
        }
        
        partidaServicio.EspacioReyAmenaza(espacio_entre_rey_amenaza, "clavada", 200, rey.getPos_tablero(), tablero, caballo.getJugador(), clavada, caballo.getId());
        
        if (clavada){
            movimiento=espacio_entre_rey_amenaza;
        }else{
        
            if (caballo.isCoronado()){
                 GeneralDeOroServicio generaldeoroservicio = new GeneralDeOroServicio();
                 movimiento=(generaldeoroservicio.verMovimientos(caballo.getJugador(), casilla, tablero));            
            }else{
                if (caballo.getJugador().equals(negro)){

                    if (casilla<79 && (casilla+21)%10!=0 && !tableroservicio.posicionOcupada(casilla+21, negro)){
                        movimiento.add(casilla+21);
                    }

                    if (casilla<80 && (casilla+19)%10!=0 && !tableroservicio.posicionOcupada(casilla+19, negro)){
                        movimiento.add(casilla+19);
                    }                
                }else{
                    if (casilla>31 && (casilla-21)%10!=0 && !tableroservicio.posicionOcupada(casilla-21, negro)){
                        movimiento.add(casilla-21);
                    }

                    if (casilla>30 && (casilla-19)%10!=0 && !tableroservicio.posicionOcupada(casilla-19, negro)){
                        movimiento.add(casilla-19);
                    }
                }
            }
        }   
        return movimiento;
    }
    
    /**Recibe un caballo y busca en el arreglo de posibles movimientos si conincide con el propuesto por el jugador*/       
    public boolean consultaMovimiento(Caballo caballo, int movimiento) {
         boolean verificador=false;
            for (Object movimiento_legal : caballo.getPosibles_movimientos()) {
                if (movimiento_legal.equals(movimiento)){
                    verificador=true;
                    break;
                }
            }
            
            return verificador;
    }
}