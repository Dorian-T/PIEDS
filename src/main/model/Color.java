package main.model;

public enum Color {
	BLUE('0'), GREEN('1'), ORANGE('2'), PURPLE('3'), RED('4'), YELLOW('5');

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

	public int toInt() {
		return Integer.parseInt(String.valueOf(colorId));
	}
}
