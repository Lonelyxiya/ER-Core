package com.origins_eternal.ercore.message.handler;

import com.origins_eternal.ercore.message.network.EnduranceMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EnduranceHandler implements IMessageHandler<EnduranceMessage, IMessage> {
    @Override
    public IMessage onMessage(EnduranceMessage message, MessageContext context) {
        EntityPlayer player = context.getServerHandler().player;
        player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1, 2, false, false));
        player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 1, 3, false, false));
        player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 1, 1, false, false));
        return null;
    }
}