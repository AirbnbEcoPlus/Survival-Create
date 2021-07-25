package fr.survivalmode;




import org.bukkit.plugin.java.JavaPlugin;

import fr.survivalmode.commands.SmCreate;

import fr.survivalmode.commands.SmInviteMenu;
import java.util.HashMap;

public class Main extends JavaPlugin {
	public HashMap<String, String> PlayerTp = new HashMap<String, String>();
	public boolean VoidGeneratorEnable = false;
	@Override
	public void onEnable() {
		saveDefaultConfig();
		System.out.println("Merci d'utiliser Survival Create");
		getCommand("sc").setExecutor(new SmCreate(this));
		getServer().getPluginManager().registerEvents(new sclisten(this), this);
		getCommand("scinvite").setExecutor(new SmInviteMenu(this));
		if(getServer().getPluginManager().getPlugin("VoidGen") != null) {
			VoidGeneratorEnable = true;
		}
		
  }
}