package com.cobblemon.mdks.cobblemon_raids;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.events.battles.BattleVictoryEvent;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import java.util.function.Consumer;

public class CobblemonEventHandler {
    public static void init(){
        CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.HIGH, toUnit(CobblemonEventHandler::onBattleWin));
    }

    private static void onBattleWin(BattleVictoryEvent event){
        if(event.getBattle().isPvW()){
            for (BattleActor winner : event.getWinners()) {
                for (BattlePokemon battlePokemon : winner.getPokemonList()) {

                }
            }
        }
    }

    // Helper method to convert a Consumer to a Function1
    private static <T> Function1<T, Unit> toUnit(Consumer<T> function) {
        return t -> {
            function.accept(t);
            return Unit.INSTANCE;
        };
    }
}
