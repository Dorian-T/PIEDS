package main.model;

/**
 * Represents the colors of the game.
 */
public enum Color {

	// === Constants ===

	/**
	 * The blue color.
	 */
	BLUE('0'),

	/**
	 * The green color.
	 */
	GREEN('1'),

	/**
	 * The orange color.
	 */
	ORANGE('2'),

	/**
	 * The purple color.
	 */
	PURPLE('3'),

	/**
	 * The red color.
	 */
	RED('4'),

	/**
	 * The yellow color.
	 */
	YELLOW('5');


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
	 * @param id The identifier of the color.
	 * @return The color corresponding to the given identifier.
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
