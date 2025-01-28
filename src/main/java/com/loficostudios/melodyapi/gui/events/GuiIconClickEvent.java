package com.loficostudios.melodyapi.gui.events;


import com.loficostudios.melodyapi.gui.events.base.GuiEvent;
import com.loficostudios.melodyapi.gui.guiicon.GuiIcon;
import com.loficostudios.melodyapi.gui.interfaces.IGui;
import org.bukkit.entity.Player;


public class GuiIconClickEvent extends GuiEvent {
    private final GuiIcon icon;
    public GuiIconClickEvent(Player player, IGui gui, GuiIcon icon) {
        super(player, gui);
        this.icon = icon;
    }

    public GuiIcon getIcon() {
        return icon;
    }
}
