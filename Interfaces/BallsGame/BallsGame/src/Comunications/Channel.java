package Comunications;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Channel implements Runnable {

    private TGComunications tgComunications;
    private TestChannel testChanel;

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket SOCKET;

    private int sendTime;
    private long recievedTime;

    private Interlocutor interlocutor;
    private boolean isClient;

    public Channel(TGComunications tgComunications, boolean isClient) {

        this.tgComunications = tgComunications;
        this.isClient = isClient;
        this.interlocutor = new Interlocutor();
    }

    @Override
    public void run() {
        setSocket();
        while (true) {

            try {

                // Leer mensajes entrantes
                if (SOCKET != null && SOCKET.isConnected()) {
                
                    System.out.println("Esperando un mensaje");
                    dataIn();
                    System.out.println("Ha llegado un mensaje");
                } else {

                    System.out.println("Conexión perdida, intentando reconectar...");
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {

                System.out.println(e);
            }
        }
    }

    public void setSocket() {
        try {
            if (isClient) {
                
                // Iniciar socket como cliente
                this.tgComunications.setClientConection(
                        new ClientConection(this.tgComunications, 1616, "localhost"));
                this.tgComunications.getClientConection().setIntentarReconectar(true);
                this.tgComunications.getClientConection().createConnection();
                this.SOCKET = this.tgComunications.getClientConection().getSOCKET();
                System.out.println("Conexion establecida como cliente correctamente");
            } else {

                // Iniciar socket como servidor
                this.tgComunications
                        .setServerConection(new ServerConection(this.tgComunications, 1626));
                this.tgComunications.getServerConection().createConnection();
                this.SOCKET = this.tgComunications.getServerConection().getCLSOCK();
                System.out.println("Conexion establecida como servidor correctamente");
            }
            System.out.println(this.SOCKET);

            // Inicializar los objetos BufferedReader y PrintWriter
            OutputStream os = SOCKET.getOutputStream();
            this.out = new ObjectOutputStream(os);
            InputStream is = SOCKET.getInputStream();
            this.in = new ObjectInputStream(is);
    
            // Una vez se ha creado el socket creamos el test channel para asegurar que siga funcionando
            this.testChanel = new TestChannel(this, 10000);
            new Thread(this.testChanel).start();
        } catch (IOException e) {

            System.out.println(e);
        }
    }

    public synchronized void killSocket() {
        // Si detectamos que la conexion no funciona como queremos eliminamos el socket 
        try {

            stopTestChannel();
            in.close();
            out.close();
            if (this.tgComunications.getServerConection() != null) {
                SOCKET.close();
                if (!this.tgComunications.getServerConection().isSocketClosed()) {
                    this.tgComunications.getServerConection().killSocket();
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            SOCKET = null;
            System.err.println("Matando el socket...");
        }
        
        // Una vez ha sido eliminado lo volvemos a crear y el nuevo se quedara esperando a que se reestablezca la connexion
        try {
            Thread.sleep(5000);
            setSocket();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    // Metodo para mandar informacion 
    public void sendData() {

    }
    
    // Metodo para recibir informacion
    public void dataIn() {
        Message m;
        try {

            while (SOCKET != null && (m = (Message) in.readObject()) != null) {
                if (m.isPing()) {

                    System.out.println("Ping recibido");
                    return;
                }

                long time = (System.currentTimeMillis());
                setRecievedTime(time);
            }
        } catch (Exception e) {

            System.err.println("Error en la conexion");
            killSocket();
        }
    }
    
    // Metodo para hacer pings y asegurar el funcionamiento correcto del Socket
    public boolean ping() {
        try {
            System.out.println("Manda ping");
            out.writeObject(Message.ping());
            return true;
        } catch (Exception e) {
            System.out.println("Error en el envío del heartbeat: " + e);
            return false;
        }
    }
    
    // Parar el hilo del test channel
    public void stopTestChannel() {
        if (testChanel != null) {
            testChanel.pararEjecucion();
            testChanel = null;
        }
    }

    // Getters And Setters
    public boolean isOk() {
        return false;
    }

    public TGComunications getTgComunications() {
        return tgComunications;
    }

    public void setTgComunications(TGComunications tgComunications) {
        this.tgComunications = tgComunications;
    }

    public TestChannel getTestChanel() {
        return testChanel;
    }

    public void setTestChanel(TestChannel testChanel) {
        this.testChanel = testChanel;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public Socket getSOCKET() {
        return SOCKET;
    }

    public void setSOCKET(Socket socket) {
        this.SOCKET = socket;
    }

    public int getSendTime() {
        return sendTime;
    }

    public void setSendTime(int sendTime) {
        this.sendTime = sendTime;
    }

    public long getRecievedTime() {
        return recievedTime;
    }

    public void setRecievedTime(long recievedTime) {
        this.recievedTime = recievedTime;
    }

    public Interlocutor getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(Interlocutor interlocutor) {
        this.interlocutor = interlocutor;
    }

    public boolean isClient() {
        return isClient;
    }

    public void setIsClient(boolean wasClient) {
        this.isClient = wasClient;
    }
}
