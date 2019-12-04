package Shogi.Shogi.entidades;

import java.util.ArrayList;
import java.util.HashSet;

public class Tablero {
    private String [][] matriz_tablero = new String [9][9];
    private int [][] tablero_id_piezas = new int [9][9];
    private ArrayList<Integer> captura_por_las_blancas;
    private ArrayList<Integer> capturada_por_las_negreas;
    private ArrayList<Alfil> alfiles;
    private ArrayList<Caballo> caballos;
    private ArrayList<GeneralDeOro> generales_oro;
    private ArrayList<GeneralDePlata> generales_plata;
    private ArrayList<Lancero> lanceros;
    private ArrayList<Peon> peones;
    private ArrayList<Torre> torres;
    private Rey rey_blanco;
    private Rey rey_negro;
    private HashSet<Integer> blancas_movimientos;
    private HashSet<Integer> negras_movimientos;
    private HashSet<Integer> casillas_blancas;
    private HashSet<Integer> casillas_negras;
    
    public Tablero() {
        this.captura_por_las_blancas = null;
        this.capturada_por_las_negreas = null;
    }
 

    public ArrayList<Integer> getCaptura_por_las_blancas() {
        return captura_por_las_blancas;
    }

    public void setCaptura_por_las_blancas(ArrayList<Integer> captura_por_las_blancas) {
        this.captura_por_las_blancas = captura_por_las_blancas;
    }

    public ArrayList<Integer> getCapturada_por_las_negreas() {
        return capturada_por_las_negreas;
    }

    public void setCapturada_por_las_negreas(ArrayList<Integer> capturada_por_las_negreas) {
        this.capturada_por_las_negreas = capturada_por_las_negreas;
    }

    public ArrayList<Alfil> getAlfiles() {
        return alfiles;
    }

    public void setAlfiles(ArrayList<Alfil> alfiles) {
        this.alfiles = alfiles;
    }

    public ArrayList<Caballo> getCaballos() {
        return caballos;
    }

    public void setCaballos(ArrayList<Caballo> caballos) {
        this.caballos = caballos;
    }

    public ArrayList<GeneralDeOro> getGenerales_oro() {
        return generales_oro;
    }

    public void setGenerales_oro(ArrayList<GeneralDeOro> generales_oro) {
        this.generales_oro = generales_oro;
    }

    public ArrayList<GeneralDePlata> getGenerales_plata() {
        return generales_plata;
    }

    public void setGenerales_plata(ArrayList<GeneralDePlata> generales_plata) {
        this.generales_plata = generales_plata;
    }

    public ArrayList<Lancero> getLanceros() {
        return lanceros;
    }

    public void setLanceros(ArrayList<Lancero> lanceros) {
        this.lanceros = lanceros;
    }

    public ArrayList<Peon> getPeones() {
        return peones;
    }

    public void setPeones(ArrayList<Peon> peones) {
        this.peones = peones;
    }

    public ArrayList<Torre> getTorres() {
        return torres;
    }

    public void setTorres(ArrayList<Torre> torres) {
        this.torres = torres;
    }

    public Rey getRey_blanco() {
        return rey_blanco;
    }

    public void setRey_blanco(Rey rey_blanco) {
        this.rey_blanco = rey_blanco;
    }

    public Rey getRey_negro() {
        return rey_negro;
    }

    public void setRey_negro(Rey rey_negro) {
        this.rey_negro = rey_negro;
    }

    public HashSet<Integer> getBlancas_movimientos() {
        return blancas_movimientos;
    }

    public void setBlancas_movimientos(HashSet<Integer> blancas_movimientos) {
        this.blancas_movimientos = blancas_movimientos;
    }

    public HashSet<Integer> getNegras_movimientos() {
        return negras_movimientos;
    }

    public void setNegras_movimientos(HashSet<Integer> negras_movimientos) {
        this.negras_movimientos = negras_movimientos;
    }

    public HashSet<Integer> getCasillas_blancas() {
        return casillas_blancas;
    }

    public void setCasillas_blancas(HashSet<Integer> casillas_blancas) {
        this.casillas_blancas = casillas_blancas;
    }

    public HashSet<Integer> getCasillas_negras() {
        return casillas_negras;
    }

    public void setCasillas_negras(HashSet<Integer> casillas_negras) {
        this.casillas_negras = casillas_negras;
    }

    public String[][] getMatriz_tablero() {
        return matriz_tablero;
    }

    public void setMatriz_tablero(String[][] matriz_tablero) {
        this.matriz_tablero = matriz_tablero;
    }

    public int[][] getTablero_id_piezas() {
        return tablero_id_piezas;
    }

    public void setTablero_id_piezas(int[][] tablero_id_piezas) {
        this.tablero_id_piezas = tablero_id_piezas;
    }

     
}
    

    