package com.origins_eternal.ercore.utils.proxy;

import com.origins_eternal.ercore.config.Config;
import com.origins_eternal.ercore.gen.GenOres;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.origins_eternal.ercore.utils.registry.OredictRegister.registerOredicts;
import static com.origins_eternal.ercore.utils.registry.MaterialRegister.registerMaterials;
import static com.origins_eternal.ercore.utils.registry.FluidRegister.*;
import static com.origins_eternal.ercore.utils.registry.RecipeRegister.registerRecipes;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        Config.registerConfig(event);
        GameRegistry.registerWorldGenerator(new GenOres(), 0);
        registerFluids();
        registerFluidModels();
        registerMaterials();
    }
    public void init(FMLInitializationEvent event) {
        registerOredicts();
        registerRecipes();
    }
}