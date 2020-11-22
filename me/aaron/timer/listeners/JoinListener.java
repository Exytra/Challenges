package me.aaron.timer.listeners;

import me.aaron.timer.Main;
import me.aaron.timer.challenges.Trafficlight;
import me.aaron.timer.scoreboard.Sb;
import me.aaron.timer.timer.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinListener implements Listener {
    Trafficlight trafficlight = new Trafficlight(Main.getInstance());
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage("§a» §f" + p.getName());
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(SettingsModes.maxHP);
        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        p.setHealthScale(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

        if (SettingsModes.timer.get(SettingsItems.ItemType.AUTOSTART) == SettingsItems.ItemState.ENABLED) {
            if (Bukkit.getOnlinePlayers().size() == 1) {
                Timer.resume(false);
            }
        }

        if (SettingsModes.challenge.get(SettingsItems.ItemType.SPEED) == SettingsItems.ItemState.ENABLED) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1, 30));
        }

        if (SettingsModes.challenge.get(SettingsItems.ItemType.TRAFFICLIGHT) == SettingsItems.ItemState.ENABLED) {
            //trafficlight.addPlayer(p);
        }

        Sb.createScoreboard(p);

        if (SettingsModes.challenge.get(SettingsItems.ItemType.FORCEBLOCK) == SettingsItems.ItemState.ENABLED) {
            /*ForceBlock forceBlock = new ForceBlock(Main.getInstance());
            forceBlock.addPlayer(p);*/
        }

        //p.teleport(loc);
    }
}
