package com.loficostudios.melodyapi.melodygui.events;

import com.loficostudios.melodyapi.melodygui.MelodyGui;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class GuiEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    @Getter
    private final Player player;
    @Getter
    private final MelodyGui gui;

    private boolean cancelled;

    public GuiEvent(Player player, MelodyGui gui) {
        this.player = player;
        this.gui = gui;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
