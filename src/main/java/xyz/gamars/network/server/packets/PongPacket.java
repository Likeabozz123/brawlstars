package xyz.gamars.network.server.packets;

import xyz.gamars.network.Packet;

public class PongPacket implements Packet {

    private String message;

    public PongPacket() {
        this.message = "Pong!";
    }

    public String getMessage() {
        return message;
    }


}
