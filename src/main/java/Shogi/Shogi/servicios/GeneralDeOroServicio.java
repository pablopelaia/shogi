package Shogi.Shogi.servicios;

import Shogi.Shogi.entidades.GeneralDeOro;
import Shogi.Shogi.entidades.Rey;
import Shogi.Shogi.entidades.Tablero;
import Shogi.Shogi.enumeraciones.Jugador;
import static Shogi.Shogi.enumeraciones.Jugador.negro;
import java.util.ArrayList;
import java.util.HashSet;

public class GeneralDeOroServicio {
    
    /**
     * Se crean 4 generales de oro del 7 al 10, con un "switch" distingo si es negro o blanco y asigno posición.    
    */ 
    public void Crear (GeneralDeOro oro,  HashSet<Integer> piezas_mov, HashSet<Integer> casillas_color, int id, Jugador jugador){
        
        ArrayList<Integer> posibles_movimientos = new ArrayList<>();
        String nombre;
        
        oro.setId(id);
        oro.setTipo("GdO");
        oro.setJugador(jugador);
        
        switch (id){
            case 7:
                oro.setPos_tablero(94);                
                posibles_movimientos.add(83);
                piezas_mov.add(83);
                posibles_movimientos.add(84);
                piezas_mov.add(84);
                posibles_movimientos.add(85);
                piezas_mov.add(85);
                break;
            case 8:
                oro.setPos_tablero(96);
                posibles_movimientos.add(85);
                piezas_mov.add(85);
                posibles_movimientos.add(86);
                piezas_mov.add(86);
                posibles_movimientos.add(87);               
                piezas_mov.add(87);
                break;
            case 9:
                oro.setPos_tablero(14);
                posibles_movimientos.add(23);
                piezas_mov.add(23);
                posibles_movimientos.add(24);
                piezas_mov.add(24);
                posibles_movimientos.add(25);
                piezas_mov.add(25);
                break;
            case 10:
                oro.setPos_tablero(16);
                posibles_movimientos.add(25);
                piezas_mov.add(26);
                posibles_movimientos.add(27);
                piezas_mov.add(25);
                posibles_movimientos.add(26);
                piezas_mov.add(27);
                break;
        }
        
        casillas_color.add(oro.getPos_tablero());
        
        if (oro.getJugador().equals(negro)){
            nombre="▲☻"+oro.getTipo();
        }else{
            nombre="▼☺"+oro.getTipo();
        }
    
        oro.setNombre(nombre);        
        oro.setPosibles_movimientos(posibles_movimientos);
        oro.setCapturado(false);
                
    }
    
    /**
     * Devuelve un arreglo con las casillas de los posibles movimientos que tendrá la pieza en el siguiente turno.
     * A diferencia de los métodos homónimos en las otras piezas, aca recibe el color y casilla en la que se encuentra la
     * pieza. Esto se debe a que el método será utilizado en otras piezas que sean coronadas.
     * Defino la variable movimiento para la posición a ocupar. 
     
    1° Ya que este método puede recibir piezas coronadas que no son originalmente Gral de Oro, primero consilto si lo son
     y de ser ser así veo si es pieza clavada o no (las otras piezas si llegan a este punto es porque ya se consultó antes).
     
    2° Se llama al método que determinará si es una pieza clavada o no (En ajedrez se le dice pieza clavada a 
      la pieza que no puede moverse de su casilla por estar protegiendo a su rey). En caso de ser pieza clavada, tendrá
      solamente movimeintos en el espacio que haya entre su rey y la pieza que lo amenaza.
    
    3° Defino los cuatro posibles movientos que son iguales, tanto para piezas negras como para blancas, y luego
    consulto el color para analizar los dos movimientos restantes.
     */
    
    public ArrayList<Integer> verMovimientos (Jugador color, int casilla, Tablero tablero){
        
        ArrayList<Integer> arreglo = new ArrayList<>();
        ArrayList<Integer> espacio_entre_rey_amenaza = new ArrayList<>();
        PartidaServicio partidaServicio = new PartidaServicio();
        TableroServicio tableroservicio = new TableroServicio();
        Rey rey = new Rey();
        
        int movimiento=casilla+10;
        boolean clavada=false;
        
        if (casilla>6 && casilla<11){
            int id_casilla=partidaServicio.buscaId(casilla, tablero);            
            if (color.equals(negro)){
                rey=tablero.getRey_negro();
            }else{
                rey=tablero.getRey_blanco();
            }
            
            partidaServicio.EspacioReyAmenaza(espacio_entre_rey_amenaza, "clavada", 200, rey.getPos_tablero(), tablero, color, clavada, id_casilla);
        }
        
        if (clavada){
            arreglo=espacio_entre_rey_amenaza;
        }else{
            
            if (movimiento<100){      
                if (!tableroservicio.posicionOcupada(tablero, movimiento, color)){
                    arreglo.add(movimiento);
                }
            }

            movimiento=casilla-10;
            if (movimiento>10){      
                if (!tableroservicio.posicionOcupada(tablero, movimiento, color)){
                    arreglo.add(movimiento);
                }
            }

            movimiento=casilla+1;
            if (movimiento%10!=0){      
                if (!tableroservicio.posicionOcupada(tablero, movimiento, color)){
                    arreglo.add(movimiento);
                }
            }

            movimiento=casilla-1;
            if (movimiento%10!=0){      
                if (!tableroservicio.posicionOcupada(tablero, movimiento, color)){
                    arreglo.add(movimiento);
                }
            }

            if (color.equals(negro)){
                movimiento=casilla-11;
                if (movimiento>10){
                    if (!tableroservicio.posicionOcupada(tablero, movimiento, color)){
                        arreglo.add(movimiento);
                    }
                }
                movimiento=casilla-9;
                if (movimiento>11){
                    if (!tableroservicio.posicionOcupada(tablero, movimiento, color)){
                        arreglo.add(movimiento);
                    }
                }
            }else{
                movimiento=casilla+11;
                if (movimiento<100){
                    if (!tableroservicio.posicionOcupada(tablero, movimiento, color)){
                        arreglo.add(movimiento);
                    }
                }
                movimiento=casilla+9;
                if (movimiento<99){
                    if (!tableroservicio.posicionOcupada(tablero, movimiento, color)){
                        arreglo.add(movimiento);
                    }
                }            
            }
        }
        return arreglo;
    }
    
    /**Recibe un gral. de Oro y busca en el arreglo de posibles movimientos si conincide con el propuesto por el jugador*/
    public boolean consultaMovimiento(GeneralDeOro oro, int movimiento) {
         boolean verificador=false;
            for (Object movimiento_legal : oro.getPosibles_movimientos()) {
                if (movimiento_legal.equals(movimiento)){
                    verificador=true;
                    break;
                }
            }
            
            return verificador;
    }
}