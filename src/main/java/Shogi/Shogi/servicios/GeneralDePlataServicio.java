package Shogi.Shogi.servicios;

import Shogi.Shogi.entidades.GeneralDePlata;
import Shogi.Shogi.entidades.Rey;
import Shogi.Shogi.entidades.Tablero;
import Shogi.Shogi.enumeraciones.Jugador;
import static Shogi.Shogi.enumeraciones.Jugador.negro;
import java.util.ArrayList;
import java.util.HashSet;

public class GeneralDePlataServicio {
    
    /**
     * Creo cuatro generales de plata del 11 al 14, con un "switch" distingo si es negro o blanco y asigno posición.    
    */ 
    public void Crear (GeneralDePlata plata,  HashSet<Integer> piezas_mov, HashSet<Integer>  casillas_color, int id, Jugador jugador){
        
        ArrayList<Integer> posibles_movimientos = new ArrayList<>();
        String nombre;
        
        plata.setId(id);
        plata.setTipo("GdP");
        plata.setJugador(jugador);
        
        switch (id){
            case 11:
                plata.setPos_tablero(93);
                posibles_movimientos.add(83);
                posibles_movimientos.add(84);
                piezas_mov.add(83);
                piezas_mov.add(84);
                break;
            case 12:
                plata.setPos_tablero(97);
                posibles_movimientos.add(86);
                posibles_movimientos.add(87);
                piezas_mov.add(86);
                piezas_mov.add(87);
                break;
            case 13:
                plata.setPos_tablero(13);
                posibles_movimientos.add(23);
                posibles_movimientos.add(24);
                piezas_mov.add(23);
                piezas_mov.add(24);
                break;
            case 14:
                plata.setPos_tablero(17);
                posibles_movimientos.add(26);
                posibles_movimientos.add(27);
                piezas_mov.add(26);
                piezas_mov.add(27);
                break;
        }
        
        casillas_color.add(plata.getPos_tablero());
        
        if (plata.getJugador().equals(negro)){
            nombre="N  "+plata.getTipo();
        }else{
            nombre="B  "+plata.getTipo();
        }
    
        plata.setNombre(nombre);        
        plata.setPosibles_movimientos(posibles_movimientos);
        plata.setCapturado(false);
        plata.setCoronado(false);
        
    }
    
    /**
     * Devuelve un arreglo con las casillas de los posibles movimientos que tendrá la pieza en el siguiente turno.
     * Recibe un general de plata y defino como variables casilla para la posición a ocupar. 
     
    1° Primero se llama al método que determinará si es una pieza clavada o no (En ajedrez se le dice pieza clavada a 
      la pieza que no puede moverse de su casilla por estar protegiendo a su rey). En caso de ser pieza clavada, tendrá
      solamente movimeintos en el espacio que haya entre su rey y la pieza que lo amenaza.
      * 
    2° Analizo si está o no coronada la pieza, de ser así llamo al método correspondiente.
     
    3° De lo contrario, primero agrego los 4 posibles movimientos comunes a ambos colores y luego el que falta según
      corresponde.
     */  
    public ArrayList<Integer> verMovimientos (GeneralDePlata generaldeplata, Tablero tablero){
        
        ArrayList<Integer> movimiento = new ArrayList<>();
        ArrayList<Integer> espacio_entre_rey_amenaza = new ArrayList<>();
        PartidaServicio partidaServicio = new PartidaServicio();
        TableroServicio tableroservicio = new TableroServicio();
        Rey rey = new Rey();
        
        int casilla=generaldeplata.getPos_tablero();
        boolean clavada=false;
        
        if (generaldeplata.getJugador().equals(negro)){
            rey=tablero.getRey_negro();
        }else{
            rey=tablero.getRey_blanco();
        }
        
        partidaServicio.EspacioReyAmenaza(espacio_entre_rey_amenaza, "clavada", 200, rey.getPos_tablero(), tablero, generaldeplata.getJugador(), clavada, generaldeplata.getId());
        
        if (clavada){
            movimiento=espacio_entre_rey_amenaza;
        }else{
            if (generaldeplata.isCoronado()){
                GeneralDeOroServicio generaldeoroSsrvicio = new GeneralDeOroServicio();
                movimiento=generaldeoroSsrvicio.verMovimientos(generaldeplata.getJugador(), casilla, tablero);
            }else{
                casilla=casilla+9;
                if(casilla<99 && casilla%10!=0 && !tableroservicio.posicionOcupada(tablero, casilla, generaldeplata.getJugador())){
                    movimiento.add(casilla);
                }

                casilla=generaldeplata.getPos_tablero()+11;
                if(casilla<100 && casilla%10!=0 && !tableroservicio.posicionOcupada(tablero, casilla, generaldeplata.getJugador())){
                    movimiento.add(casilla);
                }

                casilla=casilla-9;
                if(casilla>11 && casilla%10!=0 && !tableroservicio.posicionOcupada(tablero, casilla, generaldeplata.getJugador())){
                    movimiento.add(casilla);
                }
                casilla=generaldeplata.getPos_tablero()-11;
                if(casilla>10 && casilla%10!=0 && !tableroservicio.posicionOcupada(tablero, casilla, generaldeplata.getJugador())){
                    movimiento.add(casilla);
                }

                if (generaldeplata.getJugador().equals(negro)){
                    casilla=generaldeplata.getPos_tablero()-10;
                    if (casilla>10 && !tableroservicio.posicionOcupada(tablero, casilla, negro)){
                        movimiento.add(casilla);
                    }
                }else{
                    casilla=generaldeplata.getPos_tablero()+10;
                    if (casilla<100 && !tableroservicio.posicionOcupada(tablero, casilla, negro)){
                        movimiento.add(casilla);
                    }                
                }
            }
        }
        return movimiento;
    }
    
    /**Recibe un gral de Plata y busca en el arreglo de posibles movimientos si conincide con el propuesto por el jugador*/
    public boolean consultaMovimiento(GeneralDePlata plata, int movimiento) {
         boolean verificador=false;
            for (Object movimiento_legal : plata.getPosibles_movimientos()) {
                if (movimiento_legal.equals(movimiento)){
                    verificador=true;
                    break;
                }
            }
            
            return verificador;               
    }
}
