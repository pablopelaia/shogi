package Shogi.Shogi.entidades;

public class Alfil extends Piezas {

    private boolean coronado;
    private boolean capturado;

    public Alfil() {
        this.coronado = false;
        this.capturado = false;
    }

    public boolean isCoronado() {
        return coronado;
    }

    public void setCoronado(boolean coronado) {
        this.coronado = coronado;
    }

    public boolean isCapturado() {
        return capturado;
    }

    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }
    
}