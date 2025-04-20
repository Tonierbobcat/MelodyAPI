package com.loficostudios.melodyapi.gui.impl;

import com.loficostudios.melodyapi.gui.MelodyGui;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class PopOutGui extends MelodyGui {
    private final Consumer<Player> onClose;

    public PopOutGui(int size, Component title, Consumer<Player> onClose) {
        super(size, title);
        this.onClose = onClose;
    }

    public void onClose(Player player) {
        if (onClose != null) {
            onClose.accept(player);
        }
    }
}
