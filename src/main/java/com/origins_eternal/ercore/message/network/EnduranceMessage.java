package com.origins_eternal.ercore.message.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EnduranceMessage implements IMessage {
    public float value;

    public EnduranceMessage() {

    }

    public EnduranceMessage(float value) {
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        value = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(value);
    }
}