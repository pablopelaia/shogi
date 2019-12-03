package Shogi.Shogi.entidades;

public class Lancero extends Piezas{

    private boolean capturado;
    private boolean coronado;

    public Lancero() {
        this.capturado = false;
        this.coronado = false;
    }    

    public boolean isCapturado() {
        return capturado;
    }

    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }

    public boolean isCoronado() {
        return coronado;
    }

    public void setCoronado(boolean coronado) {
        this.coronado = coronado;
    }
    
}
