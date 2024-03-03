package de.suyuimo.teslamod.init;

import java.util.ArrayList;
import java.util.List;

import de.suyuimo.teslamod.TeslaMod;
import de.suyuimo.teslamod.blocks.Block3DModelBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    //Custom Models
    public static final Block MODEL_Y = new Block3DModelBase("model_Y", Material.CLOTH, TeslaMod.TESLA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);


}