package com.darksoldier1404.dpbn.commands;

import com.darksoldier1404.dpbn.Banknote;
import com.darksoldier1404.dpbn.functions.DPBNFunction;
import com.darksoldier1404.dppc.builder.command.ArgumentIndex;
import com.darksoldier1404.dppc.builder.command.ArgumentType;
import com.darksoldier1404.dppc.builder.command.CommandBuilder;

import static com.darksoldier1404.dpbn.Banknote.getInstance;


public class DPBNCommand {
    private static final Banknote plugin = getInstance();
    private static final CommandBuilder builder = new CommandBuilder(plugin);

    public static void init() {
        builder.beginSubCommand("item", "/dpbn item - set the banknote item")
                .withPermission("dpbn.admin")
                .executesPlayer((p, args) -> {
                    DPBNFunction.editBanknoteItem(p);
                    return true;
                });
        builder.beginSubCommand("get", "/dpbn get <amount> (quantity)")
                .withPermission("dpbn.use")
                .withArgument(ArgumentIndex.ARG_0, ArgumentType.DOUBLE, plugin.getConfig().getDoubleList("Settings.SuggestedAmounts"))
                .withOptionalArgument(ArgumentIndex.ARG_1, ArgumentType.INTEGER)
                .executesPlayer((p, args) -> {
                    double price = args.getDouble(ArgumentIndex.ARG_0);
                    int quantity = args.getInteger(ArgumentIndex.ARG_1) != null ? args.getInteger(ArgumentIndex.ARG_1) : 1;
                    DPBNFunction.giveBanknoteItem(p, price, quantity);
                    return true;
                });
        builder.beginSubCommand("reload", "/dpbn reload - reload the config")
                .withPermission("dpbn.admin")
                .executes((sender, args) -> {
                    plugin.reload();
                    sender.sendMessage(plugin.getPrefix() + "§aConfiguration reloaded.");
                    return true;
                });
        builder.build("dpbn");
    }
}
