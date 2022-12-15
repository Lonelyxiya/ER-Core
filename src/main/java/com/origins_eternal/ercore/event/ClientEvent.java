package com.origins_eternal.ercore.event;

import com.origins_eternal.ercore.message.network.KeyMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.origins_eternal.ercore.ERCore.MOD_ID;
import static com.origins_eternal.ercore.ERCore.PACKET_HANDLER;
import static com.origins_eternal.ercore.utils.Utils.checkStringTags;
import static com.origins_eternal.ercore.utils.proxy.ClientProxy.*;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ClientEvent {
    public static DataParameter<Float> endurance = new DataParameter<>(99, DataSerializers.FLOAT);

    public static DataParameter<String> password = new DataParameter<>(213, DataSerializers.STRING);

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        EntityDataManager dataManager = player.getDataManager();
        if (UP.isKeyDown()) {
            checkStringTags(player, "U");
        } else if (DOWN.isKeyDown()) {
            checkStringTags(player, "D");
        } else if (LEFT.isKeyDown()) {
            checkStringTags(player, "L");
        } else if (RIGHT.isKeyDown()) {
            checkStringTags(player, "R");
            String data = dataManager.get(password);
            if (data.contains("SUUDDLLRR")) {
                PACKET_HANDLER.sendToServer(new KeyMessage());
                dataManager.set(password, "S");
            }
        } else if ((!UP.isPressed()) && (!DOWN.isPressed()) && (!LEFT.isPressed()) && (!RIGHT.isPressed())) {
            checkStringTags(player, "F");
            dataManager.set(password, "S");
        }
    }
}