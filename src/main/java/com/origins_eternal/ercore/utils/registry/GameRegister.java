package com.origins_eternal.ercore.utils.registry;

import com.origins_eternal.ercore.content.block.Blocks;
import com.origins_eternal.ercore.content.block.Ores;
import com.origins_eternal.ercore.content.item.Blueprints;
import com.origins_eternal.ercore.content.item.Items;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

import static com.origins_eternal.ercore.ERCore.MOD_ID;

@Mod.EventBusSubscriber
public class GameRegister {
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : Blocks.BLOCKS) {
            event.getRegistry().register(block);
        }
        for (Block ore : Ores.ORES) {
            event.getRegistry().register(ore);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : Items.ITEMS) {
            event.getRegistry().register(item);
        }
        for (Item print : Blueprints.PRINTS) {
            event.getRegistry().register(print);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        for (Item item : Items.ITEMS) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
        }
        for (Item print : Blueprints.PRINTS) {
            ModelLoader.setCustomModelResourceLocation(print, 0, new ModelResourceLocation(MOD_ID + ":" + "blueprint", "inventory"));
        }
        for (Block block : Blocks.BLOCKS) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "inventory"));
        }
        for (Block ore : Ores.ORES) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ore), 0, new ModelResourceLocation(Objects.requireNonNull(ore.getRegistryName()), "inventory"));
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModels();
    }
}
