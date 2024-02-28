package xyz.gamars.network.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import xyz.gamars.network.Network;
import xyz.gamars.network.Packet;

import java.io.IOException;

public class GameServer {

    private Server server;

    public GameServer() {
        server = new Server();
        try {
            server.bind(Network.PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Network.register(server);

    }

    public void start() {
        server.start();
    }

    public void close() {
        server.close();
    }

    public void addListener(Listener listener) {
        server.addListener(listener);
    }

    public void sendPacketToAllTCP(Packet packet) {
        server.sendToAllTCP(packet);
    }

    public void sendPacketToAllUDP(Packet packet) {
        server.sendToAllUDP(packet);
    }

    public void sendPacketToAllTCPExcept(int connectionID, Packet packet) {
        server.sendToAllExceptTCP(connectionID, packet);
    }

    public void sendPacketToAllUDPExcept(int connectionID, Packet packet) {
        server.sendToAllExceptUDP(connectionID, packet);
    }

    public void sendPacketTCP(int connectionID, Packet packet) {
        server.sendToTCP(connectionID, packet);
    }

    public void sendPacketUDP(int connectionID, Packet packet) {
        server.sendToUDP(connectionID, packet);
    }

    public Connection[] getConnections() {
        return server.getConnections();
    }

}
