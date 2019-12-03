package Shogi.Shogi.entidades;

public class Rey extends Piezas{
    private boolean jaque;

    public Rey() {
        this.jaque = false;
    }

    public boolean isJaque() {
        return jaque;
    }

    public void setJaque(boolean jaque) {
        this.jaque = jaque;
    }
       
}
