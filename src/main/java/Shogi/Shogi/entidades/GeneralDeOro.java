package Shogi.Shogi.entidades;

public class GeneralDeOro extends Piezas{

    private boolean capturado;

    public GeneralDeOro() {
        this.capturado = false;
    }

    public boolean isCapturado() {
        return capturado;
    }

    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }
        
}