package main.model;

/**
 * Represents the colors of the game.
 */
public enum Color {

	// === Constants ===

	BLUE('0'), GREEN('1'), ORANGE('2'), PURPLE('3'), RED('4'), YELLOW('5');


	// === Variables ===

	/**
	 * Represents the identifier of a color.
	 */
	private char id;


	// === Constructors ===

	/**
	 * Constructs a color with the given identifier.
	 *
	 * @param colorId The identifier of the color.
	 */
	Color(char colorId) {
		this.id = colorId;
	}

	/**
	 * Returns the color corresponding to the given identifier.
	 *
	 * @param id
	 * @return
	 */
	public static Color fromChar(char id) {
		for (Color color : Color.values())
			if (color.getId() == id)
				return color;
		throw new IllegalArgumentException("No color with id: " + id + " found");
	}


	// === Getters ===

	/**
	 * Returns the identifier of the color.
	 *
	 * @return The identifier of the color.
	 */
	public char getId() {
		return id;
	}


	// === Methods ===

	/**
	 * Converts the color ID to an integer value.
	 *
	 * @return the integer representation of the color ID
	 */
	public int toInt() {
		return Integer.parseInt(String.valueOf(id));
	}
}
