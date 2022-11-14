package com.origins_eternal.ercore.content.material;

import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.materials.Material;

import static slimeknights.tconstruct.tools.TinkerTraits.*;

public class Materials {
    public static Material obsidian_mana = new Material("obsidian_magic", TextFormatting.DARK_PURPLE).addTrait(duritos, "bowstring").addTrait(duritos,"head").addTrait(duritos, "handle").addTrait(duritos, "extra");
    public static Material tungsten_steel = new Material("tungsten_steel", TextFormatting.DARK_GRAY).addTrait(heavy).addTrait(sharp, "head").addTrait(sharp, "bowstring").addTrait(dense, "handle").addTrait(stiff, "extra").addTrait(dense, "extra");
}