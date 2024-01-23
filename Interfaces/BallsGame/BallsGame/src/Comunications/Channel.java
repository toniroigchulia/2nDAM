package Comunications;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Channel implements Runnable {

    private TGComunications tgComunications;
    private TestChanel testChanel;

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;

    private int sendTime;
    private long recievedTime;

    private Interlocutor interlocutor;
    private boolean runState;
    private boolean wasClient;

    public Channel(TGComunications tgComunications) {
    
        this.tgComunications = tgComunications;
        this.interlocutor = new Interlocutor();
        this.testChanel = new TestChanel(this, 10000);
        new Thread(this.testChanel).start();
        setSocket();
    }

    @Override
    public void run() {
        while (runState) {
        
            System.out.println("No se ha perdido la conexion");
            try {
            
                // Verificar si la conexión actual está cerrada
                if (socket == null || socket.isClosed() || !socket.isConnected()) {
                    System.out.println("Conexión perdida, intentando reconectar...");
                    Thread.sleep(1000);
                    reconnect();
                }

                // Leer mensajes entrantes
                if (socket != null && socket.isConnected()) {
                    dataIn();
                }

                // Esperar un poco antes de verificar la conexión de nuevo
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            
                System.out.println("Error en la reconexión: " + e);
            }
        }
    }

    public void setSocket() {
        try {
            boolean clientConection;

            this.tgComunications.setClientConection(
                    new ClientConection(this.tgComunications, interlocutor.getPort(), interlocutor.getIp()));
            this.tgComunications.getClientConection().setIntentarReconectar(false);
            this.tgComunications.getClientConection().run();
            this.socket = this.tgComunications.getClientConection().getSocket();
            clientConection = this.tgComunications.getClientConection().isConexionEstablecida();
            if (clientConection) {

                wasClient = true;
                System.out.println("Conexion establecida como cliente correctamente");
            } else {

                this.tgComunications.getClientConection().setIntentarReconectar(true);
                System.out.println("Abortando conexion como cliente...");
                // Iniciar el servidor
                this.tgComunications
                        .setServerConection(new ServerConection(this.tgComunications, interlocutor.getPort()));
                this.tgComunications.getServerConection().run();
                this.socket = this.tgComunications.getServerConection().getClsock();
                System.out.println("Conexion establecida como servidor correctamente");
            }

            System.out.println(socket);
            // Inicializar los objetos BufferedReader y PrintWriter
            OutputStream os = socket.getOutputStream();
            this.out = new ObjectOutputStream(os);
            InputStream is = socket.getInputStream();
            this.in = new ObjectInputStream(is);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void reconnect() {
        try {
            // Iniciar servidor y esperar conexiones entrantes
            System.out.println("Iniciando servidor...");
            this.tgComunications.setServerConection(new ServerConection(this.tgComunications, interlocutor.getPort()));
            this.tgComunications.getServerConection().run();
            socket = this.tgComunications.getServerConection().getClsock();
            TestChanel healthCareConnection = new TestChanel(this, 10000);
            setTestChanel(healthCareConnection);
            new Thread(healthCareConnection).start();

            // Reinicializar los objetos BufferedReader y PrintWriter
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {

            System.err.println("No se ha podido conectar");
        } catch (NullPointerException e) {

            System.out.println("Error en la conexión: " + e);
        }
    }

    public synchronized void killSocket() {
        try {
        
            stopTestChannel();
            in.close();
            out.close();
            if (this.tgComunications.getServerConection() != null) {
            
                socket.close();
                if (!this.tgComunications.getServerConection().isSocketClosed()) {
                
                    this.tgComunications.getServerConection().killSocket();
                }
            }
        } catch (IOException e) {
        
            e.printStackTrace();
        } finally {
        
            socket = null;
            System.err.println("Matando el socket...");
        }
    }

    public void sendData() {

    }
    
    public void dataIn() {
        Message m;
        System.out.println("Esperando mensaje...");
        try {
        
            while (socket != null && socket.isConnected() && (m = (Message) in.readObject()) != null) {
            
                System.out.println("Recibido ping");
                if (m.isPing()) {
                
                    System.out.println("Recibido ping");
                    dataIn();
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
    
    public void stopTestChannel(){
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

    public TestChanel getTestChanel() {
        return testChanel;
    }

    public void setTestChanel(TestChanel testChanel) {
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

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
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

    public boolean isRunState() {
        return runState;
    }

    public void setRunState(boolean runState) {
        this.runState = runState;
    }

    public boolean isWasClient() {
        return wasClient;
    }

    public void setWasClient(boolean wasClient) {
        this.wasClient = wasClient;
    }
}
