package com.origins_eternal.ercore.utils.proxy;

import com.origins_eternal.ercore.content.gui.Overlay;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

import static com.origins_eternal.ercore.ERCore.MOD_ID;
import static com.origins_eternal.ercore.utils.Utils.*;
import static com.origins_eternal.ercore.utils.registry.MaterialRegister.preTinker;

public class ClientProxy extends CommonProxy{
    public static KeyBinding UP, DOWN, LEFT, RIGHT;

    public static void registerKeys() {
        ClientRegistry.registerKeyBinding(UP = new KeyBinding("Up", Keyboard.KEY_UP, "key.category." + MOD_ID) );
        ClientRegistry.registerKeyBinding(DOWN = new KeyBinding("Down", Keyboard.KEY_DOWN, "key.category." + MOD_ID) );
        ClientRegistry.registerKeyBinding(LEFT = new KeyBinding("Left", Keyboard.KEY_LEFT, "key.category." + MOD_ID) );
        ClientRegistry.registerKeyBinding(RIGHT = new KeyBinding("Right", Keyboard.KEY_RIGHT, "key.category." + MOD_ID) );
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) { super.preInit(event); }

    @Override
    public void construct(FMLConstructionEvent event) throws IOException {
        setChinese();
        moveFiles();
        installResourcepacks();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        registerKeys();
        if (Loader.isModLoaded("rtg")) {
            defaultWorldtype();
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new Overlay());
    }
}