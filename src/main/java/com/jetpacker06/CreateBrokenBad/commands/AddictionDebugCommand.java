package com.jetpacker06.CreateBrokenBad.commands;

import com.jetpacker06.CreateBrokenBad.register.CBBCapabilities;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;

public class AddictionDebugCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("addiction")
                .requires(c -> c.hasPermission(2))
                .then(Commands.literal("show")
                        .executes(AddictionDebugCommand::show))
                .then(Commands.literal("clear")
                        .executes(AddictionDebugCommand::clear))
                .then(Commands.literal("set")
                        .then(Commands.literal("addiction")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .executes(AddictionDebugCommand::setAddiction)))
                        .then(Commands.literal("withdrawal")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .executes(AddictionDebugCommand::setWithdrawal)))
                        .then(Commands.literal("tolerance")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .executes(AddictionDebugCommand::setTolerance)))
                        .then(Commands.literal("toxicity")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .executes(AddictionDebugCommand::setToxicity)))
                )
        );
    }

    private static int show(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();
            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                player.displayClientMessage(new TranslatableComponent("commands.createbb.addiction.show",
                        "" + ChatFormatting.YELLOW + addiction.addiction,
                        "" + ChatFormatting.YELLOW + addiction.withdrawal,
                        "" + ChatFormatting.YELLOW + addiction.tolerance,
                        "" + ChatFormatting.YELLOW + addiction.toxicity), true);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }

    private static int clear(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();

            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                addiction.reset();
                player.displayClientMessage(new TranslatableComponent("commands.createbb.addiction.clear"), true);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }

    private static int setAddiction(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();
            float value = FloatArgumentType.getFloat(context, "value");
            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                addiction.addiction = value;
                player.displayClientMessage(new TranslatableComponent("commands.createbb.addiction.set_addiction", "" + ChatFormatting.YELLOW + value), true);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }

    private static int setWithdrawal(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();
            float value = FloatArgumentType.getFloat(context, "value");
            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                addiction.withdrawal = value;
                player.displayClientMessage(new TranslatableComponent("commands.createbb.addiction.set_withdrawal", "" + ChatFormatting.YELLOW + value), true);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }

    private static int setTolerance(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();
            float value = FloatArgumentType.getFloat(context, "value");
            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                addiction.tolerance = value;
                player.displayClientMessage(new TranslatableComponent("commands.createbb.addiction.set_tolerance", "" + ChatFormatting.YELLOW + value), true);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }

    private static int setToxicity(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();
            float value = FloatArgumentType.getFloat(context, "value");
            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                addiction.toxicity = value;
                player.displayClientMessage(new TranslatableComponent("commands.createbb.addiction.set_toxicity", "" + ChatFormatting.YELLOW + value), true);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }
}
