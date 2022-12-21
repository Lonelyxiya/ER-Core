package com.origins_eternal.ercore.event;

import com.origins_eternal.ercore.config.Config;
import com.origins_eternal.ercore.message.network.EnduranceMessage;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Set;

import static com.origins_eternal.ercore.ERCore.MOD_ID;
import static com.origins_eternal.ercore.ERCore.PACKET_HANDLER;
import static com.origins_eternal.ercore.event.ClientEvent.endurance;
import static com.origins_eternal.ercore.utils.Utils.getBlockstate;
import static com.origins_eternal.ercore.utils.Utils.setFloatTags;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class CommonEvent {
    @SubscribeEvent
    public static void onCreateFluidSource(BlockEvent.CreateFluidSourceEvent event) {
        event.setResult(Event.Result.DENY);
    }

    @SubscribeEvent
    public static void onFluidPlaceBlock(BlockEvent.FluidPlaceBlockEvent event) {
        Block block = event.getState().getBlock();
        if (block.equals(Blocks.STONE)) {
            event.setNewState(getBlockstate("taiga:basalt", Blocks.STONE));
        } else if (block.equals(Blocks.COBBLESTONE)) {
            event.setNewState(getBlockstate("chisel:basalt", Blocks.COBBLESTONE));
        } else if (block.equals(Blocks.OBSIDIAN)) {
            event.setNewState(getBlockstate("advancedrocketry:basalt", Blocks.OBSIDIAN));
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        if (Config.enableEndurance) {
            EntityPlayer player = event.getEntityPlayer();
            setFloatTags(player, 0f);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        World world = player.world;
        if (world.isRemote) {
            if ((!player.isCreative()) && (!player.isSpectator())) {
                if (Config.enableEndurance) {
                    Set<String> tags = player.getTags();
                    if (tags.contains("float")) {
                        EntityDataManager dataManager = player.getDataManager();
                        float value = dataManager.get(endurance);
                        float max = Config.endurance + player.getMaxHealth() - 20 + player.experienceLevel;
                        float weakness = value / max;
                        if (value > max) {
                            dataManager.set(endurance, max);
                        }
                        if (weakness <= 0.25) {
                            PACKET_HANDLER.sendToServer(new EnduranceMessage());
                        }
                    } else {
                        setFloatTags(player, 0f);
                    }
                    if ((player.moveForward != 0) || (player.moveStrafing != 0)) {
                        if (!player.isRiding()) {
                            if (player.isInWater()) {
                                setFloatTags(player, -0.01f);
                            } else if (player.isSprinting()) {
                                setFloatTags(player, -0.03f);
                            } else {
                                setFloatTags(player, 0.01f);
                            }
                        }
                    } else {
                        if (player.isSneaking()) {
                            setFloatTags(player, 0.01f);
                        } else if ((player.onGround) || (player.isRiding()) || (player.isInWater())) {
                            setFloatTags(player, 0.02f);
                        }
                    }
                    if ((!player.onGround) && (!player.isPotionActive(MobEffects.LEVITATION))) {
                        if (player.isElytraFlying()) {
                            setFloatTags(player, -0.01f);
                        } else if ((!player.isOnLadder()) && (!player.isInWater()) && (!player.isRiding())) {
                            if (!player.isSprinting()) {
                                setFloatTags(player, -0.03f);
                            } else {
                                setFloatTags(player, -0.02f);
                            }
                        }
                    }
                }
            }
        }
    }
}