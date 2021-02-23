package me.fTeam.fSK.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.util.Timespan;
import com.sun.istack.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import java.lang.management.ManagementFactory;

public class ExprUptime extends SimpleExpression<Timespan> {
    static {
        Skript.registerExpression(ExprUptime.class, Timespan.class, ExpressionType.SIMPLE,
                "[the] [server] uptime"); //The actual expression format
    }

    @Override
    public Class<? extends Timespan> getReturnType() {
        return Timespan.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        return true;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Uptime expression";
    }

    @Override
    @Nullable
    protected Timespan[] get(Event event) {
        return new Timespan[] { new Timespan(ManagementFactory.getRuntimeMXBean().getUptime())};
    }
}
