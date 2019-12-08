package Shogi.Shogi.servicios;

import Shogi.Shogi.entidades.Alfil;
import Shogi.Shogi.entidades.Caballo;
import Shogi.Shogi.entidades.GeneralDeOro;
import Shogi.Shogi.entidades.GeneralDePlata;
import Shogi.Shogi.entidades.Lancero;
import Shogi.Shogi.entidades.Partida;
import Shogi.Shogi.entidades.Peon;
import Shogi.Shogi.entidades.Rey;
import Shogi.Shogi.entidades.Tablero;
import Shogi.Shogi.entidades.Torre;
import Shogi.Shogi.enumeraciones.Jugador;
import static Shogi.Shogi.enumeraciones.Jugador.blanco;
import static Shogi.Shogi.enumeraciones.Jugador.negro;
import java.util.ArrayList;
import java.util.HashSet;

public class TableroServicio {
    
/** Se crea el tablero, seteandolo en las posiciones reglamentarias de las piezas, cada arreglo de los distintos tipos
 de piezas se cargan con los valores iniciles correspondientes de las mismas, una vez creada cada pieza, se la ubica en
 el tablero (ambos, el representeado por id de piezas y el que se mostrará en pantalla)*/
    
    public Tablero crear(){
        
        Tablero tablero = new Tablero();
        String[][] tablero_piezas = new String [9][9];
        int[][] tablero_id = new int [9][9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                tablero_id[i][j]=0;
                tablero_piezas[i][j]="      "; 
            }            
        }
        
        tablero.setTablero_id_piezas(tablero_id);
        tablero.setMatriz_tablero(tablero_piezas);
        
        HashSet<Integer> blancas_movimientos = new HashSet<>();
        HashSet<Integer> negras_movimientos = new HashSet<>();
        HashSet<Integer> casillas_blancas = new HashSet<>();
        HashSet<Integer> casillas_negras = new HashSet<>();
        
        ArrayList<Alfil> alfiles = new ArrayList<>();
        ArrayList<Caballo> caballos = new ArrayList<>();
        ArrayList<GeneralDeOro> generaldeoros = new ArrayList<>();
        ArrayList<GeneralDePlata> generaldeplatas = new ArrayList<>();
        ArrayList<Lancero> lanceros = new ArrayList<>();
        ArrayList<Peon> peones = new ArrayList<>();
        ArrayList<Torre> torres = new ArrayList<>();
                     
        AlfilServicio a_serv = new AlfilServicio();
        CaballoServicio c_serv = new CaballoServicio();
        GeneralDeOroServicio o_serv = new GeneralDeOroServicio();
        GeneralDePlataServicio gp_serv = new GeneralDePlataServicio();
        LanceroServicio l_serv = new LanceroServicio(); 
        PeonServicio p_serv = new PeonServicio(); 
        TorreServicio t_serv = new TorreServicio(); 
        
        Alfil alfil_1 = new Alfil();               
        a_serv.Crear(alfil_1, casillas_negras, 1, negro);
        ubicaFichaEnTablero(alfil_1.getPos_tablero(), alfil_1.getId(), tablero, alfil_1.getNombre());
        alfiles.add(alfil_1);
        
        Alfil alfil_2 = new Alfil();
        a_serv.Crear(alfil_2, casillas_blancas, 2, blanco);
        ubicaFichaEnTablero(alfil_2.getPos_tablero(), alfil_2.getId(), tablero, alfil_2.getNombre());
        alfiles.add(alfil_2);
                
        Caballo caballo_3 = new Caballo();
        c_serv.Crear(caballo_3, casillas_negras, 3, negro);
        ubicaFichaEnTablero(caballo_3.getPos_tablero(), caballo_3.getId(),tablero, caballo_3.getNombre());
        caballos.add(caballo_3);
        
        Caballo caballo_4 = new Caballo();
        c_serv.Crear(caballo_4, casillas_negras, 4, negro);
        ubicaFichaEnTablero(caballo_4.getPos_tablero(), caballo_4.getId(),tablero, caballo_4.getNombre());
        caballos.add(caballo_4);
        
        Caballo caballo_5 = new Caballo();
        c_serv.Crear(caballo_5, casillas_blancas, 5, blanco);
        ubicaFichaEnTablero(caballo_5.getPos_tablero(), caballo_5.getId(),tablero, caballo_5.getNombre());
        caballos.add(caballo_5);
        
        Caballo caballo_6 = new Caballo();
        c_serv.Crear(caballo_6, casillas_blancas, 6, blanco);
        ubicaFichaEnTablero(caballo_6.getPos_tablero(), caballo_6.getId(),tablero, caballo_6.getNombre());
        caballos.add(caballo_6);
              
        GeneralDeOro generaldeoro_7 = new GeneralDeOro();        
        o_serv.Crear(generaldeoro_7, negras_movimientos, casillas_negras, 7, negro);
        ubicaFichaEnTablero(generaldeoro_7.getPos_tablero(), generaldeoro_7.getId(),tablero, generaldeoro_7.getNombre());
        generaldeoros.add(generaldeoro_7);
        
