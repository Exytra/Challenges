package me.aaron.timer.commands;

import me.aaron.timer.Main;
import me.aaron.timer.projects.AllMobs;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import me.aaron.timer.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class MobsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
            if (SettingsModes.projects.get(SettingsItems.ItemType.ALL_MOBS) == SettingsItems.ItemState.ENABLED) {
                if (args.length == 0) {
                    sender.sendMessage(Main.getPrefix("Alle Mobs", "Es fehlen noch folgende §9" + AllMobs.mobnames.size() + " Mobs§7: §e" + Utils.firstLatterCapitalized(AllMobs.mobnames.toString().replace("_", " ").replace("[", "").replace("]", ""))));
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("remove")) {
                        try {
                            AllMobs.mobnames.remove(args[1].toUpperCase());
                            AllMobs.entities.remove(EntityType.valueOf(args[1].toUpperCase()));
                            sender.sendMessage(Main.getPrefix("Skip Mob", "Das Mob §9" + args[1] + " §7wurde gelöscht."));
                        } catch (Exception e) {
                            sender.sendMessage(Main.getPrefix("Skip Mob", "§9/mobs remove <§oMob-ID§7>"));
                        }
                    }
                } else {
                    sender.sendMessage(Main.getPrefix("Skip Mob", "§9/mobs remove <§oMob-ID§7>"));
                }
            } else {
                sender.sendMessage(Main.getPrefix("Alle Mobs", "Dieser Befehl kann nur verwendet werden, wenn das Projelt §9Alle Mobs §7aktiviert ist."));
            }
        return false;
    }
}
