package me.aaron.timer.commands;

import me.aaron.timer.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.aaron.timer.invsee.Invsee;

public class InvseeCommand implements CommandExecutor {
    Invsee invsee = new Invsee();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player s = (Player) sender;
        if (s.hasPermission("challenges.invsee")) {
            if (args.length == 1) {
                Player p = Bukkit.getPlayer(args[0]);
                String pname = p.getName();
                invsee.invsee(p, s);
            } else {
                s.sendMessage(Main.getPrefix("InvSee", "/invsee §9<Player Name>"));
            }
        } else {
            s.sendMessage(Main.getPrefix("InvSee", "Du hast hierfür §ckeine Berechtigung"));
        }


        return false;
    }
}
