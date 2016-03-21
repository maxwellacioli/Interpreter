package main;
public class Number extends Data {
	int value;

	public Number(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Integer.toString(value);
	}
}
