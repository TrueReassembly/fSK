package me.fTeam.fSK.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import com.sun.istack.internal.Nullable;
import org.bukkit.OfflinePlayer;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.entity.Entity;

public class CondIsDead extends Condition {

    static {
        Skript.registerCondition(CondIsDead.class, "%entity% (1¦is|2¦is(n't| not)) dead");
    }

    @Nullable
    private Expression<Entity> entity;

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.entity = (Expression<Entity>) expressions[0];
        setNegated(parser.mark == 1);
        return true;
    }

    @Override
    public String toString(Event event, boolean b) {
        return null;
    }

    @Override
    public boolean check(Event event) {
        final Entity e = entity.getSingle(event); //Set the target player to "P" to use in variables
        if (e == null) return false;
        return e.isDead() == isNegated();
    }
}
