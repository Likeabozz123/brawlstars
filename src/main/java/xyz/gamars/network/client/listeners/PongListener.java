package xyz.gamars.network.client.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import xyz.gamars.network.server.packets.PongPacket;

public class PongListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof PongPacket) {
            PongPacket pongPacket = (PongPacket) object;
            System.out.println(pongPacket.getMessage());
        }
    }
}
