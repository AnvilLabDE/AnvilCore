package anvil.Minefabser.API.handler;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AnvilParser {
	
	/**
	 * Konvertiert eine {@link Location} zu einem {@link String}
	 * 
	 * @param {@link Location}
	 * @return {@link String}
	 */
	public static String locationToString(Location loc) {
		String world, x, y, z, yaw, pitch;
		
		world	= loc.getWorld().getName();
		x		= String.valueOf(loc.getX());
		y		= String.valueOf(loc.getY());
		z		= String.valueOf(loc.getZ());
		yaw		= String.valueOf(loc.getYaw());
		pitch	= String.valueOf(loc.getPitch());
		
		if (!yaw.equals("0") && !pitch.equals("0"))
			return world + ";" + x + ";" + y + ";" + z + ";" + yaw + ";" + pitch;
		else
			return world + ";" + x + ";" + y + ";" + z;
	}
	
	/**
	 * Konvertiert einen {@link CommandSender} zu einer UniqueID ({@link UUID})
	 * 
	 * @param {@link CommandSender}
	 * @return UniqueID - {@link UUID}
	 */
	public static UUID senderToUniqueID(CommandSender cs) {
		if (cs instanceof Player)
			return ((Player) cs).getUniqueId();
		
		if (cs instanceof ConsoleCommandSender)
			return UUID.fromString("3c00b58d-a066-4f52-a2b7-615641dcbe00"); // MojangUser: Server
		
		if (cs instanceof BlockCommandSender)
			return UUID.fromString("8e22931b-a3c4-4588-8e32-4ba3e45fd423"); // MojangUser: CommandBlock
		
		return null;
	}

}
