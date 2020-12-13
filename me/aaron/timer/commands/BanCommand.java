package me.aaron.timer.commands;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Permissions;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || Permissions.hasPermission((Player) sender, Permissions.Rank.ADMIN)) {
            if (args.length >= 1) {
                try {
                    Player p = Bukkit.getPlayerExact(args[0]);
                    BanList banList = Bukkit.getBanList(BanList.Type.NAME);
                    StringBuilder msg = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        msg.append(args[i] + " ");
                    }
                    String reason = msg.toString();
                    banList.addBan(p.getName(), (args.length == 1) ? "Banned by Administrator" : reason, null, null);
                    if (args.length == 1) {
                        p.kickPlayer("§cDu wurdest auf diesem Server permanent gebannt!");
                    } else {
                        p.kickPlayer("§cDu wurdest auf diesem Server permanent gebannt!\n\n§7Grund: §f" + reason);
                    }
                    Bukkit.broadcastMessage(Main.getPrefix("Ban", "Der Spieler §9" + p.getName() + "§7 wurde §cvom Bannhammer §7erwischt!"));
                } catch (Exception e) {
                    sender.sendMessage(Main.getPrefix("Ban", "Der Spieler §9" + args[0] + "§7 konnte §cnicht gefunden §7werden."));
                }
            }
        } else {
            sender.sendMessage(Main.getPrefix("Ban", "Du hast §ckeine Berechtigung §7diesen Befehl auszuführen."));
        }
        return false;
    }
}
