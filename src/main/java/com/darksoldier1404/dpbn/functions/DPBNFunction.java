package com.darksoldier1404.dpbn.functions;


import com.darksoldier1404.dpbn.Banknote;
import com.darksoldier1404.dppc.api.essentials.MoneyAPI;
import com.darksoldier1404.dppc.api.inventory.DInventory;
import com.darksoldier1404.dppc.utils.NBT;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.darksoldier1404.dpbn.Banknote.getInstance;

public class DPBNFunction {
    private static final Banknote plugin = getInstance();

    public static void editBanknoteItem(Player p) {
        DInventory inv = new DInventory("Banknote Item Editor", 27, plugin);
        ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta im = pane.getItemMeta();
        im.setDisplayName(" ");
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pane.setItemMeta(im);
        NBT.setStringTag(pane, "dppc_clickcancel", "true");
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, pane);
        }
        ItemStack banknoteItem = plugin.getConfig().getItemStack("Settings.BanknoteItem");
        inv.setItem(13, banknoteItem);
        inv.setChannel(1);
        inv.openInventory(p);
    }

    public static void giveBanknoteItem(Player p, double amount, int quantity) {
        if (!isBanknoteItemSet()) {
            p.sendMessage(plugin.getPrefix() + "§cBanknote item is not set. Please contact an administrator.");
            return;
        }
        if (amount <= 0) {
            p.sendMessage(plugin.getPrefix() + "§cAmount must be greater than 0.");
            return;
        }
        if (!MoneyAPI.hasEnoughMoney(p, amount * quantity)) {
            p.sendMessage(plugin.getPrefix() + "§cYou do not have enough money to get this banknote.");
            return;
        }
        if (isEnableBanknoteAmountLimit() && amount > getMaxBanknoteAmount()) {
            p.sendMessage(plugin.getPrefix() + "§cThe amount exceeds the maximum limit of §f" + getMaxBanknoteAmount() + "§c per banknote.");
            return;
        }
        if (quantity <= 0) {
            p.sendMessage(plugin.getPrefix() + "§cQuantity must be at least 1.");
            return;
        }
        ItemStack banknoteItem = getBanknoteItem().clone();
        NBT.setDoubleTag(banknoteItem, "dpbn_amount", amount);
        NBT.setStringTag(banknoteItem, "dpbn_item", "true");
        applyPlaceholder(banknoteItem, p);
        banknoteItem.setAmount(quantity);
        MoneyAPI.takeMoney(p, amount * quantity);
        p.getInventory().addItem(banknoteItem);
        p.sendMessage(plugin.getPrefix() + "§aYou have received §f" + quantity + "§a banknote(s) worth §f" + amount + "§a each.");
    }

    public static ItemStack getBanknoteItem() {
        return plugin.getConfig().getItemStack("Settings.BanknoteItem");
    }

    public static boolean isBanknoteItemSet() {
        return plugin.getConfig().getItemStack("Settings.BanknoteItem") != null;
    }

    public static boolean isBanknoteItem(ItemStack item) {
        if (item == null) return false;
        return NBT.hasTagKey(item, "dpbn_item");
    }

    public static double getBanknoteAmount(ItemStack item) {
        if (!isBanknoteItem(item)) return 0.0;
        return NBT.getDoubleTag(item, "dpbn_amount");
    }

    public static boolean isAllowOffHandRedeem() {
        return plugin.getConfig().getBoolean("Settings.AllowOffHandUse", false);
    }

    public static boolean isEnableBanknoteAmountLimit() {
        return plugin.getConfig().getBoolean("Settings.EnableBanknoteAmountLimit", true);
    }

    public static double getMaxBanknoteAmount() {
        return plugin.getConfig().getDouble("Settings.MaxAmountPerBanknote", 1000000.0);
    }

    @NotNull
    public static ItemStack applyPlaceholder(@NotNull ItemStack item, @Nullable Player p) {
        if (isBanknoteItem(item)) {
            double amount = getBanknoteAmount(item);
            String playerName = (p != null) ? p.getName() : "Unknown";
            ItemMeta im = item.getItemMeta();
            if (im != null) {
                String displayName = im.getDisplayName();
                displayName = displayName.replace("<amount>", String.valueOf(amount));
                displayName = displayName.replace("<player>", playerName);
                im.setDisplayName(displayName);
                if (im.hasLore()) {
                    java.util.List<String> lore = im.getLore();
                    if (lore != null) {
                        for (int i = 0; i < lore.size(); i++) {
                            String line = lore.get(i);
                            line = line.replace("<amount>", String.valueOf(amount));
                            line = line.replace("<player>", playerName);
                            lore.set(i, line);
                        }
                        im.setLore(lore);
                    }
                }
                item.setItemMeta(im);
            }
        }
        return item;
    }
}
