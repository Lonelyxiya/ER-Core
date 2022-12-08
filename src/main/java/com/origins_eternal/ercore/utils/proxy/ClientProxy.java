package com.origins_eternal.ercore.utils.proxy;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.client.texture.MetalTextureTexture;
import slimeknights.tconstruct.library.materials.Material;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.origins_eternal.ercore.ERCore.MOD_ID;
import static com.origins_eternal.ercore.event.ClientEvent.registerKeys;
import static com.origins_eternal.ercore.utils.GameUtils.*;
import static com.origins_eternal.ercore.utils.registry.MaterialRegister.registerMaterials;

public class ClientProxy extends CommonProxy{
    public static List<String> Chars = new ArrayList<>();
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        if (Loader.isModLoaded("tconstruct")) {
            registerMaterials();
        }
    }

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
    @Optional.Method(modid = "tconstruct")
    public static void setRenderInfo(Material material, Fluid fluid) {
        material.setRenderInfo(new MaterialRenderInfo.AbstractMaterialRenderInfo() {
            @Override
            public TextureAtlasSprite getTexture(ResourceLocation resourceLocation, String location) {
                return new MetalTextureTexture(new ResourceLocation(MOD_ID + ":materials/" + material.getIdentifier()), resourceLocation, location, fluid.getColor(), 2f, 3f, 0.5f);
            }
        });
    }
}