        GeneralDeOro generaldeoro_8 = new GeneralDeOro(); 
        o_serv.Crear(generaldeoro_8, negras_movimientos, casillas_negras, 8, negro);
        ubicaFichaEnTablero(generaldeoro_8.getPos_tablero(), generaldeoro_8.getId(),tablero, generaldeoro_8.getNombre());
        generaldeoros.add(generaldeoro_8);
        
        GeneralDeOro generaldeoro_9 = new GeneralDeOro(); 
        o_serv.Crear(generaldeoro_9, blancas_movimientos, casillas_blancas, 9, blanco);
        ubicaFichaEnTablero(generaldeoro_9.getPos_tablero(), generaldeoro_9.getId(),tablero, generaldeoro_9.getNombre());
        generaldeoros.add(generaldeoro_9);
        
        GeneralDeOro generaldeoro_10 = new GeneralDeOro(); 
        o_serv.Crear(generaldeoro_10, blancas_movimientos, casillas_blancas, 10, blanco);
        ubicaFichaEnTablero(generaldeoro_10.getPos_tablero(), generaldeoro_10.getId(),tablero, generaldeoro_10.getNombre());
        generaldeoros.add(generaldeoro_10);
                
        GeneralDePlata generaldeplata_11 = new GeneralDePlata();        
        gp_serv.Crear(generaldeplata_11, negras_movimientos, casillas_negras, 11, negro);
        ubicaFichaEnTablero(generaldeplata_11.getPos_tablero(), generaldeplata_11.getId(),tablero, generaldeplata_11.getNombre());
        generaldeplatas.add(generaldeplata_11);
        
        GeneralDePlata generaldeplata_12 = new GeneralDePlata(); 
        gp_serv.Crear(generaldeplata_12, negras_movimientos, casillas_negras, 12, negro);
        ubicaFichaEnTablero(generaldeplata_12.getPos_tablero(), generaldeplata_12.getId(),tablero, generaldeplata_12.getNombre());
        generaldeplatas.add(generaldeplata_12);
        
        GeneralDePlata generaldeplata_13 = new GeneralDePlata(); 
        gp_serv.Crear(generaldeplata_13, blancas_movimientos, casillas_negras, 13, blanco);
        ubicaFichaEnTablero(generaldeplata_13.getPos_tablero(), generaldeplata_13.getId(),tablero, generaldeplata_13.getNombre());
        generaldeplatas.add(generaldeplata_13);
        
        GeneralDePlata generaldeplata_14 = new GeneralDePlata(); 
        gp_serv.Crear(generaldeplata_14, blancas_movimientos, casillas_negras, 14, blanco);
        ubicaFichaEnTablero(generaldeplata_14.getPos_tablero(), generaldeplata_14.getId(),tablero, generaldeplata_14.getNombre());
        generaldeplatas.add(generaldeplata_14);

        Lancero lancero_15 = new Lancero();
        l_serv.Crear(lancero_15, 15, negro, casillas_negras);
        ubicaFichaEnTablero(lancero_15.getPos_tablero(), lancero_15.getId(),tablero, lancero_15.getNombre());
        lanceros.add(lancero_15);
        
        Lancero lancero_16 = new Lancero();
        l_serv.Crear(lancero_16, 16, blanco, casillas_blancas);
        ubicaFichaEnTablero(lancero_16.getPos_tablero(), lancero_16.getId(),tablero, lancero_16.getNombre());
        lanceros.add(lancero_16);
        
        Peon peon_17 = new Peon();
        p_serv.Crear(peon_17, negras_movimientos, casillas_negras, 17, negro);
        ubicaFichaEnTablero(peon_17.getPos_tablero(), peon_17.getId(),tablero, peon_17.getNombre());
        peones.add(peon_17); 
        
        Peon peon_18 = new Peon();
        p_serv.Crear(peon_18, negras_movimientos, casillas_negras, 18, negro);
        ubicaFichaEnTablero(peon_18.getPos_tablero(), peon_18.getId(),tablero, peon_18.getNombre());
        peones.add(peon_18);
        
        Peon peon_19 = new Peon();
        p_serv.Crear(peon_19, negras_movimientos, casillas_negras, 19, negro);
        ubicaFichaEnTablero(peon_19.getPos_tablero(), peon_19.getId(),tablero, peon_19.getNombre());
        peones.add(peon_19);
        
        Peon peon_20 = new Peon();
        p_serv.Crear(peon_20, negras_movimientos, casillas_negras, 20, negro);
        ubicaFichaEnTablero(peon_20.getPos_tablero(), peon_20.getId(),tablero, peon_20.getNombre());
        peones.add(peon_20);
        
        Peon peon_21 = new Peon();
        p_serv.Crear(peon_21, negras_movimientos, casillas_negras, 21, negro);
        ubicaFichaEnTablero(peon_21.getPos_tablero(), peon_21.getId(),tablero, peon_21.getNombre());
        peones.add(peon_21);
        
