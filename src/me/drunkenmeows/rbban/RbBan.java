package me.drunkenmeows.rbban;

import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RbBan extends JavaPlugin	{

	@Override
	public void onDisable() {
		
	}
	
	@Override
	public void onEnable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)	{	
		if(commandLabel.equalsIgnoreCase("rbban") && (sender instanceof Player) && (sender.hasPermission("rbban.admin"))) {
			if(args.length > 0)
			{
				Player p = (Player)sender;
				String name = args[0];
				String msg = "griefing.";
				
				if(args.length > 1)
					msg = args[1];
				
				//get offline player
				OfflinePlayer offline = getServer().getOfflinePlayer(name);
				
				//set the player as banned
				offline.setBanned(true);
				//kick them
				if(offline.isOnline())
					getServer().getPlayer(name).kickPlayer("You were BANNED for "+ msg);					
				
				String world = p.getWorld().getName();
								
				String command = "lb rb world "+world+" player "+name+" since 10d";
				getServer().dispatchCommand(sender, command);
				
				getServer().broadcastMessage(command);
				
				getServer().broadcastMessage("Player "+name+" has been ROLLED-BACK 10 days in "+ world);
				getServer().broadcastMessage("Player "+name+" has been BANNED for "+msg);
			
			} else {
				//no player name
			}
			
		}
		
		return true;

	}
	
}
