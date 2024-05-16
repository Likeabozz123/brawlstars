package xyz.gamars.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import xyz.gamars.network.client.packets.PingPacket;
import xyz.gamars.network.server.packets.PongPacket;

public class Network {

    public static final int PORT = 54555;

    /**
     * Registers all classes, so they're serialized to send as a packet
     *
     * @param endPoint The endpoint to register classes with.
     * @author Daryan, Vishak, Sai
     */
    public static void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();

        /*
         serialize packets here
         example : kryo.register(PacketClass.class);
        */
        kryo.register(Packet.class);
        kryo.register(PingPacket.class);
        kryo.register(PongPacket.class);


    }


}
