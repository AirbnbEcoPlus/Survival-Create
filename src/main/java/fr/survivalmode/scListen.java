package fr.survivalmode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import static org.bukkit.Bukkit.getServer;


public class scListen implements Listener {
	private Main main;
	
	public scListen(Main main) {
		   this.main = main;
	}
	@EventHandler
	//Décharge le monde si un joueur quitte le monde
	    public void onQuit(PlayerQuitEvent event) {
	        Player player = event.getPlayer();
	        String name = player.getName();
	        if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
			getServer().unloadWorld(name + "SurvivalCreateWorld", true);
			if (getServer().getWorlds().contains(name + "SurvivalCreateWorld_nether")){
				getServer().unloadWorld(name + "SurvivalCreateWorld_nether", true);
			}
				if (getServer().getWorlds().contains(name + "SurvivalCreateWorld_the_end")) {
					getServer().unloadWorld(name + "SurvivalCreateWorld_the_end", true);
				}
			main.getConfig().set("arenas." + name + "SurvivalCreateWorld.isLoaded", false);
			main.saveConfig();
	   }
	        }
	   @EventHandler
	   public void onClick(InventoryClickEvent event) {
			Inventory inv = event.getInventory();
			Player player = (Player) event.getWhoClicked();
			String name = player.getName();
			ItemStack current = event.getCurrentItem();		
			if(current == null) return;
			//Decharger le monde
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.BED) {
					event.setCancelled(true);
					player.closeInventory();
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_nether");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_the_end");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld_nether");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld_the_end");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.quitworld"));
   			}
		}
	}
}
			
	   

