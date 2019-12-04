package Shogi.Shogi.servicios;

import Shogi.Shogi.entidades.Alfil;
import Shogi.Shogi.entidades.Caballo;
import Shogi.Shogi.entidades.GeneralDeOro;
import Shogi.Shogi.entidades.GeneralDePlata;
import Shogi.Shogi.entidades.Partida;
import Shogi.Shogi.entidades.Lancero;
import Shogi.Shogi.entidades.Participante;
import Shogi.Shogi.entidades.Peon;
import Shogi.Shogi.entidades.Piezas;
import Shogi.Shogi.entidades.Rey;
import Shogi.Shogi.entidades.Tablero;
import Shogi.Shogi.entidades.Torre;
import Shogi.Shogi.enumeraciones.Jugador;
import static Shogi.Shogi.enumeraciones.Jugador.blanco;
import static Shogi.Shogi.enumeraciones.Jugador.negro;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JOptionPane;

public class PartidaServicio {
     
    public Partida crearPartida() {
        
        Partida partida = new Partida();
                
        Participante j1 = new Participante();
        Participante j2 = new Participante();
        Tablero tablero = new Tablero();
        
        TableroServicio tableroservicio = new TableroServicio ();
        ParticipanteServicio participantesercicio = new ParticipanteServicio();
        
        String nombre1=JOptionPane.showInputDialog("Ingrese el nombre del primer jugador");
        String nombre2=JOptionPane.showInputDialog("Ingrese el nombre del segundo jugador");
        
        participantesercicio.Crear(j1, j2, nombre1, nombre2);
        
        tablero=tableroservicio.crear();
        
        partida.setTablero(tablero);
        
        if (j1.getColor().equals(negro)){
            partida.setParticipante_negras(j1);
            partida.setParticipante_blancas(j2);
        }else{
            partida.setParticipante_negras(j2);
            partida.setParticipante_blancas(j1);
        }
        
        partida.setTurno(0);
        partida.setCheck_mate(false);
        
        return partida;
    }
        
    /**El mÃƒÂ©todo devolverÃƒÂ¡ un valor lÃƒÂ³gico, si es jaque mate o no; en caso de poner en jaque al rey sin que sea
     * mate, harÃƒÂ¡ un solamente se va a setear el atributo jaque verdadero del rey.
     1Ã‚Â° De acuerdo al color se ven si hay o no piezas capturadas por el jugador.
     
     2Ã‚Â° De haber piezas capturadas se consulta si desea ingresarla y de ser posible la misma coronada.
     
     3Ã‚Â° En caso que no se haya ingresado pieza, mediante un "do-while" se solicita al jugador que ingrese la casilla
      en donde se encuentra la pieza que desea mover y la casilla de destino. SaldrÃƒÂ¡ del bucle cuando haya ingresado
      una movimiento permitido. Si es permitido se llama al mÃƒÂ©todo "void" verificaMovimiento el cual recibe el turno
      correspondientes, el tablero, las casillas a mover y un verificador que dirÃƒÂ¡ si el movimiento es o no posible.
      De ser posible realiza el movimiento segÃƒÂºn corresponde.
      
      4Ã‚Â° Terminado el turno se setean todas las piezas (a excepciÃƒÂ³n del rey del siguiente turno). En el caso de las
        piezas del turno que acaba de concluir, tambiÃƒÂ©n se cuentan como posibles movimientos a las que provocan jaque
        a su propio rey. Esto es por si esos posibles movimientos ponen en jaque al rey del siguiente turno, a este se
        le niegue esa casilla (al rey que acaba de terminar esto no le afecta ya que falta un turno para mover por lo 
        que se volverÃƒÂ¡ a revisar antes que le vuelva a tocar, los posibles movimientos)
        
      5Ã‚Â° Se revisa los movimientos del rey del siguiente turno para ver si hay o no jauqe, estar en jaque se llama a
        otro mÃƒÂ©todo para buscar una soluciÃƒÂ³n al mismo, agotadas todas las posibilidades se determina que hay jaque mate
        es decir, finalizciÃƒÂ³n de la partida*/  
        
    public void nuevoTurno (Partida partida){
        
        boolean verificador = false, capturadas=false;
        int casilla_origen, casilla_destino, id_pieza;
        Jugador turno;
        String respuesta;
        
        ArrayList<Integer> piezas_capturadas = new ArrayList<>();
        TableroServicio tableroservicio = new TableroServicio();
        Tablero tablero = partida.getTablero();
                
        partida.setTurno(partida.getTurno()+1);
        
        if (partida.getTurno()%2==0){
                turno=blanco;
                piezas_capturadas=tablero.getCaptura_por_las_blancas();                
            }else{
                turno=negro;
                piezas_capturadas=tablero.getCapturada_por_las_negreas();
        }
        
        capturadas=(!piezas_capturadas.isEmpty());
        
        tableroservicio.imprimirTablero(partida, turno);
        
        if (capturadas){
            
            do {
                respuesta=JOptionPane.showInputDialog("Desea ingresar pieza capturada: si o no");
                
            } while (!(respuesta.equals("si")||respuesta.equals("no")));
            
            if (respuesta.equals("si")){
                
                ArrayList<String> capturadas_tipo =null;
                String tipo;
                
                tableroservicio.imprimeCapturadas(piezas_capturadas, capturadas_tipo);
                
                do {
                    casilla_origen=Integer.parseInt(JOptionPane.showInputDialog("ingrese id de la pieza que desea ingresar"));
                    casilla_destino=Integer.parseInt(JOptionPane.showInputDialog("ingrese el casillero al cual desea ingresar la pieza"));
                    
                    verificador=tableroservicio.compruebaCasillaLibre(casilla_destino, tablero);
                    
                } while (!verificador);
                
                respuesta=null;
                tipo=tableroservicio.buscaTipo (casilla_origen, piezas_capturadas, capturadas_tipo);
                
                if (!tipo.equals("General de Oro")){
                    
                    do {
                        respuesta=JOptionPane.showInputDialog("Desea ingresar la pieza en forma coronada: si o no");
                        
                    } while (!(respuesta.equals("si")||respuesta.equals("no")));
                }
                
                tableroservicio.ingresaPieza(casilla_origen, casilla_destino, respuesta, tablero, tipo);                                
            } 
        }
        
        if (!verificador){
                     
            do {
                casilla_origen=Integer.parseInt(JOptionPane.showInputDialog("ingrese el casillero de la pieza que desea mover"));
                casilla_destino=Integer.parseInt(JOptionPane.showInputDialog("ingrese el casillero al que desea mover la pieza"));

                if (casilla_destino==casilla_origen){
                    verificador=false;

                }else{    
                    id_pieza=buscaId(casilla_origen, tablero);

                    if (id_pieza==0){
                        verificador=false;

                    }else{
                        verificaMovimiento(verificador, turno, tablero, id_pieza, casilla_destino, casilla_origen);                    
                    }     
                }

                if (!verificador){
                    System.out.println("El movimiento ingresado es invÃƒÂ¡lido");
                }
            } while (!verificador);
        }
        
        partida.setTablero(tablero);
        
        System.out.println("SE REALIZÃ“ EL MOVIMIENTO CON Ã‰XITO, ANALIZANDO JAQUE");
        tableroservicio.imprimirTablero(partida, turno);
        System.out.println("SE REALIZÃ“ EL MOVIMIENTO CON Ã‰XITO, ANALIZANDO JAQUE");
        
        tableroservicio.seteaNuevosPosiblesMovimientos(tablero, turno);
        
        revisaJaque(partida, turno);
        
        if (partida.isCheck_mate()){
            solucionaJaque(partida, turno);
        }
    }
    
    
/**Recibe la ubicaciÃƒÂ³n de la pieza en el tablero (ArrayList) por lo que al iterar el arreglo conseguimos el Id*/
    public int buscaId(int pieza, Tablero tablero){
        int fila, columna;
        
        columna=pieza%10;
        fila=((pieza-columna)/10)-1;
        columna=columna-1;
        
        return tablero.getTablero_id_piezas()[fila][columna];
    }
    
    
/** Con el id de la pieza se puede determinar de quÃƒÂ© pieza se trata (alfil si es 1 ÃƒÂ³ 2, si va del 3 al 4 caballo, etc) y se llama al mÃƒÂ©todo
De ser posible realizar el movimiento lo realiza, de lo contrario.

1Ã‚Â° Con el uso de if-else, identificamos a que grupo de piezas corresponde.
* 
2Ã‚Â° Se itera el arreglo de la pieza segÃƒÂºn corresponda para buscar la pieza a mover y se comprueba que pertenezca al
jugador de ese turno.

3Ã‚Â° Se llama a un mÃƒÂ©todo encargado de verificÃƒÂ¡r si la pieza puede moverse al casillero propuesto.*/
    
