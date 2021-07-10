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

public class SmInvite implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		Player player = (Player) sender;
		Inventory invinv = Bukkit.createInventory(null, 9, "§8SurvivalCreateInvite");
		
		ItemStack invite = new ItemStack(Material.BED, 1);
		ItemMeta inviteM = invite.getItemMeta();
		inviteM.setDisplayName("Inviter un ami");
		invite.setItemMeta(inviteM);
		
		ItemStack publicworld = new ItemStack(Material.EMERALD, 1);
		ItemMeta publicworldM = publicworld.getItemMeta();
		publicworldM.setDisplayName("Inviter un ami");
		publicworld.setItemMeta(publicworldM);
		
		ItemStack switchworld = new ItemStack(Material.EMERALD, 1);
		ItemMeta switchworldM = switchworld.getItemMeta();
		switchworldM.setDisplayName("Mettre votre monde en public");
		switchworld.setItemMeta(switchworldM);
		
		invinv.setItem(2, invite);
		invinv.setItem(4, publicworld);
		invinv.setItem(4, switchworld);
		player.openInventory(invinv);
		
		
		return false;
	}

}
