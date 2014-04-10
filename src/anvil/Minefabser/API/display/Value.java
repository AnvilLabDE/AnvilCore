package anvil.Minefabser.API.display;

/**
 * Ein Wert f�r das {@link Menu} bzw. die {@link Option}
 */
public class Value {
	
	private String		display, description;
	private Clickable	clickable;
	
	/**
	 * Erstellt einen neuen Wert (Value)
	 * @param Der Text, der angezeigt werden soll
	 * @param Die Beschreibung des Punkts
	 * @param {@link Clickable} - Was soll beim Klicken passieren
	 */
	public Value(String display, String description, Clickable clickable) {
		this.display		= display;
		this.description	= description;
		this.clickable		= clickable;
	}
	
	/**
	 * Gibt den Text der angezeigt werden soll zur�ck
	 * @return Text, der angezeigt werden soll
	 */
	public String getDisplay() {
		return this.display;
	}
	
	/**
	 * Gibt die Beschreibung zur�ck
	 * @return Beschreibung
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Gibt das {@link Clickable} zur�ck
	 * @return {@link Clickable}
	 */
	public Clickable getClickable() {
		return this.clickable;
	}

}
