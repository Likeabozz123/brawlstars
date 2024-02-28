package xyz.gamars.network.server.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import xyz.gamars.network.client.packets.PingPacket;
import xyz.gamars.network.server.packets.PongPacket;

public class PingListener extends Listener {

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
