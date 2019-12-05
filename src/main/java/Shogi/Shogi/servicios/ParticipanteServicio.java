
package Shogi.Shogi.servicios;

import Shogi.Shogi.entidades.Participante;
import static Shogi.Shogi.enumeraciones.Jugador.blanco;
import static Shogi.Shogi.enumeraciones.Jugador.negro;

public class ParticipanteServicio {
    
    public void Crear (Participante jugador_1, Participante jugador_2, String nombre_1, String nombre_2){
        
        int sorteo;
        
        sorteo=(int)(Math.random()*10);
        System.out.println("CREAR PARTICIPANTES SORTEO="+sorteo);
        if (sorteo%2==0){
            jugador_1.setId(1);
            jugador_1.setNombre(nombre_1);
            jugador_1.setColor(negro);
            jugador_2.setId(2);
            jugador_2.setNombre(nombre_2);
            jugador_2.setColor(blanco);
        }else{
            jugador_2.setId(1);
            jugador_2.setNombre(nombre_1);
            jugador_2.setColor(negro);
            jugador_1.setId(2);
            jugador_1.setNombre(nombre_2);
            jugador_1.setColor(blanco);
        }
        
        System.out.println("PARTICIPANTES CREADOS,FIN MÉTODO");

    }
}