    public void verificaMovimiento(boolean verificador, Jugador turno, Tablero tablero, int  id_pieza, int casilla_origen, int casilla_destino){
        
        String respuesta = null;
        
        TableroServicio tableroservicio = new TableroServicio();
                      
        if (id_pieza<3){
            AlfilServicio alfilservicio = new AlfilServicio();
            ArrayList<Alfil> arreglo =tablero.getAlfiles();
            Alfil pieza_a_mover = new Alfil();
            
            for (Alfil alfil : arreglo) {
                if(alfil.getId()==id_pieza){
                    verificador=(alfil.getJugador().equals(turno));
                    
                    if (verificador){
                        pieza_a_mover=alfil;
                    }                    
                    break;
                }
            }
            
            if (verificador){
                verificador=alfilservicio.consultaMovimiento(pieza_a_mover, casilla_destino);
                
                if (verificador){
                    arreglo.remove(pieza_a_mover);
                    moverPieza(tablero, pieza_a_mover, casilla_destino, casilla_origen, turno);
                    
                    if (turno.equals(negro)){
                        if (!pieza_a_mover.isCoronado() && casilla_destino<40){
                            respuesta="consultar";
                        }                        
                    }else{
                        
                        if (!pieza_a_mover.isCoronado() && casilla_destino>70){
                            respuesta="consultar";
                        }
                    }
                    
                    if (respuesta.equals("consultar")){
                        
                        do {
                            respuesta=JOptionPane.showInputDialog("Desea coronar la pieza: si o no");
                                
                        } while (!(respuesta.equals("si")||respuesta.equals("no")));
                        
                        if (respuesta.equals("si")){
                            pieza_a_mover.setCoronado(true);
                        }
                    }
                    
                    arreglo.add(pieza_a_mover);
                    tablero.setAlfiles(arreglo);
                }          
            }                        
        }else{
            
            if (id_pieza<7){
                
                CaballoServicio caballoservicio = new CaballoServicio();
                ArrayList<Caballo> arreglo = tablero.getCaballos();
                Caballo pieza_a_mover = new Caballo();
                
                for (Caballo caballo : arreglo) {
                    if(caballo.getId()==id_pieza){
                        verificador=(caballo.getJugador().equals(turno));
                        if (verificador){
                            pieza_a_mover=caballo;
                        }
                        break;
                    }     
                }
                
                if (verificador){
                    verificador=caballoservicio.consultaMovimiento(pieza_a_mover, casilla_destino);              
                    
                    if (verificador){
                        arreglo.remove(pieza_a_mover);                    
                        moverPieza(tablero, pieza_a_mover, casilla_destino, casilla_origen, turno);                    
                        
                        if (turno.equals(negro)){
                            if (!pieza_a_mover.isCoronado() && casilla_destino<40){
                                
                                if (casilla_destino<30){
                                    respuesta="coronar";
                                }else{
                                    respuesta="consultar";
                                }
                            }
                        
                        }else{
                            if (!pieza_a_mover.isCoronado() && casilla_destino>70){
                                
                                if (casilla_destino>80){
                                    respuesta="coronar";
                                }else{
                                    respuesta="consultar";
                                }
                            }
                        }
                    
                        if (respuesta.equals("consultar")){
                        
                            do {
                                respuesta=JOptionPane.showInputDialog("Desea coronar la pieza: si o no");
                                
                            } while (!(respuesta.equals("si")||respuesta.equals("no")));

                            if (respuesta.equals("si")){
                                respuesta="coronar";
                            }
                        }
                        
                        if (respuesta.equals("coronar")){
                            pieza_a_mover.setCoronado(true);
                        }
                                                
                        arreglo.add(pieza_a_mover);
                        tablero.setCaballos(arreglo);
                    }               
                }                
            }else{
                
                if (id_pieza<11){
                    
                    GeneralDeOroServicio generaldeoroServicio = new GeneralDeOroServicio();
                    ArrayList<GeneralDeOro> arreglo = tablero.getGenerales_oro();
                    GeneralDeOro pieza_a_mover = new GeneralDeOro();
                    
                    for (GeneralDeOro gold : arreglo) {
                        if (gold.getId()==id_pieza){
                            verificador=(gold.getJugador().equals(turno));
                            if (verificador){
                                pieza_a_mover=gold;
                            }
                            break;
                        }                        
                    }
                    
                    if (verificador){
                        verificador=generaldeoroServicio.consultaMovimiento(pieza_a_mover, casilla_destino);
                    
                        if (verificador){
                            arreglo.remove(pieza_a_mover);                        
                            moverPieza(tablero, pieza_a_mover, casilla_destino, casilla_origen, turno);
                            
                            arreglo.add(pieza_a_mover);
                            tablero.setGenerales_oro(arreglo);
                        }
                    }                    
                }else{
                    
                    if (id_pieza<15){
                        
                        GeneralDePlataServicio generaldeplataservicio = new GeneralDePlataServicio();
                        ArrayList<GeneralDePlata> arreglo = tablero.getGenerales_plata();
                        GeneralDePlata pieza_a_mover = new GeneralDePlata();
                    
                        for (GeneralDePlata silver : arreglo) {
                        
                            if (silver.getId()==id_pieza){
                                verificador=(silver.getJugador().equals(turno));
                            
                                if (verificador){
                                    pieza_a_mover=silver;
                                }
                                break;
                            }                            
                        }
                        
                        if (verificador){
                            verificador=generaldeplataservicio.consultaMovimiento(pieza_a_mover, casilla_destino);
                            
                            if (verificador){
                                arreglo.remove(pieza_a_mover);                            
                                moverPieza(tablero, pieza_a_mover, casilla_destino, casilla_origen, turno);
                                
                                if (turno.equals(negro)){
                                    
                                    if (!pieza_a_mover.isCoronado() && casilla_destino<40){
                                        respuesta="consultar";
                                    }
                                }else{
                                    
                                    if (!pieza_a_mover.isCoronado() && casilla_destino>70){
                                        respuesta="consultar";
                                    }
                                }

                                if (respuesta.equals("consultar")){

                                    do {
                                        respuesta=JOptionPane.showInputDialog("Desea coronar la pieza: si o no");

                                    } while (!(respuesta.equals("si")||respuesta.equals("no")));

                                    if (respuesta.equals("si")){
                                        pieza_a_mover.setCoronado(true);
                                    }
                                }
                                
                                arreglo.add(pieza_a_mover);
                                tablero.setGenerales_plata(arreglo);
                            }
                        }                        
                    }else{
                        
                        if (id_pieza<17){
                        
                            LanceroServicio lanceroservicio = new LanceroServicio();
                            ArrayList<Lancero> arreglo = tablero.getLanceros();
                            Lancero pieza_a_mover = new Lancero();
                            
                            for (Lancero lancero : arreglo) {
                            
                                if(lancero.getId()==id_pieza){
                                    verificador=(lancero.getJugador().equals(turno));
                                    
                                    if (verificador){
                                        pieza_a_mover=lancero;
                                    }
                                    break;
                                }                                
                            }
                            
                            if (verificador){
                                verificador=lanceroservicio.consultaMovimiento(pieza_a_mover, casilla_destino);
                            
                                if (verificador){
                                    arreglo.remove(pieza_a_mover);                                
                                    moverPieza(tablero, pieza_a_mover, casilla_destino, casilla_origen, turno);   
                                    
                                    if (turno.equals(negro)){
                                        if (!pieza_a_mover.isCoronado() && casilla_destino<40){

                                            if (casilla_destino<20){
                                                respuesta="coronar";
                                            }else{
                                                respuesta="consultar";
                                            }
                                        }
                                    }else{
                                        
                                        if (!pieza_a_mover.isCoronado() && casilla_destino>70){

                                            if (casilla_destino>90){
                                                respuesta="coronar";
                                            }else{
                                                respuesta="consultar";
                                            }
                                        }
                                    }

                                    if (respuesta.equals("consultar")){

                                        do {
                                            respuesta=JOptionPane.showInputDialog("Desea coronar la pieza: si o no");

                                        } while (!(respuesta.equals("si")||respuesta.equals("no")));

                                        if (respuesta.equals("si")){
                                            respuesta="coronar";
                                        }
                                    }

                                    if (respuesta.equals("coronar")){
                                        pieza_a_mover.setCoronado(true);
                                    }
                                                                        
                                    arreglo.add(pieza_a_mover);
                                    tablero.setLanceros(arreglo);
                                }
                            }                            
                        }else{
                            
                            if (id_pieza<35){
                            
                                PeonServicio peonservicio = new PeonServicio();
                                ArrayList<Peon> arreglo = tablero.getPeones();
                                Peon pieza_a_mover = new Peon();
                                
                                for (Peon peon : arreglo) {
                                
                                    if (peon.getId()==id_pieza){
                                        verificador=(peon.getJugador().equals(turno));
                                    
                                        if (verificador){
                                            pieza_a_mover=peon;
                                        }
                                        break;
                                    }                                    
                                }
                                
                                if (verificador){
                                    verificador=peonservicio.consultaMovimiento(pieza_a_mover, casilla_destino);
                                
                                    if (verificador){
                                   
                                        arreglo.remove(pieza_a_mover);                                    
                                        moverPieza(tablero, pieza_a_mover, casilla_destino, casilla_origen, turno);
                                        
                                        if (turno.equals(negro)){
                                            if (!pieza_a_mover.isCoronado() && casilla_destino<40){

                                                if (casilla_destino<20){
                                                    respuesta="coronar";
                                                }else{
                                                    respuesta="consultar";
                                                }
                                            }
                                        }else{
                                            
                                            if (!pieza_a_mover.isCoronado() && casilla_destino>70){

                                                if (casilla_destino>90){
                                                    respuesta="coronar";
                                                }else{
                                                    respuesta="consultar";
                                                }
                                            }
                                        }

                                        if (respuesta.equals("consultar")){

                                            do {
                                                respuesta=JOptionPane.showInputDialog("Desea coronar la pieza: si o no");

                                            } while (!(respuesta.equals("si")||respuesta.equals("no")));

                                            if (respuesta.equals("si")){
                                                respuesta="coronar";
                                            }
                                        }

                                        if (respuesta.equals("coronar")){
                                            pieza_a_mover.setCoronado(true);
                                        }
                                                                                
                                        arreglo.add(pieza_a_mover);
                                        tablero.setPeones(arreglo);
                                    }
                                }                          
                            }else{
                                
                                if (id_pieza<39){
                                
                                    TorreServicio torreservicio = new TorreServicio();
                                    ArrayList<Torre> arreglo = tablero.getTorres();
                                    Torre pieza_a_mover = new Torre();
                                    
                                    for (Torre torre : arreglo) {
                                    
                                        if (torre.getId()==id_pieza){
                                            verificador=(torre.getJugador().equals(turno));
                                        
                                            if (verificador){
                                                pieza_a_mover=torre;
                                            }
                                            break;
                                        }
                                    }
                                    
                                    if (verificador){
                                        verificador=torreservicio.consultaMovimiento(pieza_a_mover, casilla_destino);
                                        
                                        if (verificador){
                                            arreglo.remove(pieza_a_mover);                                        
                                            moverPieza(tablero, pieza_a_mover, casilla_destino, casilla_origen, turno);  
                                            
                                            if (turno.equals(negro)){
                                                if (!pieza_a_mover.isCoronado() && casilla_destino<40){
                                                    respuesta="consultar";
                                                }

                                            }else{
                                                if (!pieza_a_mover.isCoronado() && casilla_destino>70){
                                                    respuesta="consultar";
                                                }
                                            }

                                            if (respuesta.equals("consultar")){

                                                do {
                                                    respuesta=JOptionPane.showInputDialog("Desea coronar la pieza: si o no");

                                                } while (!(respuesta.equals("si")||respuesta.equals("no")));

                                                if (respuesta.equals("si")){
                                                    pieza_a_mover.setCoronado(true);
                                                }
                                            }
                                                                                        
                                            arreglo.add(pieza_a_mover);
                                            tablero.setTorres(arreglo);
                                        }
                                    }                                    
                                }else{
                                    
                                    ReyServicio reyservicio = new ReyServicio();
                                    Rey pieza_a_mover = new Rey();
                                    
                                    if (id_pieza==39){
                                        if (turno.equals(negro)){
                                            pieza_a_mover=tablero.getRey_negro();
                                        }else{
                                            
                                            verificador=false;
                                        }
                                    }else{
                                        
                                        if (turno.equals(blanco)){
                                            pieza_a_mover=tablero.getRey_blanco();
                                        }else{
                                            
                                            verificador=false;
                                        }
                                    }
                                    
                                    if (verificador){
                                        verificador=reyservicio.consultaMovimiento(pieza_a_mover, casilla_destino);
                                        
                                        if (verificador){
                                            moverPieza(tablero, pieza_a_mover, casilla_destino, casilla_origen, turno);
                                            
                                            if (turno.equals(negro)){
                                                tablero.setRey_negro(pieza_a_mover);
                                            }else{
                                                tablero.setRey_blanco(pieza_a_mover);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    
/** Recibe el tablero, una variable del tipo Piezas (debido a que necesitaremos solamente los atributos de la superclase
 utilizamos la misma para no hacer el mismo mÃƒÂ©todo para cada clase).
 
 1Ã‚Â° Identificamos el color de las piezas contrarias. 
 
 2Ã‚Â° verificamos si el casillero a ocupar estÃƒÂ¡ ocupado por una pieza del color contrario (ya se verificÃƒÂ³ del propio
 no lo estÃƒÂ©).
 
 3Ã‚Â° De estÃƒÂ¡r ocupado se busca el id correspondiente y se llama al metodo comer pieza.
 
 4Ã‚Â° A la pieza se le setea la nueva posiciÃƒÂ³n en el tablero y lo mismo hacemos con las matrices que lo representan.
 */        
    
    public void moverPieza(Tablero tablero, Piezas pieza, int casilla_destino, int casilla_origen, Jugador turno){
         
        int columna, fila, id_contrario;
        String nombre_pieza;
        Jugador color_contrario;
        
        TableroServicio tableroservicio = new TableroServicio();
                 
        if (turno.equals(negro)){
             color_contrario=blanco;
        }else{
             color_contrario=negro;
        }
         
        if (tableroservicio.posicionOcupada(tablero, casilla_destino, color_contrario)){
             id_contrario=buscaId(casilla_destino, tablero);
             
             comePieza(tablero, id_contrario, turno, casilla_destino);
        }
        
        pieza.setPos_tablero(casilla_destino);
         
        if (pieza.getJugador().equals(negro)){
            
            tablero.getCasillas_negras().remove(casilla_origen);
            tablero.getCasillas_negras().add(casilla_destino);
        }else{
            
            tablero.getCasillas_blancas().remove(casilla_origen);
            tablero.getCasillas_blancas().add(casilla_destino);
        }
        
        columna=casilla_origen%10;
        fila=((casilla_origen-columna)/10)-1;
        columna=columna-1;
        
        tablero.getTablero_id_piezas()[fila][columna]=0;
        
        nombre_pieza = tablero.getMatriz_tablero()[fila][columna];
        tablero.getMatriz_tablero()[fila][columna]="     ";
        
        columna=casilla_destino%10;
        fila=((casilla_destino-columna)/10)-1;
        columna=columna-1;
        
        tablero.getTablero_id_piezas()[fila][columna]=pieza.getId();
        tablero.getMatriz_tablero()[fila][columna]=nombre_pieza;        
         
    }
    
/* Nuevamente con el uso de los "if-else" identificamos de quÃƒÂ© tipo pieza se trata.
    Se a la pieza el possiciÃƒÂ³n 0 que por no estar mÃƒÂ¡s en el tablero y se cambia el color al que pertenece.
    No hace falta setear las matrices que representan al tablero ya que eso se harÃ¡ cuando se mueva la pieza.
    */
    public void comePieza(Tablero tablero, int id_contrario, Jugador color_ataca, int casilla_destino) {
                 
        if (id_contrario<3){
            
            ArrayList<Alfil> arreglo = tablero.getAlfiles();
            Alfil pieza_a_comer = new Alfil();
            
            for (Alfil alfil : arreglo) {
                
                if (alfil.getId()==id_contrario){
                    pieza_a_comer=alfil;                    
                    break;
                }                
            }
            
            arreglo.remove(pieza_a_comer);
            
            pieza_a_comer.setPos_tablero(0);
            pieza_a_comer.setCapturado(true);
            pieza_a_comer.setCoronado(false);
            pieza_a_comer.setJugador(color_ataca);
            pieza_a_comer.setPosibles_movimientos(null);
            
            arreglo.add(pieza_a_comer);
            tablero.setAlfiles(arreglo);
            
        }else{
            if (id_contrario<7){
                
                ArrayList<Caballo> arreglo = tablero.getCaballos();
                Caballo pieza_a_comer = new Caballo();
                
                for (Caballo caballo : arreglo) {
                
                    if (caballo.getId()==id_contrario){
                        pieza_a_comer=caballo;                                         
                        break;                        
                    }                    
                }
                
                arreglo.remove(pieza_a_comer);
                
                pieza_a_comer.setPos_tablero(0);
                pieza_a_comer.setCapturado(true);
                pieza_a_comer.setCoronado(false);
                pieza_a_comer.setJugador(color_ataca);
                pieza_a_comer.setPosibles_movimientos(null);
                
                arreglo.add(pieza_a_comer);
                tablero.setCaballos(arreglo);
                
            }else{
                if (id_contrario<11){
                
                    ArrayList<GeneralDeOro> arreglo =tablero.getGenerales_oro();
                    GeneralDeOro pieza_a_comer = new GeneralDeOro();
                    
                    for (GeneralDeOro oro : arreglo) {
                    
                        if(oro.getId()==id_contrario){
                            pieza_a_comer=oro;
                            break;
                        }                        
                    }
                    
                    arreglo.remove(pieza_a_comer);
                    
                    pieza_a_comer.setPos_tablero(0);
                    pieza_a_comer.setCapturado(true);
                    pieza_a_comer.setJugador(color_ataca);
                    pieza_a_comer.setPosibles_movimientos(null);
                    
                    arreglo.add(pieza_a_comer);
                    tablero.setGenerales_oro(arreglo);
                    
                }else{
                    if (id_contrario<15){
                    
                        ArrayList<GeneralDePlata> arreglo =tablero.getGenerales_plata();
                        GeneralDePlata pieza_a_comer = new GeneralDePlata();
                        
                        for (GeneralDePlata plata : arreglo) {
                        
                            if (plata.getId()==id_contrario){
                                pieza_a_comer=plata;
                                break;
                            }                            
                        }
                        
                        arreglo.remove(pieza_a_comer);
                        
                        pieza_a_comer.setPos_tablero(0);
                        pieza_a_comer.setCapturado(true);
                        pieza_a_comer.setCoronado(false);
                        pieza_a_comer.setJugador(color_ataca);
                        pieza_a_comer.setPosibles_movimientos(null);
                        
                        arreglo.add(pieza_a_comer);
                        tablero.setGenerales_plata(arreglo);
                        
                    }else{
                        if (id_contrario<17){
                        
                            ArrayList<Lancero> arreglo=tablero.getLanceros();
                            Lancero pieza_a_comer = new Lancero();
                            
                            for (Lancero lancero : arreglo){
                            
                                if (lancero.getId()==id_contrario){
                                    pieza_a_comer=lancero;                           
                                    break;
                                }
                            }
                            
                            arreglo.remove(pieza_a_comer);
                            
                            pieza_a_comer.setPos_tablero(0);
                            pieza_a_comer.setCapturado(true);
                            pieza_a_comer.setCoronado(false);
                            pieza_a_comer.setJugador(color_ataca);
                            pieza_a_comer.setPosibles_movimientos(null);
                            
                            arreglo.add(pieza_a_comer);
                            tablero.setLanceros(arreglo);
                            
                        }else{
                            if (id_contrario<35){
                            
                                ArrayList<Peon> arreglo=tablero.getPeones();
                                Peon pieza_a_comer = new Peon();
                                
                                for (Peon peon : arreglo) {
                                
                                    if (peon.getId()==id_contrario){
                                        pieza_a_comer=peon;
                                        break;
                                    }                      
                                }
                                
                                arreglo.remove(pieza_a_comer);
                                
                                pieza_a_comer.setPos_tablero(0);
                                pieza_a_comer.setCapturado(true);
                                pieza_a_comer.setCoronado(false);
                                pieza_a_comer.setJugador(color_ataca);
                                pieza_a_comer.setPosibles_movimientos(null);
                                
                                arreglo.add(pieza_a_comer);
                                tablero.setPeones(arreglo);
                                
                            }else{
                                ArrayList<Torre> arreglo=tablero.getTorres();
                                Torre pieza_a_comer = new Torre();
                                
                                for (Torre torre : arreglo) {
                                
                                    if (torre.getId()==id_contrario){
                                        pieza_a_comer=torre;
                                         break;                                      
                                    }                                                                             
                                }
                                
                                arreglo.remove(pieza_a_comer);
                                
                                pieza_a_comer.setPos_tablero(0);
                                pieza_a_comer.setCapturado(true);
                                pieza_a_comer.setCoronado(false);
                                pieza_a_comer.setJugador(color_ataca);
                                pieza_a_comer.setPosibles_movimientos(null);
                                
                                arreglo.add(pieza_a_comer);
                                tablero.setTorres(arreglo);
                                
                            }
                        }
                    }
                }
            }
        }
        
        if (color_ataca.equals(negro)){
            
            tablero.getCapturada_por_las_negreas().add(id_contrario);
            tablero.getCasillas_blancas().remove(casilla_destino);
            
        }else{
            
            tablero.getCaptura_por_las_blancas().add(id_contrario);
            tablero.getCasillas_negras().remove(casilla_destino);
        }        
    }             
 
    
/*Se ve si hay o no jaque, de ser asÃƒÂ­ lo informa y se buscarÃƒÂ¡ solucionar en otro mÃƒÂ©todo.
    1Ã‚Â° Se determina el color del rey que se va a analizar (siempre serÃƒÂ¡ el del siguiente turno.
    
    2Ã‚Â° Se buscan todos los movimientos posibles sin contar las casillas ocupadas por las propias.
    
    3Ã‚Â° Iteramos el arreglo obtenido y tambiÃƒÂ©n el de los posibles movimientos del color contrario, en un nuevo arreglo
    vamos agregando todos los movimientos del rey que no coincidan con los contrarios, con que coincida uno se hace un
    set true al atributo jaque del rey.
    
    4Ã‚Â° finalmente se setea el nuevo arreglo de moviemientos al rey (de este modo se eliminaron las que provocan jaque.    
  */    
    public void revisaJaque(Partida partida, Jugador turno) {
        
        boolean agrega_movimiento = false;
        
        ReyServicio reyServicio = new ReyServicio();
        Rey rey = new Rey();
        
        HashSet<Integer> contrarias_movimientos = new HashSet<>();
        ArrayList<Integer> movimientos_actuales = new ArrayList<>();
        ArrayList<Integer> movimientos_rey = new ArrayList<>();
        
        if (turno.equals(negro)){
            rey=partida.getTablero().getRey_blanco();
            contrarias_movimientos=partida.getTablero().getNegras_movimientos();
        }else{
            rey=partida.getTablero().getRey_negro();
            contrarias_movimientos=partida.getTablero().getBlancas_movimientos();
        }
        
        rey.setJaque(false);
        Tablero tablero=partida.getTablero();
        
        movimientos_actuales=reyServicio.verMovimientos(tablero, rey);
                
        for (Integer posible_movimiento : movimientos_actuales) {            
            for (Integer movimiento_pieza_contraria : contrarias_movimientos) {
                
                if (movimiento_pieza_contraria==rey.getPos_tablero()){                    
                    rey.setJaque(true);
                    agrega_movimiento=false;
                }else{
                    
                    if (movimiento_pieza_contraria==posible_movimiento){                                        
                        break;
                    }else{
                        agrega_movimiento= true;
                    }
                }
                if (agrega_movimiento){
                    movimientos_rey.add(posible_movimiento);
                }
            }
        }
        
        rey.setPosibles_movimientos(movimientos_rey);
        
        if (turno.equals(negro)){
            partida.getTablero().setRey_blanco(rey);
        }else{
            partida.getTablero().setRey_negro(rey);
        }
        
        if (rey.getPosibles_movimientos().isEmpty()&& rey.isJaque()){
            partida.setCheck_mate(true);          
        }
    }

    /*Se llega con el rey en jaque:
    
    1Ã‚Â° Determinamos la posiciÃƒÂ³n del rey amenazado como todos los posibles movimientos de las otras piezas de ese color.
    
    2Ã‚Â° Se iteran todos los arreglos de piezas (alfiles, caballos, etc) y se busca en cada areglo:
    
    a) Por cada pieza iteramos los posibles movimienos, si alguno coincide con el rey significa que es amenaza.
    b) De ser amenaza se itera los movimienos posilbes de las piezas del rey para buscar comer a la pieza amenazante.
    c) Por ultimo si perciste el jaque, se utiliza un mÃƒÂ©todo por si el jaque es a distancia (alfil, torre o lancero) y
    el mismo puede ser bloqueado, este mÃƒÂ©todo devolverÃƒÂ¡ un arreglo con los casilleros posibles a bloquear.
    d) Se itera el arreglo devuleto por el mÃƒÂ©todo del punto anterior junto con los posibles movimientos de defensa para
    buscar ocuparlos y asÃƒÂ­ bloquear el ataque.
    
    3Ã‚Â° Se devuelve la partida con el atributo check_mate, en verdadero o falso segÃƒÂ­n se haya podido o no sulucionas.
    */ 
    public void solucionaJaque(Partida partida, Jugador color_ataque) {
    
        boolean check_mate=false, pone_en_jaque= false;
        int casilla_rey, casilla_pieza_amenaza =0;
        Jugador color_rey;
        
        ArrayList<Integer> casillas_rey = new ArrayList<>();
        ArrayList<Integer> movimientos_amenaza = new ArrayList<>();
        HashSet<Integer> movimientos_defensa = new HashSet<>();
        
        ArrayList<Alfil> alfiles = partida.getTablero().getAlfiles();
        ArrayList<Caballo> caballos = partida.getTablero().getCaballos();
        ArrayList<GeneralDeOro> generalDeOros = partida.getTablero().getGenerales_oro();
        ArrayList<GeneralDePlata> generalDePlatas = partida.getTablero().getGenerales_plata();
        ArrayList<Lancero> lanceros = partida.getTablero().getLanceros();
        ArrayList<Peon> peones = partida.getTablero().getPeones();
        ArrayList<Torre> torres = partida.getTablero().getTorres();
        Rey rey = new Rey();
        
        if (color_ataque.equals(blanco)){
            color_rey=negro;
            casilla_rey = partida.getTablero().getRey_negro().getPos_tablero();
            casillas_rey=partida.getTablero().getRey_negro().getPosibles_movimientos();
            movimientos_defensa=partida.getTablero().getNegras_movimientos();
        }else{
            
            color_rey=blanco;
            casilla_rey=partida.getTablero().getRey_blanco().getPos_tablero();
            casillas_rey=partida.getTablero().getRey_blanco().getPosibles_movimientos();
            movimientos_defensa=partida.getTablero().getBlancas_movimientos();
        }
        
        for (Alfil alfil : alfiles) {
            
            if (alfil.getJugador().equals(color_ataque)){                
                
                for (Integer casilla_atacada : alfil.getPosibles_movimientos()) {
                    
                    if (casilla_atacada==casilla_rey){
                        casilla_pieza_amenaza=alfil.getPos_tablero();
                        pone_en_jaque=true;
                        break;
                    }
                }
                
                if (pone_en_jaque){
                    
                    for (Integer defensor : movimientos_defensa) {
                        
                        if (defensor==casilla_pieza_amenaza){
                            pone_en_jaque=false;
                            break;
                        }
                    }
                    
                    if (pone_en_jaque){
                        
                        EspacioReyAmenaza(movimientos_amenaza,"Alfil", casilla_pieza_amenaza, casilla_rey, partida.getTablero(), color_rey, false, 41);
                        
                        if (!movimientos_amenaza.isEmpty()){
                            
                            for (Integer amenaza : movimientos_amenaza) {
                                for (Integer defiende : movimientos_defensa) {
                                    
                                    if (defiende==amenaza){
                                        pone_en_jaque=false;
                                        break;
                                    }
                                }
                                
                                if(!pone_en_jaque){
                                    break;
                                }
                            }
                        }
                    }
                    
                    if (pone_en_jaque){
                        check_mate=true;
                        break;
                    }
                }
            }
            
            if (pone_en_jaque){
                check_mate=true;
            }
        }
        
        if (!check_mate){
            
            for (Caballo caballo : caballos) {
            
                if (caballo.getJugador().equals(color_ataque)){                
                
                    for (Integer casilla_amenaza : caballo.getPosibles_movimientos()) {
                    
                        if (casilla_amenaza==casilla_rey){
                            casilla_pieza_amenaza=caballo.getPos_tablero();
                            pone_en_jaque=true;
                            break;
                        }
                 }
                    
                 if (pone_en_jaque){
                
                     for (Integer defensor : movimientos_defensa) {
                     
                         if (defensor==casilla_pieza_amenaza){
                                pone_en_jaque=false;
                                break;
                            }
                     }
                       
                        if (pone_en_jaque){
                        check_mate=true;
                        break;
                        }
                    }
                }
                
                if (pone_en_jaque){
                    check_mate=true;
                }
            }
        }
        
        if(!check_mate){
            
            for (GeneralDeOro oro : generalDeOros) {
                
                if (oro.getJugador().equals(color_ataque)){                
                
                    for (Integer casilla_amenaza : oro.getPosibles_movimientos()) {
                    
                        if (casilla_amenaza==casilla_rey){
                            casilla_pieza_amenaza=oro.getPos_tablero();
                            pone_en_jaque=true;
                            break;
                        }
                    }
                    
                    if (pone_en_jaque){
                    
                        for (Integer defensor : movimientos_defensa) {
                        
                            if (defensor==casilla_pieza_amenaza){
                                pone_en_jaque=false;
                                break;
                            }
                        }
                        
                        if (pone_en_jaque){
                            check_mate=true;
                            break;
                        }
                    }
                }
                
                if (pone_en_jaque){
                    check_mate=true;
                }
            }
        }
        
        if(!check_mate){
        
            for (GeneralDePlata plata : generalDePlatas) {
        
                if (plata.getJugador().equals(color_ataque)){                
                
                    for (Integer casilla_amenaza : plata.getPosibles_movimientos()) {
                    
                        if (casilla_amenaza==casilla_rey){
                            casilla_pieza_amenaza=plata.getPos_tablero();
                            pone_en_jaque=true;
                            break;
                        }
                    }
                    
                    if (pone_en_jaque){
                    
                        for (Integer defensor : movimientos_defensa) {
                        
                            if (defensor==casilla_pieza_amenaza){
                                pone_en_jaque=false;
                                break;
                            }
                        }
                        
                        if (pone_en_jaque){
                            check_mate=true;
                            break;
                        }
                    }
                }
                
                if (pone_en_jaque){
                    check_mate=true;
                }
            }
        }
        
        if(!check_mate){
        
            for (Lancero lancero : lanceros) {
             
                if (lancero.getJugador().equals(color_ataque)){                
                
                    for (Integer casilla_amenaza : lancero.getPosibles_movimientos()) {
                    
                        if (casilla_amenaza==casilla_rey){
                            casilla_pieza_amenaza=lancero.getPos_tablero();
                            pone_en_jaque=true;
                            break;
                        }
                    }
                    
                    if (pone_en_jaque){
                    
                        for (Integer defensor : movimientos_defensa) {
                        
                            if (defensor==casilla_pieza_amenaza){
                                pone_en_jaque=false;
                                break;
                            }
                        }
                        
                        if (pone_en_jaque && !lancero.isCoronado()){
                        
                            EspacioReyAmenaza(movimientos_amenaza,"Lancero", casilla_pieza_amenaza, casilla_rey, partida.getTablero(), color_rey, false, 41);
                        
                            if (!movimientos_amenaza.isEmpty()){
                            
                                for (Integer amenaza : movimientos_amenaza) {
                            
                                    for (Integer defiende : movimientos_defensa) {
                                    
                                        if (defiende==amenaza){
                                            pone_en_jaque=false;
                                            break;
                                        }
                                    }
                                
                                    if(!pone_en_jaque){
                                        break;
                                    }
                                }
                            }
                        }
                        
                        if (pone_en_jaque){
                            check_mate=true;
                            break;
                        }
                    }
                }
                
                if (pone_en_jaque){
                    check_mate=true;
                }
            }
        }
        
        if (!check_mate){
        
            for (Peon peon : peones) {
              
                if (peon.getJugador().equals(color_ataque)){                
                
                    for (Integer casilla_amenaza : peon.getPosibles_movimientos()) {
                    
                        if (casilla_amenaza==casilla_rey){
                            casilla_pieza_amenaza=peon.getPos_tablero();
                            pone_en_jaque=true;
                            break;
                        }
                    }
                    
                    if (pone_en_jaque){
                    
                        for (Integer defensor : movimientos_defensa) {
                        
                            if (defensor==casilla_pieza_amenaza){
                                pone_en_jaque=false;
                                break;
                            }
                        }
                        
                        if (pone_en_jaque){
                            check_mate=true;
                            break;
                        }
                    }
                }
                
                if (pone_en_jaque){
                    check_mate=true;
                }
            }
        }
        
        if (check_mate){
        
            for (Torre torre : torres) {
                
                if (torre.getJugador().equals(color_ataque)){                
                
                    for (Integer casilla_amenaza : torre.getPosibles_movimientos()) {
                    
                        if (casilla_amenaza==casilla_rey){
                            casilla_pieza_amenaza=torre.getPos_tablero();
                            pone_en_jaque=true;
                            break;
                        }
                    }
                    
                    if (pone_en_jaque){
                    
                        for (Integer defensor : movimientos_defensa) {
                        
                            if (defensor==casilla_pieza_amenaza){
                                pone_en_jaque=false;
                                break;
                            }
                        }
                        
                        if (pone_en_jaque){
                        
                            EspacioReyAmenaza(movimientos_amenaza,"Torre", casilla_pieza_amenaza, casilla_rey, partida.getTablero(), color_rey, false, 41);
                        
                            if (!movimientos_amenaza.isEmpty()){
                            
                                for (Integer amenaza : movimientos_amenaza) {
                                
                                    for (Integer defiende : movimientos_defensa) {
                                    
                                        if (defiende==amenaza){
                                            pone_en_jaque=false;
                                            break;
                                        }
                                    }
                                    
                                    if(!pone_en_jaque){
                                        break;
                                    }
                                }
                            }
                        }
                        
                        if (pone_en_jaque){
                            check_mate=true;
                            break;
                        }
                    }
                }
            }
        }
        
        if (pone_en_jaque){
            check_mate=true;
        }
        
        partida.setCheck_mate(check_mate);
    }
    
    /* Para las piezas que comen a distancia como lo son lancero, torre alfil a excepciÃ³n del caballo (no se puede bloquear
    un jaque del caballo, solamente se podrÃ­a cambiar de lugar el rey o comer la pieza) se busca el espacio entre el rey la
    pieza que amenaza. Obtenido este espacio se puede ver las casillas a ocupar para bloquear un jaque.
    Este mÃ©todo actÃºa de dos formas:
    
    1Â° Recibe una pieza el tipo de la mismaque pone en jaque al rey, el tipo de pieza  y el correspondiente rey (las posiciones) En
    base a estos datos buscarÃ¡ las casillas libres entre ambas piezas y las devolverÃ¡ en un arreglo de las casillas a ocupar para
    bloquear el jaque.
    
    2Â° La otra funcciÃ³n que cumple es para determinar si una pieza, es "pieza clavada" (en ajedrez se le llama asÃ­ a las piezas
    que no pueden salir de la casilla que ocupan ya que estÃ¡n bloqueando un jaque de su propio rey). En este caso va a devolver
    un un valor booleano a la variable pasada como parÃ¡metro (clavada) y el arreglo de los movimientos que puedan hacer la pieza
    (solamente serÃ¡n los que esten entre su rey y la pieza que lo amenaza por lo que se descartarÃ¡n todos los otros posibles.
    
    Para diferenciar ambas funcionalidades del mÃ©todo, en el primer caso va a recibir como id_pieza clavada el nÃºmero 41 (no
    hay pieza con ese id). El mÃ©todo tambiÃ©n lo identifica con la variable tipo si es alfil, torre o lancero. En el segundo caso
    como posiciÃ³n de la pieza amenaza, el valor serÃ¡ 200 (no existe en el tablero) y el tipo en vez de ser una pieza, serÃ¡ "Clavada" 
    
    El mÃ©todo va a analizar los ocho segmentos desde el rey en donde hasta el lÃ­mite del tablero trayendo las casillas libres,
    si encuentra una pieza, analiza si es la pieza amenazada (en el caso que busque la primera opciÃ³n) o si es una pieza que pueda
    o no determinar si se trata de pieza clavada (la segunda opciÃ³n). (Si se busca la opciÃ³n uno se analizarÃ¡ y agregarÃ¡ al arreglo
    las casillas segÃºn el tipo de pieza que se trate; por el contrario si es la opciÃ³n "clavada" se analizan los ocho segmentos).
    
    */
    public void EspacioReyAmenaza (ArrayList<Integer> arreglo, String tipo, int casilla_amenaza, int casilla_rey, Tablero tablero, Jugador color_rey, boolean encontrado, int id_pieza_clavada){
        
        int movimiento=casilla_rey;
                                
        if (tipo.equals("alfil")||tipo.equals("clavada")){
            
            do {
                movimiento=movimiento-11;
                
                if (movimiento>10 && movimiento%10!=0){
                    if (movimiento==casilla_amenaza){
                        encontrado=true;
                        break;
                    }else{
                        arreglo.add(movimiento);
                    }
                }
            } while (movimiento>21);
        
        /* Si encontrado es verdadero es porque no se necesita buscar en los otros segmentos, si el arreglo estÃ¡ vacÃ­o no
         habria casilla libre para bloquear un jaque. Si ademÃ¡s de estas dos premisas pieza clavada es distindo de 0, se llama
         a un nuevo mÃ©todo que completara la tarea.
         Para la funcionalidad de pieza clavada llevarÃ¡ el arreglo completo por mÃ¡s que encuentre otra pieza en el segmento, luego
         Se depurarÃ¡ de ser necesario en el nuevo mÃ©todo llamado*/
            
            if (!encontrado && !arreglo.isEmpty() && id_pieza_clavada!=0){
                compruebaPiezaClavada("Alfil", arreglo, id_pieza_clavada, color_rey, tablero, encontrado);
            }
            
            if (!encontrado){
                arreglo=null;
                movimiento=casilla_rey;
                
                do {
                    movimiento=movimiento-9;
                    
                    if (movimiento>11 && movimiento%10!=0){
                        if (movimiento==casilla_amenaza){
                            encontrado=true;
                            break;
                        }else{
                            arreglo.add(movimiento);
                        }
                    }                                        
                } while (movimiento>20);
            }
            
            if (!encontrado && !arreglo.isEmpty() && id_pieza_clavada!=0){
                compruebaPiezaClavada("Alfil", arreglo, id_pieza_clavada, color_rey, tablero, encontrado);        
            }
                
            if (!encontrado){
                arreglo=null;
                movimiento=casilla_rey;
                    
                do {
                    movimiento=movimiento+9;
                    
                    if (movimiento<99 && movimiento%10!=0){
                        if (movimiento==casilla_amenaza){
                            encontrado=true;
                            break;
                        }else{
                            arreglo.add(movimiento);
                        }
                    }                    
                }while (movimiento<90);
            }
            
            if (!encontrado && !arreglo.isEmpty() && id_pieza_clavada!=0){
                compruebaPiezaClavada("Alfil", arreglo, id_pieza_clavada, color_rey, tablero, encontrado);
            }
            
            if (!encontrado){
                arreglo=null;
                movimiento=casilla_rey;
                        
                do {
                    movimiento=movimiento+11;
                            
                    if (movimiento<100 && movimiento%10!=0){
                        if (movimiento==casilla_amenaza){
                            encontrado=true;
                            break;
                        }else{
                            arreglo.add(movimiento);
                        }
                    }                
                } while (movimiento<89);
            }
            
            if (!encontrado && !arreglo.isEmpty() && id_pieza_clavada!=0){
                compruebaPiezaClavada("Alfil", arreglo, id_pieza_clavada, color_rey, tablero, encontrado);
            }
        }
        
        if ((!encontrado) && (tipo.equals("Alfil"))){
            if (!(color_rey.equals(blanco)&&tipo.equals("Lancero"))){
                
                do {
                    movimiento=movimiento-10;
                    
                    if (movimiento>10){
                        if (movimiento==casilla_amenaza){
                            encontrado=true;
                            break;
                        }else{
                            arreglo.add(movimiento);
                        }
                    }
                    
                } while (movimiento>20);
                
                if (!encontrado && !arreglo.isEmpty() && id_pieza_clavada!=0){
                    compruebaPiezaClavada("Lancero", arreglo, id_pieza_clavada, color_rey, tablero, encontrado);
                }
            }
        }
        
        if ((!encontrado) && (tipo.equals("Alfil"))){
            arreglo=null;
            movimiento=casilla_rey;
            
            if (!(color_rey.equals(negro) && tipo.equals("Lancero"))){
                
                do {
                    movimiento=movimiento+10;
                    
                    if (movimiento<100){
                        if (movimiento==casilla_amenaza){
                            encontrado=true;
                            break;
                        }else{
                            arreglo.add(movimiento);
                        }
                    }
                } while (movimiento<90);
                
                if (!encontrado && !arreglo.isEmpty() && id_pieza_clavada!=0){
                    compruebaPiezaClavada("Lancero", arreglo, id_pieza_clavada, color_rey, tablero, encontrado);
                }
            }
        }
        
        if (!encontrado){
            arreglo=null;
            movimiento=casilla_rey;
            
            if ((tipo.equals("Torre")) || (tipo.equals("Clavada"))){
                
                do {
                    movimiento=movimiento+1;
                    
                    if (movimiento%10!=0){
                        if (movimiento==casilla_amenaza){
                            encontrado=true;
                            break;
                        }else{
                            arreglo.add(movimiento);
                        }
                    }
                } while (movimiento%10!=0);
                
                if (!encontrado && !arreglo.isEmpty() && id_pieza_clavada!=0){
                    compruebaPiezaClavada("Torre", arreglo, id_pieza_clavada, color_rey, tablero, encontrado);
                }
            }
        }
        
        if (!encontrado && (tipo.equals("Torre") || (tipo.equals("Clavada")))){
            arreglo=null;
            movimiento=casilla_rey;
            
            do {
                movimiento=movimiento-1;
                
                if (movimiento%10!=0){
                    if (movimiento==casilla_amenaza){
                        encontrado=true;
                        break;
                    }else{
                        arreglo.add(movimiento);
                    }
                }
            } while (movimiento%10!=0);
            
            if (!encontrado && !arreglo.isEmpty() && id_pieza_clavada!=0){
                compruebaPiezaClavada("Torre", arreglo, id_pieza_clavada, color_rey, tablero, encontrado);
            }
        }
    }

    
    /*1Â° Se itera el arreglo recibido, por cada casilla se identifica el id en el tablero,de ser 0 se agrega a un arreglo
    auxiliar el valor de la casilla y se pasa a la siguiente iteraciÃ³n.
    
    2Â° De no no ser 0 se pregunta si corresponde al id de la pieza clavada, de ser asÃ­ la variable pieza presente se setea 
    como verdadero y se pasa a la siguiente iteraciÃ³n. En caso que sea el id de otra pieza se consulta de el tipo (no de la
    pieza encontrada sino del que viene como parÃ¡metro (determina la direcciÃ³n del segmento, digonal horizontal o vertical).
    En base al tipo se consulta primero si corresponde a alguno de los id que tiene ese grupo de piezas y de ser asÃ­ se
    itera el arreglo del tablero correspondiente a ese tipo de piezas (sino, no hay pieza amenaza); si la pieza encontrada 
    en el arreglo es del mismo color del rey, no es amenaza, de lo contrario si.*/    
    public void compruebaPiezaClavada(String tipo, ArrayList<Integer> arreglo, int id_pieza_clavada, Jugador color_rey, Tablero tablero, boolean clavada){
        
        boolean pieza_presente=false;
        int id_casilla;
        ArrayList<Integer> nuevo_arreglo=null;
                
        for (Integer casilla : arreglo) {
            
            id_casilla=buscaId(casilla, tablero);
                        
            if (id_casilla==0){
                nuevo_arreglo.add(casilla);
                
            }else{
                if (id_casilla!=id_pieza_clavada){
                    
                    switch  (tipo){
                        case "Alfil":
                            if (id_casilla==1 || id_casilla==2){
                                
                                ArrayList<Alfil> alfiles =tablero.getAlfiles();
                                    
                                for (Alfil alfil : alfiles) {
                                    
                                    if (alfil.getId()==id_casilla){
                                        if (alfil.getJugador().equals(color_rey)){
                                            clavada=false;
                                        }else{
                                            clavada=true;
                                        }
                                        break;
                                    }
                                }
                                
                            }else{
                                clavada=false;
                            }
                            break;
                           
                        case "Lancero":
                            if (id_casilla==15 || id_casilla==16){
                                ArrayList<Lancero> lanceros =tablero.getLanceros();
                                    
                                for (Lancero lancero : lanceros) {
                                    
                                    if (lancero.getId()==id_casilla){
                                        if (lancero.getJugador().equals(color_rey)){
                                            clavada=false;
                                        }else{
                                            clavada=true;
                                        }
                                        break;
                                    }
                                }
                                
                            }else{
                                clavada=false;
                            }
                            break;
                            
                        case "Torre":
                            if (id_casilla>34 && id_casilla<39){
                                ArrayList<Torre> torres =tablero.getTorres();
                                    
                                for (Torre torre : torres) {
                                
                                    if (torre.getId()==id_casilla){
                                        if (torre.getJugador().equals(color_rey)){
                                            clavada=false;
                                        }else{
                                            clavada=true;
                                        }
                                        break;
                                    }
                                }   
                                
                            }else{
                                clavada=false;
                            }
                            break;
                    }
                    
                }else{
                    pieza_presente=true;
                }
            }
        }
        if (!pieza_presente){
            clavada=false;
        }
        arreglo=nuevo_arreglo;
    }

    public void anuncioJaqueMate(Partida partida) {
        
        System.out.println("Jaque Mate");
        
        if (partida.getTurno()%2!=0){
            System.out.println("El jugador "+partida.getParticipante_negras().getNombre()+" gana el partido");
        }else{
            System.out.println("El jugador "+partida.getParticipante_blancas().getNombre()+" gana el partido");
        }
    }    
}