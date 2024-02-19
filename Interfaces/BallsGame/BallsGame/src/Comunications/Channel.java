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

    public Channel(TGComunications tgComunications, Interlocutor interlocutor) {

        this.tgComunications = tgComunications;
        this.interlocutor = interlocutor;
    }

    @Override
    public void run() {
        while (true) {

            try {
                // Leer mensajes entrantes
                if (SOCKET != null && SOCKET.isConnected()) {
                
                    System.out.println("Esperando un mensaje...");
                    this.dataIn();
                    System.out.println("Ha llegado un mensaje");
                } else if (SOCKET != null) {
                
                    this.setDownChannel();
                    System.out.println("Esprando conexion...");
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {

                System.out.println(e);
            }
        }
    }

    public void setSocket(Socket SOCKET) {
        try {
            this.SOCKET = SOCKET;
            // Inicializar los objetos BufferedReader y PrintWriter
            OutputStream os = this.SOCKET.getOutputStream();
            this.out = new ObjectOutputStream(os);
            InputStream is = this.SOCKET.getInputStream();
            this.in = new ObjectInputStream(is);
            // Una vez se ha creado el socket creamos el test channel para asegurar que siga funcionando
            this.testChanel = new TestChannel(this, 10000);
            new Thread(this.testChanel).start();
        } catch (IOException e) {

            System.out.println(e);
        }
    }

    public synchronized void setDownChannel() {
        // Si detectamos que la conexion no funciona como queremos eliminamos el socket 
        try {

            stopTestChannel();
            in.close();
            out.close();
            SOCKET.close();

        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            SOCKET = null;
            System.err.println("Matando el socket...");
        }
    }

    // Metodo para mandar informacion 
    public void sendData() {

    }
    
    // Metodo para recibir informacion
    public void dataIn() {
        try {
        
            DataFrame data = (DataFrame) in.readObject();
            if (data != null) {
                switch (data.getDataFramType()) {
                    case APLICATION_FRAME:
                        
                        break;
                    case INTERNAL_INFO:
                    
                        break;
                    case FRAME_REFUSED:
                    
                        break;
                    case KEEP_ALIVE:
                        System.out.println("Ping recibido");
                        this.sendPingBack();
                        break;
                    case KEEP_ALIVE_BACK:
                        System.out.println("Ping recibido de vuelta");
                        recievedTime = System.currentTimeMillis();
                        break;
                    default:
                        break;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    
    public void sendPing() {
        try {
            DataFrame data = new DataFrame(DataFrameType.KEEP_ALIVE, "Ping");
            out.writeObject(data);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sendPingBack() {
        try {
            DataFrame data = new DataFrame(DataFrameType.KEEP_ALIVE_BACK, "PingBack");
            out.writeObject(data);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
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
