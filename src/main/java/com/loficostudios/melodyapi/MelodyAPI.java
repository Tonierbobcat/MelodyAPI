/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi;

import com.loficostudios.melodyapi.listeners.GuiListener;
import com.loficostudios.melodyapi.managers.GuiManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class MelodyAPI extends JavaPlugin {

    @Getter
    private static MelodyAPI instance;

    @Getter
    private final GuiManager guiManager = new GuiManager();

    @Override
    public void onEnable() {
        instance = this;

        registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEvents() {
        Arrays.asList(
                new GuiListener()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }
}
