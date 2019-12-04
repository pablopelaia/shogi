package Shogi.Shogi.entidades;

public class Partida {
    
    private int turno;
    private  Participante participante_negras;
    private  Participante participante_blancas;
    private boolean check_mate;
    private Tablero tablero;

    public Partida() {        
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Participante getParticipante_negras() {
        return participante_negras;
    }

    public void setParticipante_negras(Participante participante_negras) {
        this.participante_negras = participante_negras;
    }

    public Participante getParticipante_blancas() {
        return participante_blancas;
    }

    public void setParticipante_blancas(Participante participante_blancas) {
        this.participante_blancas = participante_blancas;
    }

    public boolean isCheck_mate() {
        return check_mate;
    }

    public void setCheck_mate(boolean check_mate) {
        this.check_mate = check_mate;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

   
}