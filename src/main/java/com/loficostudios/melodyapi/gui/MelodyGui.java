/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */



package com.loficostudios.melodyapi.gui;

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
    }

    public int getSize() {
        return this.size;
    }

    public @NotNull String getTitle() {
        return this.title;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}



