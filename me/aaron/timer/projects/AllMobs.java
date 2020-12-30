package me.aaron.timer.projects;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AllMobs {
    public static ArrayList<String> mobnames = new ArrayList<>();
    public static ArrayList<EntityType> entities = new ArrayList<>();

    public static void start() {
        if (mobnames.size() == 0) {
            for (EntityType entityType : EntityType.values()) {
                if (entityType.isAlive() && entityType != EntityType.ARMOR_STAND && entityType != EntityType.PLAYER && entityType != EntityType.GIANT) {
                    entities.add(entityType);
                    mobnames.add(entityType.name());
                }
            }
        } else {
            for (String mobs : mobnames) {
                entities.add(EntityType.valueOf(mobs));
            }
        }
    }

    public static void killed(EntityType entityType) {
        if (entities.contains(entityType)) {
            entities.remove(entityType);
            mobnames.clear();
            for (EntityType entityType2 : entities) {
                mobnames.add(entityType2.name());
            }
            for (Player pl : Bukkit.getOnlinePlayers()) {
                pl.sendTitle("§9" + Utils.firstLatterCapitalized(entityType.getKey().getKey().replace("_", " ")) + "§7 getötet!", "", 5, 30, 5);
            }
        }
        if (entities.size() == 0) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                pl.sendTitle("§9Alle Mobs §7getötet", "", 10, 60, 10);
                pl.sendMessage(Main.getPrefix("Alle Mobs", "Du hast §9alle Mobs §7getötet!"));
            }
        }
    }

}
