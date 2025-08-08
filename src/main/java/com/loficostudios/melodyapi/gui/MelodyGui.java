/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
<<<<<<< Updated upstream
 * @Link https://github.com/Tonierbobcat/MelodyAPI
 * @version 0.1.3
=======
 * @version MelodyApi
>>>>>>> Stashed changes
 */



package com.loficostudios.melodyapi.gui;

<<<<<<< Updated upstream
import com.loficostudios.melodyapi.gui.events.GuiOpenEvent;
import com.loficostudios.melodyapi.gui.icon.GuiIcon;
import com.loficostudios.melodyapi.gui.interfaces.IGui;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class MelodyGui implements IGui {
    protected static final String DEFAULT_MENU_TITLE = "&#C608FBM&#C322FBe&#C03CFBl&#BE56FCo&#BB70FCd&#B88BFCy &#B3BFFDG&#B0D9FDU&#ADF3FDI";

    private Inventory inventory;
    private final Map<Integer, GuiIcon> displayedIcons = new HashMap<>();

    private Component title;

    private final int size;

    public MelodyGui(int size) {
        this(size, (Component) null);
    }

    public  MelodyGui(int size, Component title) {
        this.size = IGui.validateSize(size);
        this.title = title;
        this.inventory = Bukkit.createInventory(this,
                this.size,
                title != null ? title : Component.text(DEFAULT_MENU_TITLE));
    }

    public boolean open(@NotNull Player player) {
        var event = new GuiOpenEvent(player, this);
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            return false;
        }

        player.openInventory(this.inventory);
        return true;
    }

    public boolean close(@NotNull Player player) {
        player.closeInventory();
        return true;
    }

    public void refresh() {
    }

    public Collection<GuiIcon> getDisplayedIcons() {
        return new ArrayList<>(this.displayedIcons.values());
    }

    public GuiIcon getIcon(int slot) {
        return this.displayedIcons.get(slot);
=======
import com.loficostudios.melodyapi.utils.StringUtils;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class MelodyGui implements InventoryHolder {
    protected static final String DEFAULT_MENU_TITLE = "&#C608FBM&#C322FBe&#C03CFBl&#BE56FCo&#BB70FCd&#B88BFCy &#B3BFFDG&#B0D9FDU&#ADF3FDI";

    @Getter
    private Map<Integer, GuiIcon> icons = new HashMap<>();

    private final Inventory inventory;
    private final String title;

    private final int size;

    public MelodyGui(JavaPlugin plugin, int size, String title) {
        this.size = validateSize(size);
        this.title = title;
        this.inventory = plugin.getServer().createInventory(this,
                this.size,
                !StringUtils.isNullOrEmpty(title) ? title : DEFAULT_MENU_TITLE);
    }

    public MelodyGui(JavaPlugin plugin, int size) {
        this(plugin, size, null);
    }

    public final void fill(@NotNull GuiIcon icon, int start, int end, Boolean replaceExisting) {
        for(int i = start; i < end; ++i) {

            if (!replaceExisting && this.icons.containsKey(i)) {
                continue; // Skip this iteration if replaceExisting is false and key exists
            }

            setSlot(i, icon);
        }
    }

    public void open(@NotNull Player player) {
        GuiManager.instance().setGui(player, this);
        player.openInventory(this.inventory);
    }

    protected void clear() {
        if (icons.isEmpty()) return;

        icons.forEach((index, icon) -> {
            this.getInventory().setItem(index, new ItemStack(Material.AIR));
        });

        icons.clear();
    }

    public void close(@NotNull Player player) {
        player.closeInventory();
    }

    public void setSlot(@NotNull Integer slot, @NotNull GuiIcon icon) {
        this.icons.put(slot, icon);
        this.inventory.setItem(slot, icon.getItem());
    }

    public GuiIcon getIcon(@NotNull Integer slot) {
        return this.icons.get(slot);
    }

    private int validateSize(int size) {
        final Set<Integer> allowedInventorySize = new HashSet<>(Set.of(
                9, 18, 27, 36, 45, 54
        ));

        return allowedInventorySize.stream()
                .filter(allowedSize -> allowedSize >= size)
                .min(Integer::compareTo)
                .orElse(9);
>>>>>>> Stashed changes
    }

    public int getSize() {
        return this.size;
    }

<<<<<<< Updated upstream
    public @NotNull Component getTitle() {
=======
    public @NotNull String getTitle() {
>>>>>>> Stashed changes
        return this.title;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }
<<<<<<< Updated upstream

    @Override
    public boolean setSlot(int slot, @Nullable ItemStack item) {
        if (item == null) {
            setSlot(slot, new GuiIcon(new ItemStack(Material.AIR), ""));
            return true;
        }

        setSlot(slot, new GuiIcon(item, item.getType().name().toLowerCase() + System.currentTimeMillis()));
        return true;
    }

    @Override
    public boolean setSlot(int slot, @Nullable GuiIcon icon) {
        if (icon == null) {
            setSlot(slot, new GuiIcon(new ItemStack(Material.AIR), ""));
            return true;
        }
        this.displayedIcons.put(slot, icon);
        this.inventory.setItem(slot, icon.item());
        return true;
    }

    public void setTitle(@NotNull Component text) {
        this.title = text;

        var contents = inventory.getContents();
        var viewers = new ArrayList<>(inventory.getViewers());
        for (HumanEntity viewer : viewers) {
            viewer.closeInventory();
        }
        inventory = Bukkit.createInventory(this, this.size, this.title);
        for (HumanEntity viewer : viewers) {
            viewer.openInventory(this.inventory);
        }
        inventory.setContents(contents);
    }

    protected void fill(@NotNull GuiIcon icon, int start, int end, Boolean replaceExisting) {
        for(int i = start; i < end; ++i) {

            if (!replaceExisting && this.displayedIcons.containsKey(i)) {
                continue; // Skip this iteration if replaceExisting is false and key exists
            }

            setSlot(i, icon);
        }
    }

    protected void clear() {
        if (displayedIcons.isEmpty()) return;

        var entries = new ArrayList<>(displayedIcons.entrySet());

        for (Map.Entry<Integer, GuiIcon> entry : entries) {
            this.getInventory().setItem(entry.getKey(), new ItemStack(Material.AIR));
        }


        displayedIcons.clear();
    }

    protected void clear(GuiIcon... excluded) {
        if (displayedIcons.isEmpty()) return;

        var entries = new ArrayList<>(displayedIcons.entrySet());
        for (Map.Entry<Integer, GuiIcon> entry : entries) {
            var found = false;
            for (GuiIcon guiIcon : excluded) {
                if (entry.getValue() .equals(guiIcon)) {
                    found = true;
                    break;
                }
            }
            if (found)
                this.getInventory().setItem(entry.getKey(), new ItemStack(Material.AIR));
        }

        displayedIcons.clear();
    }
}


=======
}



>>>>>>> Stashed changes
