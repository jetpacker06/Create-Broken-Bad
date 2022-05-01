package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.custom.BrassCallBellAdvancementTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class AllCustomTriggerAdvancements {
    public static BrassCallBellAdvancementTrigger DING = new BrassCallBellAdvancementTrigger();
    public static void register() {
        CriteriaTriggers.register(DING);
    }
}