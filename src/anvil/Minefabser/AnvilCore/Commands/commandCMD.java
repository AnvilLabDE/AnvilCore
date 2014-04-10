package anvil.Minefabser.AnvilCore.Commands;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_7_R2.CraftServer;
import org.json.simple.parser.ParseException;

import anvil.Minefabser.API.base.AnvilCommand;
import anvil.Minefabser.API.base.AnvilSender;
import anvil.Minefabser.API.base.AnvilSubject;
import anvil.Minefabser.API.base.SubjectManager;
import anvil.Minefabser.API.display.ClickAction;
import anvil.Minefabser.API.display.Clickable;
import anvil.Minefabser.API.display.Menu;
import anvil.Minefabser.API.display.Option;
import anvil.Minefabser.API.display.Value;
import anvil.Minefabser.API.handler.LevelPresets;
import anvil.Minefabser.API.handler._;

public class commandCMD extends AnvilCommand {

	public commandCMD() throws SQLException, ParseException {
		super(	"command", 
				LevelPresets.ADMINISTRATE, 
				"Befehl um Befehle zu bearbeiten", 
				"/command", 
				"cmd");
	}

	@Override
	public void execute(AnvilSender sender, String alias, String[] args) {
		if (args.length == 2 || args.length == 3) {
			Command command = ((CraftServer) Bukkit.getServer()).getCommandMap().getCommand(args[0]);
			if (command != null) {
				if (args[1].equalsIgnoreCase("enabled") || args[1].equalsIgnoreCase("level")) {
					if (command instanceof AnvilCommand) {
						if (args[1].equalsIgnoreCase("enabled"))
							if (args.length == 3)
								if (args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")) {
									boolean setEnabled = Boolean.valueOf(args[2].toUpperCase());
									
									if (!setEnabled && ((AnvilCommand) command).isEnabled()) {
										try {
											((AnvilCommand) command).setEnabled(setEnabled);
										} catch (SQLException e) {
											sender.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
										}
										sender.sendPlusChat(this.getLevel(), _.plus_commandSettingChanged, sender.getDisplayName(), super.getName(), "Aktiviert", setEnabled);
									} else
									if (setEnabled && !((AnvilCommand) command).isEnabled()) {
										try {
											((AnvilCommand) command).setEnabled(setEnabled);
										} catch (SQLException e) {
											sender.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
										}
										sender.sendPlusChat(this.getLevel(), _.plus_commandSettingChanged, sender.getDisplayName(), super.getName(), "Aktiviert", setEnabled);
									} else
										sender.sendMessage(_.commandCMD_alreadyStatus, command.getName());
								} else
									sender.sendMessage(_.error_noBoolean, args[2]);
							//TODO else
					} else
						sender.sendMessage(_.command_notAnAnvilCommand, command.getName());
				} else
				if (args.length == 3 && (args[1].equalsIgnoreCase("allow") || args[1].equalsIgnoreCase("deny") || args[0].equalsIgnoreCase("check"))) {
					AnvilSubject subject = SubjectManager.getSubject(args[2]);
					if (subject != null) {
						if (sender.beats(subject)) {
							// TODO
						} else
							sender.sendMessage(_.error_cannotBeat, subject.getDisplayName());
					} else
						sender.sendMessage(_.subject_cannotFind, args[2]);
				} else
					showHelp(sender, command.getName(), null);
			} else
				sender.sendMessage(_.command_cannotFind, args[0]);
		} else
			showHelp(sender, null, null);
	}

	@Override
	public List<String> tabComplete(AnvilSender sender, String alias, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void showHelp(AnvilSender sender, String commandName, String subjectName) {
		if (commandName == null)
			commandName = "<Befehl>";
		if (subjectName == null)
			subjectName = "<Subjekt>";
		
		Menu menu = new Menu("Hilfe für /command");
		
		Option opt1 = new Option("Anvil-Befehle");
		opt1.addValues(
				new Value("/command " + commandName + " enabled [<true|false>]", "Schaltet " + commandName + " an/aus oder zeigt den Status", 
						new Clickable(ClickAction.SUGGEST_COMMAND, "/command " + commandName + " enabled ")),
				new Value("/command " + commandName + " level [<Level>]", "Setzt das Level von " + commandName + " oder zeigt es an", 
						new Clickable(ClickAction.SUGGEST_COMMAND, "/command " + commandName + " level "))
				);
		
		Option opt2 = new Option("Alle Befehle");
		opt2.addValues(
				new Value("/command " + commandName + " allow " + subjectName, "Gibt " + subjectName + " die Permissions für den Befehl " + commandName, 
						new Clickable(ClickAction.SUGGEST_COMMAND, "/command " + commandName + " allow " + subjectName)),
				new Value("/command " + commandName + " deny " + subjectName, "Nimmt " + subjectName + " die Permissions für den Befehl " + commandName, 
						new Clickable(ClickAction.SUGGEST_COMMAND, "/command " + commandName + " deny " + subjectName)),
				new Value("/command " + commandName + " check " + subjectName, "Zeigt ob " + subjectName + " die Permissions für den Befehl " + commandName + " hat", 
						new Clickable(ClickAction.SUGGEST_COMMAND, "/command " + commandName + " check " + subjectName))	
				);
		
		menu.addOptions(opt1, opt2);
		
		menu.showMenu(sender);
	}

}
