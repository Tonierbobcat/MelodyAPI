package com.loficostudios.melodyapi.melodygui.events;

import com.loficostudios.melodyapi.melodygui.MelodyGui;
import org.bukkit.entity.Player;

public class GuiCloseEvent extends GuiEvent {
    public GuiCloseEvent(Player player, MelodyGui gui) {
        super(player, gui);
    }
}
