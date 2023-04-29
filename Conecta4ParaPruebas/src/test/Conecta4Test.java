package src.test;

import src.domain.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class Conecta4Test
{
    public Conecta4Test(){
    }


    @Test
    public void shouldCreateConecta4(){
        try{
            Conecta4 gm = new Conecta4(4,4);
        }catch(Conecta4Exception e){
            fail("Threw a exception");
        }
        try{
            Conecta4 gm = new Conecta4(7,5);
        }catch(Conecta4Exception e){
            fail("Threw a exception");
        }
    }
    
    @Test
    public void shouldNotCreateConecta4(){
        try{
            Conecta4 gm = new Conecta4(3,3);
            fail("Did not throw exception");
        }catch(Conecta4Exception e){
            assertEquals(Conecta4Exception.WRONG_RANGE,e.getMessage());
        }
        
        try{
            Conecta4 gm = new Conecta4(-3,4);
            fail("Did not throw exception");
        }catch(Conecta4Exception e){
            assertEquals(Conecta4Exception.WRONG_RANGE,e.getMessage());
        }
    }
    
    
    @Test
    public void shouldChangePlayer(){
        try{
            Conecta4 gm = new Conecta4(4,4);
            for(int i = 0; i < 2; i++){
                assertTrue(gm.player());
                gm.play(0);
                assertFalse(gm.player());
                gm.play(0);
            }
        }catch(Conecta4Exception e){
            fail("Threw a exception");
        }
    }
    
    @Test
    public void shouldPlay(){
        try{
            Conecta4 gm = new Conecta4(4,4);
            for(int i = 0; i < 3; i++){
                gm.play(0);
                gm.play(1);
                gm.play(2);
                gm.play(3);
            }
        }catch(Conecta4Exception e){
            fail("Threw a exception");
        }
    }
    
    @Test
    public void shouldPlayTie(){
        try{
            Conecta4 gm = new Conecta4(4,4);
            for(int i = 0; i < 4; i++){
                gm.play(0);
                gm.play(1);
                gm.play(2);
                gm.play(3);
            }
            fail("Did not throw exception");
        }catch(Conecta4Exception e){
            assertEquals(Conecta4Exception.BOARD_FULL,e.getMessage());
        }
        
        try{
            Conecta4 gm = new Conecta4(12,15);
            for(int i = 0; i < 12; i++){
                for(int j = 0; j<15;j++){
                   gm.play(j); 
                }
            }
            fail("Did not throw exception");
        }catch(Conecta4Exception e){
            assertEquals(Conecta4Exception.BOARD_FULL,e.getMessage());
        }
    }
    
    @Test
    public void shouldNotPlayIfColumnFull(){
        try{
            Conecta4 gm = new Conecta4(4,4);
            gm.play(0);
            gm.play(0);
            gm.play(0);
            gm.play(0);
            gm.play(0);
            fail("Did not throw exception");
        }catch(Conecta4Exception e){
            assertEquals(Conecta4Exception.COLUMN_FULL,e.getMessage());
        }
    }
    
    @Test
    public void shouldNotPlayIfNoColumn(){
        try{
            Conecta4 gm = new Conecta4(4,4);
            gm.play(5);
            fail("Did not throw exception");
        }catch(Conecta4Exception e){
            assertEquals(Conecta4Exception.NO_COLUMN,e.getMessage());
        }
    }
    
    @Test
    public void shouldWin(){
        try{
            Conecta4 gm = new Conecta4(4,4);
            //Horizontal
            gm.play(0);
            gm.play(0);
            gm.play(1);
            gm.play(0);
            gm.play(2);
            gm.play(1);
            assertTrue(gm.play(3));
            //Vertical
            gm = new Conecta4(4,4);
            gm.play(0);
            gm.play(1);
            gm.play(0);
            gm.play(1);
            gm.play(0);
            gm.play(1);
            assertTrue(gm.play(0));
            //Diagonal
            gm = new Conecta4(4,4);
            gm.play(0); // jugador1 en columna 0
            gm.play(1); // jugador2 en columna 1
            gm.play(1); // jugador1 en columna 1
            gm.play(2); // jugador2 en columna 2
            gm.play(2); // jugador1 en columna 2
            gm.play(0); // jugador2 en columna 0
            gm.play(2); // jugador1 en columna 2
            gm.play(3); // jugador2 en columna 3
            gm.play(2); // jugador1 en columna 2
            gm.play(3);// jugador2 gana en columna 3
            gm.play(3);
            gm.play(0);
            assertTrue(gm.play(3));
            //Diagonal inversa
            gm = new Conecta4(4, 4);
            assertFalse(gm.play(3)); // Jugador 1
            assertFalse(gm.play(2)); // Jugador 2
            assertFalse(gm.play(1)); // Jugador 1
            assertFalse(gm.play(3)); // Jugador 2
            assertFalse(gm.play(2)); // Jugador 1
            assertFalse(gm.play(1)); // Jugador 2
            assertFalse(gm.play(0)); // Jugador 1
            assertFalse(gm.play(0)); // Jugador 2
            assertFalse(gm.play(1)); // Jugador 1
            assertFalse(gm.play(0)); // Jugador 2
            assertTrue(gm.play(0)); // Jugador 1
        }catch(Conecta4Exception e){
            System.out.println(e.getMessage());
            fail("Threw a exception");
        }
        
    }
}
