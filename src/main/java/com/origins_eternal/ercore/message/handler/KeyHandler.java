package com.origins_eternal.ercore.message.handler;

import com.origins_eternal.ercore.message.network.KeyMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class KeyHandler implements IMessageHandler<KeyMessage, IMessage> {
    @Override
    public IMessage onMessage(KeyMessage message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().player;
        player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 20 * 15, 1, false, false));
        player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 20 * 15, 1, false, false));
        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 20 * 10, 3, false, false));
        return null;
    }
}