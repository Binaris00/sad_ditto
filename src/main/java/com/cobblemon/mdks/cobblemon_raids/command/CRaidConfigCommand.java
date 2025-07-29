package com.cobblemon.mdks.cobblemon_raids.command;

import com.cobblemon.mdks.cobblemon_raids.config.Config;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import static net.minecraft.commands.Commands.literal;

public class CRaidConfigCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("template")
                .then(literal("config")
                        .then(literal("reload")
                                .executes(context -> {
                                    Config.getInstance().hotReload();
                                    context.getSource().sendSystemMessage(Component.literal("Reloaded!").withStyle(ChatFormatting.GREEN));
                                    return 1;
                                })
                        )

                )
        );
    }
}