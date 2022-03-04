package fr.survivalmode;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class scListenManager implements Listener {
    private Main main;
    public scListenManager(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        String name = player.getName();
        ItemStack current = event.getCurrentItem();
        if (current == null) return;
        //Affiche le menu de gestion du temps
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
            if(current.getType() == Material.WATCH) {
                event.setCancelled(true);
                player.closeInventory();

                Inventory invtime = Bukkit.createInventory(null, 27, "§8SurvivalCreateTime");
                ItemStack day = new ItemStack(Material.JACK_O_LANTERN, 1);
                ItemMeta dayM = day.getItemMeta();
                dayM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.day"));
                day.setItemMeta(dayM);


                ItemStack night = new ItemStack(Material.PUMPKIN, 1);
                ItemMeta nightM = night.getItemMeta();
                nightM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.night"));
                night.setItemMeta(nightM);



                invtime.setItem(12, day);
                invtime.setItem(14, night);
                player.openInventory(invtime);

            }
        }
        //Met la nuit
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateTime")) {
            if(current.getType() == Material.PUMPKIN) {
                event.setCancelled(true);
                player.closeInventory();
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
                    World worldtime = Bukkit.getWorld(name + "SurvivalCreateWorld");
                    worldtime.setTime(12000);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.setnight"));
                }else {
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
                }
            }
        }
        //Met le jour
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateTime")) {
            if(current.getType() == Material.JACK_O_LANTERN) {
                event.setCancelled(true);
                player.closeInventory();
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
                    World worldtime = Bukkit.getWorld(name + "SurvivalCreateWorld");
                    worldtime.setTime(0);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.setday"));
                }else {
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
                }
            }
        }
        //Affiche le menu de gestion du mode de jeu
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
            if(current.getType() == Material.COMPASS) {
                event.setCancelled(true);
                player.closeInventory();
                Inventory invGamemode = Bukkit.createInventory(null, 9, "§8SurvivalCreateGamemode");
                ItemStack survival = new ItemStack(Material.CHEST, 1);
                ItemMeta survivalM = survival.getItemMeta();
                survivalM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.survival"));
                survival.setItemMeta(survivalM);

                ItemStack creative = new ItemStack(Material.BRICK, 1);
                ItemMeta creativeM = creative.getItemMeta();
                creativeM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.creative"));
                creative.setItemMeta(creativeM);

                ItemStack spectator = new ItemStack(Material.EYE_OF_ENDER, 1);
                ItemMeta spectatorM = spectator.getItemMeta();
                spectatorM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.spectator"));
                spectator.setItemMeta(spectatorM);

                ItemStack adventure = new ItemStack(Material.BOOKSHELF, 1);
                ItemMeta adventureM = adventure.getItemMeta();
                adventureM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.adventure"));
                adventure.setItemMeta(adventureM);

                invGamemode.setItem(1, survival);
                invGamemode.setItem(3, creative);
                invGamemode.setItem(5, spectator);
                invGamemode.setItem(7, adventure);
                player.openInventory(invGamemode);
            }
        }
        //Mettre le jouer en survie
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
            if(current.getType() == Material.CHEST) {
                event.setCancelled(true);
                player.closeInventory();
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.survival"));
                }else
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));

                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.survival"));
                }
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.survival"));
                }
            }
        }
        //Mettre le joueur en créatif
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
            if(current.getType() == Material.BRICK) {
                event.setCancelled(true);
                player.closeInventory();
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.creative"));
                }else {
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
                }

                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.creative"));
                }
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.creative"));
                }
            }
        }
        //Met le joueur en spectateur
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
            if(current.getType() == Material.EYE_OF_ENDER) {
                event.setCancelled(true);
                player.closeInventory();
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.spectator"));
                }else {
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
                }

                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.spectator"));
                }
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.spectator"));
                }
            }
        }
        //Mettre le joueur en aventure
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
            if(current.getType() == Material.BOOKSHELF) {
                event.setCancelled(true);
                player.closeInventory();
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.adventure"));
                }else {
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
                }

                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.adventure"));
                }
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.adventure"));
                }
            }
        }
        //Affiche le menu de difficulté
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
            if(current.getType() == Material.DIAMOND_CHESTPLATE) {
                event.setCancelled(true);
                player.closeInventory();

                Inventory invdifi = Bukkit.createInventory(null, 27, "§8SurvivalCreateDifficulty");
                ItemStack peace = new ItemStack(Material.IRON_BLOCK, 1);
                ItemMeta peaceM = peace.getItemMeta();
                peaceM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.peacefull"));
                peace.setItemMeta(peaceM);


                ItemStack facile = new ItemStack(Material.GOLD_BLOCK, 1);
                ItemMeta facileM = facile.getItemMeta();
                facileM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.easy"));
                facile.setItemMeta(facileM);

                ItemStack normal = new ItemStack(Material.DIAMOND_BLOCK, 1);
                ItemMeta normalM = normal.getItemMeta();
                normalM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.normal"));
                normal.setItemMeta(normalM);

                ItemStack hard = new ItemStack(Material.EMERALD_BLOCK, 1);
                ItemMeta hardM = hard.getItemMeta();
                hardM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.hard"));
                hard.setItemMeta(hardM);

                invdifi.setItem(10, peace);
                invdifi.setItem(12, facile);
                invdifi.setItem(14, normal);
                invdifi.setItem(16, hard);
                player.openInventory(invdifi);
            }
        }
        //Change la difficulté en paisible
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
            if(current.getType() == Material.IRON_BLOCK) {
                player.closeInventory();
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
                    event.setCancelled(true);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world difficulty peaceful " + name + "SurvivalCreateWorld");
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.difficultychange"));
                }else {
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
                }
            }
        }
        //Change la difficulté en facile
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
            if(current.getType() == Material.GOLD_BLOCK) {
                player.closeInventory();
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
                    event.setCancelled(true);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world difficulty easy " + name + "SurvivalCreateWorld");
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.difficultychange"));
                } else {
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
                }
            }
        }
        //Change la difficulté en normal
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
            if(current.getType() == Material.DIAMOND_BLOCK) {
                player.closeInventory();
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
                    event.setCancelled(true);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world difficulty normal " + name + "SurvivalCreateWorld");
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.difficultychange"));
                }else {
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
                }
            }
        }
        //Change la difficulté en difficile
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
            if(current.getType() == Material.EMERALD_BLOCK) {
                player.closeInventory();
                if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
                    event.setCancelled(true);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world difficulty hard " + name + "SurvivalCreateWorld");
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.difficultychange"));
                }else {
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
                }
            }
        }
    }
}
