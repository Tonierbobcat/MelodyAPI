package com.loficostudios.melodyapi.gui.events;

import com.loficostudios.melodyapi.gui.GuiIcon;
import com.loficostudios.melodyapi.gui.MelodyGui;
import com.loficostudios.melodyapi.gui.events.base.GuiEvent;
import org.bukkit.entity.Player;


public class GuiIconClickEvent extends GuiEvent {
    private final GuiIcon icon;
    public GuiIconClickEvent(Player player, MelodyGui gui, GuiIcon icon) {
        super(player, gui);
        this.icon = icon;
    }

    public GuiIcon getIcon() {
        return icon;
    }
}
