package anvil.Minefabser.API.display;

import net.minecraft.server.v1_7_R2.EnumClickAction;

/**
 * Repräsentiert EnumClickAction (NMS)
 */
public enum ClickAction {
	
	OPEN_FILE		(EnumClickAction.OPEN_FILE),
	OPEN_URL		(EnumClickAction.OPEN_URL),
	RUN_COMMAND		(EnumClickAction.RUN_COMMAND),
	SUGGEST_COMMAND	(EnumClickAction.SUGGEST_COMMAND),
	;
	
	private EnumClickAction enumClickAction;
	
	private ClickAction(EnumClickAction enumClickAction) {
		this.enumClickAction = enumClickAction;
	}
	
	/**
	 * Gibt das EnumClickAction (NMS) zurück, welches ClickAction repräsentiert
	 * @return EnumClickAction (NMS)
	 */
	public EnumClickAction getEnumClickAction() {
		return this.enumClickAction;
	}

}
