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

    /**
     * Starts the server
     * @author Daryan, Vishak, Sai
     */
    public void start() {
        server.start();
    }

    /**
     * Closes the server
     */
    public void close() {
        server.close();
    }

    /**
     * Adds a listener to the server
     *
     * @param listener listener
     */
    public void addListener(Listener listener) {
        server.addListener(listener);
    }

    /**
     * Send packet to all connected through TCP
     *
     * @param packet packet
     */
    public void sendPacketToAllTCP(Packet packet) {
        server.sendToAllTCP(packet);
    }

    /**
     * Send packet to all connected through UDP
     *
     * @param packet packet
     */
    public void sendPacketToAllUDP(Packet packet) {
        server.sendToAllUDP(packet);
    }

    /**
     * Send a packet to all connected except one connection, through TCP
     *
     * @param connectionID connection id of the excluded connection
     * @param packet       packet
     */
    public void sendPacketToAllTCPExcept(int connectionID, Packet packet) {
        server.sendToAllExceptTCP(connectionID, packet);
    }

    /**
     * Send a packet to all connected except one connection, through UDP
     *
     * @param connectionID connection id of the excluded connection
     * @param packet       packet
     */
    public void sendPacketToAllUDPExcept(int connectionID, Packet packet) {
        server.sendToAllExceptUDP(connectionID, packet);
    }

    /**
     * Send packet to single connection through TCP
     *
     * @param connectionID connection id to send to
     * @param packet       packet
     */
    public void sendPacketTCP(int connectionID, Packet packet) {
        server.sendToTCP(connectionID, packet);
    }

    /**
     * Send packet to single connection through UDP
     *
     * @param connectionID connection id to send to
     * @param packet       packet
     */
    public void sendPacketUDP(int connectionID, Packet packet) {
        server.sendToUDP(connectionID, packet);
    }

    /**
     * Return all connections to the server
     *
     * @return connections
     */
    public Connection[] getConnections() {
        return server.getConnections();
    }

}