        Peon peon_22 = new Peon();
        p_serv.Crear(peon_22, negras_movimientos, casillas_negras, 22, negro);
        ubicaFichaEnTablero(peon_22.getPos_tablero(), peon_22.getId(),tablero, peon_22.getNombre());
        peones.add(peon_22);
        
        Peon peon_23 = new Peon();
        p_serv.Crear(peon_23, negras_movimientos, casillas_negras, 23, negro);
        ubicaFichaEnTablero(peon_23.getPos_tablero(), peon_23.getId(),tablero, peon_23.getNombre());
        peones.add(peon_23);
        
        Peon peon_24 = new Peon();
        p_serv.Crear(peon_24, negras_movimientos, casillas_negras, 24, negro);
        ubicaFichaEnTablero(peon_24.getPos_tablero(), peon_24.getId(),tablero, peon_24.getNombre());
        peones.add(peon_24);
        
        Peon peon_25 = new Peon();
        p_serv.Crear(peon_25, negras_movimientos, casillas_negras, 25, negro);
        ubicaFichaEnTablero(peon_25.getPos_tablero(), peon_25.getId(),tablero, peon_25.getNombre());
        peones.add(peon_25);
        
        Peon peon_26 = new Peon();
        p_serv.Crear(peon_26, blancas_movimientos, casillas_blancas, 26, blanco);
        ubicaFichaEnTablero(peon_26.getPos_tablero(), peon_26.getId(),tablero, peon_26.getNombre());
        peones.add(peon_26);
        
        Peon peon_27 = new Peon();
        p_serv.Crear(peon_27, blancas_movimientos, casillas_blancas, 27, blanco);
        ubicaFichaEnTablero(peon_27.getPos_tablero(), peon_27.getId(),tablero, peon_27.getNombre());
        peones.add(peon_27);
        
        Peon peon_28 = new Peon();
        p_serv.Crear(peon_28, blancas_movimientos, casillas_blancas, 28, blanco);
        ubicaFichaEnTablero(peon_28.getPos_tablero(), peon_28.getId(),tablero, peon_28.getNombre());
        peones.add(peon_28);
        
        Peon peon_29 = new Peon();
        p_serv.Crear(peon_29, blancas_movimientos, casillas_blancas, 29, blanco);
        ubicaFichaEnTablero(peon_29.getPos_tablero(), peon_29.getId(),tablero, peon_29.getNombre());
        peones.add(peon_29);
        
        Peon peon_30 = new Peon();
        p_serv.Crear(peon_30, blancas_movimientos, casillas_blancas, 30, blanco);
        ubicaFichaEnTablero(peon_30.getPos_tablero(), peon_30.getId(),tablero, peon_30.getNombre());
        peones.add(peon_30);
        
        Peon peon_31 = new Peon();
        p_serv.Crear(peon_31, blancas_movimientos, casillas_blancas, 31, blanco);
        ubicaFichaEnTablero(peon_31.getPos_tablero(), peon_31.getId(),tablero, peon_31.getNombre());
        peones.add(peon_31);
        
        Peon peon_32 = new Peon();
        p_serv.Crear(peon_32, blancas_movimientos, casillas_blancas, 32, blanco);
        ubicaFichaEnTablero(peon_32.getPos_tablero(), peon_32.getId(),tablero, peon_32.getNombre());
        peones.add(peon_32);
        
        Peon peon_33 = new Peon();
        p_serv.Crear(peon_33, blancas_movimientos, casillas_blancas, 33, blanco);
        ubicaFichaEnTablero(peon_33.getPos_tablero(), peon_33.getId(),tablero, peon_33.getNombre());
        peones.add(peon_33);
        
        Peon peon_34 = new Peon();
        p_serv.Crear(peon_34, blancas_movimientos, casillas_blancas, 34, blanco);
        ubicaFichaEnTablero(peon_34.getPos_tablero(), peon_34.getId(),tablero, peon_34.getNombre());
        peones.add(peon_34);

        Torre torre_35 = new Torre();        
        t_serv.Crear(torre_35, negras_movimientos, casillas_negras, 35, negro);
        ubicaFichaEnTablero(torre_35.getPos_tablero(), torre_35.getId(),tablero, torre_35.getNombre());
        torres.add(torre_35);
        
        Torre torre_36 = new Torre();
        t_serv.Crear(torre_36, negras_movimientos, casillas_negras, 36, negro);
        ubicaFichaEnTablero(torre_36.getPos_tablero(), torre_36.getId(),tablero, torre_36.getNombre());
        torres.add(torre_36);
        
        Torre torre_37 = new Torre();
        t_serv.Crear(torre_37, blancas_movimientos, casillas_blancas, 37, blanco);
        ubicaFichaEnTablero(torre_37.getPos_tablero(), torre_37.getId(),tablero, torre_37.getNombre());
        torres.add(torre_37);
        
        Torre torre_38 = new Torre();
        t_serv.Crear(torre_38, blancas_movimientos, casillas_blancas, 38, blanco);
        ubicaFichaEnTablero(torre_38.getPos_tablero(), torre_38.getId(),tablero, torre_38.getNombre());
        torres.add(torre_38);
        
        ReyServicio s_rey = new ReyServicio();
        
