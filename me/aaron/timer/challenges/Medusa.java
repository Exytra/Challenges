package me.aaron.timer.challenges;

import me.aaron.timer.Main;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import me.aaron.timer.utils.Timer;
import me.aaron.timer.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Medusa {
    public static void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (SettingsModes.challenge.get(SettingsItems.ItemType.MEDUSA) == SettingsItems.ItemState.ENABLED) {
                if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        for (LivingEntity e : pl.getWorld().getLivingEntities()) {
                            if (Utils.isLookingOnHead(pl, e)) {
                                Location loc = pl.getLocation();
                                Material material = Material.AIR;
                                while (!material.isSolid()) {
                                    int randX = ThreadLocalRandom.current().nextInt(loc.getBlockX() - 20, loc.getBlockX() + 20);
                                    int randY = ThreadLocalRandom.current().nextInt(20, 70);
                                    int randZ = ThreadLocalRandom.current().nextInt(loc.getBlockZ() - 20, loc.getBlockZ() + 20);

                                    material = pl.getWorld().getBlockAt(randX, randY, randZ).getType();
                                }
                                e.getWorld().getBlockAt(e.getLocation().getBlockX(), e.getLocation().getBlockY(), e.getLocation().getBlockZ()).setType(material);
                                pl.playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

                                e.remove();
                            }
                        }
                    }
                }
            }
        }, 0, 1);
    }
}
