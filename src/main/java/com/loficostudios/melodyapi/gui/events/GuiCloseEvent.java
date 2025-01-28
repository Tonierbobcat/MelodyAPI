package com.loficostudios.melodyapi.gui.events;


import com.loficostudios.melodyapi.gui.events.base.GuiEvent;
import com.loficostudios.melodyapi.gui.interfaces.IGui;
import org.bukkit.entity.Player;

public class GuiCloseEvent extends GuiEvent {
    public GuiCloseEvent(Player player, IGui gui) {
        super(player, gui);
    }
}
