package com.origins_eternal.ercore.content.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Set;

import static com.origins_eternal.ercore.ERCore.MOD_ID;
import static com.origins_eternal.ercore.event.ClientEvent.endurance;

public class Overlay extends Gui {
    private final ResourceLocation gui = new ResourceLocation(MOD_ID, "textures/gui/endurance.png");

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
        if ((!player.isCreative()) && (!player.isSpectator())) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
                Set<String> tags = player.getTags();
                if (tags.contains("float")) {
                    mc.renderEngine.bindTexture(gui);
                    EntityDataManager dataManager = player.getDataManager();
                    float bar = 80;
                    float max = 20;
                    float percent = bar / max;
                    float value = dataManager.get(endurance);
                    int current = (int) (percent * value);
                    int posX = event.getResolution().getScaledWidth() / 2 + 10;
                    int posY = event.getResolution().getScaledHeight() - 59;
                    drawTexturedModalRect(posX, posY, 0, 0, 80, 9);
                    drawTexturedModalRect(posX, posY, 0, 9, current, 9);
                }
            }
        }
    }
}