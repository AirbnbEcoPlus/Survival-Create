package fr.survivalmode;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class scListenCreate implements Listener{
    private Main main;
    public scListenCreate(Main main) {
        this.main = main;
    }
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        String name = player.getName();
        ItemStack current = event.getCurrentItem();
        if (current == null) return;
        //Afficher le menu de creation de mondes
        if (inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
            if (current.getType() == Material.GRASS) {
                event.setCancelled(true);
                player.closeInventory();

                Inventory invseed = Bukkit.createInventory(null, 27, "§8SurvivalCreateSeed");
                ItemStack seednormal = new ItemStack(Material.DIRT, 1);
                ItemMeta seednormalM = seednormal.getItemMeta();
                seednormalM.setDisplayName("Monde normal");
                seednormal.setItemMeta(seednormalM);


                ItemStack seedFlat = new ItemStack(Material.WOOD_PLATE, 1);
                ItemMeta seedFlatM = seedFlat.getItemMeta();
                seedFlatM.setDisplayName("Monde Plat");
                seedFlat.setItemMeta(seedFlatM);

                ItemStack minigames = new ItemStack(Material.STICK, 1);
                ItemMeta minigamesM = seedFlat.getItemMeta();
                minigamesM.setDisplayName("Graine Mini-Jeux");
                minigames.setItemMeta(minigamesM);

                if (main.VoidGeneratorEnable == true) {
                    ItemStack seedVoid = new ItemStack(Material.BEDROCK, 1);
                    ItemMeta seedVoidM = seedVoid.getItemMeta();
                    seedVoidM.setDisplayName("Monde Vide");
                    seedVoid.setItemMeta(seedVoidM);

                    invseed.setItem(11, seednormal);
                    invseed.setItem(13, seedFlat);
                    invseed.setItem(15, seedVoid);
                    player.openInventory(invseed);
                }


                invseed.setItem(11, seednormal);
                invseed.setItem(13, seedFlat);
                invseed.setItem(24, minigames);

                player.openInventory(invseed);

            }
        }
        //Créer un monde plat
        if (inv.getName().equalsIgnoreCase("§8SurvivalCreateSeed")) {
            if (current.getType() == Material.WOOD_PLATE) {
                event.setCancelled(true);
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loading"));
                WorldCreator wc_flat = new WorldCreator(name + "SurvivalCreateWorld");
                wc_flat.environment(World.Environment.NORMAL);
                wc_flat.type(WorldType.FLAT);
                wc_flat.createWorld();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loadingsucces"));

            }
        }
        //Créer un monde normal
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateSeed")) {
            if(current.getType() == Material.DIRT) {
                event.setCancelled(true);
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loading"));
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.step1"));
                WorldCreator wc = new WorldCreator(name + "SurvivalCreateWorld");
                wc.environment(World.Environment.NORMAL);
                wc.type(WorldType.NORMAL);
                wc.createWorld();
                World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
                WorldBorder border = world.getWorldBorder();
                border.setSize(10000.0);
                border.setCenter(0.0, 0.0);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.step2"));
                WorldCreator wc_nether = new WorldCreator(name + "SurvivalCreateWorld_nether");
                wc_nether.environment(World.Environment.NETHER);
                wc_nether.type(WorldType.NORMAL);
                wc_nether.createWorld();
                World world_nether = Bukkit.getWorld(name + "SurvivalCreateWorld_nether");
                WorldBorder border_nether = world_nether.getWorldBorder();
                border_nether.setSize(10000.0);
                border_nether.setCenter(0.0, 0.0);
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.step3"));
                WorldCreator wc_the_end = new WorldCreator(name + "SurvivalCreateWorld_the_end");
                wc_the_end.environment(World.Environment.THE_END);
                wc_the_end.type(WorldType.NORMAL);
                wc_the_end.createWorld();
                World world_the_end = Bukkit.getWorld(name + "SurvivalCreateWorld_the_end");
                WorldBorder border_the_end = world_the_end.getWorldBorder();
                border_the_end.setSize(10000.0);
                border_the_end.setCenter(0.0, 0.0);
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loadingsucces"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world inventory merge " + name + "SurvivalCreateWorld " + name + "SurvivalCreateWorld_nether " + name + "SurvivalCreateWorld_the_end");
            }
        }
        //Créer un monde Vide
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateSeed")) {
            if(current.getType() == Material.BEDROCK) {
                event.setCancelled(true);
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loading"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world create " + name + "SurvivalCreateWorld:VoidGenerator");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loadingsucces"));
            }
        }
        //Téléporter le joueur
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
            if(current.getType() == Material.ENDER_PEARL) {
                if (!player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
                    event.setCancelled(true);
                    player.closeInventory();

                    main.getConfig().set("arenas." + name + "SurvivalCreateWorld.isLoaded", true);
                    main.saveConfig();
                }else {
                    player.sendMessage(ChatColor.GREEN+ "Vous etes deja dans votre monde");
                }
            }
        }
        //Supprimer le monde
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
            if(current.getType() == Material.BARRIER) {
                event.setCancelled(true);
                player.closeInventory();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld_nether");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld_the_end");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_nether");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_the_end");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world delete " + name + "SurvivalCreateWorld");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world delete " + name + "SurvivalCreateWorld_nether");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world delete " + name + "SurvivalCreateWorld_the_end");
                main.getConfig().set("arenas." + name + "SurvivalCreateWorld", null);
                main.saveConfig();
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.deleteworld"));
            }
        }
    }
}
