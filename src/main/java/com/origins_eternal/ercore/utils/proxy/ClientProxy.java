package com.origins_eternal.ercore.utils.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.origins_eternal.ercore.utils.registry.FluidRegister.registerFluidModels;
import static com.origins_eternal.ercore.utils.registry.FluidRegister.registerFluids;
import static com.origins_eternal.ercore.utils.registry.MaterialRegister.registerMaterials;

public class ClientProxy extends CommonProxy{
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        registerFluids();
        registerFluidModels();
        registerMaterials();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }
}