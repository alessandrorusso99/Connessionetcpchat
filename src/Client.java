    
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.System.in;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.net.InetAddress;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class Client {


    Socket connection;
    //nome o IP del server
    String serverAddress;
    //porta del server in ascolto
    int port;
    private boolean fineCom;
    String autore;

    void Client() {
        connection = null;
        fineCom=false;
        //nome o IP del server
    }

    void avvioClient(String serverAddress,int port) {
        //apertura della connessione al server sulla porta specificata
        this.serverAddress=serverAddress;
        this.port=port;
        System.out.println("tentativo connessione");
        try {
            InetAddress address = InetAddress.getByName(serverAddress); //traduzione in formato indirizzo per il socket
            connection = new Socket(address, port);
            System.out.println("Connessione aperta");
        } catch (ConnectException e) {
            System.err.println("Server non disponibile!");
        } catch (UnknownHostException e1) {
            System.err.println("Errore DNS!");
        } catch (IOException e2) {//
            System.err.println(e2);
            e2.printStackTrace();
        }
    }

    void invioClient() {
        try {  
            System.out.println("inserisci messaggio per il server");
            InputStreamReader tastiera=new InputStreamReader(System.in);
            BufferedReader buffer=new BufferedReader(tastiera);
            String n=buffer.readLine();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            String b = n + "\n";
            bw.write(b);
            bw.flush();
            System.out.println("il messaggio inviato ");
            if(n.equalsIgnoreCase("end")){
                fineCom=true;
            }else{
                fineCom=false;
            }           

        } catch (IOException a) {} //chiusura della connnessione
    }
    void ricezioneClient() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String a = br.readLine();
        System.out.println("il messaggio inviato dal server è " + a);
    }
    void chiusuraClient(){ 
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connessione chiusa!");
            }
        } catch (IOException e) {
            System.err.println("Errore nella chiusura della connessione!");
        }    
    }
    public boolean stopCom(){
        return fineCom;
    }
}

    

