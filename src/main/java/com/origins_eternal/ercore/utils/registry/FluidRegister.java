package com.origins_eternal.ercore.utils.registry;

import com.origins_eternal.ercore.content.fluid.Fluids;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

import static com.origins_eternal.ercore.ERCore.ERCORE;
import static com.origins_eternal.ercore.ERCore.MOD_ID;

public class FluidRegister {
	public static final List<BlockFluidClassic> FLUIDBLOCKS = new ArrayList<>();
	public static void registerFluids() {
		for (Fluid fluid : Fluids.FLUIDS) {
			FluidRegistry.registerFluid(fluid);
			FluidRegistry.addBucketForFluid(fluid);

			BlockFluidClassic fluidblock = new BlockFluidClassic(fluid, Material.LAVA);
			String fluidname = fluid.getUnlocalizedName().substring(13);
			fluidblock.setTranslationKey(MOD_ID + ".molten_" + fluidname).setRegistryName(MOD_ID, "molten_" + fluidname).setCreativeTab(ERCORE);
			FLUIDBLOCKS.add(fluidblock);
			ForgeRegistries.BLOCKS.register(fluidblock);
			ForgeRegistries.ITEMS.register(new ItemBlock(fluidblock).setRegistryName(MOD_ID, "molten" + fluidname));
		}
	}

	public static void registerFluidModels() {
		for (Block fluidblock : FLUIDBLOCKS) {
			String fluidname = fluidblock.getTranslationKey().substring(12);
			ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(fluidblock), new ItemMeshDefinition() {
				@Nonnull
				@Override
				public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
					return new ModelResourceLocation(MOD_ID + ":fluid", fluidname);
				}
			});
			ModelLoader.setCustomStateMapper(fluidblock, new StateMapperBase() {
				@Nonnull
				@Override
				protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
					return new ModelResourceLocation(MOD_ID + ":fluid", fluidname);
				}
			});
		}
	}
}