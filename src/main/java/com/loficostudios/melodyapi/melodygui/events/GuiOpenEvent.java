package com.loficostudios.melodyapi.melodygui.events;

import com.loficostudios.melodyapi.melodygui.MelodyGui;
import org.bukkit.entity.Player;

public class GuiOpenEvent extends GuiEvent {
    public GuiOpenEvent(Player player, MelodyGui gui) {
        super(player, gui);
    }
}
