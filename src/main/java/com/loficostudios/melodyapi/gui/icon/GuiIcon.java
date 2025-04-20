/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.gui.icon;

import com.loficostudios.melodyapi.utils.bukkit.ItemStackUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GuiIcon {

    private final ItemStack item;
    private Consumer<InventoryClickEvent> action;

    private static class Validate {
        private static void isTrue(boolean b, String message) {
            if (!b) throw new IllegalArgumentException(message);
        }
    }

    public GuiIcon(ItemStack item, Component display, List<? extends Component> description, @Nullable BiConsumer<Player, ClickType> onClick) {
        Validate.isTrue(item != null, "Item is null");
        Validate.isTrue(display != null, "Display is null");
        Validate.isTrue(description != null, "Description is null");

        var clone = item.clone();
        var meta = clone.getItemMeta();
        if (meta != null) {
            meta.displayName(display);

            if (!description.isEmpty()) {
                var lore = meta.lore();
                if (lore != null && !lore.isEmpty()) {
                    lore.add(Component.text(" "));
                    lore.addAll(description);
                } else {
                    lore = new ArrayList<>(description);
                }
                meta.lore(lore);
            }

            clone.setItemMeta(meta);
        }

        this.item = clone;
        this.onClick(onClick);
    }

    public GuiIcon(ItemStack item, Component display, List<? extends Component> description) {
        this(item, display, description, null);
    }

    public GuiIcon(ItemStack item, Component display) {
        this(item, display, List.of(), null);
    }

    public GuiIcon(Material material, Component display, List<? extends Component> description, @Nullable BiConsumer<Player, ClickType> onClick) {
        this(new ItemStack(material), display, description, onClick);
    }

    public GuiIcon(Material material, Component display, @Nullable BiConsumer<Player, ClickType> onClick) {
        this(material, display, List.of(), onClick);
    }

    public GuiIcon(Material material, Component display) {
        this(material, display, List.of(), null);
    }

    @Deprecated
    public GuiIcon(@NotNull ItemStack item, @Nullable String id, @Nullable BiConsumer<Player, ClickType> onClick) {
        this(item, ItemStackUtils.getDisplayNameOrElseMaterialName(item), List.of(), onClick);
    }

    @Deprecated
    public GuiIcon(@NotNull ItemStack item, @Nullable String id) {
        this(item, ItemStackUtils.getDisplayNameOrElseMaterialName(item), List.of(), null);
    }

    @Deprecated
    public ItemStack item() {
        return item;
    }

    public GuiIcon amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public @NotNull List<Component> description() {
        var meta = Objects.requireNonNull(item.getItemMeta());
        var lore = meta.lore();
        return lore != null ? lore : List.of();
    }

    public GuiIcon description(@NotNull List<Component> lines) {
        var meta = Objects.requireNonNull(item.getItemMeta());
        meta.lore(lines);
        item.setItemMeta(meta);
        return this;
    }

    public boolean hasDescription() {
        return !description().isEmpty();
    }

    public void onClick(@Nullable BiConsumer<Player, ClickType> onClick) {
        if (onClick != null) {
            this.action = (clickEvent) -> onClick.accept((Player)clickEvent.getWhoClicked(), clickEvent.getClick());
        }
    }

    public void onClick(InventoryClickEvent e) {
        if (this.action != null)
            this.action.accept(e);
    }
}