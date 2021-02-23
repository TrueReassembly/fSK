package me.fTeam.fSK.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import com.sun.istack.internal.Nullable;
import org.bukkit.OfflinePlayer;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

public class CondIsOP extends Condition {

    static {
        Skript.registerCondition(CondIsOP.class, "%offlineplayer% (1¦is|2¦is(n't| not)) op[ped]");
    }

    @Nullable
    private Expression<OfflinePlayer> player;

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<OfflinePlayer>) expressions[0];
        setNegated(parser.mark == 1);
        return true;
    }

    @Override
    public String toString(Event event, boolean b) {
        return null;
    }

    @Override
    public boolean check(Event event) {
        final OfflinePlayer p = player.getSingle(event); //Set the target player to "P" to use in variables
        if (p == null) return isNegated();
        return p.isOp() == isNegated();
    }
}
