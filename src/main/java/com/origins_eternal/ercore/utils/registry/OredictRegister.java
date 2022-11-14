package com.origins_eternal.ercore.utils.registry;

import com.origins_eternal.ercore.content.block.Ores;
import com.origins_eternal.ercore.content.item.Blueprints;
import com.origins_eternal.ercore.content.item.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class OredictRegister {
    public static void registerOredicts() {
        for (Block ore : Ores.ORES) {
            String blockname = ore.getTranslationKey().replace("_ore", "");
            String oredictname = blockname.substring(12,13).toUpperCase() + blockname.substring(13);
            OreDictionary.registerOre("ore" + oredictname, ore);
        }

        for (Item item : Items.ITEMS) {
            String itemname = item.getTranslationKey();
            if (itemname.contains("ingot")) {
                String ingotname = itemname.replaceAll("_", "").replace("ingot", "");
                String oredictname = ingotname.substring(12, 13).toUpperCase() + ingotname.substring(13);
                OreDictionary.registerOre("ingot" + oredictname, item);
            } else if (itemname.contains("nugget")) {
                String nuggetname = itemname.replaceAll("_", "").replace("nugget", "");
                String oredictname = nuggetname.substring(12, 13).toUpperCase() + nuggetname.substring(13);
                OreDictionary.registerOre("nugget" + oredictname, item);
            } else if (!(itemname.contains("_"))) {
                OreDictionary.registerOre(itemname, item);
            }
        }

        for (Item print : Blueprints.PRINTS) {
            String itemname = print.getTranslationKey();
            OreDictionary.registerOre("blueprint", print);
            if (itemname.contains("workshop")) {
                OreDictionary.registerOre("blueprintWorkshop", print);
            } else if (itemname.contains("workstation")) {
                OreDictionary.registerOre("blueprintWorkstation", print);
            }
        }
    }
}