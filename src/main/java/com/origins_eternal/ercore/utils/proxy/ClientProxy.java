package com.origins_eternal.ercore.utils.proxy;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.MaterialIntegration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.origins_eternal.ercore.event.ClientEvent.registerKeys;
import static com.origins_eternal.ercore.utils.GameUtils.*;

public class ClientProxy extends CommonProxy{

    public static final List<MaterialIntegration> MATERIAL_INTEGRATIONS = new ArrayList<>();

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
}