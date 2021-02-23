package me.fTeam.fSK.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import org.bukkit.event.Event;

import java.util.Base64;
import java.util.Objects;

public class ExprEncodeBase64 extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprEncodeBase64.class, String.class, ExpressionType.SIMPLE,
                "%string% [(encoded|encrypted|converted)] (to|as) base[ ]64"); //The actual expression format;
    }


    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    private Expression<String> text;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        text = (Expression<String>) expressions[0];
        return true;
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        try {
            return new String[]{Base64.getEncoder().encodeToString(Objects.requireNonNull(text.getSingle(event)).getBytes())};
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return null;
    }
}
