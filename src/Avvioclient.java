    
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class Avvioclient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int port=2000;   
        // TODO code application logic here
        Client c=new Client();
        c.avvioClient("localhost", port);
        while(c.stopCom()==false){
            c.invioClient();
            if(c.stopCom()==false){
                c.ricezioneClient();
            }
        } 
        c.chiusuraClient();
    }
   
}
