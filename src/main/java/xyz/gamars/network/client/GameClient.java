package xyz.gamars.network.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import xyz.gamars.network.Network;
import xyz.gamars.network.Packet;

import java.io.IOException;

public class GameClient {

    private Client client;

    public GameClient() {
        client = new Client();

        Network.register(client);
    }

    /**
     * Starts the client
     * @author Daryan, Vishak, Sai
     */
    public void start() {
        client.start();

    }

    /**
     * Connects the client to the server
     * @author Daryan, Vishak, Sai
     */
    public void connect() {
        try {
            client.connect(5000, "mc.gamars.xyz", Network.PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the client
     * @author Daryan, Vishak, Sai
     */
    public void close() {
        client.close();
    }

    /**
     * Adds a listener to the client
     * @param listener listener
     * @author Daryan, Vishak, Sai
     */
    public void addListener(Listener listener) {
        client.addListener(listener);
    }

    /**
     * Sends a TCP packet to the server
     *
     * @param packet packet
     * @author Daryan, Vishak, Sai
     */
    public void sendPacketTCP(Packet packet) {
        client.sendTCP(packet);
    }

    /**
     * Sends a UDP packet to the server
     *
     * @param packet packet
     * @author Daryan, Vishak, Sai
     */
    public void sendPacketUDP(Packet packet) {
        client.sendUDP(packet);
    }

    /**
     * Returns whether the client is connected to the server
     * @author Daryan, Vishak, Sai
     */
    public boolean isConnected() {
        return client.isConnected();

    }


}
