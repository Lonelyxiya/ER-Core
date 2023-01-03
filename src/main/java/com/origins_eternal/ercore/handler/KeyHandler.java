package com.origins_eternal.ercore.handler;

import com.origins_eternal.ercore.message.KeyMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Timer;
import java.util.TimerTask;

public class KeyHandler implements IMessageHandler<KeyMessage, IMessage> {
    @Override
    public IMessage onMessage(KeyMessage message, MessageContext context) {
        EntityPlayer player = context.getServerHandler().player;
        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;
        player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20 * 10, 2, false, false));
        player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 20 * 15, 1, false, false));
        player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 20 * 15, 1, false, false));
        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 20 * 10, 3, false, false));
        Timer timer = new Timer();
        TimerTask key = new TimerTask() {
            @Override
            public void run() {
                player.world.createExplosion(player, x, y, z, 3.2f, true);
                player.attemptTeleport(x, y, z);
                player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 20 * 5, 1, false, false));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20 * 12, 3, false, false));
                player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20 * 12, 2, false, false));
            }
        };
        timer.schedule(key, 15000);
        return null;
    }
}