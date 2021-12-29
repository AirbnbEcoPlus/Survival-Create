package fr.survivalmode.world;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.survivalmode.Main;


public class WorldManager {

	private List<World> worlds = new ArrayList<>();
	private Main main;
	public WorldManager(Main main) {
		this.main = main;
	}
	public void addWorld(World world) {
		this.worlds.add(world);
	}
	public void delWorld() {
		this.worlds.clear();
	}
	public void inventoryPublic(Player player) {
		int counternumber = 0;
		
		Inventory invworlds = Bukkit.createInventory(null, 27, "§2SurvivalCreateWorlds");
		main.GetWorldList();
	for(World worldz : worlds) {
		if(ifWorldIsLoaded(worldz.getWorldName()) == true) {
		ItemStack world = new ItemStack(Material.GRASS, 1);
		ItemMeta worldM = world.getItemMeta();
		worldM.setDisplayName(worldz.getWorldName());
		ArrayList<String> lore = new ArrayList<String>();
		
		lore.add("");
		worldM.setLore(lore);
		world.setItemMeta(worldM);
		invworlds.setItem(counternumber, world);
		counternumber += 1;
		}
		player.openInventory(invworlds);
	}
	}
	public boolean ifWorldIsLoaded(String worldName){
		for(World worldz : worlds) {
			if(worldz.getWorldName() == worldName) {
				if(worldz.getWorldIsLoaded() == true) {
					return true;
				}
			}
		}
		return false;
	}
	public Location getWorldByPlayerHost(String worldName) {
		for(World worldz : worlds) {
			if(worldz.getWorldName() == worldName) {
				Location locworld = worldz.getLocSpawn();
				return locworld;
			}
		}
		return null;
	}
	public boolean ifWorldIsPublic(Player player) {
		for(World worldz : worlds) {
			if(worldz.getWorldIsPublic() == true) {
				
			}
	}
		return false;
	
	
	}
}
