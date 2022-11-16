package com.origins_eternal.ercore.utils.registry;

import com.origins_eternal.ercore.content.fluid.Fluids;
import com.origins_eternal.ercore.content.material.Materials;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;

import static com.origins_eternal.ercore.content.item.Items.Obsidian_Magic_Shard;
import static com.origins_eternal.ercore.content.item.Items.Tungsten_Steel_Ingot;
import static com.origins_eternal.ercore.utils.proxy.ClientProxy.setRenderInfo;

public class MaterialRegister {
    public static void addMaterials(int volume, Item item, Material material, Fluid fluid, int headDura, float headSpeed, float headAttack, float handleMod, int handleDura, int extra, int headLevel, boolean craft, boolean cast, float drawSpeed, float range, float bonusDamage) {

        TinkerRegistry.addMaterial(material);
        TinkerRegistry.addMaterialStats(material, new HeadMaterialStats(headDura, headSpeed, headAttack, headLevel));
        TinkerRegistry.addMaterialStats(material, new HandleMaterialStats(handleMod, handleDura));
        TinkerRegistry.addMaterialStats(material, new ExtraMaterialStats(extra));
        TinkerRegistry.addMaterialStats(material, new BowMaterialStats(drawSpeed, range, bonusDamage));

        material.setFluid(fluid).setCraftable(craft).setCastable(cast).addItem(item, 1, volume);;
        material.setRepresentativeItem(item);
        setRenderInfo(material, fluid);
    }

    public static void registerMaterials() {
        addMaterials(144, Tungsten_Steel_Ingot, Materials.tungsten_steel, Fluids.Tungsten_Steel, 2000, 8f, 10f, 3f, 100, 100, 9, false, true, 3f, 4f, 7f);
        addMaterials(72, Obsidian_Magic_Shard, Materials.obsidian_mana, Fluids.Obsidian_Magic, 200, 7.07f, 4.2f, 0.9f, -100, 90, 5, false, true, 5f, 0.4f, -1f);
    }
}
