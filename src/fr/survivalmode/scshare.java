package fr.survivalmode;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class scshare implements Listener {

	private Main main;
	public scshare(Main main) {
	this.main = main;
	}
	@EventHandler
	   public void onClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		String name = player.getName();
		ItemStack current = event.getCurrentItem();		
		if(current == null) return;
		if(inv.getName().equalsIgnoreCase("§8SurvivalCreateInvite")) {
			if(current.getType() == Material.REDSTONE_BLOCK) {
				main.getConfig().set("arenas." + name + "SurvivalCreateWorld" , null);
				main.saveConfig();
				
			}
			if(current.getType() == Material.EMERALD) {
				event.setCancelled(true);
				if(player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
					if(main.getConfig().get("arenas." + name + "SurvivalCreateWorld.posY") != null) {
					main.getConfig().set("arenas." + name + "SurvivalCreateWorld.worldName" , "Monde de " + name);
                	main.getConfig().set("arenas." + name + "SurvivalCreateWorld.playerhost" , name);
                	main.getConfig().set("arenas." + name + "SurvivalCreateWorld.world" , name + "SurvivalCreateWorld");
                	main.getConfig().set("arenas." + name + "SurvivalCreateWorld.maxplayer" , "8");
                	main.getConfig().set("arenas." + name + "SurvivalCreateWorld.isLoaded", false);
                	main.saveConfig();
                	
				}else {
					player.sendMessage(ChatColor.RED + main.getConfig().getString("message.menu.answerResponse.scdodef"));
				}
					main.getConfig().getString("message.menu.answerResponse.quitworld");
				}else {
			    	player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
				}
				
				
			}else if(current.getType() == Material.REDSTONE_BLOCK) {
				main.getConfig().set("arenas." + name + "SurvivalCreateWorld", null);
				player.sendMessage("Votre monde n'est plus en public");
			}
	}
		
  }
}
