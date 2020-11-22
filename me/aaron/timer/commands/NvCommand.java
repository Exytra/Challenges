package me.aaron.timer.commands;

import me.aaron.timer.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.aaron.timer.nv.Nv;

public class NvCommand implements CommandExecutor {
    Nv nv = new Nv();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player p = (Player) sender;
        if (p.hasPermission("challenges.nv")) {
            nv.night_vision(p);
        } else {
            p.sendMessage(Main.getPrefix("Night-Vision", "Du hast hierfür §ckeine Berechtigung"));
        }
        return false;
    }
}
