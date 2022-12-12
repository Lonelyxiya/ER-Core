package com.origins_eternal.ercore.utils.proxy;

import com.origins_eternal.ercore.config.Config;
import com.origins_eternal.ercore.gen.GenOres;
import com.origins_eternal.ercore.message.handler.KeyHandler;
import com.origins_eternal.ercore.message.network.KeyMessage;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

import static com.origins_eternal.ercore.ERCore.PACKET_HANDLER;
import static com.origins_eternal.ercore.utils.registry.FluidRegister.registerFluids;
import static com.origins_eternal.ercore.utils.registry.MaterialRegister.preTinker;
import static com.origins_eternal.ercore.utils.registry.OredictRegister.registerOredicts;
import static com.origins_eternal.ercore.utils.registry.RecipeRegister.registerRecipes;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        registerFluids();
        if (Loader.isModLoaded("tconstruct")) {
            preTinker();
        }
        Config.registerConfig(event);
        GameRegistry.registerWorldGenerator(new GenOres(), 0);
        PACKET_HANDLER.registerMessage(KeyHandler.class, KeyMessage.class, 0, Side.SERVER);
    }

    public void construct(FMLConstructionEvent event) throws IOException {

    }

    public void init(FMLInitializationEvent event) {
        registerOredicts();
        registerRecipes();
    }
}