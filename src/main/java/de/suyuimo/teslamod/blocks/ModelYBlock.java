package de.suyuimo.teslamod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModelYBlock extends Block {
    public ModelYBlock() {
        super(Material.IRON); // Material.IRON als Beispiel, wählen Sie das passende Material für Ihren Block
        setUnlocalizedName("modely_block"); // Der interne Name Ihres Blocks
        setRegistryName("modely_block"); // Der Registry-Name, muss einzigartig sein
        // Weitere Eigenschaften wie Härte, Widerstandsfähigkeit usw. können hier gesetzt werden
        setHardness(2.0f);
        setResistance(10.0f);
    }
}
