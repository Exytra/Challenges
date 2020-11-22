package me.aaron.timer.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SettingsItems {
    public static ItemStack getMenuItem(ItemType type, ItemState state) {
        ItemStack itemStack = new ItemStack(Material.STONE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        if (type == ItemType.DMGALERT) {
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemMeta.setDisplayName("§6Schaden im Chat");
                itemLore.add(" ");
                itemLore.add("§9Beschreibung:");
                itemLore.add("§7Zeigt den Schaden den");
                itemLore.add("§7Spieler bekommen im Chat an.");
                itemLore.add(" ");
                itemLore.add("§8[§9Klick§8] §7An / Aus");
                itemLore.add(" ");
                itemLore.add("§8[§4Inaktiv§8]");
                itemLore.add(" ");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemMeta.setDisplayName("§6Schaden im Chat");
                itemLore.add(" ");
                itemLore.add("§9Beschreibung:");
                itemLore.add("§7Zeigt den Schaden den");
                itemLore.add("§7Spieler bekommen im Chat an.");
                itemLore.add(" ");
                itemLore.add("§8[§9Klick§8] §7An / Aus");
                itemLore.add(" ");
                itemLore.add("§8[§2Aktiv§8]");
                itemLore.add(" ");
            }
        } else if (type == ItemType.GEITEILTEHERZEN) {
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemMeta.setDisplayName("§8[§4Inaktiv§8]");
            } else if (state == ItemState.ENABLED){
                itemStack.setType(Material.LIME_DYE);
                itemMeta.setDisplayName("§8[§2Aktiv§8]");
            }
        } else if (type == ItemType.RESPAWN) {
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemMeta.setDisplayName("§8[§4Nicht erlaubt§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemMeta.setDisplayName("§8[§2Erlaubt§8]");
            }
        } else if (type == ItemType.ONELIFE) {
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemMeta.setDisplayName("§8[§4Inaktiv§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemMeta.setDisplayName("§8[§2Aktiv§8]");
            }
        } else if (type == ItemType.FLYONDAMAGE) {
            itemStack.setType(Material.FEATHER);
            itemMeta.setDisplayName("§6Bei Schaden in die Luft fliegen");
            if (state == ItemState.DISABLED) {
                itemLore.add(" ");
                itemLore.add("§9Beschreibung:");
                itemLore.add("§7Wenn ein Spieler Schaden bekommt,");
                itemLore.add("§7wird er in die Luft geschleudert.");
                itemLore.add(" ");
                itemLore.add("§8[§9Klick§8] §7An / Aus");
                itemLore.add(" ");
                itemLore.add("§8[§4Inaktiv§8]");
                itemLore.add(" ");
            } else {
                itemLore.add(" ");
                itemLore.add("§9Beschreibung:");
                itemLore.add("§7Wenn ein Spieler Schaden bekommt,");
                itemLore.add("§7wird er in die Luft geschleudert.");
                itemLore.add(" ");
                itemLore.add("§8[§9Klick§8] §7An / Aus");
                itemLore.add(" ");
                itemLore.add("§8[§2Aktiv§8]");
                itemLore.add(" ");
            }
        } else if (type == ItemType.NATURALREGENERATION) {
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemMeta.setDisplayName("§8[§4Deaktiviert§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemMeta.setDisplayName("§8[§2Aktiviert§8]");
            }
        } else if (type == ItemType.TIMER) {
            itemMeta.setDisplayName("§6Timer");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Zeigt am unteren Bildschirmrand");
            itemLore.add("§7einen §9Timer §7an.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiv§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.REVERSE) {
            itemMeta.setDisplayName("§6Rückwärts");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Lässt den Timer vorwärts");
            itemLore.add("§7oder rückwärts laufen.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7Vorwärts / Rückwärts");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.ARROW);
                itemLore.add("§8[§2Vorwärts§8]");
            } else {
                itemStack.setType(Material.SPECTRAL_ARROW);
                itemLore.add("§8[§4Rückwärts§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.SENDTITLE) {
            itemMeta.setDisplayName("§6Titel");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Sendet einen Titel an alle Spieler");
            itemLore.add("§7wenn sich die Einstellungen ändern.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiv§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.AUTOSTART) {
            itemMeta.setDisplayName("§6Automatischer Timer");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Startet den Timer bei einem Player-Join");
            itemLore.add("§7automatisch und stoppt den Timer wenn kein");
            itemLore.add("§7Spieler mehr auf dem Server ist automatisch");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.CRIMSON_STEM);
                itemLore.add("§8[§4Aus§8]");
            } else {
                itemStack.setType(Material.WARPED_STEM);
                itemLore.add("§8[§2An§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.SPEED) {
            itemStack.setType(Material.DIAMOND_BOOTS);
            itemMeta.setDisplayName("§6Speed 30");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Gibt allen Entitäten Speed 30.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemLore.add("§8[§2Aktiv§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.OTHERREGENERATION) {
            itemMeta.setDisplayName("§6Restliche Regeneration");
            itemLore.add(" ");
            itemLore.add("§cErlaubt die unnatürliche Regeneration.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Deaktiviert§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiviert§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.KEEP_INVENTORY) {
            itemMeta.setDisplayName("§6Keep Inventory");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Erlaubt den Spielern nach dem");
            itemLore.add("§7Tod ihr Inventar zu behalten.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Deaktiviert§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiviert§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.SHOWCOORDSONDEAETH) {
            itemMeta.setDisplayName("§6Koordinaten bei Tod");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Zeigt die Koordinaten vom Todesort");
            itemLore.add("§7eines Spielers hinter der Todesnachricht an.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Deaktiviert§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiviert§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.PVP) {
            itemMeta.setDisplayName("§6PVP");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Erlaubt, dass sich Spieler");
            itemLore.add("§7gegenseitig Schaden machen können.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Deaktiviert§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiviert§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.RESETCONFIRM) {
            itemMeta.setDisplayName("§6Reset Confirm");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Benötigt ein nach §9/reset §7ein");
            itemLore.add("§9/reset confirm §7damit die Welten");
            itemLore.add("§7zurückgesetzt werden.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Deaktiviert§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiviert§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.ENDER_DRAGON) {
            itemMeta.setDisplayName("§6Enderdrache");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Die Challenge ist absolviert, wenn");
            itemLore.add("§7der Enderdrache besiegt wird.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Deaktiviert§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiviert§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.WITHER) {
            itemMeta.setDisplayName("§6Wither");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Die Challenge ist absolviert, wenn");
            itemLore.add("§7der Wither besiegt wird.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Deaktiviert§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiviert§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.DIRT) {
            itemStack.setType(Material.DIRT);
            itemMeta.setDisplayName("§6Nur auf Dirt");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Jeder Spieler darf nur auf");
            itemLore.add("§7Dirt stehen.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemLore.add("§8[§2Aktiv§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.TENHEARTS) {
            itemStack.setType(Material.REDSTONE);
            itemMeta.setDisplayName("§6Niemals volle Herzen");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Sobald ein Spieler volle");
            itemLore.add("§7Herzen hat, stirbt er.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemLore.add("§8[§2Aktiv§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.TRAFFICLIGHT) {
            itemStack.setType(Material.SLIME_BALL);
            itemMeta.setDisplayName("§6Ampel Challenge");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Am oberen Bildschrimrand");
            itemLore.add("§7ist eine Ampel, die vorgibt, ob");
            itemLore.add("§7sich die Spieler bewegen dürfen.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemLore.add("§8[§2Aktiv§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.ONEBLOCKONEHEART) {
            itemStack.setType(Material.GRASS_BLOCK);
            itemMeta.setDisplayName("§61 Block = 1 Herz Schaden");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Wenn sich ein Spieler 1 Block");
            itemLore.add("§7bewegt, bekommt er 1 Herz Schaden.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemLore.add("§8[§2Aktiv§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.DAMAGEMIRROR) {
            itemStack.setType(Material.BOW);
            itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
            itemMeta.setDisplayName("§6Gespiegelter Schaden");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Mit einer §a50 % §7Wahrscheinlichkeit");
            itemLore.add("§7wird der Schaden, den ein Spieler");
            itemLore.add("§7einem Mob zufügt, gespiegelt.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemLore.add("§8[§2Aktiv§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.TABHP) {
            itemMeta.setDisplayName("§6TAB-HP");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Zeigt die Leben der Spieler");
            itemLore.add("§7an, wenn man TAB drückt");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiv§8]");
                itemLore.add(" ");
                itemLore.add("§7Falls, die Herzen nicht");
                itemLore.add("§7angezeigt werden, musst Du");
                itemLore.add("§7einmal Schaden nehmen oder");
                itemLore.add("§7reloggen.");
            }
            itemLore.add(" ");
        } else if (type == ItemType.HARDCORE) {
            itemMeta.setDisplayName("§6Hardcore");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Setzt die Schwierigkeit auf");
            itemLore.add("§7Hardcore.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiv§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.FORCEBLOCK) {
            itemStack.setType(Material.DIAMOND_ORE);
            itemMeta.setDisplayName("§6Force-Block");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Gibt den Spielern vor");
            itemLore.add("§7auf welchem Block sie als");
            itemLore.add("§7nächstes stehen müssen.");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemLore.add("§8[§2Aktiv§8]");
            }
            itemLore.add(" ");
        } else if (type == ItemType.BUNGEECORD) {
            itemMeta.setDisplayName("§6BungeeCord");
            itemLore.add(" ");
            itemLore.add("§9Beschreibung:");
            itemLore.add("§7Wenn diese Funktion aktiviert");
            itemLore.add("§7ist, werden die Spieler zum Beispiel");
            itemLore.add("§7Bei einem Reset nicht gekickt sondern");
            itemLore.add("§7zum Server §a\"Hub\" §7weitergeleitet.");
            itemLore.add(" ");
            itemLore.add("§7Funktioniert wenn ein BungeeCord-Netzwerk");
            itemLore.add("§7mit einem Server §a\"Hub\" §7 existiert");
            itemLore.add(" ");
            itemLore.add("§8[§9Klick§8] §7An / Aus");
            itemLore.add(" ");
            if (state == ItemState.DISABLED) {
                itemStack.setType(Material.RED_DYE);
                itemLore.add("§8[§4Inaktiv§8]");
            } else {
                itemStack.setType(Material.LIME_DYE);
                itemLore.add("§8[§2Aktiv§8]");
            }
            itemLore.add(" ");
        }

        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


    public enum ItemType {
        // settings
        DMGALERT,
        TIMER,
        BACKPACK,
        SCOREBOARD,
        GEITEILTEHERZEN,
        RESPAWN,
        ONELIFE,
        SENDTITLE,
        SHOWCOORDSONDEAETH,
        RESETCONFIRM,
        HARDCORE,
        CANCELLDAMAGE,
        BUNGEECORD,

        // timer
        RESUME,
        REVERSE,
        AUTOSTART,

        // scoreboard
        TABHP,

        // gamerules
        NATURALREGENERATION,
        OTHERREGENERATION,
        PVP,
        KEEP_INVENTORY,

        // challenges
        WITHER,
        ENDER_DRAGON,
        FLYONDAMAGE,
        SPEED,
        DIRT,
        TENHEARTS,
        TRAFFICLIGHT,
        ONEBLOCKONEHEART,
        DAMAGEMIRROR,
        FORCEBLOCK,
    }

    public enum ItemState {
        DISABLED,
        ENABLED;
    }
}
