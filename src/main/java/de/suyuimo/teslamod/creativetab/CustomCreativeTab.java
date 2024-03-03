package de.suyuimo.teslamod.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CustomCreativeTab extends CreativeTabs
{
    public CustomCreativeTab()
    {
        super("tesla");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(Items.APPLE);
    }
}