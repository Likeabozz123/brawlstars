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

    public void start() {
        client.start();

    }

    public void connect() {
        try {
            client.connect(5000, "localhost", Network.PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        client.close();
    }

    public void addListener(Listener listener) {
        client.addListener(listener);
    }

    public void sendPacketTCP(Packet packet) {
        client.sendTCP(packet);
    }

    public void sendPacketUDP(Packet packet) {
        client.sendUDP(packet);
    }

    public boolean isConnected() {
        return client.isConnected();

    }





}
