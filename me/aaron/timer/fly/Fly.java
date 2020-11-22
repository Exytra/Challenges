package me.aaron.timer.fly;

import org.bukkit.entity.Player;

public class Fly {
    public void switch_fly(Player p) {
        p.setAllowFlight(!(p.getAllowFlight()));

        if (p.getAllowFlight()) {
            p.sendMessage("§8[§6Fly§8]§a Aktiviert");
        } else {
            p.sendMessage("§8[§6Fly§8]§c Deaktiviert");
        }
    }
}
