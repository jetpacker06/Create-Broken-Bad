package com.jetpacker06.CreateBrokenBad.register;


import com.simibubi.create.AllFluids;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.util.entry.FluidEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidStack;

public class CBBFluids {
    public static Registrate REGISTRATE;

    public static ResourceLocation still = ResourceLocation.withDefaultNamespace("block/water_still");
    public static ResourceLocation flow = ResourceLocation.withDefaultNamespace("block/water_flow");

    public static FluidEntry<BaseFlowingFluid.Flowing> LIQUID_BLUE_METHAMPHETAMINE;
    public static FluidEntry<BaseFlowingFluid.Flowing> LIQUID_METHAMPHETAMINE;
    public static FluidEntry<BaseFlowingFluid.Flowing> METHYLAMINE;
    public static FluidEntry<BaseFlowingFluid.Flowing> METHANOL;
    public static FluidEntry<BaseFlowingFluid.Flowing> HYDROGEN;
    public static FluidEntry<BaseFlowingFluid.Flowing> OXYGEN;
    public static FluidEntry<BaseFlowingFluid.Flowing> AMMONIA;
    public static FluidEntry<BaseFlowingFluid.Flowing> PHENYLACETIC_ACID;
    public static FluidEntry<BaseFlowingFluid.Flowing> ACETIC_ANHYDRIDE;
    public static FluidEntry<BaseFlowingFluid.Flowing> PHENYLACETONE;

    public static ItemEntry<BucketItem> LIQUID_BLUE_METHAMPHETAMINE_BUCKET;
    public static ItemEntry<BucketItem> LIQUID_METHAMPHETAMINE_BUCKET;
    public static ItemEntry<BucketItem> METHYLAMINE_BUCKET;
    public static ItemEntry<BucketItem> METHANOL_BUCKET;
    public static ItemEntry<BucketItem> HYDROGEN_BUCKET;
    public static ItemEntry<BucketItem> OXYGEN_BUCKET;
    public static ItemEntry<BucketItem> AMMONIA_BUCKET;
    public static ItemEntry<BucketItem> PHENYLACETIC_ACID_BUCKET;
    public static ItemEntry<BucketItem> ACETIC_ANHYDRIDE_BUCKET;
    public static ItemEntry<BucketItem> PHENYLACETONE_BUCKET;

    /**
     * Creates a white fluid.
     */
    public static FluidBuilder<BaseFlowingFluid.Flowing, Registrate> basicFluid(String name) {
        return basicFluid(name, 0xffffffff);
    }

    /**
     * Creates a fluid with a given color. Use the format 0xAA(hex)
     */
    public static FluidBuilder<BaseFlowingFluid.Flowing, Registrate> basicFluid(String name, int color) {
        return REGISTRATE.fluid(name, still, flow, (p, r1, r2) -> new NoColorFluidAttributes(p, color))
                .properties(p -> p.viscosity(500).density(500))//.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA))
                .fluidProperties(p -> p.tickRate(5).slopeFindDistance(6).explosionResistance(100f))
                .source(BaseFlowingFluid.Source::new);
    }
    public static ItemEntry<BucketItem> getBucket(FluidBuilder<BaseFlowingFluid.Flowing, Registrate> fluid) {
        return fluid.bucket().properties(p -> p.stacksTo(1)).register();
    }
    public static void register(Registrate registrate) {
        REGISTRATE = registrate;

        var f1 = basicFluid("liquid_blue_methamphetamine", 0xff42ddf5);
        LIQUID_BLUE_METHAMPHETAMINE_BUCKET = getBucket(f1);
        LIQUID_BLUE_METHAMPHETAMINE = f1.register();

        var f2 = basicFluid("liquid_methamphetamine");
        LIQUID_METHAMPHETAMINE_BUCKET = getBucket(f2);
        LIQUID_METHAMPHETAMINE = f2.register();

        var f3 = basicFluid("methylamine");
        METHYLAMINE_BUCKET = getBucket(f3);
        METHYLAMINE = f3.register();

        var f4 = basicFluid("methanol");
        METHANOL_BUCKET = getBucket(f4);
        METHANOL = f4.register();

        var f5 = basicFluid("hydrogen");
        HYDROGEN_BUCKET = getBucket(f5);
        HYDROGEN = f5.register();

        var f6 = basicFluid("oxygen");
        OXYGEN_BUCKET = getBucket(f6);
        OXYGEN = f6.register();

        var f7 = basicFluid("ammonia");
        AMMONIA_BUCKET = getBucket(f7);
        AMMONIA = f7.register();

        var f8 = basicFluid("phenylacetic_acid");
        PHENYLACETIC_ACID_BUCKET = getBucket(f8);
        PHENYLACETIC_ACID = f8.register();

        var f9 = basicFluid("acetic_anhydride");
        ACETIC_ANHYDRIDE_BUCKET = getBucket(f9);
        ACETIC_ANHYDRIDE = f9.register();

        var f10 = basicFluid("phenylacetone");
        PHENYLACETONE_BUCKET = getBucket(f10);
        PHENYLACETONE = f10.register();

    }

    /**
     * (Edited from Create)
     * Removing alpha from tint prevents optifine from forcibly applying biome
     * colors to modded fluids (Makes translucent fluids disappear)
     */
    public static class NoColorFluidAttributes extends AllFluids.TintedFluidType {
        private final int color;
        public NoColorFluidAttributes(Properties properties, int color) {
            super(properties, still, flow);
            this.color = color;
        }

        @Override
        protected int getTintColor(FluidStack stack) {
            return color;
        }

        @Override
        public int getTintColor(FluidState state, BlockAndTintGetter world, BlockPos pos) {
            return color;
        }

    }
}
