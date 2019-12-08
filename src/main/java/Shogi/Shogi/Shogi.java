package Shogi.Shogi;

import Shogi.Shogi.entidades.Partida;
import Shogi.Shogi.servicios.PartidaServicio;

public class Shogi {

/*Inicia el juego creando una partida, luego dentro de un "do-while" se iniciará un nuevo turno hasta que check mate
    sea verdadero 
 */	
    public static void main(String[] args) {
            
        PartidaServicio partidaservicio = new PartidaServicio();
        Partida partida = partidaservicio.crearPartida();
         
            do {
                partidaservicio.nuevoTurno(partida);
            } while (!partida.isCheck_mate());
         
            partidaservicio.anuncioJaqueMate(partida);
	}
}
