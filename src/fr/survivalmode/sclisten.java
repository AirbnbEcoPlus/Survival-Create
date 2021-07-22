package fr.survivalmode;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;




public class sclisten implements Listener { 
	 
		        

	        	
	        	
	        
	        
			
			
	   
	
	private Main main;
	
	   public sclisten(Main main) {
		   this.main = main;
	}
	@EventHandler
	    public void onQuit(PlayerQuitEvent event) {
	        Player player = event.getPlayer();
	        String name = player.getName();
	        if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
	        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_nether");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_the_end");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld_nether");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld_the_end");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
	   }
	        }
	   @EventHandler
	   public void onClick(InventoryClickEvent event) {
			Inventory inv = event.getInventory();
			Player player = (Player) event.getWhoClicked();
			String name = player.getName();

			ItemStack current = event.getCurrentItem();		
			if(current == null) return;
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.GRASS) {
					event.setCancelled(true);
					player.closeInventory();
					
					Inventory invseed = Bukkit.createInventory(null, 27, "§8SurvivalCreateSeed");
					ItemStack seednormal = new ItemStack(Material.DIRT, 1);
					ItemMeta seednormalM = seednormal.getItemMeta();
					seednormalM.setDisplayName("Monde normal");
					seednormal.setItemMeta(seednormalM);
					
					
					ItemStack seedFlat = new ItemStack(Material.WOOD_PLATE, 1);
					ItemMeta seedFlatM = seedFlat.getItemMeta();
					seedFlatM.setDisplayName("Monde Plat");
					seedFlat.setItemMeta(seedFlatM);
					
					ItemStack seedVoid = new ItemStack(Material.BEDROCK, 1);
					ItemMeta seedVoidM = seedVoid.getItemMeta();
					seedVoidM.setDisplayName("Monde Vide");
					seedVoid.setItemMeta(seedVoidM);
					
					invseed.setItem(11, seednormal);
					invseed.setItem(13, seedFlat);
					invseed.setItem(15, seedVoid);
					player.openInventory(invseed);

				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateSeed")) {
				if(current.getType() == Material.WOOD_PLATE) {
					event.setCancelled(true);
					player.closeInventory();
					player.sendMessage("§2Votre Monde est en train de se charger");
					WorldCreator wc_flat = new WorldCreator(name + "SurvivalCreateWorld");
					wc_flat.environment(World.Environment.NORMAL);
					wc_flat.type(WorldType.FLAT);
					wc_flat.createWorld();	
					this.main.getConfig().set("world.private", name + "SurvivalCreateWorld");
					this.main.saveConfig();
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
					
					

					
					player.sendMessage("§2Votre Monde est maintenant charger vous pouvez le rejoindre");
					
				}
			}
			
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateSeed")) {
				if(current.getType() == Material.DIRT) {
					event.setCancelled(true);
					player.closeInventory();
					player.sendMessage("§2Votre Monde est en train de se charger");
					player.sendMessage("§2Etape 1 sur 3");
					WorldCreator wc = new WorldCreator(name + "SurvivalCreateWorld");
					wc.environment(World.Environment.NORMAL);
					wc.type(WorldType.NORMAL);
					wc.createWorld();
                    World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
					WorldBorder border = world.getWorldBorder();
					border.setSize(10000.0);
					border.setCenter(0.0, 0.0);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
					player.sendMessage("§2Etape 2 sur 3");
					WorldCreator wc_nether = new WorldCreator(name + "SurvivalCreateWorld_nether");
					wc_nether.environment(World.Environment.NETHER);
					wc_nether.type(WorldType.NORMAL);
					wc_nether.createWorld();
                    World world_nether = Bukkit.getWorld(name + "SurvivalCreateWorld_nether");
					WorldBorder border_nether = world_nether.getWorldBorder();
					border_nether.setSize(10000.0);
					border_nether.setCenter(0.0, 0.0);
					player.sendMessage("§2Etape 3 sur 3");
					WorldCreator wc_the_end = new WorldCreator(name + "SurvivalCreateWorld_the_end");
					wc_the_end.environment(World.Environment.THE_END);
					wc_the_end.type(WorldType.NORMAL);
					wc_the_end.createWorld();
                    World world_the_end = Bukkit.getWorld(name + "SurvivalCreateWorld_the_end");
					WorldBorder border_the_end = world_the_end.getWorldBorder();
					border_the_end.setSize(10000.0);
					border_the_end.setCenter(0.0, 0.0);
					player.sendMessage("§2Votre Monde est maintenant charger vous pouvez le rejoindre");
					this.main.getConfig().set("world.private", name + "SurvivalCreateWorld");
					this.main.saveConfig();
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world merge " + name + "SurvivalCreateWorld " + name + "SurvivalCreateWorld_the_end " + name + "SurvivalCreateWorld_the_end");

					
				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateSeed")) {
				if(current.getType() == Material.BEDROCK) {
					event.setCancelled(true);
					player.closeInventory();
					player.sendMessage("§2Votre Monde est en train de se charger");
					WorldCreator wc_void = new WorldCreator(name + "SurvivalCreateWorld");
					wc_void.environment(World.Environment.NORMAL);
					wc_void.type(WorldType.NORMAL);
					wc_void.generator("VoidGenerator");
					wc_void.createWorld();
					this.main.getConfig().set("world.private", name + "SurvivalCreateWorld");
					this.main.saveConfig();
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
				}
			}
			
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateInvite")) {
				if(current.getType() == Material.EMERALD) {
					player.sendMessage("§2Votre monde est desormait publique");
					this.main.getConfig().set("world.public", name + "SurvivalCreateWorld_nether");
					this.main.saveConfig();
					player.closeInventory();
			
			
	}
}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateInviteDemande")) {
				if(current.getType() == Material.EMERALD_BLOCK) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + name + " " + main.PlayerTp.get("target"));
					
					player.closeInventory();	
	}
}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.WATCH) {
					event.setCancelled(true);
					player.closeInventory();
					
					Inventory invtime = Bukkit.createInventory(null, 27, "§8SurvivalCreateTime");
					ItemStack day = new ItemStack(Material.JACK_O_LANTERN, 1);
					ItemMeta dayM = day.getItemMeta();
					dayM.setDisplayName("Jour");
					day.setItemMeta(dayM);
					
					
					ItemStack night = new ItemStack(Material.PUMPKIN, 1);
					ItemMeta nightM = night.getItemMeta();
					nightM.setDisplayName("Nuit");
					night.setItemMeta(nightM);
					
					
					
					invtime.setItem(12, day);
					invtime.setItem(14, night);
					player.openInventory(invtime);

				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateTime")) {
				if(current.getType() == Material.PUMPKIN) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 World worldtime = Bukkit.getWorld(name + "SurvivalCreateWorld");
						 worldtime.setTime(12000);
					 }else {
						 player.sendMessage("§2Vous devez être dans votre monde");
					 }
				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateTime")) {
				if(current.getType() == Material.JACK_O_LANTERN) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 World worldtime = Bukkit.getWorld(name + "SurvivalCreateWorld");
						 worldtime.setTime(0);
					 }else {
						 player.sendMessage("§2Vous devez être dans votre monde");
					 }
				}
			}
			
				
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.ENDER_PEARL) {
					event.setCancelled(true);
					player.closeInventory();
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld");
					player.sendMessage("Pour inviter des amis ou pour gerer votre monde /sc");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world tp " + name + " " + name + "SurvivalCreateWorld " );
					
					
			
		
   			}
  }
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
					
					
			
		
   			}
  }
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.COMPASS) {
					event.setCancelled(true);
					player.closeInventory();
					Inventory invGamemode = Bukkit.createInventory(null, 9, "§8SurvivalCreateGamemode");
					ItemStack survival = new ItemStack(Material.CHEST, 1);
					ItemMeta survivalM = survival.getItemMeta();
					survivalM.setDisplayName("§aSurvie");
					survival.setItemMeta(survivalM);
					
					ItemStack creative = new ItemStack(Material.BRICK, 1);
					ItemMeta creativeM = creative.getItemMeta();
					creativeM.setDisplayName("§aCréatif");
					creative.setItemMeta(creativeM);
					
					ItemStack spectator = new ItemStack(Material.EYE_OF_ENDER, 1);
					ItemMeta spectatorM = spectator.getItemMeta();
					spectatorM.setDisplayName("§aSpectateur");
					spectator.setItemMeta(spectatorM);
					
					ItemStack adventure = new ItemStack(Material.BOOKSHELF, 1);
					ItemMeta adventureM = adventure.getItemMeta();
					adventureM.setDisplayName("§aSpectateur");
					adventure.setItemMeta(adventureM);
					
					invGamemode.setItem(1, survival);
					invGamemode.setItem(3, creative);
					invGamemode.setItem(5, spectator);
					invGamemode.setItem(7, adventure);
					player.openInventory(invGamemode);
				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
				if(current.getType() == Material.CHEST) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 player.setGameMode(GameMode.SURVIVAL);
						 player.sendMessage("§2Vous êtes maintenant en survie");
					 }else
						 player.sendMessage("§§Vous devez être dans votre monde");
				
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
						 player.setGameMode(GameMode.SURVIVAL);
						 player.sendMessage("§2Vous êtes maintenant en survie");
					 }
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
						 player.setGameMode(GameMode.SURVIVAL);
						 player.sendMessage("§2Vous êtes maintenant en survie");
					 }
				}
				
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
				if(current.getType() == Material.BRICK) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 player.setGameMode(GameMode.CREATIVE);
						 player.sendMessage("§2Vous êtes maintenant en Créatif");
					 }else {
						 player.sendMessage("§2Vous devez être dans votre monde");
					 }
						
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
						 player.setGameMode(GameMode.CREATIVE);
						 player.sendMessage("§2Vous êtes maintenant en Créatif");
					 }
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
						 player.setGameMode(GameMode.CREATIVE);
						 player.sendMessage("§2Vous êtes maintenant en Créatif");
					 }
				}
				
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
				if(current.getType() == Material.EYE_OF_ENDER) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 player.setGameMode(GameMode.SPECTATOR);
						 player.sendMessage("§2Vous êtes maintenant en Spectateur");
					 }else {
						 player.sendMessage("§2Vous devez être dans votre monde");
					 }
						
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
						 player.setGameMode(GameMode.SPECTATOR);
						 player.sendMessage("§2Vous êtes maintenant en Spectateur");
					 }
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
						 player.setGameMode(GameMode.SPECTATOR);
						 player.sendMessage("§2Vous êtes maintenant en Spectateur");
					 }
				}
				
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
				if(current.getType() == Material.BOOKSHELF) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 player.setGameMode(GameMode.ADVENTURE);
						 player.sendMessage("§2Vous êtes maintenant en Aventure");
					 }else {
						 player.sendMessage("§2Vous devez être dans votre monde");
					 }
						
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
						 player.setGameMode(GameMode.ADVENTURE);
						 player.sendMessage("§2Vous êtes maintenant en Aventure");
					 }
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
						 player.setGameMode(GameMode.ADVENTURE);
						 player.sendMessage("§2Vous êtes maintenant en Aventure");
					 }
				}
				
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.JACK_O_LANTERN) {
					event.setCancelled(true);
					player.closeInventory();
					Inventory invinv = Bukkit.createInventory(null, 9, "§8SurvivalCreateInvite");
					
					ItemStack invite = new ItemStack(Material.BED, 1);
					ItemMeta inviteM = invite.getItemMeta();
					inviteM.setDisplayName("Inviter un ami");
					invite.setItemMeta(inviteM);
					
					ItemStack publicworld = new ItemStack(Material.COMPASS, 1);
					ItemMeta publicworldM = publicworld.getItemMeta();
					publicworldM.setDisplayName("Rejoindre un Monde en Public");
					publicworld.setItemMeta(publicworldM);
					
					ItemStack switchworld = new ItemStack(Material.EMERALD, 1);
					ItemMeta switchworldM = switchworld.getItemMeta();
					switchworldM.setDisplayName("Mettre votre monde en public");
					switchworld.setItemMeta(switchworldM);
					
					invinv.setItem(2, invite);
					invinv.setItem(4, publicworld);
					invinv.setItem(6, switchworld);
					player.openInventory(invinv);
					
					
				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateInvite")) {
				if(current.getType() == Material.BED) {
					event.setCancelled(true);
					player.closeInventory();
					player.sendMessage("§2Pour inviter un ami /scinvite (Pseudo du joueur)");
   			}
  }
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateInvite")) {
				if(current.getType() == Material.COMPASS) {
					event.setCancelled(true);
					player.closeInventory();
					player.sendMessage("Bientot");
					
   			}
  }
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.DIAMOND_CHESTPLATE) {
					event.setCancelled(true);
					player.closeInventory();
					
					Inventory invdifi = Bukkit.createInventory(null, 27, "§8SurvivalCreateDifficulty");
					ItemStack peace = new ItemStack(Material.IRON_BLOCK, 1);
					ItemMeta peaceM = peace.getItemMeta();
					peaceM.setDisplayName("Paisible");
					peace.setItemMeta(peaceM);
					
					
					ItemStack facile = new ItemStack(Material.GOLD_BLOCK, 1);
					ItemMeta facileM = facile.getItemMeta();
					facileM.setDisplayName("Facile");
					facile.setItemMeta(facileM);
					
					ItemStack normal = new ItemStack(Material.DIAMOND_BLOCK, 1);
					ItemMeta normalM = normal.getItemMeta();
					normalM.setDisplayName("Normal");
					normal.setItemMeta(normalM);
					
					ItemStack hard = new ItemStack(Material.EMERALD_BLOCK, 1);
					ItemMeta hardM = hard.getItemMeta();
					hardM.setDisplayName("Dificile");
					hard.setItemMeta(hardM);
					
					invdifi.setItem(10, peace);
					invdifi.setItem(12, facile);
					invdifi.setItem(14, normal);
					invdifi.setItem(16, hard);
					player.openInventory(invdifi);
				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
				if(current.getType() == Material.IRON_BLOCK) {
					player.closeInventory();
					if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						event.setCancelled(true);
						Bukkit.getWorld(name + "SurvivalCreateWorld").setDifficulty(Difficulty.PEACEFUL);
						player.sendMessage("§2La difficulté a été changé");
					}else {
						 player.sendMessage("§2Vous devez être dans votre monde");
					 }
				}
	  }  
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
				if(current.getType() == Material.GOLD_BLOCK) {
					player.closeInventory();
					if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						event.setCancelled(true);
						Bukkit.getWorld(name + "SurvivalCreateWorld").setDifficulty(Difficulty.EASY);
						player.sendMessage("§2La difficulté a été changé");
					} else {
						 player.sendMessage("§2Vous devez être dans votre monde");
					 }
				}
	 }
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
				if(current.getType() == Material.DIAMOND_BLOCK) {
					player.closeInventory();
					if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						event.setCancelled(true);
						Bukkit.getWorld(name + "SurvivalCreateWorld").setDifficulty(Difficulty.NORMAL);
						player.sendMessage("§2La difficulté a été changé");
					}else {
						 player.sendMessage("§2Vous devez être dans votre monde");
					 }
				}
	 }
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
				if(current.getType() == Material.EMERALD_BLOCK) {
					player.closeInventory();
					if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						event.setCancelled(true);
						Bukkit.getWorld(name + "SurvivalCreateWorld").setDifficulty(Difficulty.HARD);
						player.sendMessage("§2La difficulté a été changé");
						
					}else {
						 player.sendMessage("§2Vous devez être dans votre monde");
					 }
				}
	 }
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.BARRIER) {
					event.setCancelled(true);
					player.closeInventory();
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld_nether");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld_the_end");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_nether");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_the_end");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world delete " + name + "SurvivalCreateWorld");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world delete " + name + "SurvivalCreateWorld_nether");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world delete " + name + "SurvivalCreateWorld_the_end");
					player.sendMessage("§2Votre monde est maintenant supprimé");
					
					
   }
  }

					
					
   
  
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				Inventory invborder = Bukkit.createInventory(null, 27, "§8SurvivalCreateBorder");
				if(current.getType() == Material.BEACON) {
					event.setCancelled(true);
					player.closeInventory();
					
					if(player.hasPermission("sc.vip")) {
						ItemStack  borderdefault = new ItemStack(Material.GLASS, 1);
						ItemMeta borderdefaultM = borderdefault.getItemMeta();
						borderdefaultM.setDisplayName("Mettre la limite de votre monde a 10 000 blocs");
						borderdefault.setItemMeta(borderdefaultM);
						
						ItemStack  bordervip = new ItemStack(Material.IRON_BLOCK, 1);
						ItemMeta bordervipM = bordervip.getItemMeta();
						bordervipM.setDisplayName("Mettre la limite de votre monde a 30 000 blocs");
						bordervip.setItemMeta(bordervipM);
						
						ItemStack  bordervip2 = new ItemStack(Material.BARRIER, 1);
						ItemMeta bordervip2M = bordervip2.getItemMeta();
						bordervip2M.setDisplayName("Mettre la limite de votre monde a 60 000 blocs(Seulement pour les V.I.P+)");
						bordervip2.setItemMeta(bordervip2M);
						
						ItemStack  bordervip3 = new ItemStack(Material.BARRIER, 1);
						ItemMeta bordervip3M = bordervip3.getItemMeta();
						bordervip3M.setDisplayName("Mettre la limite de votre monde a 120 000 blocs(Seulement pour les V.I.P++)");
						bordervip3.setItemMeta(bordervip3M);
						
						invborder.setItem(10, borderdefault);
						invborder.setItem(12, bordervip);
						invborder.setItem(14, bordervip2);
						invborder.setItem(16, bordervip3);
						
						if(player.hasPermission("sc.vip2")) {
							ItemStack  borderdefaultv2 = new ItemStack(Material.GLASS, 1);
							ItemMeta borderdefaultMv2 = borderdefaultv2.getItemMeta();
							borderdefaultMv2.setDisplayName("Mettre la limite de votre monde a 10 000 blocs");
							borderdefaultv2.setItemMeta(borderdefaultMv2);
							
							ItemStack  bordervipv2 = new ItemStack(Material.IRON_BLOCK, 1);
							ItemMeta bordervipMv2 = bordervipv2.getItemMeta();
							bordervipMv2.setDisplayName("Mettre la limite de votre monde a 30 000 blocs");
							bordervipv2.setItemMeta(bordervipMv2);
							
							ItemStack  bordervip2v2 = new ItemStack(Material.GOLD_BLOCK, 1);
							ItemMeta bordervip2Mv2 = bordervip2v2.getItemMeta();
							bordervip2Mv2.setDisplayName("Mettre la limite de votre monde a 60 000 blocs");
							bordervip2v2.setItemMeta(bordervip2Mv2);
							
							ItemStack  bordervip3v2 = new ItemStack(Material.BARRIER, 1);
							ItemMeta bordervip3Mv2 = bordervip3v2.getItemMeta();
							bordervip3Mv2.setDisplayName("Mettre la limite de votre monde a 120 000 blocs(Seulement pour les V.I.P++)");
							bordervip3v2.setItemMeta(bordervip3Mv2);
							invborder.setItem(10, borderdefaultv2);
							invborder.setItem(12, bordervipv2);
							invborder.setItem(14, bordervip2v2);
							invborder.setItem(16, bordervip3v2);
							
							if(player.hasPermission("sc.vip3")) {
								ItemStack  borderdefaultv3 = new ItemStack(Material.GLASS, 1);
								ItemMeta borderdefaultMv3 = borderdefaultv3.getItemMeta();
								borderdefaultMv3.setDisplayName("Mettre la limite de votre monde a 10 000 blocs");
								borderdefaultv3.setItemMeta(borderdefaultMv3);
								
								ItemStack  bordervipv3 = new ItemStack(Material.IRON_BLOCK, 1);
								ItemMeta bordervipMv3 = bordervipv3.getItemMeta();
								bordervipMv3.setDisplayName("Mettre la limite de votre monde a 30 000 blocs");
								bordervipv3.setItemMeta(bordervipMv3);
								
								ItemStack  bordervip2v3 = new ItemStack(Material.GOLD_BLOCK, 1);
								ItemMeta bordervip2Mv3 = bordervip2v3.getItemMeta();
								bordervip2Mv3.setDisplayName("Mettre la limite de votre monde a 60 000 blocs");
								bordervip2v3.setItemMeta(bordervip2Mv3);
								
								ItemStack  bordervip3v3 = new ItemStack(Material.DIAMOND_BLOCK, 1);
								ItemMeta bordervip3Mv3 = bordervip3v3.getItemMeta();
								bordervip3Mv3.setDisplayName("Mettre la limite de votre monde a 120 000 blocs");
								bordervip3v3.setItemMeta(bordervip3Mv3);
								invborder.setItem(10, borderdefaultv3);
								invborder.setItem(12, bordervipv3);
								invborder.setItem(14, bordervip2v3);
								invborder.setItem(16, bordervip3v3);
									
							}
						}
					}else
					{
						ItemStack  borderdefault = new ItemStack(Material.GLASS, 1);
						ItemMeta borderdefaultM = borderdefault.getItemMeta();
						borderdefaultM.setDisplayName("Mettre la limite de votre monde a 10 000 blocs");
						borderdefault.setItemMeta(borderdefaultM);
						
						ItemStack  bordervip = new ItemStack(Material.BARRIER, 1);
						ItemMeta bordervipM = bordervip.getItemMeta();
						bordervipM.setDisplayName("Mettre la limite de votre monde a 30 000 blocs(Seulement pour les V.I.P)");
						bordervip.setItemMeta(bordervipM);
						
						ItemStack  bordervip2 = new ItemStack(Material.BARRIER, 1);
						ItemMeta bordervip2M = bordervip2.getItemMeta();
						bordervip2M.setDisplayName("Mettre la limite de votre monde a 60 000 blocs(Seulement pour les V.I.P+)");
						bordervip2.setItemMeta(bordervip2M);
						
						ItemStack  bordervip3 = new ItemStack(Material.BARRIER, 1);
						ItemMeta bordervip3M = bordervip3.getItemMeta();
						bordervip3M.setDisplayName("Mettre la limite de votre monde a 120 000 blocs(Seulement pour les V.I.P++)");
						bordervip3.setItemMeta(bordervip3M);
						
						invborder.setItem(10, borderdefault);
						invborder.setItem(12, bordervip);
						invborder.setItem(14, bordervip2);
						invborder.setItem(16, bordervip3);
					}
					player.openInventory(invborder);
				}
			}
	   
				
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
			if(current.getType() == Material.GLASS) {
						player.closeInventory();
						World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
						
						WorldBorder border = world.getWorldBorder();
						
						border.setSize(10000.0);
						border.setCenter(0.0, 0.0);
						
						player.sendMessage("§2La limite de votre monde est maintenant de 10 000 blocs");
				 
					}
    }
	   
				
		if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
			if(current.getType() == Material.IRON_BLOCK) {
				if(player.hasPermission("sc.vip")) {
					player.closeInventory();
					World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
					
					WorldBorder border = world.getWorldBorder();
					
					border.setSize(30000.0);
					border.setCenter(0.0, 0.0);
					
					player.sendMessage("§2La limite de votre monde est maintenant de 30 000 blocs");
						}
						
  }


		if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
			if(current.getType() == Material.GOLD_BLOCK) {
				if(player.hasPermission("sc.vip2")) {
					player.closeInventory();
					World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
					
					WorldBorder border = world.getWorldBorder();
					
					border.setSize(60000.0);
					border.setCenter(0.0, 0.0);
					
					player.sendMessage("§2La limite de votre monde est maintenant de 60 000 blocs");
						}
						
    }
   }		

		if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
			if(current.getType() == Material.DIAMOND_BLOCK) {
				if(player.hasPermission("sc.vip3")) {
					player.closeInventory();
					World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
					
					WorldBorder border = world.getWorldBorder();
					
					border.setSize(120000.0);
					border.setCenter(0.0, 0.0);
					
					player.sendMessage("§2La limite de votre monde est maintenant de 120 000 blocs");
						}
						
    }	   
   }
  
		if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
			if(current.getType() == Material.BARRIER) {
				player.closeInventory();
				player.sendMessage("§cVous n'avez pas la permission, venez l'acheter sur la boutique §6https://endide-shop.tebex.io/");
    }
   }

}
}
}