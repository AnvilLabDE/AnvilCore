package anvil.Minefabser.API.display;

import java.text.MessageFormat;

public class MenuClickable extends Clickable {

	public MenuClickable(boolean missingArgs, String value, Object... args) {
		super(	missingArgs? ClickAction.SUGGEST_COMMAND : ClickAction.RUN_COMMAND, 
				missingArgs? MessageFormat.format(value, args) : value);
	}

}
