package com.origins_eternal.ercore.utils;

import com.origins_eternal.ercore.config.Config;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldType;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Optional;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.client.texture.MetalTextureTexture;
import slimeknights.tconstruct.library.materials.Material;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.origins_eternal.ercore.ERCore.MOD_ID;
import static com.origins_eternal.ercore.event.ClientEvent.ContraData;
import static com.origins_eternal.ercore.event.ClientEvent.EnduranceData;

public class Utils {
    public static boolean checkChinese() {
        return System.getProperty("user.language").equals("zh");
    }

    public static void moveFiles() throws IOException {
        Minecraft mc = Minecraft.getMinecraft();
        String gamepath = mc.gameDir.getPath();
        String frompath = gamepath + "\\mods\\Evolution-Reset-Resource-Pack-1.1.0.zip";
        String topath = gamepath + "\\resourcepacks\\Evolution-Reset-Resource-Pack-1.1.0.zip";
        File pack = new File(frompath);
        File resourcepack = new File(topath);
        Path resource = Paths.get(topath);
        Path backup = Paths.get(frompath);
        if (pack.exists() && !resourcepack.exists()) {
            Files.copy(backup, resource);
        } else if (!pack.exists() && resourcepack.exists()) {
            Files.copy(resource, backup);
        }
    }

    public static void installResourcepacks() {
        Minecraft mc = Minecraft.getMinecraft();
        ResourcePackRepository Repository = mc.getResourcePackRepository();
        Repository.updateRepositoryEntriesAll();
        List<ResourcePackRepository.Entry> Packs = Repository.getRepositoryEntriesAll();
        List<ResourcePackRepository.Entry> Resourcepacks = new ArrayList<>();
        for (ResourcePackRepository.Entry pack : Packs) {
            if (pack.getResourcePackName().equals("Evolution-Reset-Resource-Pack-1.1.0.zip")) {
                Resourcepacks.add(pack);
            }
            Repository.setRepositories(Resourcepacks);
        }
    }

    public static void setChinese() {
        Minecraft mc = Minecraft.getMinecraft();
        GameSettings gameSettings = mc.gameSettings;
        if (checkChinese()) {
            mc.getLanguageManager().setCurrentLanguage(new Language("zh_cn", "CN", "简体中文", false));
            gameSettings.language = "zh_cn";
        }
    }

    public static void checkStringTags(EntityPlayer player, String letter) {
        Set<String> tags = player.getTags();
        String registerData = "string";
        EntityDataManager dataManager = player.getDataManager();
        if (!tags.contains(registerData)) {
            dataManager.register(ContraData, "S");
            player.addTag(registerData);
        } else {
            String data = dataManager.get(ContraData);
            dataManager.set(ContraData, data + letter);
        }
    }

    public static String checkStatus(EntityPlayer player) {
        String status = "normal";
        Set<String> tags = player.getTags();
        String registerData = "float";
        EntityDataManager dataManager = player.getDataManager();
        float maxHealth = player.getMaxHealth();
        float max = Config.endurance + maxHealth - 20 + player.experienceLevel;
        if (tags.contains(registerData)) {
            float value = dataManager.get(EnduranceData);
            float weakness = value / max;
            if (weakness < 0.01) {
                status = "exhausted";
            } else if (weakness <= 0.25) {
                status = "tired";
            } else if (weakness <=0.99) {
                status = "normal";
            } else if (weakness > 0.99) {
                status = "spirit";
            }

        }
        return status;
    }

    public static void addStringTags(EntityPlayer player, String tag, int second) {
        Set<String> tags = player.getTags();
        if (!tags.contains(tag)) {
            player.addTag(tag);
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    player.removeTag(tag);
                }
            };
            timer.schedule(timerTask, second * 1000L);
        }
    }

    public static void setFloatTags(EntityPlayer player, Float value) {
        Set<String> tags = player.getTags();
        String registerData = "float";
        EntityDataManager dataManager = player.getDataManager();
        if (!tags.contains(registerData)) {
            float origin = (float) ((Config.endurance + player.getMaxHealth() - 20 + player.experienceLevel) * 0.32);
            dataManager.register(EnduranceData, origin);
            player.addTag(registerData);
        } else if (value != 0) {
            float foodLevel = player.getFoodStats().getFoodLevel();
            float maxFoodLevel = 20;
            float healthLevel = player.getHealth();
            float maxHealth = player.getMaxHealth();
            float maxEndurance = Config.endurance + maxHealth - 20 + player.experienceLevel;
            float enduranceLevel = dataManager.get(EnduranceData);
            double x = - (foodLevel / maxFoodLevel) - (healthLevel / maxHealth);
            float k = (float) (Math.pow(2, x) - 0.25);
            if (value > 0) {
                k = 1 - k;
            } else {
                k = 1 + k;
            }
            boolean overMin = (enduranceLevel + (value * k)) < 0;
            boolean overMax = (enduranceLevel + (value * k)) > maxEndurance;
            if ((!overMin) && (!overMax)) {
                dataManager.set(EnduranceData, enduranceLevel + (value * k));
            } else if (overMin) {
                dataManager.set(EnduranceData, 0f);
            } else {
                dataManager.set(EnduranceData, maxEndurance);
            }
        }
    }

    public static IBlockState getBlockstate(String id, Block origin) {
        Block block;
        ResourceLocation location = new ResourceLocation(id);
        block = Block.REGISTRY.getObject(location);
        if (block.equals(Blocks.AIR)) {
            block = origin;
        }
        return block.getDefaultState();
    }

    @Optional.Method(modid = "rtg")
    public static void defaultWorldtype() {
        for (int i = 0; i < WorldType.WORLD_TYPES.length; i++) {
            if (WorldType.WORLD_TYPES[i] == WorldType.byName("RTG")) {
                WorldType defaultype = WorldType.WORLD_TYPES[0];
                WorldType.WORLD_TYPES[0] = WorldType.WORLD_TYPES[i];
                WorldType.WORLD_TYPES[i] = defaultype;
                break;
            }
        }
    }

    @Optional.Method(modid = "tconstruct")
    public static void setRenderInfo(Material material, Fluid fluid) {
        material.setRenderInfo(new MaterialRenderInfo.AbstractMaterialRenderInfo() {
            @Override
            public TextureAtlasSprite getTexture(ResourceLocation resourceLocation, String location) {
                return new MetalTextureTexture(new ResourceLocation(MOD_ID + ":materials/" + material.getIdentifier()), resourceLocation, location, fluid.getColor(), 2f, 3f, 0f);
            }
        });
    }
}