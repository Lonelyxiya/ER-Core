package com.origins_eternal.ercore.content.tab;

import com.origins_eternal.ercore.content.item.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {
    public CreativeTab() {
        super("ercore");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.Sulphur, 1);
    }
}