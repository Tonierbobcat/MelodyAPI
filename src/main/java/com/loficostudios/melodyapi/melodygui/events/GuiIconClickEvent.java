package com.loficostudios.melodyapi.melodygui.events;

import com.loficostudios.melodyapi.melodygui.GuiIcon;
import com.loficostudios.melodyapi.melodygui.MelodyGui;
import lombok.Getter;
import org.bukkit.entity.Player;

public class GuiIconClickEvent extends GuiEvent {
    @Getter
    private final GuiIcon icon;
    public GuiIconClickEvent(Player player, MelodyGui gui, GuiIcon icon) {
        super(player, gui);
        this.icon = icon;
    }
}
