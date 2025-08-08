/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi;

<<<<<<< Updated upstream
public final class MelodyAPI extends MelodyPlugin<MelodyAPI> {
=======
import com.loficostudios.melodyapi.gui.GuiManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class MelodyAPI extends JavaPlugin {

    @Getter
    private static MelodyAPI instance;

    @Getter
    private final GuiManager guiManager = new GuiManager();

    public MelodyAPI() {
        instance = this;
    }

    @Override
    public void onEnable() {
        registerEvents();
    }

    @Override
    public void onDisable() {

    }

    private void registerEvents() {
        List.of(
                new GuiManager()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }
>>>>>>> Stashed changes
}
