package me.fTeam.fSK.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Date;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import java.util.Objects;

public class ExprFirstJoinDate extends SimpleExpression<Date> {
    static {
        Skript.registerExpression(ExprFirstJoinDate.class, Date.class, ExpressionType.SIMPLE,
                "[the] first (login|join) [date] of %offlineplayer%", "%offlineplayer%'[s] first (login|join) [date]"); //The actual expression format;
    }

    @Nullable
    private Expression<OfflinePlayer> player;

    @Override
    public Class<? extends Date> getReturnType() {
        return Date.class;
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
    protected Date[] get(Event event) {
        try {
            final long date = Objects.requireNonNull(player.getSingle(event)).getFirstPlayed();
            if (date > 0) {
                return new Date[]{new Date(date)};
            } else return null;
        } catch(Exception e) {
            return null;
        }
    }
}
