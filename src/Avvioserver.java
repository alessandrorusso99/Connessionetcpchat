
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class Avvioserver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int port = 2000;
        Server s = new Server();
        s.avvioServer(port);
        System.out.println("fuori ");
        boolean stopCom=false;
        while (stopCom == false) {
            String a= s.ricezioneServer();
            if (a.equalsIgnoreCase("end")) {
                stopCom = true;
            } else {
                stopCom = false;
            }
            if (a.equalsIgnoreCase("autore")) {
                s.invioServer("inserisci il nome utente");
                String b= s.ricezioneServer();
                s.setAutore(b);
            } 
            
        }
        s.chiusuraServer();

        // s.ricezioneServe r();
    }


}
