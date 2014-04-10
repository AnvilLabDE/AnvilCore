package anvil.Minefabser.API.base;

import java.sql.SQLException;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

import anvil.Minefabser.API.handler.ConsoleHandler;
import anvil.Minefabser.API.handler.PlusChat;
import anvil.Minefabser.API.handler._;
import anvil.Minefabser.API.interfaces.IMessageable;
import anvil.Minefabser.AnvilCore.AnvilMain;

public class AnvilSender extends AnvilSubject implements IMessageable {
	
	private CommandSender	cs;

	/**
	 * Erstellt/Lädt einen AnvilSender basierend auf einem AnvilUser
	 * @param {@link CommandSender} der dem AnvilSender zugeordnet sein soll
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public AnvilSender(CommandSender cs) throws SQLException, ParseException {
		super(cs instanceof Player? ((Player) cs).getUniqueId().toString() : cs.getName());
		
		if (cs instanceof Player && super.getDisplayName().equals(((Player) cs).getUniqueId().toString()))
			super.setDisplayName(cs.getName());
		
		this.cs = cs;
		
		SubjectManager.addSubject(this);
	}
	
	/**
	 * Gibt den {@link CommandSender} zum AnvilUser zurück
	 * 
	 * @return {@link CommandSender}
	 */
	public CommandSender getCommandSender() {
		return this.cs;
	}
	
	@Override
	public void sendMessage(String message, Object... args) {
		if (this.cs instanceof ConsoleCommandSender)
			ConsoleHandler.sendMessage(message, args);
		else
			this.cs.sendMessage(_.format(message, args));
	}
	
	@Override
	public void sendPlusChat(int level, String message, Object... args) {
		PlusChat.sendMessage(this, level, message, args);
	}
	
	@Override
	public void givePermission(String permission) {
		this.cs.addAttachment(AnvilMain.getPlugin(), permission, true);
	}

	@Override
	public void removePermission(String permission) {
		this.cs.addAttachment(AnvilMain.getPlugin(), permission, false);
	}

	@Override
	public boolean hasPermission(String permission) {
		return this.cs.hasPermission(permission);
	}

}
