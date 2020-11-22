package me.aaron.timer.listeners;

import me.aaron.timer.Main;
import me.aaron.timer.challenges.ForceBlock;
import me.aaron.timer.challenges.Trafficlight;
import me.aaron.timer.timer.Timer;
import me.aaron.timer.utils.Settings;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import me.aaron.timer.utils.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        int slot = e.getRawSlot();

        Player p = (Player) e.getWhoClicked();

        if (!e.getWhoClicked().getType().equals(EntityType.PLAYER)) {
            return;
        }
        if(e.getClickedInventory() == null) {
            return;
        }
        if(e.getCurrentItem() == null) {
            return;
        }

        if(e.getView().getTitle().equalsIgnoreCase(Settings.getMenuName()) || e.getView().getTitle().equalsIgnoreCase("Lebenseinstellungen") || e.getView().getTitle().equalsIgnoreCase("Challenges") || e.getView().getTitle().equalsIgnoreCase(Settings.getOtherMenuName()) || e.getView().getTitle().equalsIgnoreCase("Timer Einstellungen") || e.getView().getTitle().equalsIgnoreCase("Restliche Einstellungen §7» §8Seite 2")) {
            e.setCancelled(true);
            if(e.getCurrentItem().isSimilar(Settings.Health())) { p.openInventory(Settings.getHealthMenu()); }

            else if (e.getCurrentItem().isSimilar(Settings.ChallengesSettings())) { p.openInventory(Settings.getChallengesMenu()); }

            else if (e.getCurrentItem().isSimilar(Settings.Other())) { p.openInventory(Settings.getOtherMenu()); }

            //Lebenseinstellungen Menu
            if (e.getView().getTitle().equalsIgnoreCase("Lebenseinstellungen")) {
                switch (slot) {
                    case 10:
                        if (e.getCurrentItem().getType() == Material.LIME_DYE) {
                            SettingsModes.settings.put(SettingsItems.ItemType.GEITEILTEHERZEN, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(e.getSlot(), SettingsItems.getMenuItem(SettingsItems.ItemType.GEITEILTEHERZEN, SettingsItems.ItemState.DISABLED));
                            Utils.sendChange("§6Geteilte Herzen", "§7wurde §cdeaktiviert");
                            break;
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.GEITEILTEHERZEN, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(e.getSlot(), SettingsItems.getMenuItem(SettingsItems.ItemType.GEITEILTEHERZEN, SettingsItems.ItemState.ENABLED));
                            Utils.sendChange("§6Geteilte Herzen", "§7wurde §aaktiviert");
                            break;
                        }
                    case 11:
                        if (e.getCurrentItem().getType() == Material.LIME_DYE) {
                            SettingsModes.settings.put(SettingsItems.ItemType.RESPAWN, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(e.getSlot(), SettingsItems.getMenuItem(SettingsItems.ItemType.RESPAWN, SettingsItems.ItemState.DISABLED));
                            Utils.sendChange("§6Respawn", "§7wurde §cdeaktiviert");
                            break;
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.RESPAWN, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(e.getSlot(), SettingsItems.getMenuItem(SettingsItems.ItemType.RESPAWN, SettingsItems.ItemState.ENABLED));
                            Utils.sendChange("§6Respawn", "§7wurde §aaktiviert");
                            if (SettingsModes.settings.get(SettingsItems.ItemType.ONELIFE) == SettingsItems.ItemState.ENABLED) {
                                SettingsModes.settings.put(SettingsItems.ItemType.ONELIFE, SettingsItems.ItemState.DISABLED);
                                e.getClickedInventory().setItem(12, SettingsItems.getMenuItem(SettingsItems.ItemType.ONELIFE, SettingsItems.ItemState.DISABLED));
                            }
                            break;
                        }
                    case 12:
                        if (e.getCurrentItem().getType() == Material.LIME_DYE) {
                            SettingsModes.settings.put(SettingsItems.ItemType.ONELIFE, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(e.getSlot(), SettingsItems.getMenuItem(SettingsItems.ItemType.ONELIFE, SettingsItems.ItemState.DISABLED));
                            Utils.sendChange("§6Ein Leben für alle", "§7wurde §cdeaktiviert");
                            break;
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.ONELIFE, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(e.getSlot(), SettingsItems.getMenuItem(SettingsItems.ItemType.ONELIFE, SettingsItems.ItemState.ENABLED));
                            Utils.sendChange("§6Ein Leben für alle", "§7wurde §aaktiviert");
                            if (SettingsModes.settings.get(SettingsItems.ItemType.RESPAWN) == SettingsItems.ItemState.ENABLED) {
                                SettingsModes.settings.put(SettingsItems.ItemType.RESPAWN, SettingsItems.ItemState.DISABLED);
                                e.getClickedInventory().setItem(11, SettingsItems.getMenuItem(SettingsItems.ItemType.RESPAWN, SettingsItems.ItemState.DISABLED));
                            }
                            break;
                        }
                    case 14:
                        if (e.getClick().equals(ClickType.RIGHT)) {
                            SettingsModes.maxHP -= 1;
                            e.getClickedInventory().setItem(5, Settings.MaxHealth());
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(SettingsModes.maxHP);
                                pl.setHealth(pl.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                                pl.setHealthScale(pl.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                            }
                            break;
                        } else {
                            SettingsModes.maxHP += 1;
                            e.getClickedInventory().setItem(5, Settings.MaxHealth());
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(SettingsModes.maxHP);
                                pl.setHealth(pl.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                                pl.setHealthScale(pl.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                            }
                            break;
                        }
                    case 15:
                        if (e.getCurrentItem().getType() == Material.LIME_DYE) {
                            SettingsModes.settings.put(SettingsItems.ItemType.NATURALREGENERATION, SettingsItems.ItemState.DISABLED);
                            for (World wl : Bukkit.getWorlds()) {
                                wl.setGameRule(GameRule.NATURAL_REGENERATION, false);
                            }
                            Utils.sendChange("§6Natürliche Regeneration", "§7wurde §cdeaktiviert");
                            e.getClickedInventory().setItem(15, SettingsItems.getMenuItem(SettingsItems.ItemType.NATURALREGENERATION, SettingsItems.ItemState.DISABLED));
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.NATURALREGENERATION, SettingsItems.ItemState.ENABLED);
                            for (World wl : Bukkit.getWorlds()) {
                                wl.setGameRule(GameRule.NATURAL_REGENERATION, true);
                            }
                            Utils.sendChange("§6Natürliche Regeneration", "§7wurde §aaktiviert");
                            e.getClickedInventory().setItem(15, SettingsItems.getMenuItem(SettingsItems.ItemType.NATURALREGENERATION, SettingsItems.ItemState.ENABLED));
                        }
                        break;
                    case 16:
                        if (e.getCurrentItem().getType() == Material.LIME_DYE) {
                            SettingsModes.gamerule.put(SettingsItems.ItemType.OTHERREGENERATION, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(16, SettingsItems.getMenuItem(SettingsItems.ItemType.OTHERREGENERATION, SettingsModes.gamerule.get(SettingsItems.ItemType.OTHERREGENERATION)));
                            Utils.sendChange("§6Unnatürliche Regeneration", "§7wurde §cdeaktiviert");
                        } else {
                            SettingsModes.gamerule.put(SettingsItems.ItemType.OTHERREGENERATION, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(16, SettingsItems.getMenuItem(SettingsItems.ItemType.OTHERREGENERATION, SettingsModes.gamerule.get(SettingsItems.ItemType.OTHERREGENERATION)));
                            Utils.sendChange("§6Unnatürliche Regeneration", "§7wurde §aaktiviert");
                        }
                        break;
                    case 18:
                        p.openInventory(Settings.getMenu());
                        break;
                }
                //Challenge Menu
            } else if (e.getView().getTitle().equalsIgnoreCase("Challenges")) {
                switch (slot) {
                    case 10:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.FLYONDAMAGE) == SettingsItems.ItemState.ENABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.FLYONDAMAGE, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(10, SettingsItems.getMenuItem(SettingsItems.ItemType.FLYONDAMAGE, SettingsModes.challenge.get(SettingsItems.ItemType.FLYONDAMAGE)));
                            Utils.sendChange("§6Bei Schaden fliegen", "§cwurde deaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.FLYONDAMAGE, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(10, SettingsItems.getMenuItem(SettingsItems.ItemType.FLYONDAMAGE, SettingsModes.challenge.get(SettingsItems.ItemType.FLYONDAMAGE)));
                            Utils.sendChange("§6Bei Schaden fliegen", "§awurde aktiviert");
                        }
                        break;
                    case 11:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.SPEED) == SettingsItems.ItemState.ENABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.SPEED, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(11, SettingsItems.getMenuItem(SettingsItems.ItemType.SPEED, SettingsModes.challenge.get(SettingsItems.ItemType.SPEED)));
                            Utils.sendChange("§6Speed 30", "§7wurde §cdeaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.SPEED, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(11, SettingsItems.getMenuItem(SettingsItems.ItemType.SPEED, SettingsModes.challenge.get(SettingsItems.ItemType.SPEED)));
                            Utils.sendChange("§6Speed 30", "§7wurde §aaktiviert");
                        }
                        break;
                    case 12:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.DIRT) == SettingsItems.ItemState.ENABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.DIRT, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.DIRT, SettingsModes.challenge.get(SettingsItems.ItemType.DIRT)));
                            Utils.sendChange("§6Nur auf Dirt", "§7wurde §cdeaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.DIRT, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.DIRT, SettingsModes.challenge.get(SettingsItems.ItemType.DIRT)));
                            Utils.sendChange("§6Nur auf Dirt", "§7wurde §aaktiviert");
                        }
                        break;
                    case 13:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.TENHEARTS) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.TENHEARTS, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.TENHEARTS, SettingsModes.challenge.get(SettingsItems.ItemType.TENHEARTS)));
                            Utils.sendChange("§6Niemals volle Herzen", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.TENHEARTS, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.TENHEARTS, SettingsModes.challenge.get(SettingsItems.ItemType.TENHEARTS)));
                            Utils.sendChange("§6Niemals volle Herzen", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 14:
                        Trafficlight trafficlight = new Trafficlight(Main.getInstance());
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.TRAFFICLIGHT) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.TRAFFICLIGHT, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.TRAFFICLIGHT, SettingsModes.challenge.get(SettingsItems.ItemType.TRAFFICLIGHT)));
                            Utils.sendChange("§6Ampel-Challenge", "§7wurde §aaktiviert");
                            trafficlight.start();
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.TRAFFICLIGHT, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.TRAFFICLIGHT, SettingsModes.challenge.get(SettingsItems.ItemType.TRAFFICLIGHT)));
                            Utils.sendChange("§6Ampel-Challenge", "§7wurde §cdeaktiviert");
                            //trafficlight.stop();
                        }
                        break;
                    case 15:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.ONEBLOCKONEHEART) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.ONEBLOCKONEHEART, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.ONEBLOCKONEHEART, SettingsModes.challenge.get(SettingsItems.ItemType.ONEBLOCKONEHEART)));
                            Utils.sendChange("§61 Block = 1 Herz Schaden", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.ONEBLOCKONEHEART, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.ONEBLOCKONEHEART, SettingsModes.challenge.get(SettingsItems.ItemType.ONEBLOCKONEHEART)));
                            Utils.sendChange("§61 Block = 1 Herz Schaden", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 16:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.DAMAGEMIRROR) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.DAMAGEMIRROR, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.DAMAGEMIRROR, SettingsModes.challenge.get(SettingsItems.ItemType.DAMAGEMIRROR)));
                            Utils.sendChange("§6Gespiegelter Schaden", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.DAMAGEMIRROR, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.DAMAGEMIRROR, SettingsModes.challenge.get(SettingsItems.ItemType.DAMAGEMIRROR)));
                            Utils.sendChange("§6Gespiegelter Schaden", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 19:
                        ForceBlock forceBlock = new ForceBlock(Main.getInstance());
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.FORCEBLOCK) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.FORCEBLOCK, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.FORCEBLOCK, SettingsModes.challenge.get(SettingsItems.ItemType.FORCEBLOCK)));
                            Utils.sendChange("§6Force-Block", "§7wurde §aaktiviert");
                            forceBlock.start();
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.FORCEBLOCK, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.FORCEBLOCK, SettingsModes.challenge.get(SettingsItems.ItemType.FORCEBLOCK)));
                            Utils.sendChange("§6Force-Block", "§7wurde §cdeaktiviert");
                        }
                        break;

                    //zurück
                    case 27:
                        p.openInventory(Settings.getMenu());
                        break;
                }
            }
            //OtherMenu
            else if (e.getView().getTitle().equalsIgnoreCase(Settings.getOtherMenuName())) {
                switch (slot) {
                    case 1:
                        p.openInventory(Settings.TimerMenu());
                        break;
                    case 10:
                        if (e.getClick() == ClickType.SHIFT_LEFT) {
                            p.openInventory(Settings.TimerMenu());
                            break;
                        } else {
                            if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                                SettingsModes.settings.put(SettingsItems.ItemType.TIMER, SettingsItems.ItemState.DISABLED);
                                e.getClickedInventory().setItem(10, SettingsItems.getMenuItem(SettingsItems.ItemType.TIMER, SettingsModes.settings.get(SettingsItems.ItemType.TIMER)));
                                Utils.sendChange("§6Der Timer", "§cwurde deaktiviert");
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(" "));
                            } else {
                                SettingsModes.settings.put(SettingsItems.ItemType.TIMER, SettingsItems.ItemState.ENABLED);
                                e.getClickedInventory().setItem(10, SettingsItems.getMenuItem(SettingsItems.ItemType.TIMER, SettingsModes.settings.get(SettingsItems.ItemType.TIMER)));
                                Utils.sendChange("§6Der Timer", "§awurde aktiviert");
                            }
                        }
                        break;
                    case 11:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.SENDTITLE) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.settings.put(SettingsItems.ItemType.SENDTITLE, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(11, SettingsItems.getMenuItem(SettingsItems.ItemType.SENDTITLE, SettingsModes.settings.get(SettingsItems.ItemType.SENDTITLE)));
                            Utils.sendChange("§6Titel bei Änderung", "§7wurden §aaktiviert");
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.SENDTITLE, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(11, SettingsItems.getMenuItem(SettingsItems.ItemType.SENDTITLE, SettingsModes.settings.get(SettingsItems.ItemType.SENDTITLE)));
                        }
                        break;
                    case 12:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.DMGALERT) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.settings.put(SettingsItems.ItemType.DMGALERT, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(12, SettingsItems.getMenuItem(SettingsItems.ItemType.DMGALERT, SettingsModes.settings.get(SettingsItems.ItemType.DMGALERT)));
                            Utils.sendChange("§6Schaden im Chat", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.DMGALERT, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(12, SettingsItems.getMenuItem(SettingsItems.ItemType.DMGALERT, SettingsModes.settings.get(SettingsItems.ItemType.DMGALERT)));
                            Utils.sendChange("§6Schaden im Chat", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 13:
                        if (SettingsModes.gamerule.get(SettingsItems.ItemType.KEEP_INVENTORY) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.gamerule.put(SettingsItems.ItemType.KEEP_INVENTORY, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(13, SettingsItems.getMenuItem(SettingsItems.ItemType.KEEP_INVENTORY, SettingsModes.gamerule.get(SettingsItems.ItemType.KEEP_INVENTORY)));
                            for (World wl : Bukkit.getWorlds()) {
                                wl.setGameRule(GameRule.KEEP_INVENTORY, true);
                            }
                            Utils.sendChange("§6Keep Inventory", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.gamerule.put(SettingsItems.ItemType.KEEP_INVENTORY, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(13, SettingsItems.getMenuItem(SettingsItems.ItemType.KEEP_INVENTORY, SettingsModes.gamerule.get(SettingsItems.ItemType.KEEP_INVENTORY)));
                            for (World wl : Bukkit.getWorlds()) {
                                wl.setGameRule(GameRule.KEEP_INVENTORY, false);
                            }
                            Utils.sendChange("§6Keep Inventory", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 14:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.SHOWCOORDSONDEAETH) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.settings.put(SettingsItems.ItemType.SHOWCOORDSONDEAETH, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.SHOWCOORDSONDEAETH, SettingsModes.settings.get(SettingsItems.ItemType.SHOWCOORDSONDEAETH)));
                            Utils.sendChange("§6Todesposition im Chat", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.SHOWCOORDSONDEAETH, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.SHOWCOORDSONDEAETH, SettingsModes.settings.get(SettingsItems.ItemType.SHOWCOORDSONDEAETH)));
                            Utils.sendChange("§6Todesposition im Chat", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 15:
                        if (SettingsModes.gamerule.get(SettingsItems.ItemType.PVP) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.gamerule.put(SettingsItems.ItemType.PVP, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.PVP, SettingsModes.gamerule.get(SettingsItems.ItemType.PVP)));
                            Utils.sendChange("§6PVP", "§7wurde §aaktiviert");
                            for (World wl : Bukkit.getWorlds()) {
                                wl.setPVP(true);
                            }
                        } else {
                            SettingsModes.gamerule.put(SettingsItems.ItemType.PVP, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.PVP, SettingsModes.gamerule.get(SettingsItems.ItemType.PVP)));
                            Utils.sendChange("§6PVP", "§7wurde §cdeaktiviert");
                            for (World wl : Bukkit.getWorlds()) {
                                wl.setPVP(false);
                            }
                        }
                        break;
                    case 16:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.RESETCONFIRM) == SettingsItems.ItemState.ENABLED) {
                            SettingsModes.settings.put(SettingsItems.ItemType.RESETCONFIRM, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.RESETCONFIRM, SettingsModes.settings.get(SettingsItems.ItemType.RESETCONFIRM)));
                            Utils.sendChange("§6/reset confirm", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.RESETCONFIRM, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.RESETCONFIRM, SettingsModes.settings.get(SettingsItems.ItemType.RESETCONFIRM)));
                            Utils.sendChange("§6/reset confirm", "§7wurde §cdeaktiviert");
                        }
                        break;
                    //zurück, weiter
                    case 18:
                        p.openInventory(Settings.getMenu());
                        break;
                    case 26:
                        p.openInventory(Settings.getOtherMenu2());
                        break;
                }
            }
            //Seite 2
            else if (e.getView().getTitle().equalsIgnoreCase("Restliche Einstellungen §7» §8Seite 2")) {
                switch (slot) {
                    case 10:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.ENDER_DRAGON) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.ENDER_DRAGON, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.ENDER_DRAGON, SettingsModes.challenge.get(SettingsItems.ItemType.ENDER_DRAGON)));
                            Utils.sendChange("§6Enderdrache", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.ENDER_DRAGON, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.ENDER_DRAGON, SettingsModes.challenge.get(SettingsItems.ItemType.ENDER_DRAGON)));
                            Utils.sendChange("§6Enderdrache", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 11:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.WITHER) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.WITHER, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.WITHER, SettingsModes.challenge.get(SettingsItems.ItemType.WITHER)));
                            Utils.sendChange("§6Wither", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.WITHER, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.WITHER, SettingsModes.challenge.get(SettingsItems.ItemType.WITHER)));
                            Utils.sendChange("§6Wither", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 12:
                        if (SettingsModes.scoreboard.get(SettingsItems.ItemType.TABHP) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.scoreboard.put(SettingsItems.ItemType.TABHP, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.TABHP, SettingsModes.scoreboard.get(SettingsItems.ItemType.TABHP)));
                            Utils.sendChange("§6TABHP", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.scoreboard.put(SettingsItems.ItemType.TABHP, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.TABHP, SettingsModes.scoreboard.get(SettingsItems.ItemType.TABHP)));
                            Utils.sendChange("§6TABHP", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 13:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.HARDCORE) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.settings.put(SettingsItems.ItemType.HARDCORE, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.HARDCORE, SettingsModes.settings.get(SettingsItems.ItemType.HARDCORE)));
                            Utils.sendChange("§6Hardcore", "§7wurde §aaktiviert");
                            for (World wl : Bukkit.getWorlds()) {
                                wl.setHardcore(true);
                            }
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.HARDCORE, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.HARDCORE, SettingsModes.settings.get(SettingsItems.ItemType.HARDCORE)));
                            Utils.sendChange("§6Hardcore", "§7wurde §cdeaktiviert");
                            for (World wl : Bukkit.getWorlds()) {
                                wl.setHardcore(false);
                            }
                        }
                        break;
                    case 14:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.BUNGEECORD) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.settings.put(SettingsItems.ItemType.BUNGEECORD, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.BUNGEECORD, SettingsModes.settings.get(SettingsItems.ItemType.BUNGEECORD)));
                            Utils.sendChange("§6BungeeCord", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.BUNGEECORD, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.BUNGEECORD, SettingsModes.settings.get(SettingsItems.ItemType.BUNGEECORD)));
                            Utils.sendChange("§6BungeeCord", "§7wurde §cdeaktiviert");
                        }
                        break;
                    //zurück
                    case 18:
                        p.openInventory(Settings.getOtherMenu());
                        break;
                }
            }
            //Timer Menu
            else if (e.getView().getTitle().equalsIgnoreCase("Timer Einstellungen")) {
                switch (slot) {
                    case 1:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                            SettingsModes.settings.put(SettingsItems.ItemType.TIMER, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(1, SettingsItems.getMenuItem(SettingsItems.ItemType.TIMER, SettingsModes.settings.get(SettingsItems.ItemType.TIMER)));
                            Utils.sendChange("§6Der Timer", "§cwurde deaktiviert");
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("hi"));
                            }
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.TIMER, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(1, SettingsItems.getMenuItem(SettingsItems.ItemType.TIMER, SettingsModes.settings.get(SettingsItems.ItemType.TIMER)));
                            Utils.sendChange("§6Der Timer", "§awurde aktiviert");
                        }
                        break;
                    case 2:
                        if (e.getClick() == ClickType.LEFT) {
                            Timer.setCurrentTime(Timer.getCurrentTime() + 60);
                            e.getClickedInventory().setItem(2, Settings.TimerChangeTime());
                        } else if (e.getClick() == ClickType.RIGHT) {
                            Timer.setCurrentTime(Timer.getCurrentTime() - 60);
                            e.getClickedInventory().setItem(2, Settings.TimerChangeTime());
                        } else if (e.getClick() == ClickType.SHIFT_LEFT) {
                            Timer.setCurrentTime(Timer.getCurrentTime() + 3600);
                            e.getClickedInventory().setItem(2, Settings.TimerChangeTime());
                        } else if (e.getClick() == ClickType.SHIFT_RIGHT) {
                            Timer.setCurrentTime(Timer.getCurrentTime() - 3600);
                            e.getClickedInventory().setItem(2, Settings.TimerChangeTime());
                        }
                        Utils.sendChange("§6Der Timer", "§7wurde auf " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6") + "§7 gesetzt");
                        break;
                    case 3:
                        if (SettingsModes.timer.get(SettingsItems.ItemType.REVERSE) == SettingsItems.ItemState.ENABLED) {
                            SettingsModes.timer.put(SettingsItems.ItemType.REVERSE, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(3, SettingsItems.getMenuItem(SettingsItems.ItemType.REVERSE, SettingsModes.timer.get(SettingsItems.ItemType.REVERSE)));
                            Utils.sendChange("§6Der Timer", "§7läuft nun §avorwärts");
                        } else {
                            SettingsModes.timer.put(SettingsItems.ItemType.REVERSE, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(3, SettingsItems.getMenuItem(SettingsItems.ItemType.REVERSE, SettingsModes.timer.get(SettingsItems.ItemType.REVERSE)));
                            Utils.sendChange("§6Der Timer", "§7läuft nun §crückwärts");
                        }
                        break;
                    case 4:
                        if (SettingsModes.timer.get(SettingsItems.ItemType.AUTOSTART) == SettingsItems.ItemState.ENABLED) {
                            SettingsModes.timer.put(SettingsItems.ItemType.AUTOSTART, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(4, SettingsItems.getMenuItem(SettingsItems.ItemType.AUTOSTART, SettingsModes.timer.get(SettingsItems.ItemType.AUTOSTART)));
                            Utils.sendChange("§6Automatischer Timer", "§7wurde §cdeaktiviert");
                        } else {
                            SettingsModes.timer.put(SettingsItems.ItemType.AUTOSTART, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(4, SettingsItems.getMenuItem(SettingsItems.ItemType.AUTOSTART, SettingsModes.timer.get(SettingsItems.ItemType.AUTOSTART)));
                            Utils.sendChange("§6Automatischer Timer", "§7wurde §aaktiviert");
                        }

                        break;

                    case 9:
                        p.openInventory(Settings.getOtherMenu());
                        break;
                }
            }
        }

    }
}
