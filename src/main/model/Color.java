package main.model;

public enum Color {
	BLUE('1'), GREEN('2'), ORANGE('3'), PURPLE('4'), RED('5'), YELLOW('6');

	private char colorId;

	Color(char colorId) {
		this.colorId = colorId;
	}

	public char getColorId() {
		return colorId;
	}

	public static Color fromChar(char id) {
		for (Color color : Color.values())
			if (color.getColorId() == id)
				return color;
		throw new IllegalArgumentException("No color with id: " + id + " found");
	}

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
