package com.loficostudios.melodyapi;

import com.loficostudios.melodyapi.gui.GuiManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MelodyPlugin<T extends MelodyPlugin<T>> extends JavaPlugin {

    protected abstract void onStart();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(
                new GuiManager(this),
                this);
        onStart();
    }
}
