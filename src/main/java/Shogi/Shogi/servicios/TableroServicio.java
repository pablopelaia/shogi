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
                tablero_piezas[i][j]="     "; 
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
        
        Alfil alfil = new Alfil();
        
        a_serv.Crear(alfil, negras_movimientos, casillas_negras, 1, negro);
        ubicaFichaEnTablero(alfil.getPos_tablero(), alfil.getId(), tablero, alfil.getNombre());
        alfiles.add(alfil);
        a_serv.Crear(alfil, blancas_movimientos, casillas_blancas, 2, blanco);
        ubicaFichaEnTablero(alfil.getPos_tablero(), alfil.getId(), tablero, alfil.getNombre());
        alfiles.add(alfil);
                   
        Caballo caballo = new Caballo();
        
        c_serv.Crear(caballo, negras_movimientos, casillas_negras, 3, negro);
        ubicaFichaEnTablero(caballo.getPos_tablero(), caballo.getId(),tablero, caballo.getNombre());
        caballos.add(caballo);
        c_serv.Crear(caballo, negras_movimientos, casillas_negras, 4, negro);
        ubicaFichaEnTablero(caballo.getPos_tablero(), caballo.getId(),tablero, caballo.getNombre());
        caballos.add(caballo);
        c_serv.Crear(caballo, blancas_movimientos, casillas_blancas, 5, blanco);
        ubicaFichaEnTablero(caballo.getPos_tablero(), caballo.getId(),tablero, caballo.getNombre());
        caballos.add(caballo);
        c_serv.Crear(caballo, blancas_movimientos, casillas_blancas, 6, blanco);
        ubicaFichaEnTablero(caballo.getPos_tablero(), caballo.getId(),tablero, caballo.getNombre());
        caballos.add(caballo);
              
        GeneralDeOro generaldeoro = new GeneralDeOro();
        
        o_serv.Crear(generaldeoro, negras_movimientos, casillas_negras, 7, negro);
        ubicaFichaEnTablero(generaldeoro.getPos_tablero(), generaldeoro.getId(),tablero, generaldeoro.getNombre());
        generaldeoros.add(generaldeoro);
        o_serv.Crear(generaldeoro, negras_movimientos, casillas_negras, 8, negro);
        ubicaFichaEnTablero(generaldeoro.getPos_tablero(), generaldeoro.getId(),tablero, generaldeoro.getNombre());
        generaldeoros.add(generaldeoro);
        o_serv.Crear(generaldeoro, blancas_movimientos, casillas_blancas, 9, blanco);
        ubicaFichaEnTablero(generaldeoro.getPos_tablero(), generaldeoro.getId(),tablero, generaldeoro.getNombre());
        generaldeoros.add(generaldeoro);
        o_serv.Crear(generaldeoro, blancas_movimientos, casillas_blancas, 10, blanco);
        ubicaFichaEnTablero(generaldeoro.getPos_tablero(), generaldeoro.getId(),tablero, generaldeoro.getNombre());
        generaldeoros.add(generaldeoro);
                
        GeneralDePlata generaldeplata = new GeneralDePlata();
        
        gp_serv.Crear(generaldeplata, negras_movimientos, casillas_negras, 11, negro);
        ubicaFichaEnTablero(generaldeplata.getPos_tablero(), generaldeplata.getId(),tablero, generaldeplata.getNombre());
        generaldeplatas.add(generaldeplata);
        gp_serv.Crear(generaldeplata, negras_movimientos, casillas_negras, 12, negro);
        ubicaFichaEnTablero(generaldeplata.getPos_tablero(), generaldeplata.getId(),tablero, generaldeplata.getNombre());
        generaldeplatas.add(generaldeplata);
        gp_serv.Crear(generaldeplata, blancas_movimientos, casillas_negras, 13, blanco);
        ubicaFichaEnTablero(generaldeplata.getPos_tablero(), generaldeplata.getId(),tablero, generaldeplata.getNombre());
        generaldeplatas.add(generaldeplata);
        gp_serv.Crear(generaldeplata, blancas_movimientos, casillas_negras, 14, blanco);
        ubicaFichaEnTablero(generaldeplata.getPos_tablero(), generaldeplata.getId(),tablero, generaldeplata.getNombre());
        generaldeplatas.add(generaldeplata);

        Lancero lancero = new Lancero();
        l_serv.Crear(lancero, 15, negro, casillas_negras);
        ubicaFichaEnTablero(lancero.getPos_tablero(), lancero.getId(),tablero, lancero.getNombre());
        lanceros.add(lancero);
        l_serv.Crear(lancero, 16, blanco, casillas_blancas);
        ubicaFichaEnTablero(lancero.getPos_tablero(), lancero.getId(),tablero, lancero.getNombre());
        lanceros.add(lancero);
        
        Peon peon = new Peon();
        
        for (int i = 17; i < 26; i++) {
           p_serv.Crear(peon, negras_movimientos, casillas_negras, i, negro);
            ubicaFichaEnTablero(peon.getPos_tablero(), peon.getId(),tablero, peon.getNombre());
           peones.add(peon); 
        }
        for (int i = 26; i < 35; i++) {
            p_serv.Crear(peon, blancas_movimientos, casillas_blancas, i, blanco);
            ubicaFichaEnTablero(peon.getPos_tablero(), peon.getId(),tablero, peon.getNombre());
            peones.add(peon);
        }

        Torre torre = new Torre();
        
        t_serv.Crear(torre, negras_movimientos, casillas_negras, 35, negro);
        ubicaFichaEnTablero(torre.getPos_tablero(), torre.getId(),tablero, torre.getNombre());
        torres.add(torre);
        t_serv.Crear(torre, negras_movimientos, casillas_negras, 36, negro);
        ubicaFichaEnTablero(torre.getPos_tablero(), torre.getId(),tablero, torre.getNombre());
        torres.add(torre);
        t_serv.Crear(torre, blancas_movimientos, casillas_blancas, 37, blanco);
        ubicaFichaEnTablero(torre.getPos_tablero(), torre.getId(),tablero, torre.getNombre());
        torres.add(torre);
        t_serv.Crear(torre, blancas_movimientos, casillas_blancas, 38, blanco);
        ubicaFichaEnTablero(torre.getPos_tablero(), torre.getId(),tablero, torre.getNombre());
        torres.add(torre);
        
        ReyServicio s_rey = new ReyServicio();
        
        Rey rey = new Rey();
        rey=s_rey.Crear(negro, negras_movimientos, casillas_negras, 39);
        ubicaFichaEnTablero(rey.getPos_tablero(), rey.getId(),tablero, rey.getNombre());
        tablero.setRey_negro(rey);
        rey=s_rey.Crear(blanco, blancas_movimientos, casillas_blancas, 40);
        ubicaFichaEnTablero(rey.getPos_tablero(), rey.getId(),tablero, rey.getNombre());
        tablero.setRey_blanco(rey);
  
        tablero.setAlfiles(alfiles);
        tablero.setCaballos(caballos);
        tablero.setGenerales_oro(generaldeoros);
        tablero.setGenerales_plata(generaldeplatas);
        tablero.setLanceros(lanceros);
        tablero.setPeones(peones);
        tablero.setTorres(torres);
        tablero.setCaptura_por_las_blancas(null);
        tablero.setCapturada_por_las_negreas(null);
        
        return tablero;
    }
    
    /*Recibe un casillero y el color que queremos ver si ocupa o no el mismo, devuelve un valor booleano*/
    public boolean posicionOcupada (Tablero tablero, Integer buscar, Jugador color){      
        
        boolean respuesta=false; 
                
        if (color.equals(negro)){
            
            for (Integer casilla : tablero.getNegras_movimientos()) {
                if (casilla.equals(buscar)){
                    respuesta=true;
                    break;
                }
            }      
        }else{
            
            for (Integer casilla : tablero.getBlancas_movimientos()) {
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
        int fila,columna;
        
        columna=posicion%10;
        fila=((posicion-columna)/10)-1;
        columna=columna-1;
        
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
            }
            System.out.println("");
        }
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
                movimientos=null;
                
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
                movimientos=null;
                
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
                movimientos=null;
                
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
                movimientos=null;
                
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
                movimientos=null;
                
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
                movimientos=null;
                
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
                movimientos=null;
                
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

    public boolean compruebaCasillaLibre(int casilla_destino, Tablero tablero) {
        
        int columna, fila;
        boolean respuesta=false;
        
        columna=casilla_destino%10;
        fila=((casilla_destino-columna)/10)-1;
        columna=columna-1;
        
        if (tablero.getTablero_id_piezas()[fila][columna]==0){
            respuesta=true;                   
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
        
        int columna, fila;        
        int[][] nuevo_tablero = new int[9][9];
        String[][] nuevo_tablero_Str = new String[9][9];
        
        columna=casilla_destino%10;
        fila=((casilla_destino-columna)/10)-1;
        columna=columna-1;
        
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
                                
                if (respuesta.equals("si")){
                    alfil_aux.setCoronado(true);                    
                    coronaPieza(tablero, fila, columna, alfil_aux.getJugador(), alfil_aux.getTipo());
                }
                
                alfiles_aux.add(alfil_aux);
                tablero.setAlfiles(alfiles_aux);
                
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
                                
                if (respuesta.equals("si")){
                    caballo_aux.setCoronado(true);                    
                    coronaPieza(tablero, fila, columna, caballo_aux.getJugador(), caballo_aux.getTipo());                    
                }
                
                caballos_aux.add(caballo_aux);
                tablero.setCaballos(caballos_aux);
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
                                
                oros_aux.add(generalDeOro_aux);
                tablero.setGenerales_oro(oros_aux);
                
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
                                
                if (respuesta.equals("si")){
                    generalDePlata_aux.setCoronado(true);
                    coronaPieza(tablero, fila, columna, generalDePlata_aux.getJugador(), generalDePlata_aux.getTipo());
                }
                
                platas_aux.add(generalDePlata_aux);
                tablero.setGenerales_plata(platas_aux);
                
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
                                
                if (respuesta.equals("si")){
                    lancero_aux.setCoronado(true);
                    coronaPieza(tablero, fila, columna, lancero_aux.getJugador(), lancero_aux.getTipo());
                }
                
                lanceros_aux.add(lancero_aux);
                tablero.setLanceros(lanceros_aux);
                
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
                                
                if (respuesta.equals("si")){
                    peon_aux.setCoronado(true);
                    coronaPieza(tablero, fila, columna, peon_aux.getJugador(), peon_aux.getTipo());
                }
                
                peones_aux.add(peon_aux);
                tablero.setPeones(peones_aux);
                
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
                                
                if (respuesta.equals("si")){
                    torre_aux.setCoronado(true);
                    coronaPieza(tablero, fila, columna, torre_aux.getJugador(), torre_aux.getTipo());
                }
                
                torres_aux.add(torre_aux);
                tablero.setTorres(torres_aux);
                
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

    private void coronaPieza(Tablero tablero, int fila, int columna, Jugador jugador, String tipo) {
        
        String [][] arreglo = tablero.getMatriz_tablero();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                
                if (fila==i && columna==j){
                    if (jugador.equals(negro)){
                        arreglo[fila][columna]="▲CӾ"+tipo;
                    }else{
                        arreglo[fila][columna]="▼CӾ"+tipo;
                    }
                }else{
                    arreglo[fila][columna]=tablero.getMatriz_tablero()[fila][columna];
                }                
            }            
        }        
    }
}