        Rey rey_39 = new Rey();
        rey_39=s_rey.Crear(negro, negras_movimientos, casillas_negras, 39);
        ubicaFichaEnTablero(rey_39.getPos_tablero(), rey_39.getId(),tablero, rey_39.getNombre());
        tablero.setRey_negro(rey_39);
        
        Rey rey_40 = new Rey();
        rey_40=s_rey.Crear(blanco, blancas_movimientos, casillas_blancas, 40);
        ubicaFichaEnTablero(rey_40.getPos_tablero(), rey_40.getId(),tablero, rey_40.getNombre());
        tablero.setRey_blanco(rey_40);
  
        tablero.setAlfiles(alfiles);
        tablero.setCaballos(caballos);
        tablero.setGenerales_oro(generaldeoros);
        tablero.setGenerales_plata(generaldeplatas);
        tablero.setLanceros(lanceros);
        tablero.setPeones(peones);
        tablero.setTorres(torres);
        
        tablero.setCaptura_por_las_blancas(new ArrayList<>());
        tablero.setCapturada_por_las_negreas(new ArrayList<>());
        
        tablero.setBlancas_movimientos(blancas_movimientos);
        tablero.setNegras_movimientos(negras_movimientos);
        
        tablero.setCasillas_blancas(casillas_blancas);
        tablero.setCasillas_negras(casillas_negras);
        
        return tablero;
    }
    
    /*Recibe un casillero y el color que queremos ver si ocupa o no el mismo, devuelve un valor booleano*/
    public boolean posicionOcupada (Tablero tablero, Integer buscar, Jugador color){      
        
        boolean respuesta=false;
        
        if (color.equals(negro)){
            
            for (Integer casilla : tablero.getCasillas_negras()) {
                
                if (casilla.equals(buscar)){
                    respuesta=true;
                    break;
                }
            }      
        }else{
            
            for (Integer casilla : tablero.getCasillas_blancas()) {
                
                if (casilla.equals(buscar)){
                    respuesta=true;
                    break;
                }
            }
        }
        return respuesta;
    }
    
    /**El mÃ©todo transforma el entero de la posiciÃ³n que ocupa la pieza, el nombre de la misma y el objeto tablero.
     * Utilizamos un arreglo auxiliar para cambiar la posiciÃ³n correspondiente y setearlo al final en el tablero.
     * En segundo lugar en base a la posiciÃ³n recibida se transforma la misma en filas y columnas, se recorre el
     * arreglo y en el mismo se cambia solamente cuando coincide la posiciÃ³n buscada por el nombre de la pieza. 
     */    
    public void ubicaFichaEnTablero (int posicion, int id, Tablero tablero, String pieza){
        
        String[][] arreglo_piezas=tablero.getMatriz_tablero();
        int [][] arreglo_id=tablero.getTablero_id_piezas();
        int fila = buscaFila(posicion), columna = buscaColumna(posicion);
                       
        arreglo_piezas[fila][columna]=pieza;
        arreglo_id[fila][columna]=id;
        
        tablero.setTablero_id_piezas(arreglo_id);
        tablero.setMatriz_tablero(arreglo_piezas);
        
    }
    
    public void imprimirTablero(Partida partida, Jugador turno){
        Tablero tablero = partida.getTablero();
        
        System.out.println("Turno N° "+partida.getTurno());
        
        if (turno.equals(blanco)){
            System.out.println("Mueve fichas blancas "+partida.getParticipante_blancas().getNombre());
        }else{
            System.out.println("Mueve fichas negras "+partida.getParticipante_negras().getNombre());
        }
        
        System.out.println("");
        
        for (int i = 0; i < 9; i++) {
            System.out.print((i+1)+"0 s  ");
            for (int j = 0; j < 9; j++) {
                System.out.print(tablero.getMatriz_tablero()[i][j]);
                System.out.print("|");
            }
            System.out.println("");
        }
        System.out.println(" ");
    }
    

    
