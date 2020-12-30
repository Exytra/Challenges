package me.aaron.timer.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Settings {
    public static Inventory getMenu() {
        Inventory Settings = Bukkit.createInventory(null, 27, "Settings");
        Utils.fillWithGlass(Settings);

        Settings.setItem(10, ChallengesSettings());
        Settings.setItem(12, Health());
        Settings.setItem(14, Projects());
        Settings.setItem(16, Other());

        return Settings;
    }

    public static Inventory getHealthMenu() {
        Inventory HealthSettings = Bukkit.createInventory(null, 27, "Lebenseinstellungen");
        Utils.fillWithGlass(HealthSettings);

        HealthSettings.setItem(1, GeteilteHerzen());
        HealthSettings.setItem(2, AllowRespawn());
        HealthSettings.setItem(3, EinLebenFurAlle());
        HealthSettings.setItem(5, MaxHealth());
        HealthSettings.setItem(6, NatrualRegeneration());
        HealthSettings.setItem(7, OtherRegeneration());
        HealthSettings.setItem(10, SettingsItems.getMenuItem(SettingsItems.ItemType.GEITEILTEHERZEN, SettingsModes.settings.get(SettingsItems.ItemType.GEITEILTEHERZEN)));
        HealthSettings.setItem(11, SettingsItems.getMenuItem(SettingsItems.ItemType.RESPAWN, SettingsModes.settings.get(SettingsItems.ItemType.RESPAWN)));
        HealthSettings.setItem(12, SettingsItems.getMenuItem(SettingsItems.ItemType.ONELIFE, SettingsModes.settings.get(SettingsItems.ItemType.ONELIFE)));
        HealthSettings.setItem(14, MaxHealthButton());
        HealthSettings.setItem(15, SettingsItems.getMenuItem(SettingsItems.ItemType.NATURALREGENERATION, SettingsModes.gamerule.get(SettingsItems.ItemType.NATURALREGENERATION)));
        HealthSettings.setItem(16, SettingsItems.getMenuItem(SettingsItems.ItemType.OTHERREGENERATION, SettingsModes.gamerule.get(SettingsItems.ItemType.OTHERREGENERATION)));
        HealthSettings.setItem(18, Back());

        return HealthSettings;
    }

    public static Inventory getChallengesMenu() {
        Inventory inv = Bukkit.createInventory(null, 36, "Challenges");
        Utils.fillWithGlass(inv);

        inv.setItem(10, SettingsItems.getMenuItem(SettingsItems.ItemType.FLYONDAMAGE, SettingsModes.challenge.get(SettingsItems.ItemType.FLYONDAMAGE)));
        inv.setItem(11, SettingsItems.getMenuItem(SettingsItems.ItemType.SPEED, SettingsModes.challenge.get(SettingsItems.ItemType.SPEED)));
        inv.setItem(12, SettingsItems.getMenuItem(SettingsItems.ItemType.DIRT, SettingsModes.challenge.get(SettingsItems.ItemType.DIRT)));
        inv.setItem(13, SettingsItems.getMenuItem(SettingsItems.ItemType.TENHEARTS, SettingsModes.challenge.get(SettingsItems.ItemType.TENHEARTS)));
        inv.setItem(14, SettingsItems.getMenuItem(SettingsItems.ItemType.TRAFFICLIGHT, SettingsModes.challenge.get(SettingsItems.ItemType.TRAFFICLIGHT)));
        inv.setItem(15, SettingsItems.getMenuItem(SettingsItems.ItemType.ONEBLOCKONEHEART, SettingsModes.challenge.get(SettingsItems.ItemType.ONEBLOCKONEHEART)));
        inv.setItem(16, SettingsItems.getMenuItem(SettingsItems.ItemType.DAMAGEMIRROR, SettingsModes.challenge.get(SettingsItems.ItemType.DAMAGEMIRROR)));
        inv.setItem(19, SettingsItems.getMenuItem(SettingsItems.ItemType.FORCEBLOCK, SettingsModes.challenge.get(SettingsItems.ItemType.FORCEBLOCK)));
        inv.setItem(20, SettingsItems.getMenuItem(SettingsItems.ItemType.BEDROCKWALL, SettingsModes.challenge.get(SettingsItems.ItemType.BEDROCKWALL)));
        inv.setItem(21, SettingsItems.getMenuItem(SettingsItems.ItemType.THEFLOORISLAVA, SettingsModes.challenge.get(SettingsItems.ItemType.THEFLOORISLAVA)));
        inv.setItem(22, SettingsItems.getMenuItem(SettingsItems.ItemType.FORCEMOB, SettingsModes.challenge.get(SettingsItems.ItemType.FORCEMOB)));
        inv.setItem(23, SettingsItems.getMenuItem(SettingsItems.ItemType.NO_CRAFTING, SettingsModes.challenge.get(SettingsItems.ItemType.NO_CRAFTING)));
        inv.setItem(24, SettingsItems.getMenuItem(SettingsItems.ItemType.NO_TRADING, SettingsModes.challenge.get(SettingsItems.ItemType.NO_TRADING)));

        inv.setItem(27, Back());

        return inv;
    }

    public static Inventory getOtherMenu() {
        Inventory inv = Bukkit.createInventory(null, 27, "Restliche Einstellungen §7» §8Seite 1");
        Utils.fillWithGlass(inv);

        inv.setItem(1, TimerSettings());
        inv.setItem(2, SendTitle());
        inv.setItem(3, SendDamageMessage());
        inv.setItem(4, KeepInventorySettings());
        inv.setItem(5, ShowCoordsOnDeath());
        inv.setItem(6, PVP());
        inv.setItem(7, NeedResetConfirm());

        inv.setItem(10, SettingsItems.getMenuItem(SettingsItems.ItemType.TIMER, SettingsModes.settings.get(SettingsItems.ItemType.TIMER)));
        inv.setItem(11, SettingsItems.getMenuItem(SettingsItems.ItemType.SENDTITLE, SettingsModes.settings.get(SettingsItems.ItemType.SENDTITLE)));
        inv.setItem(12, SettingsItems.getMenuItem(SettingsItems.ItemType.DMGALERT, SettingsModes.settings.get(SettingsItems.ItemType.DMGALERT)));
        inv.setItem(13, SettingsItems.getMenuItem(SettingsItems.ItemType.KEEP_INVENTORY, SettingsModes.gamerule.get(SettingsItems.ItemType.KEEP_INVENTORY)));
        inv.setItem(14, SettingsItems.getMenuItem(SettingsItems.ItemType.SHOWCOORDSONDEAETH, SettingsModes.settings.get(SettingsItems.ItemType.SHOWCOORDSONDEAETH)));
        inv.setItem(15, SettingsItems.getMenuItem(SettingsItems.ItemType.PVP, SettingsModes.gamerule.get(SettingsItems.ItemType.PVP)));
        inv.setItem(16, SettingsItems.getMenuItem(SettingsItems.ItemType.RESETCONFIRM, SettingsModes.settings.get(SettingsItems.ItemType.RESETCONFIRM)));

        inv.setItem(26, Next());
        inv.setItem(18, Back());

        return inv;
    }

    public static Inventory getOtherMenu2() {
        Inventory inv = Bukkit.createInventory(null, 27, "Restliche Einstellungen §7» §8Seite 2");
        Utils.fillWithGlass(inv);

        inv.setItem(1, Enderdragon());
        inv.setItem(2, Wither());
        inv.setItem(3, TABHP());
        inv.setItem(4, Hardcore());
        inv.setItem(5, BUNGEECORD());
        inv.setItem(6, Backup());
        inv.setItem(7, AFK());

        inv.setItem(10, SettingsItems.getMenuItem(SettingsItems.ItemType.ENDER_DRAGON, SettingsModes.challenge.get(SettingsItems.ItemType.ENDER_DRAGON)));
        inv.setItem(11, SettingsItems.getMenuItem(SettingsItems.ItemType.WITHER, SettingsModes.challenge.get(SettingsItems.ItemType.WITHER)));
        inv.setItem(12, SettingsItems.getMenuItem(SettingsItems.ItemType.TABHP, SettingsModes.scoreboard.get(SettingsItems.ItemType.TABHP)));
        inv.setItem(13, SettingsItems.getMenuItem(SettingsItems.ItemType.HARDCORE, SettingsModes.settings.get(SettingsItems.ItemType.HARDCORE)));
        inv.setItem(14, SettingsItems.getMenuItem(SettingsItems.ItemType.BUNGEECORD, SettingsModes.settings.get(SettingsItems.ItemType.BUNGEECORD)));
        inv.setItem(15, SettingsItems.getMenuItem(SettingsItems.ItemType.BACKUP, SettingsModes.settings.get(SettingsItems.ItemType.BACKUP)));
        inv.setItem(16, SettingsItems.getMenuItem(SettingsItems.ItemType.AFK, SettingsModes.settings.get(SettingsItems.ItemType.AFK)));

        inv.setItem(18, Back());

        return inv;
    }

    public static Inventory TimerMenu() {
        Inventory inv = Bukkit.createInventory(null, 54, "Timer Einstellungen");
        Utils.fillWithGlass(inv);

        inv.setItem(11, ButtonPlus("hours"));
        inv.setItem(20, Clock("hours"));
        inv.setItem(29, ButtonMinus("hours"));
        inv.setItem(13, ButtonPlus("min"));
        inv.setItem(22, Clock("min"));
        inv.setItem(31, ButtonMinus("min"));
        inv.setItem(15, ButtonPlus("sec"));
        inv.setItem(24, Clock("sec"));
        inv.setItem(33, ButtonMinus("sec"));
        inv.setItem(47, SettingsItems.getMenuItem(SettingsItems.ItemType.TIMER, SettingsModes.settings.get(SettingsItems.ItemType.TIMER)));
        inv.setItem(49, SettingsItems.getMenuItem(SettingsItems.ItemType.REVERSE, SettingsModes.timer.get(SettingsItems.ItemType.REVERSE)));
        inv.setItem(51, SettingsItems.getMenuItem(SettingsItems.ItemType.AUTOSTART, SettingsModes.timer.get(SettingsItems.ItemType.AUTOSTART)));

        inv.setItem(45, Back());

        return inv;
    }

    public static Inventory ProjectMenu() {
        Inventory inv = Bukkit.createInventory(null, 36, "Projekte");
        Utils.fillWithGlass(inv);

        inv.setItem(10, SettingsItems.getMenuItem(SettingsItems.ItemType.ALL_ITEMS, SettingsModes.projects.get(SettingsItems.ItemType.ALL_ITEMS)));
        inv.setItem(11, SettingsItems.getMenuItem(SettingsItems.ItemType.ALL_MOBS, SettingsModes.projects.get(SettingsItems.ItemType.ALL_MOBS)));

        inv.setItem(27, Back());

        return inv;
    }

    public static ItemStack TABHP() {
        ItemStack itemStack = new ItemStack(Material.POPPY);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6TAB-HP");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Zeigt die Leben der Spieler");
        itemLore.add("§7an, wenn man TAB drückt");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7An / Aus");
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack AFK() {
        ItemStack itemStack = new ItemStack(Material.DEAD_TUBE_CORAL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6AFK");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Wenn ein Spieler AFK ist,");
        itemLore.add("§7steht AFK neben seinem Namen.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7An / Aus");
        itemLore.add(" ");
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack Clock(String type) {
        ItemStack itemStack = new ItemStack(Material.CLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        switch (type) {
            case "sec":
                itemMeta.setDisplayName("§6Sekunden");
                break;
            case "min":
                itemMeta.setDisplayName("§6Minuten");
                break;
            case "hours":
                itemMeta.setDisplayName("§6Stunden");
                break;
        }
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Ändert die Zeit des Timers.");
        itemLore.add(" ");
        itemLore.add("§7Momentan: " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6§l"));
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack ButtonPlus(String type) {
        ItemStack itemStack = new ItemStack(Material.OAK_BUTTON);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        switch (type) {
            case "sec":
                itemMeta.setDisplayName("§6Sekunden");
                break;
            case "min":
                itemMeta.setDisplayName("§6Minuten");
                break;
            case "hours":
                itemMeta.setDisplayName("§6Stunden");
                break;
        }
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Ändert die Zeit des Timers.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7+ 1");
        itemLore.add("§8[§9Shift-Klick§8] §7+ 10");
        itemLore.add(" ");
        itemLore.add("§7Momentan: " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6§l"));
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack ButtonMinus(String type) {
        ItemStack itemStack = new ItemStack(Material.OAK_BUTTON);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        switch (type) {
            case "sec":
                itemMeta.setDisplayName("§6Sekunden");
                break;
            case "min":
                itemMeta.setDisplayName("§6Minuten");
                break;
            case "hours":
                itemMeta.setDisplayName("§6Stunden");
                break;
        }
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Ändert die Zeit des Timers.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7- 1");
        itemLore.add("§8[§9Shift-Klick§8] §7- 10");
        itemLore.add(" ");
        itemLore.add("§7Momentan: " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6§l"));
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }


    public static ItemStack Backup() {
        ItemStack itemStack = new ItemStack(Material.ELYTRA);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6Backup");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7In den angegebenen Intervallen wird ein");
        itemLore.add("§7Backup von allen Welt-Ordnern gemacht.");
        itemLore.add(" ");
        itemLore.add("§8[§9Links-Klick§8] §7+ 1");
        itemLore.add("§8[§9Rechts-Klick§8] §7- 1");
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack BUNGEECORD() {
        ItemStack itemStack = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

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

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack Hardcore() {
        ItemStack itemStack = new ItemStack(Material.CRIMSON_FUNGUS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6Hardcore");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Setzt die Schwierigkeit auf");
        itemLore.add("§7Hardcore.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7An / Aus");
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack SendTitle() {
        ItemStack itemStack = new ItemStack(Material.NAME_TAG);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6Titel");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Sendet einen Titel an alle Spieler");
        itemLore.add("§7wenn sich die Einstellungen ändern.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7An / Aus");
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack Enderdragon() {
        ItemStack itemStack = new ItemStack(Material.DRAGON_HEAD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6Enderdrache");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Die Challenge ist absolviert, wenn");
        itemLore.add("§7der Enderdrache besiegt wird.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7An / Aus");
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack Wither() {
        ItemStack itemStack = new ItemStack(Material.WITHER_SKELETON_SKULL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6Wither");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Die Challenge ist absolviert, wenn");
        itemLore.add("§7der Wither besiegt wird.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7An / Aus");
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack Next() {
        ItemStack itemStack = new ItemStack(Material.ARROW);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName("§6Weiter");
        itemLore.add(" ");
        itemLore.add("§7Bringt dich auf die nächste Seite.");
        itemLore.add(" ");

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

    public static ItemStack SendDamageMessage() {
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6Schaden im Chat");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Zeigt den Schaden den");
        itemLore.add("§7Spieler bekommen im Chat an.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7An / Aus");
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack KeepInventorySettings() {
        ItemStack itemStack = new ItemStack(Material.CHEST);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6Keep Inventory");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Erlaubt den Spielern nach dem");
        itemLore.add("§7Tod ihr Inventar zu behalten.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7An / Aus");
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack ShowCoordsOnDeath() {
        ItemStack itemStack = new ItemStack(Material.BEACON);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6Koordinaten bei Tod");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Zeigt die Koordinaten vom Todesort");
        itemLore.add("§7eines Spielers hinter der Todesnachricht an.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7An / Aus");
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack PVP() {
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6PVP");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Erlaubt, dass sich Spieler");
        itemLore.add("§7gegenseitig Schaden machen können.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7An / Aus");
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack NeedResetConfirm() {
        ItemStack itemStack = new ItemStack(Material.FILLED_MAP);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName("§6Reset Confirm");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Benötigt ein nach §9/reset §7ein");
        itemLore.add("§9/reset confirm §7damit die Welten");
        itemLore.add("§7zurückgesetzt werden.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7An / Aus");
        itemLore.add(" ");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static String getOtherMenuName() {
        return "Restliche Einstellungen §7» §8Seite 1";
    }

    public static void openInv(Player p) {
        p.openInventory(getMenu());
    }

    public static String getMenuName() {
        return "Settings";
    }

    public static ItemStack TimerSettings() {
        ItemStack itemStack = new ItemStack(Material.CLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName("§6Timer");
        itemLore.add(" ");
        itemLore.add("§9Beschreibung:");
        itemLore.add("§7Zeigt am unteren Bildschirmrand");
        itemLore.add("§7einen §9Timer §7an.");
        itemLore.add(" ");
        itemLore.add("§8[§9Klick§8] §7öffnet Einstellungen");
        itemLore.add(" ");

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

    public static ItemStack ChallengesSettings() {
        ItemStack Challenges = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta ChallengesMeta = Challenges.getItemMeta();
        ArrayList<String> ChallengesLore = new ArrayList<>();
        ChallengesMeta.setDisplayName("§9Challenges");
        ChallengesLore.add(" ");
        ChallengesLore.add("§7Öffnet die §9Einstellungen §7für die §9Challenges");
        ChallengesLore.add(" ");

        ChallengesMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        ChallengesMeta.setLore(ChallengesLore);
        Challenges.setItemMeta(ChallengesMeta);

        return Challenges;
    }

    public static ItemStack MaxHealth() {
        ItemStack Challenges = new ItemStack(Material.RED_DYE);
        ItemMeta ChallengesMeta = Challenges.getItemMeta();
        ArrayList<String> ChallengesLore = new ArrayList<>();
        ChallengesMeta.setDisplayName("§9Maximale Leben");
        ChallengesLore.add(" ");
        ChallengesLore.add("§7Derzeit: §6" + SettingsModes.maxHP + " HP");
        ChallengesLore.add(" ");

        ChallengesMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        ChallengesMeta.setLore(ChallengesLore);
        Challenges.setItemMeta(ChallengesMeta);

        return Challenges;
    }

    public static ItemStack MaxHealthButton() {
        ItemStack Challenges = new ItemStack(Material.STONE_BUTTON);
        ItemMeta ChallengesMeta = Challenges.getItemMeta();
        ArrayList<String> ChallengesLore = new ArrayList<>();
        ChallengesMeta.setDisplayName("§9Maximale Leben");
        ChallengesLore.add(" ");
        ChallengesLore.add("§8[§6Rechts-Klick§8] §7 - 1");
        ChallengesLore.add("§8[§6Links-Klick§8] §7 + 1");
        ChallengesLore.add(" ");

        ChallengesMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        ChallengesMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        ChallengesMeta.setLore(ChallengesLore);
        Challenges.setItemMeta(ChallengesMeta);

        return Challenges;
    }

    public static ItemStack Health() {
        ItemStack Health = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta HealthMeta = Health.getItemMeta();
        ArrayList<String> HealthLore = new ArrayList<>();
        HealthMeta.setDisplayName("§9Lebensanzeige");
        HealthLore.add(" ");
        HealthLore.add("§7Öffnet die §9Lebenseinstellungen.");
        HealthLore.add(" ");

        HealthMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        HealthMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        HealthMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        HealthMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        HealthMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        HealthMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        HealthMeta.setLore(HealthLore);
        Health.setItemMeta(HealthMeta);

        return Health;
    }

    public static ItemStack Projects() {
        ItemStack itemStack = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName("§9Projekte");
        itemLore.add(" ");
        itemLore.add("§7Öffnet die Einstellungen für Projekte");
        itemLore.add("§7wie zum Beispiel §9Alle Achievements,");
        itemLore.add("§9Alle Items, Alle Tode §7und weitere ...");
        itemLore.add(" ");

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

    public static ItemStack Other() {
        ItemStack Other = new ItemStack(Material.STRING);
        ItemMeta OtherMeta = Other.getItemMeta();
        ArrayList<String> OtherLore = new ArrayList<>();
        OtherMeta.setDisplayName("§9Andere Einstellungen");
        OtherLore.add(" ");
        OtherLore.add("§7Öffnet §9restliche Einstellungen.");
        OtherLore.add(" ");

        OtherMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        OtherMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        OtherMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        OtherMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        OtherMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        OtherMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        OtherMeta.setLore(OtherLore);
        Other.setItemMeta(OtherMeta);

        return Other;
    }

    public static ItemStack GeteilteHerzen() {

        ItemStack GeteilteHerzen = new ItemStack(Material.GLISTERING_MELON_SLICE);
        ItemMeta GeteilteHerzenMeta = GeteilteHerzen.getItemMeta();
        ArrayList<String> GeteilteHerzenLore = new ArrayList<>();
        GeteilteHerzenMeta.setDisplayName("§6Geteilte Herzen");
        GeteilteHerzenLore.add(" ");
        GeteilteHerzenLore.add("§cAlle Spieler teilen ihre Herzen.");
        GeteilteHerzenLore.add(" ");

        GeteilteHerzenMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        GeteilteHerzenMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        GeteilteHerzenMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        GeteilteHerzenMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        GeteilteHerzenMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        GeteilteHerzenMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        GeteilteHerzenMeta.setLore(GeteilteHerzenLore);
        GeteilteHerzen.setItemMeta(GeteilteHerzenMeta);

        return GeteilteHerzen;
    }

    public static ItemStack NatrualRegeneration() {

        ItemStack itemStack = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName("§6Natürliche Regeneration");
        itemLore.add(" ");
        itemLore.add("§cErlaubt die natürliche Regeneration.");
        itemLore.add(" ");

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

    public static ItemStack OtherRegeneration() {

        ItemStack itemStack = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName("§6Restliche Regeneration");
        itemLore.add(" ");
        itemLore.add("§cErlaubt die unnatürliche Regeneration.");
        itemLore.add(" ");

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


    public static ItemStack Back() {

        ItemStack Back = new ItemStack(Material.CROSSBOW);
        ItemMeta BackMeta = Back.getItemMeta();
        ArrayList<String> BackLore = new ArrayList<>();
        BackMeta.setDisplayName("§6Zurück");
        BackLore.add(" ");
        BackLore.add("§7Bringt dich auf die vorherige Seite.");
        BackLore.add(" ");

        BackMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        BackMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        BackMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        BackMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        BackMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        BackMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        BackMeta.setLore(BackLore);
        Back.setItemMeta(BackMeta);

        return Back;
    }

    public static ItemStack AllowRespawn() {
        ItemStack AllowRespawn = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta AllowRespawnMeta = AllowRespawn.getItemMeta();
        ArrayList<String> AllowRespawnLore = new ArrayList<>();
        AllowRespawnMeta.setDisplayName("§6Respawn");
        AllowRespawnLore.add(" ");
        AllowRespawnLore.add("§cErlaubt den Spielern nach einem Tod zu respawnen.");
        AllowRespawnLore.add(" ");

        AllowRespawnMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        AllowRespawnMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        AllowRespawnMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        AllowRespawnMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        AllowRespawnMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        AllowRespawnMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        AllowRespawnMeta.setLore(AllowRespawnLore);
        AllowRespawn.setItemMeta(AllowRespawnMeta);

        return AllowRespawn;
    }

    public static ItemStack EinLebenFurAlle() {
        ItemStack EinLebenFurAlle = new ItemStack(Material.POPPY);
        ItemMeta EinLebenFurAlleMeta = EinLebenFurAlle.getItemMeta();
        ArrayList<String> EinLebenFurAlleLore = new ArrayList<>();
        EinLebenFurAlleMeta.setDisplayName("§6Ein Leben für alle");
        EinLebenFurAlleLore.add(" ");
        EinLebenFurAlleLore.add("§cStirbt ein Spieler, ist der Run vorbei.");
        EinLebenFurAlleLore.add(" ");
        EinLebenFurAlleLore.add("§cÜberschreibt §6Respawn");
        EinLebenFurAlleLore.add(" ");

        EinLebenFurAlleMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        EinLebenFurAlleMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        EinLebenFurAlleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        EinLebenFurAlleMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        EinLebenFurAlleMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        EinLebenFurAlleMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        EinLebenFurAlleMeta.setLore(EinLebenFurAlleLore);
        EinLebenFurAlle.setItemMeta(EinLebenFurAlleMeta);

        return EinLebenFurAlle;
    }
}
