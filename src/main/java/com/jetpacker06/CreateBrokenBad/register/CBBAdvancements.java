package com.jetpacker06.CreateBrokenBad.register;

import com.simibubi.create.foundation.advancement.AllTriggers;
import com.simibubi.create.foundation.advancement.SimpleCreateTrigger;

public class CBBAdvancements {

    public static SimpleCreateTrigger DING;

    public static void register() {
        DING = AllTriggers.addSimple("use_brass_call_bell");
    }
}