/* Terminada la jugada, es decir cuando el jugador ya moviÃ³ su pieza y decidiÃ³ coronar o no la misma, se hace un
relevamiento de los posibles movimientos de las 40 piezas.

1Â° Se ven todas las piezas sin incluir el rey perteneciente al jugdor del prÃ³ximo turno. Comenzamos con las piezas que
pueden comer a distancia (Alfiles, torres, lanceros y caballos y luego el resto).
A Las piezas del turno que acaba de conluir, le incluÃ­mos tambiÃ©n los posibles movimientos que provocan un jaque a su 
propio rey (se volverÃ¡ reelevar antes que vueva a mover) ya que estos pueden ser posibles puntos de jaque al rey
adversario. Lo mismo en caso de las fichas clavadas (Se le llaman asÃ­ a las fichas que su posiciÃ³n protejen a su propio
rey y por esto no se pueden mover).
Los movimientos del rey del siguiente turno se verÃ¡n en otros mÃ©todos aparte.*/
    
    public void seteaNuevosPosiblesMovimientos(Tablero tablero, Jugador turno) {
        
        ArrayList<Integer> movimientos = new ArrayList<>();
        HashSet<Integer> blancas_movimientos = null;
        HashSet<Integer> negras_movimientos = null;
        
        AlfilServicio alfilServicio = new AlfilServicio();
        CaballoServicio caballoServicio = new CaballoServicio();
        GeneralDeOroServicio generalDeOroServicio = new GeneralDeOroServicio();
        GeneralDePlataServicio generalDePlataServicio = new GeneralDePlataServicio();
        LanceroServicio lanceroServicio = new LanceroServicio();
        PeonServicio peonServicio = new PeonServicio();
        ReyServicio reyServicio = new ReyServicio();
        TorreServicio torreServicio = new TorreServicio();
                
        Alfil alfil = new Alfil();
        Caballo caballo = new Caballo();
        GeneralDeOro generalDeOro = new GeneralDeOro();
        GeneralDePlata generalDePlata = new GeneralDePlata();
        Lancero lancero = new Lancero();
        Peon peon = new Peon();
        Rey rey = new Rey();
        Torre torre = new Torre();
                
        ArrayList<Alfil> alfiles = new ArrayList<>();
        ArrayList<Alfil> alf_aux = tablero.getAlfiles();
        
        for (Alfil aux : alf_aux) {
            alfil=aux;
            
            if (alfil.isCapturado()){
                movimientos.clear();
                
            }else{
                movimientos=alfilServicio.verMovimientos(alfil, tablero);

                if (turno.equals(negro)){
                    for (Integer mov : movimientos) {
                        negras_movimientos.add(mov);
                    }
                }else{
                    
                    for (Integer mov : movimientos) {
                        blancas_movimientos.add(mov);
                    }
                }
            }
            
            alfil.setPosibles_movimientos(movimientos);
            alfiles.add(alfil);            
        }
        
        tablero.setAlfiles(alfiles);
        
        ArrayList<Torre> torres = new ArrayList<>();
        ArrayList<Torre> tor_aux = tablero.getTorres();
        
        for (Torre aux : tor_aux) {
            torre=aux;
            
            if (torre.isCapturado()){
                movimientos.clear();
                
            }else{
                movimientos=torreServicio.verMovimientos(tablero, torre);

                if (turno.equals(negro)){
                    for (Integer mov : movimientos) {
                        negras_movimientos.add(mov);
                    }
                }else{
                      for (Integer mov : movimientos) {
                          blancas_movimientos.add(mov);                    
                    }              
                }
            }
            torre.setPosibles_movimientos(movimientos);
            torres.add(torre);    
        }
        
        tablero.setTorres(torres);
                
        ArrayList<Lancero> lanceros = new ArrayList<>();
        ArrayList<Lancero> lan_aux = tablero.getLanceros();
        
        for (Lancero aux : lan_aux) {
            lancero=aux;
            
            if (lancero.isCapturado()){
                movimientos.clear();
                
            }else{
                movimientos=lanceroServicio.verMovimientos(lancero, tablero);

                if (turno.equals(negro)){
                    for (Integer mov : movimientos) {
                        negras_movimientos.add(mov);                    
                    }
                }else{
                    for (Integer mov : movimientos) {
                        blancas_movimientos.add(mov);                    
                    }
                }
            }
            
            lancero.setPosibles_movimientos(movimientos);
            lanceros.add(lancero);            
        }
        
        tablero.setLanceros(lanceros);
        
        ArrayList<GeneralDeOro> generalDeOros = new ArrayList<>();
        ArrayList<GeneralDeOro> aux_Oros = tablero.getGenerales_oro();
        
        for (GeneralDeOro aux : aux_Oros) {
            generalDeOro=aux;
            
            if (generalDeOro.isCapturado()){
                movimientos.clear();
                
            }else{
                movimientos=generalDeOroServicio.verMovimientos(turno, generalDeOro.getPos_tablero(), tablero);

                if (turno.equals(negro)) {
                    for (Integer mov : movimientos) {
                        negras_movimientos.add(mov);                    
                    }
                }else{
                    for (Integer mov : movimientos) {
                        blancas_movimientos.add(mov);
                    }
                }
            }
            
            generalDeOro.setPosibles_movimientos(movimientos);
            generalDeOros.add(generalDeOro);
        }
        
        tablero.setGenerales_oro(generalDeOros);
        
        ArrayList<GeneralDePlata> generalDePlatas = new ArrayList<>();
        ArrayList<GeneralDePlata> aux_plata = tablero.getGenerales_plata();
        
        for (GeneralDePlata aux : aux_plata) {
            generalDePlata=aux;
            
            if (generalDePlata.isCapturado()){
                movimientos.clear();
                
            }else{
                movimientos=generalDePlataServicio.verMovimientos(generalDePlata, tablero);

                if (turno.equals(negro)) {
                    for (Integer mov : movimientos) {
                        negras_movimientos.add(mov);
                    }                
                }else{
                    
                    for (Integer mov : movimientos) {
                        blancas_movimientos.add(mov);
                    }
                }
            }
            
            generalDePlata.setPosibles_movimientos(movimientos);
            generalDePlatas.add(generalDePlata);            
        }
        
        tablero.setGenerales_plata(generalDePlatas);
        
        ArrayList<Caballo> caballos = new ArrayList<>();
        ArrayList<Caballo> aux_cab = tablero.getCaballos();
        
        for (Caballo aux : aux_cab) {
            caballo=aux;
            
            if (caballo.isCapturado()){
                movimientos.clear();
                
            }else{
                movimientos=caballoServicio.verMovimientos(caballo, tablero);

                if (turno.equals(negro)) {
                    
                    for (Integer mov : movimientos) {
                        negras_movimientos.add(mov);                    
                    }
                }else{
                    
                    for (Integer mov : movimientos) {
                        blancas_movimientos.add(mov);                                        
                    }
                }
            }
            
            caballo.setPosibles_movimientos(movimientos);
            caballos.add(caballo);            
        }
        
        tablero.setCaballos(caballos);
        
        ArrayList<Peon> peones = new ArrayList<>(); 
        ArrayList<Peon> aux_p = tablero.getPeones(); 
        
        for (Peon aux : aux_p) {
            peon=aux;
            
            if (peon.isCapturado()){
                movimientos.clear();
                
            }else{
                movimientos=peonServicio.verMovimientos(peon, tablero);

                if (turno.equals(negro)) {
                    for (Integer mov : movimientos) {
                        negras_movimientos.add(mov);                    
                    }
                }else{
                    for (Integer mov : movimientos) {
                        blancas_movimientos.add(mov);
                    }
                }
            }
            
            peon.setPosibles_movimientos(movimientos);
            peones.add(peon);            
        }
        
        tablero.setPeones(peones);
        
        if (turno.equals(negro)){
            rey=tablero.getRey_blanco();
            movimientos=reyServicio.verMovimientos(tablero, rey);
            
            for (Integer mov : movimientos) {
                negras_movimientos.add(mov);                
            }
            
            rey.setPosibles_movimientos(movimientos);
            tablero.setRey_blanco(rey);
            
        }else{
            rey=tablero.getRey_negro();
            movimientos=reyServicio.verMovimientos(tablero, rey);
            
            for (Integer mov : movimientos) {
                negras_movimientos.add(mov);                
            }
            
            rey.setPosibles_movimientos(movimientos);
            tablero.setRey_negro(rey);            
            
        }
        
        tablero.setBlancas_movimientos(blancas_movimientos);
        tablero.setNegras_movimientos(negras_movimientos);
    }

    
    /* Recibe un arreglo con las piezas capturadas de acuerdo al turno, itera ese arreglo, identifica el tipo de pieza y
    carga un arreglo String con el id seguido del tipo de pieza. Tambien se guarda un arreglo con los tipos en las mismas
    posiciones que el arreglo de piezas capturadas (este último se recibe como parámetro vacío. Luego itera el arreglo
    string para imprimirlo.
    */
    public void imprimeCapturadas(ArrayList<Integer> piezas_capturadas, ArrayList<String> capturadas_tipo) {
        
        String tipo;
        ArrayList<String> para_imprimir = new ArrayList<>();
                
        for (Integer pieza : piezas_capturadas) {
            
            if (pieza<3){
                tipo="Alfil";
                para_imprimir.add(pieza+" "+tipo);
                capturadas_tipo.add(tipo);
                
            }else{
                if (pieza<7){
                    tipo="Caballo";
                    para_imprimir.add(pieza+" "+tipo);
                    capturadas_tipo.add(tipo);
                    
                }else{
                    if (pieza<11){
                        tipo="General de Oro";
                        para_imprimir.add(pieza+" "+tipo);
                        capturadas_tipo.add(tipo);
                        
                    }else{
                        if (pieza<15){
                            tipo="General de Plata";
                            para_imprimir.add(pieza+" "+tipo);
                            capturadas_tipo.add(tipo);
                            
                        }else{
                            if (pieza<17){
                                tipo="Lancero";
                                para_imprimir.add(pieza+" "+tipo);
                                capturadas_tipo.add(tipo);
                                
                            }else{
                                tipo="Peon";
                                if (pieza<34){
                                    para_imprimir.add(pieza+" "+tipo);
                                    capturadas_tipo.add(tipo);
                                    
                                }else{
                                    tipo="Torre";
                                    para_imprimir.add(pieza+" "+tipo);
                                    capturadas_tipo.add(tipo);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        System.out.println("Las piezas disponibles para ingresar son:");
        
        for (String imprimir : para_imprimir) {
            System.out.print(imprimir);
            System.out.print(" ");
        }
        System.out.println("");
    }

    /* Primero se llama a un método que devolverá la fila y columna de la matriz que corresponde al la casilla. Se consulta en
    la matriz si la casilla es verdadera, de no ser así se busca que sea una casilla ocupada por el rival, si no cumple ninguna
    de estas dos opciones el valor retornado sera false.*/    
    public boolean compruebaCasillaPosible(int casilla_destino, Tablero tablero, Jugador turno) {
        
        int columna=buscaColumna(casilla_destino), fila=buscaFila(casilla_destino), valor_encontrado_casilla;
        boolean respuesta=false;
                
        valor_encontrado_casilla=tablero.getTablero_id_piezas()[fila][columna];
        
        if (valor_encontrado_casilla==0){
            
            respuesta=true;                   
        }else{
            
            HashSet<Integer> arreglo = new HashSet<>();
            
            if (turno.equals(negro)){
                arreglo=tablero.getCasillas_blancas();
            }else{
                arreglo=tablero.getCasillas_negras();
            }
            
            for (Integer pieza : arreglo) {
                
                if (valor_encontrado_casilla==pieza){
                    respuesta=true;
                    break;
                }                
            }            
        }
        
        return respuesta;
    }

    /* En base a la casilla destino, se ve la fila y columna que le corresponde en el tablero de id, se itera el mismo y a
    un tablero auxiliar, en la posición de la casilla se le asigna el id de la pieza, en el resto se copia tal cual. Luego
    se setea el tablero id con el arreglo auxliar obtenido. 
    
    Luego según el tipo busca el arreglo de piezas correspondiente, lo itera y busca la que coincide con el id buscado, carga
    la pieza en un objeto auxiliar, setea carturado false y coronado true si corresponde, setea en un arreglo de piezas auxiliar 
    este objeto y luego el arreglo auxiliar en el tablero (reemplazando el arreglo de piezas).
    
    */
    public void ingresaPieza(int id_origen, int casilla_destino, String respuesta, Tablero tablero, String tipo){
        
        int columna =buscaColumna(casilla_destino), fila = buscaFila(casilla_destino);        
        int[][] nuevo_tablero = new int[9][9];
        String[][] nuevo_tablero_Str = new String[9][9];
                
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i==fila && j==columna){
                    nuevo_tablero[i][j]=id_origen;
                }else{
                     nuevo_tablero[i][j]= tablero.getTablero_id_piezas()[i][j];
                }
            }            
        }
        
        tablero.setTablero_id_piezas(nuevo_tablero);
                
        switch (tipo){
            
            case "Alfil":
                ArrayList<Alfil> alfiles = tablero.getAlfiles();
                ArrayList<Alfil> alfiles_aux = alfiles;
                Alfil alfil_aux = new Alfil();
                
                for (Alfil alfil : alfiles) {
                    if (alfil.getId()==id_origen){
                        
                        alfil_aux=alfil;                                            
                        break;
                        }
                    }
                
                alfiles_aux.remove(alfil_aux);               
                alfil_aux.setCapturado(false);
                                                             
                seteaArregloString(nuevo_tablero_Str, tablero, fila, columna, alfil_aux.getJugador(), alfil_aux.getTipo(), alfil_aux.isCoronado());
                                
                alfiles_aux.add(alfil_aux);
                tablero.setAlfiles(alfiles_aux);
                tablero.setMatriz_tablero(nuevo_tablero_Str);
                
                break;
                
            case "Caballo":
                ArrayList<Caballo> caballos = tablero.getCaballos();
                ArrayList<Caballo> caballos_aux = caballos;
                Caballo caballo_aux = new Caballo();
                
                for (Caballo caballo : caballos) {
                    if (caballo.getId()==id_origen){
                        
                        caballo_aux=caballo;                                            
                        break;
                        }
                    }
                
                caballos_aux.remove(caballo_aux);                
                caballo_aux.setCapturado(false);
                                
                seteaArregloString(nuevo_tablero_Str,tablero, fila, columna, caballo_aux.getJugador(), caballo_aux.getTipo(), caballo_aux.isCoronado());
                                
                caballos_aux.add(caballo_aux);
                tablero.setCaballos(caballos_aux);
                tablero.setMatriz_tablero(nuevo_tablero_Str);
                break;
                
            case "General de Oro":
                ArrayList<GeneralDeOro> oros = tablero.getGenerales_oro();
                ArrayList<GeneralDeOro> oros_aux = oros;
                GeneralDeOro generalDeOro_aux = new GeneralDeOro();
                
                for (GeneralDeOro generalDeOro : oros) {
                    if (generalDeOro.getId()==id_origen){
                        
                        generalDeOro_aux=generalDeOro;                                            
                        break;
                        }
                    }
                
                oros_aux.remove(generalDeOro_aux);                
                generalDeOro_aux.setCapturado(false);
                
                seteaArregloString(nuevo_tablero_Str, tablero, fila, columna, generalDeOro_aux.getJugador(), generalDeOro_aux.getTipo(), false);
                
                oros_aux.add(generalDeOro_aux);
                tablero.setGenerales_oro(oros_aux);
                tablero.setMatriz_tablero(nuevo_tablero_Str);
                break;
                
            case "General de Plata":
                ArrayList<GeneralDePlata> platas = tablero.getGenerales_plata();
                ArrayList<GeneralDePlata> platas_aux = platas;
                GeneralDePlata generalDePlata_aux = new GeneralDePlata();
                
                for (GeneralDePlata generalDePlata : platas) {
                    if (generalDePlata.getId()==id_origen){
                        
                        generalDePlata_aux=generalDePlata;                                            
                        break;
                        }
                    }
                
                platas_aux.remove(generalDePlata_aux);                
                generalDePlata_aux.setCapturado(false);
                                
                seteaArregloString(nuevo_tablero_Str, tablero, fila, columna, generalDePlata_aux.getJugador(), generalDePlata_aux.getTipo(), generalDePlata_aux.isCoronado());
                
                platas_aux.add(generalDePlata_aux);
                tablero.setGenerales_plata(platas_aux);
                tablero.setMatriz_tablero(nuevo_tablero_Str);
                break;
                
            case "Lancero":
                ArrayList<Lancero> lanceros = tablero.getLanceros();
                ArrayList<Lancero> lanceros_aux = lanceros;
                Lancero lancero_aux = new Lancero();
                
                for (Lancero lancero : lanceros) {
                    if (lancero.getId()==id_origen){
                        
                        lancero_aux=lancero;                                            
                        break;
                        }
                    }
                
                lanceros_aux.remove(lancero_aux);                
                lancero_aux.setCapturado(false);
                                
                seteaArregloString(nuevo_tablero_Str, tablero, fila, columna, lancero_aux.getJugador(), lancero_aux.getTipo(), lancero_aux.isCoronado());
                    
                lanceros_aux.add(lancero_aux);
                tablero.setLanceros(lanceros_aux);
                tablero.setMatriz_tablero(nuevo_tablero_Str);
                break;
                
            case "Peon":
                ArrayList<Peon> peones = tablero.getPeones();
                ArrayList<Peon> peones_aux = peones;
                Peon peon_aux = new Peon();
                
                for (Peon peon : peones) {
                    if (peon.getId()==id_origen){
                        
                        peon_aux=peon;                                            
                        break;
                        }
                    }
                
                peones_aux.remove(peon_aux);                
                peon_aux.setCapturado(false);
                                
                seteaArregloString(nuevo_tablero_Str, tablero, fila, columna, peon_aux.getJugador(), peon_aux.getTipo(), peon_aux.isCoronado());
                
                peones_aux.add(peon_aux);
                tablero.setPeones(peones_aux);
                tablero.setMatriz_tablero(nuevo_tablero_Str);
                break;
                
            case "Torre":
                ArrayList<Torre> torres = tablero.getTorres();
                ArrayList<Torre> torres_aux = torres;
                Torre torre_aux = new Torre();
                
                for (Torre torre : torres) {
                    if (torre.getId()==id_origen){
                        
                        torre_aux=torre;                                            
                        break;
                        }
                    }
                
                torres_aux.remove(torre_aux);                
                torre_aux.setCapturado(false);
                                
                seteaArregloString(nuevo_tablero_Str, tablero, fila, columna, torre_aux.getJugador(), torre_aux.getTipo(), torre_aux.isCoronado());
                               
                torres_aux.add(torre_aux);
                tablero.setTorres(torres_aux);
                tablero.setMatriz_tablero(nuevo_tablero_Str);
                break;
        }
    }

    public String buscaTipo(int pieza_buscada, ArrayList<Integer> piezas_capturadas, ArrayList<String> capturadas_tipo) {
        
        String tipo=null;
                
        for (Integer pieza : piezas_capturadas) {
            
            if (pieza==pieza_buscada){
                
                tipo=capturadas_tipo.get(piezas_capturadas.indexOf(pieza));
                break;
            }
        }
        return tipo;        
    }

    public void seteaArregloString(String [][] arreglo, Tablero tablero, int fila, int columna, Jugador jugador, String tipo, boolean coronado) {
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                
                if (fila==i && columna==j){
                    if (jugador.equals(negro)){
                        
                        if (coronado){
                            arreglo[fila][columna]="N #"+tipo;
                        }else{
                            arreglo[fila][columna]="N  "+tipo;
                        }
                    }else{
                        
                        if (coronado){
                            arreglo[fila][columna]="B #"+tipo;
                        }else{
                            arreglo[fila][columna]="B  "+tipo;
                        }
                    }
                }else{
                    arreglo[fila][columna]=tablero.getMatriz_tablero()[fila][columna];
                }                
            }            
        }        
    }
    
     
    /**Recibe la ubicaciÃƒÂ³n de la pieza en el tablero (ArrayList) por lo que al iterar el arreglo conseguimos el Id*/
    public int buscaId(int pieza, Tablero tablero){
        
        int fila=buscaFila(pieza), columna=buscaColumna(pieza);
                
        return tablero.getTablero_id_piezas()[fila][columna];
    }

    /* Recibe un entero que represneta a una casilla y devuleve la fila que represneta al mismo en la matriz*/
    public int buscaFila(int casilla) {
        
        int fila, auxiliar=casilla%10;
        
        fila=casilla-auxiliar;
        fila= fila/10;
        
        return fila-1;
        
    }
    
     /* Recibe un entero que represneta a una casilla y devuleve la columna que represneta al mismo en la matriz*/
    public int buscaColumna(int casilla) {
        
        int columna;
        
        columna=casilla%10;
                
        return columna-1;
        
    }  
}
