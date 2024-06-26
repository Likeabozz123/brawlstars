package xyz.gamars.network.server.packets;

import xyz.gamars.network.Packet;

public class PongPacket implements Packet {

    private String message;

    public PongPacket() {
        this.message = "Pong!";
    }

    /**
     * Returns message of the packet
     *
     * @return message
     * @author Daryan, Vishak, Sai
     */
    public String getMessage() {
        return message;
    }


}
