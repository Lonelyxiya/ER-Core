package com.origins_eternal.ercore.event;

import com.origins_eternal.ercore.message.network.KeyMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import static com.origins_eternal.ercore.ERCore.MOD_ID;
import static com.origins_eternal.ercore.ERCore.PACKET_HANDLER;
import static com.origins_eternal.ercore.utils.GameUtils.checkTags;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ClientEvent {
    public static KeyBinding UP, DOWN, LEFT, RIGHT;

    public static DataParameter<String> password = new DataParameter<>(213, DataSerializers.STRING);

    public static void registerKeys() {
        ClientRegistry.registerKeyBinding(UP = new KeyBinding("Up", Keyboard.KEY_UP, "key.category." + MOD_ID) );
        ClientRegistry.registerKeyBinding(DOWN = new KeyBinding("Down", Keyboard.KEY_DOWN, "key.category." + MOD_ID) );
        ClientRegistry.registerKeyBinding(LEFT = new KeyBinding("Left", Keyboard.KEY_LEFT, "key.category." + MOD_ID) );
        ClientRegistry.registerKeyBinding(RIGHT = new KeyBinding("Right", Keyboard.KEY_RIGHT, "key.category." + MOD_ID) );
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        EntityDataManager dataManager = player.getDataManager();
        if (UP.isKeyDown()) {
            checkTags(player, "U");
        } else if (DOWN.isKeyDown()) {
            checkTags(player, "D");
        } else if (LEFT.isKeyDown()) {
            checkTags(player, "L");
        } else if (RIGHT.isKeyDown()) {
            checkTags(player, "R");
            String data = dataManager.get(password);
            if (data.equals("SUUDDLLRR")) {
                PACKET_HANDLER.sendToServer(new KeyMessage());
                dataManager.set(password, "S");
            }
        } else if ((!UP.isPressed()) && (!DOWN.isPressed()) && (!LEFT.isPressed()) && (!RIGHT.isPressed())) {
            checkTags(player, "F");
            dataManager.set(password, "S");
        }
    }
}