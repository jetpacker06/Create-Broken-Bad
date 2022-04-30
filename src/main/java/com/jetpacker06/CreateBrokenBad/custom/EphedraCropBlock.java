package com.jetpacker06.CreateBrokenBad.custom;

import com.jetpacker06.CreateBrokenBad.register.AllItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BeetrootBlock;

public class EphedraCropBlock extends BeetrootBlock {
    public  EphedraCropBlock(Properties p_49661_) {
        super(p_49661_);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return AllItems.EPHEDRA_SEEDS.get();
    }
}
