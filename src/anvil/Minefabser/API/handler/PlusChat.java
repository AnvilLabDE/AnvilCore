package anvil.Minefabser.API.handler;

import anvil.Minefabser.API.base.AnvilSender;

public class PlusChat {
	
	public static void sendMessage(AnvilSender sender, int level, String message, Object... args) {
		sender.sendMessage("+TEMP+ " + message, args);
	}

}
