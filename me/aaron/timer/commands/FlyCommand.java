package me.aaron.timer.commands;

import me.aaron.timer.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.aaron.timer.fly.Fly;

public class FlyCommand implements CommandExecutor {
    Fly fly = new Fly();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player p = (Player) sender;
        if (p.hasPermission("challenges.fly")) {
            fly.switch_fly(p);
        } else {
            p.sendMessage(Main.getPrefix("Fly", "Du hast hierfür §ckeine Berechtigung"));
        }

        return false;
    }
}
