package com.loficostudios.melodyapi;

import com.loficostudios.melodyapi.gui.GuiManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Deprecated
public abstract class MelodyPlugin<T extends MelodyPlugin<T>> extends JavaPlugin {

    protected abstract void onStart();

    private GuiManager guiManager;

    @Override
    public void onEnable() {
        this.guiManager = new GuiManager();
        Bukkit.getPluginManager().registerEvents(
                guiManager,
                this);
        onStart();
    }

    public GuiManager getGuiManager() {
        return this.guiManager;
    }
}
