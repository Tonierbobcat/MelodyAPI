package com.loficostudios.melodyapi.gui.events;

<<<<<<< Updated upstream
import com.loficostudios.melodyapi.gui.events.base.GuiEvent;
import com.loficostudios.melodyapi.gui.interfaces.IGui;
import org.bukkit.entity.Player;

public class GuiOpenEvent extends GuiEvent {
    public GuiOpenEvent(Player player, IGui gui) {
=======
import com.loficostudios.melodyapi.gui.MelodyGui;
import com.loficostudios.melodyapi.gui.events.base.GuiEvent;
import org.bukkit.entity.Player;

public class GuiOpenEvent extends GuiEvent {
    public GuiOpenEvent(Player player, MelodyGui gui) {
>>>>>>> Stashed changes
        super(player, gui);
    }
}
