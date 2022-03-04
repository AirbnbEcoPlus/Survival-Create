package fr.survivalmode;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class scListenBorder implements Listener {
    private Main main;
    public scListenBorder(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        String name = player.getName();
        ItemStack current = event.getCurrentItem();
        if (current == null) return;
        //Affiche le menu de gestion de la barriere du monde
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
            Inventory invborder = Bukkit.createInventory(null, 27, "§8SurvivalCreateBorder");
            if(current.getType() == Material.BEACON) {
                event.setCancelled(true);
                player.closeInventory();


                ItemStack  borderdefault = new ItemStack(Material.GLASS, 1);
                ItemMeta borderdefaultM = borderdefault.getItemMeta();
                borderdefaultM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.borderlimit1"));
                borderdefault.setItemMeta(borderdefaultM);

                if(player.hasPermission("sc.vip1") || player.hasPermission("sc.vip2") || player.hasPermission("sc.vip3")){
                    ItemStack bordervip = new ItemStack(Material.IRON_BLOCK, 1);
                    ItemMeta bordervipM = bordervip.getItemMeta();
                    bordervipM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.borderlimit2"));
                    bordervip.setItemMeta(bordervipM);
                    invborder.setItem(12, bordervip);
                }else{
                    ItemStack bordervip = new ItemStack(Material.IRON_BLOCK, 1);
                    ItemMeta bordervipM = bordervip.getItemMeta();
                    bordervipM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.borderlimit2"));
                    bordervip.setItemMeta(bordervipM);
                    invborder.setItem(12, bordervip);
                }
                if(player.hasPermission("sc.vip2") || player.hasPermission("sc.vip3")) {
                    ItemStack bordervip2 = new ItemStack(Material.GOLD_BLOCK, 1);
                    ItemMeta bordervip2M = bordervip2.getItemMeta();
                    bordervip2M.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.borderlimit3"));
                    bordervip2.setItemMeta(bordervip2M);
                    invborder.setItem(14, bordervip2);
                }else{
                    ItemStack bordervip2 = new ItemStack(Material.BARRIER, 1);
                    ItemMeta bordervip2M = bordervip2.getItemMeta();
                    bordervip2M.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.noperm"));
                    bordervip2.setItemMeta(bordervip2M);
                    invborder.setItem(14, bordervip2);
                }
                if(player.hasPermission("sc.vip3")) {
                    ItemStack bordervip3 = new ItemStack(Material.DIAMOND_BLOCK, 1);
                    ItemMeta bordervip3M = bordervip3.getItemMeta();
                    bordervip3M.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.borderlimit4"));
                    bordervip3.setItemMeta(bordervip3M);
                    invborder.setItem(16, bordervip3);
                }else{
                    ItemStack bordervip3 = new ItemStack(Material.BARRIER, 1);
                    ItemMeta bordervip3M = bordervip3.getItemMeta();
                    bordervip3M.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.noperm"));
                    bordervip3.setItemMeta(bordervip3M);
                    invborder.setItem(16, bordervip3);
                }
                invborder.setItem(10, borderdefault);
                player.openInventory(invborder);

            }
        }
        //Mettre la bariere du monde a 10 000 blocs
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
            if(current.getType() == Material.GLASS) {
                player.closeInventory();
                World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
                WorldBorder border = world.getWorldBorder();
                border.setSize(10000.0);
                border.setCenter(0.0, 0.0);
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.borderset1"));
            }
        }
        //Mettre la bariere du monde a 30 000 blocs
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
            if(current.getType() == Material.IRON_BLOCK) {
                if(player.hasPermission("sc.vip1")) {
                    player.closeInventory();
                    World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
                    WorldBorder border = world.getWorldBorder();
                    border.setSize(30000.0);
                    border.setCenter(0.0, 0.0);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.borderset2"));
                }
            }
        }

        //Mettre la bariere du monde a 60 000 blocs
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
            if(current.getType() == Material.GOLD_BLOCK) {
                if(player.hasPermission("sc.vip2")) {
                    player.closeInventory();
                    World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
                    WorldBorder border = world.getWorldBorder();
                    border.setSize(60000.0);
                    border.setCenter(0.0, 0.0);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.borderset3"));
                }
            }
        }
        //Mettre la bariere du monde a 120 000 blocs
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
            if(current.getType() == Material.DIAMOND_BLOCK) {
                if(player.hasPermission("sc.vip3")) {
                    player.closeInventory();
                    World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
                    WorldBorder border = world.getWorldBorder();
                    border.setSize(120000.0);
                    border.setCenter(0.0, 0.0);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.borderset4"));
                }

            }
        }
        //Message quand la personne n'a pas les bonnes permissions
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
            if(current.getType() == Material.BARRIER) {
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.nopermission"));
            }
        }
    }
}
