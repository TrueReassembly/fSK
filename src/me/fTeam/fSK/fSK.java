package me.fTeam.fSK;

import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

import java.io.IOException;
import java.util.logging.Logger;

public class fSK extends JavaPlugin {
 
    private final Logger logger = getLogger();

    fSK instance;
    SkriptAddon addon;

    public void onEnable() {
        instance = this;
        addon = Skript.registerAddon(this);

        try {
            addon.loadClasses("me.fTeam.fSK", "elements");
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("fSK has been locked in and loaded.");
    }

    public void onDisable() {
        logger.info("fSK signing off.");
    }

    public fSK getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }
}
