package com.loficostudios.melodyapi.gui.impl;

import com.loficostudios.melodyapi.gui.MelodyGui;
import com.loficostudios.melodyapi.gui.icon.GuiIcon;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public abstract class MutableGui extends MelodyGui {

    public MutableGui(int size, Component title) {
        super(size, title);
    }

    public MutableGui(int size) {
        super(size);
    }

    public abstract @NotNull Set<Integer> getMutableSlots();

    @Override
    public boolean setSlot(int slot, @NotNull GuiIcon icon) {
        if (getMutableSlots().contains(slot)) {
            throw new IllegalArgumentException("Slot is mutable!");
        }
        return super.setSlot(slot, icon);
    }
}
