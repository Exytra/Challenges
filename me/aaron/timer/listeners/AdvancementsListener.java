package me.aaron.timer.listeners;

import me.aaron.timer.projects.AllAchievements;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AdvancementsListener implements Listener {
    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent e) {
        if (SettingsModes.projects.get(SettingsItems.ItemType.ALL_ACHIEVEMENTS) == SettingsItems.ItemState.ENABLED) {
            if (!e.getAdvancement().getKey().getKey().contains("recipes/")) {
                if (!AllAchievements.gottenAchievements.contains(e.getAdvancement().getKey().getKey())) {
                    AllAchievements.gottenAchievements.add(e.getAdvancement().getKey().getKey());
                }
                NamespacedKey key = NamespacedKey.minecraft(e.getAdvancement().getKey().getKey());
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    AdvancementProgress progress = pl.getAdvancementProgress(Bukkit.getAdvancement(key));
                    for (String criteria : progress.getRemainingCriteria()) {
                        progress.awardCriteria(criteria);
                    }
                }
            }
        }

    }
}
