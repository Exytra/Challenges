package me.aaron.timer.commands;

import me.aaron.timer.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.aaron.timer.heal.Heal;

public class HealCommand implements CommandExecutor {
    Heal heal = new Heal();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player p = (Player) sender;

        if (p.hasPermission("challenges.heal")) {
            if (args.length == 0) {
                heal.healme(p);
            } else if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
                heal.healall(p);
            } else {
                p.sendMessage("§6~~~~~~~~~~~~Heal-Command~~~~~~~~~~~~");
                p.sendMessage("§7/heal §9 heilt dich");
                p.sendMessage("§7/heal §6all §9 heilt alle");
            }
        } else {
            p.sendMessage(Main.getPrefix("Heal", "Du hast hierfür §ckeine Berechtigung"));
        }

        return false;
    }
}
