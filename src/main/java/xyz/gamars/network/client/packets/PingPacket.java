package xyz.gamars.network.client.packets;

import xyz.gamars.network.Packet;

public class PingPacket implements Packet {

    private String message;

    public PingPacket() {
        this.message = "Ping!";
    }

    /**
     * Returns what the message of the packet is
     *
     * @return message
     * @author Daryan, Vishak, Sai
     */
    public String getMessage() {
        return message;
    }

}
