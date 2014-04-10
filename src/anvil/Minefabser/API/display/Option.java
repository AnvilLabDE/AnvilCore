package anvil.Minefabser.API.display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Eine Option für das {@link Menu}
 */
public class Option {
	
	private String		name;
	private List<Value>	values = new ArrayList<>();
	
	/**
	 * Erstellt eine neue Option mit einem Namen
	 * @param Name der Option
	 */
	public Option(String name) {
		this.name = name;
	}
	
	/**
	 * Gibt den Namen der Option zurück
	 * @return Name der Option
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gibt die Werte ({@link Value}) der Option zurück
	 * @return Werte ({@link Value}) der Option
	 */
	public List<Value> getValues() {
		return this.values;
	}
	
	/**
	 * Fügt einen oder mehrere Werte zur Option hinzu
	 * @param Einen oder mehrere Werte ({@link Value})
	 */
	public void addValues(Value... values) {
		this.values.addAll(Arrays.asList(values));
	}

}
