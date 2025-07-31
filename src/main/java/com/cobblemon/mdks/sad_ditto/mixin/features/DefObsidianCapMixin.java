package com.cobblemon.mdks.sad_ditto.mixin.features;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.gmail.brendonlf.cobblemon_utility.Item.DefObsidianBottleCapItem;
import com.gmail.brendonlf.cobblemon_utility.Item.SpAtkObsidianBottleCapItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DefObsidianBottleCapItem.class)
public abstract class DefObsidianCapMixin {

    @Inject(method = "interactLivingEntity", at = @At("HEAD"), cancellable = true)
    public void SADITTO$interact(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir){
        if(entity instanceof PokemonEntity pokemon){
            if(pokemon.getPokemon().getSpecies().getName().equals("Ditto")){
                if(!player.level().isClientSide())
                    player.sendSystemMessage(Component.literal("No se puede usar chapas en Ditto por el momento")
                            .withStyle(ChatFormatting.RED));
                cir.setReturnValue(InteractionResult.FAIL);
            }
        }
    }
}
