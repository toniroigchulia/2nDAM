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
        this.testChanel = new TestChannel(this, 10000);
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
                    // Esperar un poco antes de verificar la conexión de nuevo
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

                this.tgComunications.setClientConection(
                        new ClientConection(this.tgComunications, interlocutor.getPORT(), interlocutor.getIP()));
                this.tgComunications.getClientConection().setIntentarReconectar(true);
                this.tgComunications.getClientConection().run();
                this.SOCKET = this.tgComunications.getClientConection().getSOCKET();
                System.out.println("Conexion establecida como cliente correctamente");
            } else {

                // Iniciar el servidor
                this.tgComunications
                        .setServerConection(new ServerConection(this.tgComunications, 1620));
                this.tgComunications.getServerConection().run();
                this.SOCKET = this.tgComunications.getServerConection().getCLSOCK();
                System.out.println("Conexion establecida como servidor correctamente");
            }

            System.out.println(this.SOCKET);
            // Inicializar los objetos BufferedReader y PrintWriter
            OutputStream os = SOCKET.getOutputStream();
            this.out = new ObjectOutputStream(os);
            InputStream is = SOCKET.getInputStream();
            this.in = new ObjectInputStream(is);

            new Thread(this.testChanel).start();
        } catch (IOException e) {

            System.out.println(e);
        }
    }

    public synchronized void killSocket() {
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
    }

    public void sendData() {

    }

    public void dataIn() {
        Message m;
        try {

            while (SOCKET != null && (m = (Message) in.readObject()) != null) {
                if (m.isPing()) {

                    System.out.println("Recibido ping");
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

    public void stopTestChannel() {
        if (testChanel != null) {
            testChanel.pararEjecucion();
            testChanel = null;
        }
    }

    public boolean ping() {
        try {
            System.out.println("Entra en ping");
            out.writeObject(Message.ping());
            return true;
        } catch (Exception e) {
            System.out.println("Error en el envío del heartbeat: " + e);
            return false;
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
