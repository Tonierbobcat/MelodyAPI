package com.loficostudios.melodyapi.gui.events;

import com.loficostudios.melodyapi.gui.MelodyGui;
import com.loficostudios.melodyapi.gui.events.base.GuiEvent;
import org.bukkit.entity.Player;

public class GuiCloseEvent extends GuiEvent {
    public GuiCloseEvent(Player player, MelodyGui gui) {
        super(player, gui);
    }
}
