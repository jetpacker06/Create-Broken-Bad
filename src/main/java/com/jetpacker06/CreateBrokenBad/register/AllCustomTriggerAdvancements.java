package com.jetpacker06.CreateBrokenBad.register;

import com.simibubi.create.foundation.advancement.CriterionTriggerBase;
import com.simibubi.create.foundation.advancement.SimpleCreateTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.LinkedList;
import java.util.List;

public class AllCustomTriggerAdvancements {

    private static final List<CriterionTriggerBase<?>> triggers = new LinkedList<>();

    public static SimpleCreateTrigger addSimple(String id) {
        return add(new SimpleCreateTrigger(id));
    }

    private static <T extends CriterionTriggerBase<?>> T add(T instance) {
        triggers.add(instance);
        return instance;
    }

    public static void register() {
        triggers.forEach(trigger -> {
            Registry.register(BuiltInRegistries.TRIGGER_TYPES, trigger.getId(), trigger);
        });
    }
}
