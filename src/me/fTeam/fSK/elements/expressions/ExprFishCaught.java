package me.fTeam.fSK.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.event.Event;

import java.util.Objects;

public class ExprFishCaught extends SimpleExpression<Integer> {
    static {
        Skript.registerExpression(ExprFishCaught.class, Integer.class, ExpressionType.SIMPLE,
                "[amount of] fish caught by %offlineplayer%", "%offlineplayer%'[s] [amount of] fish caught"); //The actual expression format;
    }

    @Nullable
    private Expression<OfflinePlayer> player;

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }


    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.player = (Expression<OfflinePlayer>) expressions[0];
        return true;
    }

    @Override
    public String toString(Event event, boolean b) {
        return null;
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        try {
            return new Integer[]{ Objects.requireNonNull(player.getSingle(event)).getStatistic(Statistic.FISH_CAUGHT) };
        } catch(Exception e) {
            return null;
        }
    }
}
