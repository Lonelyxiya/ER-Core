package com.origins_eternal.ercore.config;

import com.origins_eternal.ercore.ERCore;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class Config {

    public static Configuration config;
    public static int copperVeinSize = 4;
    public static int copperChance = 30;
    public static int copperMinHeight = 30;
    public static int copperMaxHeight = 80;

    public static int tinVeinSize = 5;
    public static int tinChance = 30;
    public static int tinMinHeight = 20;
    public static int tinMaxHeight = 70;

    public static int iridiumVeinSize = 1;
    public static int iridiumChance = 20;
    public static int iridiumMinHeight = 0;
    public static int iridiumMaxHeight = 30;

    public static int rutileVeinSize = 5;
    public static int rutileChance = 20;
    public static int rutileMinHeight = 0;
    public static int rutileMaxHeight = 50;

    public static int sulphurVeinSize = 10;
    public static int sulphurChance = 30;
    public static int sulphurMinHeight = 20;
    public static int sulphurMaxHeight = 120;

    public static int tungstenVeinSize = 5;
    public static int tungstenChance = 40;
    public static int tungstenMinHeight = 40;
    public static int tungstenMaxHeight = 100;

    public static void init(File file) {

        config = new Configuration(file);

        String category;

        category = "Ore Generation";
        config.addCustomCategoryComment(category, "Set ores generate veinsize, chance and min/max height by yourself!");

        copperVeinSize = config.getInt("Copper Vein Size", category, 6, 0, 10, "Set Copper Vein Size");
        copperChance = config.getInt("Copper Spawn Chance",category, 35, 0, 50, "Set Copper Spawn Chance");
        copperMinHeight = config.getInt("Copper Min Height", category, 0,0,50, "Set Copper Min Spawn Height");
        copperMaxHeight = config.getInt("Copper Max Height", category, 125, 0, 150, "Set Copper Max Spawn Height");

        tinVeinSize = config.getInt("Tin Vein Size", category, 4, 0, 10, "Set Tin Vein Size");
        tinChance = config.getInt("Tin Spawn Chance",category, 30, 0, 50, "Set Tin Spawn Chance");
        tinMinHeight = config.getInt("Tin Min Height", category, 0,0,50, "Set Tin Min Spawn Height");
        tinMaxHeight = config.getInt("Tin Max Height", category, 125, 0, 150, "Set Tin Max Spawn Height");

        iridiumVeinSize = config.getInt("Iridium Vein Size", category, 1, 0, 10, "Set Iridium Vein Size");
        iridiumChance = config.getInt("Iridium Spawn Chance",category, 20, 0, 50, "Set Iridium Spawn Chance");
        iridiumMinHeight = config.getInt("Iridium Min Height", category, 0,0,50, "Set Iridium Min Spawn Height");
        iridiumMaxHeight = config.getInt("Iridium Max Height", category, 30, 0, 150, "Set Iridium Max Spawn Height");

        rutileVeinSize = config.getInt("Rutile Vein Size", category, 5, 0, 10, "Set Rutile Vein Size");
        rutileChance = config.getInt("Rutile Spawn Chance",category, 20, 0, 50, "Set Rutile Spawn Chance");
        rutileMinHeight = config.getInt("Rutile Min Height", category, 0,0,50, "Set Rutile Min Spawn Height");
        rutileMaxHeight = config.getInt("Rutile Max Height", category, 50, 0, 150, "Set Rutile Max Spawn Height");

        sulphurVeinSize = config.getInt("Sulphur Vein Size", category, 10, 0, 10, "Set Sulphur Vein Size");
        sulphurChance = config.getInt("Sulphur Spawn Chance",category, 30, 0, 50, "Set Sulphur Spawn Chance");
        sulphurMinHeight = config.getInt("Sulphur Min Height", category, 20,0,50, "Set Sulphur Min Spawn Height");
        sulphurMaxHeight = config.getInt("Sulphur Max Height", category, 120, 0, 150, "Set Sulphur Max Spawn Height");

        tungstenVeinSize = config.getInt("Tungsten Vein Size", category, 5, 0, 10, "Set Tungsten Vein Size");
        tungstenChance = config.getInt("Tungsten Spawn Chance",category, 40, 0, 50, "Set Tungsten Spawn Chance");
        tungstenMinHeight = config.getInt("Tungsten Min Height", category, 40,0,50, "Set Tungsten Min Spawn Height");
        tungstenMaxHeight = config.getInt("Tungsten Max Height", category, 100, 0, 150, "Set Tungsten Max Spawn Height");

        config.save();
    }

    public static void registerConfig(FMLPreInitializationEvent event) {
        ERCore.config = new File(event.getModConfigurationDirectory() + "/ERCore");
        init(new File(ERCore.config.getPath(), "ERCore.cfg"));
    }
}