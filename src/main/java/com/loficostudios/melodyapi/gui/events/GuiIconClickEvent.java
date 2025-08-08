package com.loficostudios.melodyapi.gui.events;

<<<<<<< Updated upstream

import com.loficostudios.melodyapi.gui.events.base.GuiEvent;
import com.loficostudios.melodyapi.gui.icon.GuiIcon;
import com.loficostudios.melodyapi.gui.interfaces.IGui;
=======
import com.loficostudios.melodyapi.gui.GuiIcon;
import com.loficostudios.melodyapi.gui.MelodyGui;
import com.loficostudios.melodyapi.gui.events.base.GuiEvent;
>>>>>>> Stashed changes
import org.bukkit.entity.Player;


public class GuiIconClickEvent extends GuiEvent {
    private final GuiIcon icon;
<<<<<<< Updated upstream
    public GuiIconClickEvent(Player player, IGui gui, GuiIcon icon) {
=======
    public GuiIconClickEvent(Player player, MelodyGui gui, GuiIcon icon) {
>>>>>>> Stashed changes
        super(player, gui);
        this.icon = icon;
    }

    public GuiIcon getIcon() {
        return icon;
    }
}
