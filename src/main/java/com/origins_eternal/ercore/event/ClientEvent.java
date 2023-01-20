package com.origins_eternal.ercore.event;

import com.origins_eternal.ercore.ERCore;
import com.origins_eternal.ercore.message.KeyMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiSleepMP;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

import static com.origins_eternal.ercore.ERCore.MOD_ID;
import static com.origins_eternal.ercore.ERCore.packetHandler;
import static com.origins_eternal.ercore.utils.Utils.checkStringTags;
import static com.origins_eternal.ercore.utils.proxy.ClientProxy.*;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ClientEvent {
    public static DataParameter<Float> EnduranceData = new DataParameter<>(99, DataSerializers.FLOAT);

    public static DataParameter<String> ContraData = new DataParameter<>(213, DataSerializers.STRING);

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
            String data = dataManager.get(ContraData);
            if (data.contains("SUUDDLRLR")) {
                packetHandler.sendToServer(new KeyMessage());
                dataManager.set(ContraData, "S");
            }
        } else if ((!UP.isPressed()) && (!DOWN.isPressed()) && (!LEFT.isPressed()) && (!RIGHT.isPressed())) {
            checkStringTags(player, "F");
            dataManager.set(ContraData, "S");
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void GuiOpen(GuiOpenEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.inGameHasFocus) {
            EntityPlayer player = mc.player;
            Set<String> tags = player.getTags();
            if ((event.getGui() instanceof GuiSleepMP) || (event.getGui() instanceof GuiChat) || (event.getGui() instanceof GuiIngameMenu)) {
                if (tags.contains("rest")) {
                    event.setCanceled(true);
                }
            }
        }
    }
}