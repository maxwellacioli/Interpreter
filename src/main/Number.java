package main;
public class Number extends Data {
	int value;

	public Number(int value, int dataLocation) {
		this.value = value;
		super.location= dataLocation;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}
