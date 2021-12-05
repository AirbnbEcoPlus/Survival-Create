package fr.survivalmode.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.survivalmode.Main;
import net.md_5.bungee.api.ChatColor;

public class SmCreate implements CommandExecutor {

	private Main main;
	public SmCreate(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(args.length == 0) { 
			Player player = (Player) sender;
			Inventory inv = Bukkit.createInventory(null, 27, "§8SurvivalCreateMenu");
			
			ItemStack  create = new ItemStack(Material.GRASS, 1);
			ItemMeta createM = create.getItemMeta();
			createM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.create"));
			create.setItemMeta(createM);
			
			ItemStack  tp = new ItemStack(Material.ENDER_PEARL, 1);
			ItemMeta tpM = tp.getItemMeta();
			tpM.setDisplayName(ChatColor.GOLD +main.getConfig().getString("message.menu.items.teleport"));
			tp.setItemMeta(tpM);
			
			ItemStack  delete = new ItemStack(Material.BARRIER, 1);
			ItemMeta deleteM = delete.getItemMeta();
			deleteM.setDisplayName(ChatColor.GOLD +main.getConfig().getString("message.menu.items.delete"));
			delete.setItemMeta(deleteM);
			
			ItemStack  playerlist = new ItemStack(Material.JACK_O_LANTERN, 1);
			ItemMeta playerlistM = playerlist.getItemMeta();
			playerlistM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.invite"));
			playerlist.setItemMeta(playerlistM);
			
			ItemStack  border = new ItemStack(Material.BEACON, 1);
			ItemMeta borderM = border.getItemMeta();
			borderM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.border"));
			border.setItemMeta(borderM);
			
			
			ItemStack  gamemode = new ItemStack(Material.COMPASS, 1);
			ItemMeta gamemodeM = gamemode.getItemMeta();
			gamemodeM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.gamemode"));
			gamemode.setItemMeta(gamemodeM);
			
			ItemStack  time = new ItemStack(Material.WATCH, 1);
			ItemMeta timeM = time.getItemMeta();
			timeM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.time"));
			time.setItemMeta(timeM);
			
			ItemStack  difi = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
			ItemMeta difiM = difi.getItemMeta();
			difiM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.difficulty"));
			difi.setItemMeta(difiM);
			
			
			
			
			
			
			ItemStack  quit = new ItemStack(Material.BED, 1);
			ItemMeta quitM = quit.getItemMeta();
			quitM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.quit"));
			quit.setItemMeta(quitM);
			
			inv.setItem(11, create);
			inv.setItem(13, tp);
			inv.setItem(15, delete);
			inv.setItem(23, playerlist);
			inv.setItem(21, border);
			inv.setItem(22, quit);
			inv.setItem(8, gamemode);
			inv.setItem(17, time);
			inv.setItem(26, difi);
			player.openInventory(inv);
			
		}
		
		
		return false;
		
	}
	
				
  }

