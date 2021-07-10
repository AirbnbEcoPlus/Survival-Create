package fr.survivalmode;

import org.bukkit.plugin.java.JavaPlugin;

import fr.survivalmode.commands.SmCreate;
import fr.survivalmode.commands.SmInvite;


public class Main extends JavaPlugin {

	
	@Override
	public void onEnable() {
		System.out.println("Le plugin est en train de se charger...");
		getCommand("sc").setExecutor(new SmCreate());
		getServer().getPluginManager().registerEvents(new sclisten(), this);
		getCommand("sc invite").setExecutor(new SmInvite());

  }
}