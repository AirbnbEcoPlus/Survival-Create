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

public class SmCreate implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
	    
		Player player = (Player) sender;
		Inventory inv = Bukkit.createInventory(null, 27, "§8SurvivalCreateMenu");
		
		ItemStack  create = new ItemStack(Material.GRASS, 1);
		ItemMeta createM = create.getItemMeta();
		createM.setDisplayName("Créer un monde");
		create.setItemMeta(createM);
		
		ItemStack  tp = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta tpM = tp.getItemMeta();
		tpM.setDisplayName("Se téléporter a votre monde");
		tp.setItemMeta(tpM);
		
		ItemStack  delete = new ItemStack(Material.BARRIER, 1);
		ItemMeta deleteM = delete.getItemMeta();
		deleteM.setDisplayName("Supprimer votre monde");
		delete.setItemMeta(deleteM);
		
		ItemStack  playerlist = new ItemStack(Material.JACK_O_LANTERN, 1);
		ItemMeta playerlistM = playerlist.getItemMeta();
		playerlistM.setDisplayName("Voir la liste des commandes pour rejoindre un personne sur son monde"
				+ "");
		playerlist.setItemMeta(playerlistM);
		
		ItemStack  border = new ItemStack(Material.BEACON, 1);
		ItemMeta borderM = border.getItemMeta();
		borderM.setDisplayName("Gerer la limite de votre map");
		border.setItemMeta(borderM);
		
		ItemStack  gamemode = new ItemStack(Material.COMPASS, 1);
		ItemMeta gamemodeM = gamemode.getItemMeta();
		gamemodeM.setDisplayName("Gerer le mode de jeux(Survie, Créatif)");
		gamemode.setItemMeta(gamemodeM);
		
		ItemStack  time = new ItemStack(Material.WATCH, 1);
		ItemMeta timeM = time.getItemMeta();
		timeM.setDisplayName("Gerer le mode le jour et la nuit");
		time.setItemMeta(timeM);
		
		ItemStack  difi = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta difiM = difi.getItemMeta();
		difiM.setDisplayName("Gerer la difficulté");
		difi.setItemMeta(difiM);
		
		inv.setItem(3, gamemode);
		inv.setItem(11, create);
		inv.setItem(13, tp);
		inv.setItem(15, delete);
		inv.setItem(23, playerlist);
		inv.setItem(21, border);
		inv.setItem(4, difi);
		inv.setItem(5, time);
		player.openInventory(inv);
		
		
		return false;
		
	}
	
				
  }

