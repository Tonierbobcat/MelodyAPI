package com.loficostudios.melodyapi.gui.events;

<<<<<<< Updated upstream

import com.loficostudios.melodyapi.gui.events.base.GuiEvent;
import com.loficostudios.melodyapi.gui.interfaces.IGui;
import org.bukkit.entity.Player;

public class GuiCloseEvent extends GuiEvent {
    public GuiCloseEvent(Player player, IGui gui) {
=======
import com.loficostudios.melodyapi.gui.MelodyGui;
import com.loficostudios.melodyapi.gui.events.base.GuiEvent;
import org.bukkit.entity.Player;

public class GuiCloseEvent extends GuiEvent {
    public GuiCloseEvent(Player player, MelodyGui gui) {
>>>>>>> Stashed changes
        super(player, gui);
    }
}
