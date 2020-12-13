package me.aaron.timer.listeners;

import me.aaron.timer.utils.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;

import static java.lang.Math.round;


public class DamageListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (SettingsModes.settings.get(SettingsItems.ItemType.GEITEILTEHERZEN) == SettingsItems.ItemState.ENABLED) {
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    if (SettingsModes.settings.get(SettingsItems.ItemType.ONELIFE) == SettingsItems.ItemState.ENABLED) {
                        if (p.getHealth() != 0) {
                            pl.setHealth(p.getHealth());
                        }
                    } else {
                        pl.setHealth(p.getHealth());
                    }
                }
            }
            if (Timer.state == Timer.TimerState.PAUSED && SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                e.setCancelled(true);
            }
            String DamageCause;
            double Damage = e.getDamage();

            if (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                DamageCause = "Explosion";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                DamageCause = "Kontakt";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.CRAMMING) {
                DamageCause = "Zerquetschung";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH) {
                DamageCause = "Drachenatem";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                DamageCause = "Ertrinken";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                DamageCause = "Explosion";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.FALL || e.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
                DamageCause = "Fallschaden";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                DamageCause = "Feuer";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) {
                DamageCause = "Hot Floor";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                DamageCause = "Lava";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
                DamageCause = "Blitz";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.MAGIC) {
                DamageCause = "Magie";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.MELTING) {
                DamageCause = "Schmelzen";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.POISON) {
                DamageCause = "Vergiftung";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
                DamageCause = "Projektil";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
                DamageCause = "Hunger";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
                DamageCause = "Erstickung";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.THORNS) {
                DamageCause = "Dornen";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                DamageCause = "Void";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.WITHER) {
                DamageCause = "Wither";
            } else if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                DamageCause = "Attacke";
            } else {
                DamageCause = "Unbekannt";
            }
            if (Timer.state != Timer.TimerState.PAUSED && SettingsModes.settings.get(SettingsItems.ItemType.DMGALERT) == SettingsItems.ItemState.ENABLED && e.getFinalDamage() != 0 && DamageCause != "Unbekannt") {
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage("§8[§6Schaden§8] §9§l" + p.getName() + "§7 hat durch §9§l" + DamageCause + " §6" + Damage / 2 + " Herzen§7 Schaden bekommen.");
                }
            }

            if (SettingsModes.challenge.get(SettingsItems.ItemType.FLYONDAMAGE) == SettingsItems.ItemState.ENABLED) {
                if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                    if (!(DamageCause.equals("Unbekannt"))) {
                        e.setCancelled(true);
                        p.setVelocity(new Vector(0, 3, 0));
                        p.damage(e.getDamage());
                    }
                }
            }
        }
    }
}
