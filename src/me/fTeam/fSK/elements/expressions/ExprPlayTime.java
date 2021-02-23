package me.fTeam.fSK.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.event.Event;

import java.util.Objects;

public class ExprPlayTime extends SimpleExpression<Timespan> {
    static {
        Skript.registerExpression(ExprPlayTime.class, Timespan.class, ExpressionType.SIMPLE,
                "[the] play[ ]time of %offlineplayer%", "%offlineplayer%'[s] play[ ]time"); //The actual expression format;
    }

    @Nullable
    private Expression<OfflinePlayer> player;

    @Override
    public Class<? extends Timespan> getReturnType() {
        return Timespan.class;
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
    protected Timespan[] get(Event event) {
        try {
            final long time = Objects.requireNonNull(player.getSingle(event)).getStatistic(Statistic.PLAY_ONE_MINUTE);
            if (time > 0) {
                return new Timespan[]{Timespan.fromTicks_i(time)};
            } else return null;
        } catch(Exception e) {
            return null;
        }
    }
}
