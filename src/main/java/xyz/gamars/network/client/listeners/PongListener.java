package xyz.gamars.network.client.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import xyz.gamars.network.server.packets.PongPacket;

/**
 * A listener that receives Pong packets from server.
 * @author Daryan, Vishak, Sai
 */
public class PongListener extends Listener {

    /**
     * Called when a Pong packet is received.
     *
     * @param connection The connection from which the packet was received.
     * @param object     The object received.
     * @author Daryan, Vishak, Sai
     */
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof PongPacket) {
            PongPacket pongPacket = (PongPacket) object;
            System.out.println(pongPacket.getMessage());
        }
    }
}
