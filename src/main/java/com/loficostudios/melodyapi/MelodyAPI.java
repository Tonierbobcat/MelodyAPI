/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi;

import com.loficostudios.melodyapi.melodygui.GuiManager;
import lombok.Getter;

import java.util.List;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class MelodyAPI extends MelodyPlugin<MelodyAPI> {

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
}
