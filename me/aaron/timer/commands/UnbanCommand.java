package me.aaron.timer.commands;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Permissions;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnbanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || Permissions.hasPermission((Player) sender, Permissions.Rank.ADMIN)) {
            if (args.length == 1) {
                BanList banList = Bukkit.getBanList(BanList.Type.NAME);
                try {
                    banList.pardon(args[0]);
                    sender.sendMessage(Main.getPrefix("Unban", "Der Spieler §9" + args[0] + " §7wurde entbannt."));
                } catch (Exception e) {
                    sender.sendMessage(Main.getPrefix("Unban", "Der Spieler §9" + args[0] + " §7konnte nicht gefunden werden."));
                }
            }
        } else {
            sender.sendMessage(Main.getPrefix("Ban", "Du hast §ckeine Berechtigung §7diesen Befehl auszuführen."));
        }
        return false;
    }
}
