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

public class ExprKDR extends SimpleExpression<Float> {
    static {
        Skript.registerExpression(ExprKDR.class, Float.class, ExpressionType.SIMPLE,
                "[the] kdr of %offlineplayer%", "%offlineplayer%'[s] kdr"); //The actual expression format;
    }

    @Nullable
    private Expression<OfflinePlayer> player;

    @Override
    public Class<? extends Float> getReturnType() {
        return Float.class;
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
    protected Float[] get(Event event) {
        try {
            final float kills = Objects.requireNonNull(player.getSingle(event)).getStatistic(Statistic.PLAYER_KILLS);
            final float deaths = Objects.requireNonNull(player.getSingle(event)).getStatistic(Statistic.DEATHS);
            float kdr;
            if (deaths == 0) kdr = kills;
            else if (kills == 0) kdr = -deaths;
            else kdr = kills / deaths;

            return new Float[]{kdr};
        } catch(Exception e) {
            return null;
        }
    }
}
