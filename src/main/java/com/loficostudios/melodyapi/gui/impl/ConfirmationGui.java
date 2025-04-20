package com.loficostudios.melodyapi.gui.impl;

import com.loficostudios.melodyapi.MelodyPlugin;
import com.loficostudios.melodyapi.gui.icon.GuiIcon;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class ConfirmationGui extends PopOutGui {

    private final Consumer<Player> onYes;
    private final Consumer<Player> onNo;
    private final MelodyPlugin.Scheduler scheduler;

    public ConfirmationGui(MelodyPlugin.Scheduler scheduler, String message, Consumer<Player> onClose, Consumer<Player> onYes, Consumer<Player> onNo) {
        super(9, Component.text(message), onClose);
        this.scheduler = scheduler;
        this.onNo = onNo;
        this.onYes = onYes;

        setSlot(2, getYesButton());
        setSlot(6, getNoButton());
    }

    public ConfirmationGui(MelodyPlugin.Scheduler scheduler, String message, Consumer<Player> onClose, Consumer<Player> onYes) {
        this(scheduler, message, onClose, onYes, null);
    }

    private GuiIcon getYesButton() {
        return new GuiIcon(Material.LIME_STAINED_GLASS, Component.text("<green><bold>Confirm"), (p, c) -> {
            close(p);
            scheduler.runTaskLater(() -> {
                if (onYes != null) {
                    onYes.accept(p);
                }
            }, 1);
        });
    }

    private GuiIcon getNoButton() {
        return new GuiIcon(Material.RED_STAINED_GLASS, Component.text("<red><bold>Cancel"), (p,c) -> {
            close(p);
            scheduler.runTaskLater(() -> {
                if (onNo != null) {
                    onNo.accept(p);
                }
            }, 1);
        });
    }
}
