package xyz.gamars.network.server.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import xyz.gamars.network.client.packets.PingPacket;
import xyz.gamars.network.server.packets.PongPacket;

/**
 * A listener for receiving Ping packets from clients and sending back Pong packets.
 *
 * @author Daryan, Vishak, Sai
 */
public class PingListener extends Listener {

    /**
     * Called when a Ping packet is received from a client.
     *
     * @param connection The connection from which the packet was received.
     * @param object     The object received.
     * @author Daryan, Vishak, Sai
     */
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof PingPacket) {
            PingPacket pingPacket = (PingPacket) object;
            System.out.println(pingPacket.getMessage());

            PongPacket pongPacket = new PongPacket();
            connection.sendTCP(pongPacket);

        }
    }
}
