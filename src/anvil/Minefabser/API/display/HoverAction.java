package anvil.Minefabser.API.display;

import net.minecraft.server.v1_7_R2.EnumHoverAction;

/**
 * Repräsentiert EnumHoverAction (NMS)
 */
public enum HoverAction {
	
	SHOW_ACHIEVEMENT(EnumHoverAction.SHOW_ACHIEVEMENT),
	SHOW_ITEM		(EnumHoverAction.SHOW_ITEM),
	SHOW_TEXT		(EnumHoverAction.SHOW_TEXT),
	;
	
	private EnumHoverAction enumHoverAction;
	
	private HoverAction(EnumHoverAction enumHoverAction) {
		this.enumHoverAction = enumHoverAction;
	}
	
	/**
	 * Gibt das EnumHoverAction (NMS) zurück, welches HoverAction repräsentiert
	 * @return EnumHoverAction (NMS)
	 */
	public EnumHoverAction getEnumHoverAction() {
		return this.enumHoverAction;
	}

}
