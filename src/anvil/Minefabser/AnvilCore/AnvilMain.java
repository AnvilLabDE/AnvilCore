package anvil.Minefabser.AnvilCore;

import anvil.Minefabser.API.base.AnvilPlugin;
import anvil.Minefabser.API.handler.ConsoleHandler;
import anvil.Minefabser.API.handler._;

public class AnvilMain extends AnvilPlugin {
	
	@Override
	public void onEnable() {
		ConsoleHandler.setupReplacements();
		ConsoleHandler.sendMessage(_.general_startupMessage);
	}

}
