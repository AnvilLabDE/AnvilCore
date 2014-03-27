package anvil.Minefabser.API.interfaces;

public interface IMessageable {
	
	/**
	 * Sendet dem Objekt eine Nachricht
	 * @param Nachricht
	 * @param Eventuell zu erg�nzende Argumente
	 */
	public void sendMessage(String message, Object... args);

}
