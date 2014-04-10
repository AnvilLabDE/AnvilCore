package anvil.Minefabser.AnvilCore.Commands;

import java.sql.SQLException;
import java.util.List;

import org.json.simple.parser.ParseException;

import anvil.Minefabser.API.base.AnvilCommand;
import anvil.Minefabser.API.base.AnvilSender;
import anvil.Minefabser.API.display.Menu;
import anvil.Minefabser.API.display.MenuClickable;
import anvil.Minefabser.API.display.Option;
import anvil.Minefabser.API.display.Value;
import anvil.Minefabser.API.handler.LevelPresets;

public class subjectCMD extends AnvilCommand {

	public subjectCMD() throws SQLException, ParseException {
		super(	"subject", 
				LevelPresets.ADMINISTRATE, 
				"Befehl zum Verwalten von Subjekten", 
				"/subject", 
				"group", "user");
	}

	@Override
	public void execute(AnvilSender sender, String alias, String[] args) {
		sendHELP(sender, null);
	}

	@Override
	public List<String> tabComplete(AnvilSender sender, String alias, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private void sendHELP(AnvilSender sender, String subjectName) {
		boolean missingArgs = subjectName == null;
		if (subjectName == null)
			subjectName = "<Subjekt>";
		
		Menu help = new Menu("Hilfe für /subject");
		
		Option	generalOpt	= new Option("Generell");
		Option	groupOpt	= new Option("Gruppen");
		Option	senderOpt	= new Option("Sender");
		Option	playerOpt	= new Option("Spieler");
		
		generalOpt.addValues(new Value("/subject info " + subjectName, "Zeigt Informationen zu " + subjectName, new MenuClickable(missingArgs, "/subject info {0}", subjectName)));
		groupOpt.addValues(new Value("/subject createGroup <Name>", "Erstellt die Gruppe <Name>", new MenuClickable(missingArgs, "/subject createGroup {0}", subjectName)));
		
		help.addOptions(generalOpt, groupOpt, senderOpt, playerOpt);
		help.showMenu(sender);
	}

}
