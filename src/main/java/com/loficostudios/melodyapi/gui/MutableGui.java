package com.loficostudios.melodyapi.gui;

import com.loficostudios.melodyapi.gui.guiicon.GuiIcon;
import com.loficostudios.melodyapi.gui.interfaces.IGui;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class MutableGui implements IGui {
    protected final Map<Integer, GuiIcon> displayedIcons = new HashMap<>();

    public abstract @NotNull Set<Integer> getMutableSlots();

    @Override
    public GuiIcon getIcon(int slot) {
        return this.displayedIcons.get(slot);
    }

    @Override
    public boolean setSlot(int slot, @NotNull GuiIcon icon) {
        if (getMutableSlots().contains(slot)) {
            throw new IllegalArgumentException("Cannot set GuiIcon to a mutable slot!");
        }
        this.displayedIcons.put(slot, icon);
        getInventory().setItem(slot, icon.getItem());
        return true;
    }
}
