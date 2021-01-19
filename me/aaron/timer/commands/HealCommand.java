package me.aaron.timer.commands;

import me.aaron.timer.Main;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player p = (Player) sender;

        if (p.hasPermission("challenges.heal")) {
            if (args.length == 0) {
                p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                p.setFoodLevel(20);
                p.sendMessage("§8[§6Heal§8] §7Du wurdest geheilt");
            } else if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
                p.sendMessage("§8[§6Heal§8] §7Du hast alle geheilt");
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    if (!pl.getName().equals(p.getName())) {
                        pl.sendMessage("§8[§6Heal§8] §7Du wurdest von §9" + p.getName() + "§7 geheilt");
                    }
                    pl.setHealth(pl.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                    pl.setFoodLevel(20);
                }
            } else {
                p.sendMessage("§6Heal-Command");
                p.sendMessage("§7/heal §9heilt dich");
                p.sendMessage("§7/heal §6all §9heilt alle");
            }
        } else {
            p.sendMessage(Main.getPrefix("Heal", "Du hast hierfür §ckeine Berechtigung"));
        }

        return false;
    }
}
