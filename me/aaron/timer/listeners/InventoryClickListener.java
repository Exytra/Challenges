package me.aaron.timer.listeners;

import me.aaron.timer.Main;
import me.aaron.timer.challenges.*;
import me.aaron.timer.utils.*;
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

        if(e.getView().getTitle().equalsIgnoreCase(Settings.getMenuName()) || e.getView().getTitle().equalsIgnoreCase("Lebenseinstellungen") || e.getView().getTitle().equalsIgnoreCase("Challenges §7» §8Seite 1") || e.getView().getTitle().equalsIgnoreCase("Challenges §7» §8Seite 2") || e.getView().getTitle().equalsIgnoreCase(Settings.getOtherMenuName()) || e.getView().getTitle().equalsIgnoreCase("Timer Einstellungen") || e.getView().getTitle().equalsIgnoreCase("Restliche Einstellungen §7» §8Seite 2") || e.getView().getTitle().equalsIgnoreCase("Restliche Einstellungen §7» §8Seite 3") || e.getView().getTitle().equalsIgnoreCase("Projekte") || e.getView().getTitle().contains("InvSee:") || e.getView().getTitle().contains("zurücksetzen?")) {
            e.setCancelled(true);
            String title = e.getView().getTitle();
            if(e.getCurrentItem().isSimilar(Settings.Health())) { p.openInventory(Settings.getHealthMenu()); }

            else if (e.getCurrentItem().isSimilar(Settings.ChallengesSettings())) { p.openInventory(Settings.getChallengesMenu()); }

            else if (e.getCurrentItem().isSimilar(Settings.Projects())) { p.openInventory(Settings.ProjectMenu()); }

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
            } else if (e.getView().getTitle().equalsIgnoreCase("Challenges §7» §8Seite 1")) {
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
                    case 20:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.BEDROCKWALL) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.BEDROCKWALL, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.BEDROCKWALL, SettingsModes.challenge.get(SettingsItems.ItemType.BEDROCKWALL)));
                            Utils.sendChange("§6Bedrock-Wand", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.BEDROCKWALL, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.BEDROCKWALL, SettingsModes.challenge.get(SettingsItems.ItemType.BEDROCKWALL)));
                            Utils.sendChange("§6Bedrock-Wand", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 21:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.THEFLOORISLAVA) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.THEFLOORISLAVA, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.THEFLOORISLAVA, SettingsModes.challenge.get(SettingsItems.ItemType.THEFLOORISLAVA)));
                            Utils.sendChange("§6Der Boden ist Lava", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.THEFLOORISLAVA, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.THEFLOORISLAVA, SettingsModes.challenge.get(SettingsItems.ItemType.THEFLOORISLAVA)));
                            Utils.sendChange("§6Der Boden ist Lava", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 22:
                        ForceMob forceMob = new ForceMob(Main.getInstance());
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.FORCEMOB) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.FORCEMOB, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.FORCEMOB, SettingsModes.challenge.get(SettingsItems.ItemType.FORCEMOB)));
                            Utils.sendChange("§6Force-Mob", "§7wurde §aaktiviert");
                            forceMob.start();
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.FORCEMOB, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.FORCEMOB, SettingsModes.challenge.get(SettingsItems.ItemType.FORCEMOB)));
                            Utils.sendChange("§6Force-Mob", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 23:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.NO_CRAFTING) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.NO_CRAFTING, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.NO_CRAFTING, SettingsModes.challenge.get(SettingsItems.ItemType.NO_CRAFTING)));
                            Utils.sendChange("§6Kein Craften", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.NO_CRAFTING, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.NO_CRAFTING, SettingsModes.challenge.get(SettingsItems.ItemType.NO_CRAFTING)));
                            Utils.sendChange("§6Kein Craften", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 24:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.NO_TRADING) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.NO_TRADING, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.NO_TRADING, SettingsModes.challenge.get(SettingsItems.ItemType.NO_TRADING)));
                            Utils.sendChange("§6Kein Traden", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.NO_TRADING, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.NO_TRADING, SettingsModes.challenge.get(SettingsItems.ItemType.NO_TRADING)));
                            Utils.sendChange("§6Kein Traden", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 25:
                        ForceHeight forceHeight = new ForceHeight();
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.FORCE_HEIGHT) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.FORCE_HEIGHT, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.FORCE_HEIGHT, SettingsModes.challenge.get(SettingsItems.ItemType.FORCE_HEIGHT)));
                            forceHeight.start();
                            Utils.sendChange("§6Force Height", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.challenge.put(SettingsItems.ItemType.FORCE_HEIGHT, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.FORCE_HEIGHT, SettingsModes.challenge.get(SettingsItems.ItemType.FORCE_HEIGHT)));
                            Utils.sendChange("§6Force Height", "§7wurde §cdeaktiviert");
                        }
                        break;

                    //zurück
                    case 27:
                        p.openInventory(Settings.getMenu());
                        break;
                    case 35:
                        p.openInventory(Settings.getChallengesMenu2());
                        break;
                }
            }
            //Challenges Seite 2
            else if (e.getView().getTitle().equalsIgnoreCase("Challenges §7» §8Seite 2")) {
                switch (slot) {
                    case 10:
                        if (SettingsModes.challenge.get(SettingsItems.ItemType.FORCE_BIOME) == SettingsItems.ItemState.ENABLED) {
                            SettingsModes.challenge.put(SettingsItems.ItemType.FORCE_BIOME, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.FORCE_BIOME, SettingsModes.challenge.get(SettingsItems.ItemType.FORCE_BIOME)));
                            Utils.sendChange("§6Force Biome", "§7wurde §cdeaktiviert");
                        } else {
                            ForceBiome forceBiome = new ForceBiome();
                            SettingsModes.challenge.put(SettingsItems.ItemType.FORCE_BIOME, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.FORCE_BIOME, SettingsModes.challenge.get(SettingsItems.ItemType.FORCE_BIOME)));
                            Utils.sendChange("§6Force Biome", "§7wurde §aaktiviert");
                            forceBiome.start();
                        }
                        break;
                    case 27:
                        p.openInventory(Settings.getChallengesMenu());
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
                    case 15:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.BACKUP) == SettingsItems.ItemState.DISABLED) {
                            if (e.getClick().equals(ClickType.LEFT)) {
                                SettingsModes.settings.put(SettingsItems.ItemType.BACKUP, SettingsItems.ItemState.ENABLED);
                                SettingsModes.ints.put(SettingsItems.ItemType.BACKUP, 1);
                                e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.BACKUP, SettingsModes.settings.get(SettingsItems.ItemType.BACKUP)));
                                Utils.sendChange("§6Backup", "§6jede Stunde");
                            } else {
                                return;
                            }
                        } else if (SettingsModes.ints.get(SettingsItems.ItemType.BACKUP) == 1) {
                            if (e.getClick().equals(ClickType.LEFT)) {
                                SettingsModes.ints.put(SettingsItems.ItemType.BACKUP, 2);
                                e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.BACKUP, SettingsModes.settings.get(SettingsItems.ItemType.BACKUP)));
                                Utils.sendChange("§6Backup", "§7alle §6" + SettingsModes.ints.get(SettingsItems.ItemType.BACKUP) + " Stunden");
                            } else if (e.getClick().equals(ClickType.RIGHT)) {
                                SettingsModes.settings.put(SettingsItems.ItemType.BACKUP, SettingsItems.ItemState.DISABLED);
                                SettingsModes.ints.put(SettingsItems.ItemType.BACKUP, 0);
                                e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.BACKUP, SettingsModes.settings.get(SettingsItems.ItemType.BACKUP)));
                                Utils.sendChange("§6Backup", "§7wurde §cdeaktiviert");
                            }
                        } else {
                            if (e.getClick().equals(ClickType.LEFT)) {
                                SettingsModes.ints.put(SettingsItems.ItemType.BACKUP, SettingsModes.ints.get(SettingsItems.ItemType.BACKUP) + 1);
                                e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.BACKUP, SettingsModes.settings.get(SettingsItems.ItemType.BACKUP)));
                                if (SettingsModes.ints.get(SettingsItems.ItemType.BACKUP) == 1) {
                                    Utils.sendChange("§6Backup", "§6jede Stunde");
                                } else {
                                    Utils.sendChange("§6Backup", "§7alle §6" + SettingsModes.ints.get(SettingsItems.ItemType.BACKUP) + " Stunden");
                                }
                            } else if (e.getClick().equals(ClickType.RIGHT)) {
                                SettingsModes.ints.put(SettingsItems.ItemType.BACKUP, SettingsModes.ints.get(SettingsItems.ItemType.BACKUP) - 1);
                                e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.BACKUP, SettingsModes.settings.get(SettingsItems.ItemType.BACKUP)));
                                if (SettingsModes.ints.get(SettingsItems.ItemType.BACKUP) == 1) {
                                    Utils.sendChange("§6Backup", "§6jede Stunde");
                                } else {
                                    Utils.sendChange("§6Backup", "§7alle §6" + SettingsModes.ints.get(SettingsItems.ItemType.BACKUP) + " Stunden");
                                }
                            }
                        }
                        break;
                    case 16:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.AFK) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.settings.put(SettingsItems.ItemType.AFK, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.AFK, SettingsModes.settings.get(SettingsItems.ItemType.AFK)));
                            Utils.sendChange("§6AFK-Detector", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.AFK, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.AFK, SettingsModes.settings.get(SettingsItems.ItemType.AFK)));
                            Utils.sendChange("§6AFK-Detector", "§7wurde §cdeaktiviert");
                        }
                        break;
                    //zurück
                    case 18:
                        p.openInventory(Settings.getOtherMenu());
                        break;
                    case 26:
                        p.openInventory(Settings.getOtherMenu3());
                        break;
                }
            } else if (e.getView().getTitle().equalsIgnoreCase("Restliche Einstellungen §7» §8Seite 3")) {
                switch (slot) {
                    case 10:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.BACKPACK) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.settings.put(SettingsItems.ItemType.BACKPACK, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.BACKPACK, SettingsModes.settings.get(SettingsItems.ItemType.BACKPACK)));
                            Utils.sendChange("§6Backpack", "§7wurde §aaktiviert");
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.BACKPACK, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.BACKPACK, SettingsModes.settings.get(SettingsItems.ItemType.BACKPACK)));
                            Utils.sendChange("§6Backpack", "§7wurde §cdeaktiviert");
                        }
                        break;
                    case 11:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.STATS) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.settings.put(SettingsItems.ItemType.STATS, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.STATS, SettingsModes.settings.get(SettingsItems.ItemType.STATS)));
                            Utils.sendChange("§6Stats", "§7wurden §aaktiviert");
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.STATS, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.STATS, SettingsModes.settings.get(SettingsItems.ItemType.STATS)));
                            Utils.sendChange("§6Stats", "§7wurden §cdeaktiviert");
                        }
                        break;
                    case 18:
                        p.openInventory(Settings.getOtherMenu2());
                        break;
                }
            }
            //Timer Menu
            else if (e.getView().getTitle().equalsIgnoreCase("Timer Einstellungen")) {
                switch (slot) {
                    case 11:
                        if (e.getClick() == ClickType.LEFT && !(Timer.getCurrentTime() + 3600 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() + 3600);
                        } else if (e.getClick() == ClickType.SHIFT_LEFT && !(Timer.getCurrentTime() + 3600 * 10 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() + 3600 * 10);
                        }
                        Utils.sendChange("§6Der Timer", "§7wurde auf " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6") + "§7 gesetzt");
                        Timer.firststart = false;
                        e.getClickedInventory().setItem(11, Settings.ButtonPlus("hours"));
                        e.getClickedInventory().setItem(13, Settings.ButtonPlus("min"));
                        e.getClickedInventory().setItem(15, Settings.ButtonPlus("sec"));
                        e.getClickedInventory().setItem(20, Settings.Clock("hours"));
                        e.getClickedInventory().setItem(22, Settings.Clock("min"));
                        e.getClickedInventory().setItem(24, Settings.Clock("sec"));
                        e.getClickedInventory().setItem(29, Settings.ButtonMinus("hours"));
                        e.getClickedInventory().setItem(31, Settings.ButtonMinus("min"));
                        e.getClickedInventory().setItem(33, Settings.ButtonMinus("sec"));
                        break;
                    case 13:
                        if (e.getClick() == ClickType.LEFT && !(Timer.getCurrentTime() + 60 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() + 60);
                        } else if (e.getClick() == ClickType.SHIFT_LEFT && !(Timer.getCurrentTime() + 60 * 10 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() + 60 * 10);
                        }
                        Utils.sendChange("§6Der Timer", "§7wurde auf " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6") + "§7 gesetzt");
                        Timer.firststart = false;
                        e.getClickedInventory().setItem(11, Settings.ButtonPlus("hours"));
                        e.getClickedInventory().setItem(13, Settings.ButtonPlus("min"));
                        e.getClickedInventory().setItem(15, Settings.ButtonPlus("sec"));
                        e.getClickedInventory().setItem(20, Settings.Clock("hours"));
                        e.getClickedInventory().setItem(22, Settings.Clock("min"));
                        e.getClickedInventory().setItem(24, Settings.Clock("sec"));
                        e.getClickedInventory().setItem(29, Settings.ButtonMinus("hours"));
                        e.getClickedInventory().setItem(31, Settings.ButtonMinus("min"));
                        e.getClickedInventory().setItem(33, Settings.ButtonMinus("sec"));
                        break;
                    case 15:
                        if (e.getClick() == ClickType.LEFT && !(Timer.getCurrentTime() + 1 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() + 1);
                        } else if (e.getClick() == ClickType.SHIFT_LEFT && !(Timer.getCurrentTime() + 10 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() + 10);
                        }
                        Utils.sendChange("§6Der Timer", "§7wurde auf " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6") + "§7 gesetzt");
                        Timer.firststart = false;
                        e.getClickedInventory().setItem(11, Settings.ButtonPlus("hours"));
                        e.getClickedInventory().setItem(13, Settings.ButtonPlus("min"));
                        e.getClickedInventory().setItem(15, Settings.ButtonPlus("sec"));
                        e.getClickedInventory().setItem(20, Settings.Clock("hours"));
                        e.getClickedInventory().setItem(22, Settings.Clock("min"));
                        e.getClickedInventory().setItem(24, Settings.Clock("sec"));
                        e.getClickedInventory().setItem(29, Settings.ButtonMinus("hours"));
                        e.getClickedInventory().setItem(31, Settings.ButtonMinus("min"));
                        e.getClickedInventory().setItem(33, Settings.ButtonMinus("sec"));
                        break;
                    case 29:
                        if (e.getClick() == ClickType.LEFT && !(Timer.getCurrentTime() - 3600 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() - 3600);
                        } else if (e.getClick() == ClickType.SHIFT_LEFT && !(Timer.getCurrentTime() - 3600 * 10 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() - 3600 * 10);
                        }
                        Utils.sendChange("§6Der Timer", "§7wurde auf " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6") + "§7 gesetzt");
                        Timer.firststart = false;
                        e.getClickedInventory().setItem(11, Settings.ButtonPlus("hours"));
                        e.getClickedInventory().setItem(13, Settings.ButtonPlus("min"));
                        e.getClickedInventory().setItem(15, Settings.ButtonPlus("sec"));
                        e.getClickedInventory().setItem(20, Settings.Clock("hours"));
                        e.getClickedInventory().setItem(22, Settings.Clock("min"));
                        e.getClickedInventory().setItem(24, Settings.Clock("sec"));
                        e.getClickedInventory().setItem(29, Settings.ButtonMinus("hours"));
                        e.getClickedInventory().setItem(31, Settings.ButtonMinus("min"));
                        e.getClickedInventory().setItem(33, Settings.ButtonMinus("sec"));
                        break;
                    case 31:
                        if (e.getClick() == ClickType.LEFT && !(Timer.getCurrentTime() - 60 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() - 60);
                        } else if (e.getClick() == ClickType.SHIFT_LEFT && !(Timer.getCurrentTime() - 60 * 10 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() - 60 * 10);
                        }
                        Utils.sendChange("§6Der Timer", "§7wurde auf " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6") + "§7 gesetzt");
                        Timer.firststart = false;
                        e.getClickedInventory().setItem(11, Settings.ButtonPlus("hours"));
                        e.getClickedInventory().setItem(13, Settings.ButtonPlus("min"));
                        e.getClickedInventory().setItem(15, Settings.ButtonPlus("sec"));
                        e.getClickedInventory().setItem(20, Settings.Clock("hours"));
                        e.getClickedInventory().setItem(22, Settings.Clock("min"));
                        e.getClickedInventory().setItem(24, Settings.Clock("sec"));
                        e.getClickedInventory().setItem(29, Settings.ButtonMinus("hours"));
                        e.getClickedInventory().setItem(31, Settings.ButtonMinus("min"));
                        e.getClickedInventory().setItem(33, Settings.ButtonMinus("sec"));
                        break;
                    case 33:
                        if (e.getClick() == ClickType.LEFT && !(Timer.getCurrentTime() - 1 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() - 1);
                        } else if (e.getClick() == ClickType.SHIFT_LEFT && !(Timer.getCurrentTime() - 10 < 0)) {
                            Timer.setCurrentTime(Timer.getCurrentTime() - 10);
                        }
                        Utils.sendChange("§6Der Timer", "§7wurde auf " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6") + "§7 gesetzt");
                        Timer.firststart = false;
                        e.getClickedInventory().setItem(11, Settings.ButtonPlus("hours"));
                        e.getClickedInventory().setItem(13, Settings.ButtonPlus("min"));
                        e.getClickedInventory().setItem(15, Settings.ButtonPlus("sec"));
                        e.getClickedInventory().setItem(20, Settings.Clock("hours"));
                        e.getClickedInventory().setItem(22, Settings.Clock("min"));
                        e.getClickedInventory().setItem(24, Settings.Clock("sec"));
                        e.getClickedInventory().setItem(29, Settings.ButtonMinus("hours"));
                        e.getClickedInventory().setItem(31, Settings.ButtonMinus("min"));
                        e.getClickedInventory().setItem(33, Settings.ButtonMinus("sec"));
                        break;
                    case 47:
                        if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                            SettingsModes.settings.put(SettingsItems.ItemType.TIMER, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.TIMER, SettingsModes.settings.get(SettingsItems.ItemType.TIMER)));
                            Utils.sendChange("§6Der Timer", "§cwurde deaktiviert");
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("hi"));
                            }
                        } else {
                            SettingsModes.settings.put(SettingsItems.ItemType.TIMER, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.TIMER, SettingsModes.settings.get(SettingsItems.ItemType.TIMER)));
                            Utils.sendChange("§6Der Timer", "§awurde aktiviert");
                        }
                        break;
                    case 49:
                        if (SettingsModes.timer.get(SettingsItems.ItemType.REVERSE) == SettingsItems.ItemState.ENABLED) {
                            SettingsModes.timer.put(SettingsItems.ItemType.REVERSE, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.REVERSE, SettingsModes.timer.get(SettingsItems.ItemType.REVERSE)));
                            Utils.sendChange("§6Der Timer", "§7läuft nun §avorwärts");
                        } else {
                            SettingsModes.timer.put(SettingsItems.ItemType.REVERSE, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.REVERSE, SettingsModes.timer.get(SettingsItems.ItemType.REVERSE)));
                            Utils.sendChange("§6Der Timer", "§7läuft nun §crückwärts");
                        }
                        break;
                    case 51:
                        if (SettingsModes.timer.get(SettingsItems.ItemType.AUTOSTART) == SettingsItems.ItemState.ENABLED) {
                            SettingsModes.timer.put(SettingsItems.ItemType.AUTOSTART, SettingsItems.ItemState.DISABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.AUTOSTART, SettingsModes.timer.get(SettingsItems.ItemType.AUTOSTART)));
                            Utils.sendChange("§6Automatischer Timer", "§7wurde §cdeaktiviert");
                        } else {
                            SettingsModes.timer.put(SettingsItems.ItemType.AUTOSTART, SettingsItems.ItemState.ENABLED);
                            e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.AUTOSTART, SettingsModes.timer.get(SettingsItems.ItemType.AUTOSTART)));
                            Utils.sendChange("§6Automatischer Timer", "§7wurde §aaktiviert");
                        }
                        break;
                    case 45:
                        p.openInventory(Settings.getOtherMenu());
                        break;
                }
            } else if (e.getView().getTitle().equalsIgnoreCase("Projekte")) {
                switch (slot) {
                    case 10:
                        if (e.getClick() == ClickType.LEFT) {
                            if (SettingsModes.projects.get(SettingsItems.ItemType.ALL_ITEMS) == SettingsItems.ItemState.ENABLED) {
                                SettingsModes.projects.put(SettingsItems.ItemType.ALL_ITEMS, SettingsItems.ItemState.DISABLED);
                                e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.ALL_ITEMS, SettingsModes.projects.get(SettingsItems.ItemType.ALL_ITEMS)));
                                Utils.sendChange("§6Alle Items", "§7wurde §cdeaktiviert");
                            } else {
                                SettingsModes.projects.put(SettingsItems.ItemType.ALL_ITEMS, SettingsItems.ItemState.ENABLED);
                                e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.ALL_ITEMS, SettingsModes.projects.get(SettingsItems.ItemType.ALL_ITEMS)));
                                Utils.sendChange("§6Alle Items", "§7wurde §aaktiviert");
                            }
                        } else if (e.getClick() == ClickType.RIGHT) {
                            p.openInventory(Settings.resetPrompt("Alle Items"));
                        }
                        break;
                    case 11:
                        if (e.getClick() == ClickType.LEFT) {
                            if (SettingsModes.projects.get(SettingsItems.ItemType.ALL_MOBS) == SettingsItems.ItemState.ENABLED) {
                                SettingsModes.projects.put(SettingsItems.ItemType.ALL_MOBS, SettingsItems.ItemState.DISABLED);
                                e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.ALL_MOBS, SettingsModes.projects.get(SettingsItems.ItemType.ALL_MOBS)));
                                Utils.sendChange("§6Alle Mobs", "§7wurde §cdeaktiviert");
                            } else {
                                SettingsModes.projects.put(SettingsItems.ItemType.ALL_MOBS, SettingsItems.ItemState.ENABLED);
                                e.getClickedInventory().setItem(slot, SettingsItems.getMenuItem(SettingsItems.ItemType.ALL_MOBS, SettingsModes.projects.get(SettingsItems.ItemType.ALL_MOBS)));
                                Utils.sendChange("§6Alle Mobs", "§7wurde §aaktiviert");
                            }
                        } else if (e.getClick() == ClickType.RIGHT) {
                            p.openInventory(Settings.resetPrompt("Alle Mobs"));
                        }
                        break;
                    case 27:
                        p.openInventory(Settings.getMenu());
                        break;
                }
            } else if (e.getView().getTitle().contains("zurücksetzen?")) {
                switch (slot) {
                    case 11:
                        if (title.contains("Alle Items")) {
                            Config.resetProject(SettingsItems.ItemType.ALL_ITEMS);
                            Utils.sendChange("§6Alle Items sammeln", "§7wurde §czurückgesetzt");
                        } else if (title.contains("Alle Mobs")) {
                            Config.resetProject(SettingsItems.ItemType.ALL_MOBS);
                            Utils.sendChange("§6Alle Mobs töten", "§7wurde §czurückgesetzt");
                        }
                        p.closeInventory();
                        break;
                    case 15:
                        p.openInventory(Settings.ProjectMenu());
                }
            }
        }

    }
}
