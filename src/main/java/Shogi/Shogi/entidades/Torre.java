package Shogi.Shogi.entidades;

public class Torre extends Piezas{

    private boolean capturado;
    private boolean coronado;

    public Torre() {
        this.capturado = capturado;
        this.coronado = coronado;
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
