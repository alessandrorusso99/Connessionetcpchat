
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int port;
    Socket connection;
    ServerSocket sSocket;
    private boolean fineCom,setAut;
    String autore;
   

    void Server() {
        connection = null;
        sSocket = null;
        fineCom = false;
    }

    //oggetto ServerSocket necessario per accettare richieste dal client
    //oggetto da usare per realizzare la connessione TCP
    void avvioServer(int port) throws IOException {
        this.port = port;
        sSocket = new ServerSocket(port);
        System.out.println("In attesa di connessioni!");
        boolean uscita = false;
        while (uscita == false) {

            connection = sSocket.accept();
            System.out.println("Socket server: " + connection.getLocalSocketAddress());
            System.out.println("Socket client: " + connection.getRemoteSocketAddress());
            if (connection.isConnected() == true) {
                uscita = true;
            }
            System.out.println("Connessione stabilita su porta: " + sSocket.getLocalPort());
        }

    }

    void invioServer(String n) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        System.out.println("messaggio inviato");
        String b = n + "\n";
        bw.write(b);
        bw.flush();

    }

    String ricezioneServer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String a = br.readLine();
        System.out.println("il messaggio inviato dal client è " + a);
        return a;
       /* if (a.equalsIgnoreCase("end")) {
            fineCom = true;
        } else {
            fineCom = false;
        }
        if(a.equalsIgnoreCase("autore")){
            setAut=true;
        } else{
            setAut=false;
        }*/
    }

    public void chiusuraServer() throws IOException {

        //chiusura della connessione con il client
        try {
            connection.close();
            sSocket.close();
        } catch (IOException ex) {
            System.err.println("Errore nella chiusura della connessione!");
        }
        System.out.println("Connessione chiusa!");
    }
    public void setAutore(String autore){
        this.autore=autore;
    }
}
