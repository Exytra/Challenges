package me.aaron.timer.heal;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Heal {
    public void healme(Player p) {
        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        p.setFoodLevel(20);
        p.sendMessage("§8[§6Heal§8] §7Du wurdest geheilt");
    }

    public void healall(Player p) {
        p.sendMessage("§8[§6Heal§8] §8Du hast alle geheilt");
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (!pl.getName().equals(p.getName())) {
                pl.sendMessage("§8[§6Heal§8] §7Du wurdest von §9" + p.getName() + "§7 geheilt");
            }
            pl.setHealth(pl.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            pl.setFoodLevel(20);
        }
    }
}
