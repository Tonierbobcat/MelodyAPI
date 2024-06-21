package com.loficostudios.melodyapi;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class MelodyAPI extends JavaPlugin {

    @Getter
    private static MelodyAPI instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
