package main;
public class Instruction extends Data {
	private int instructionType;
	private String string;
	
	public Instruction(String str, int dataLocation) {
		string = str;
		super.location = dataLocation; 
	}

	public int getInstructionType() {
		return instructionType;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public void setInstructionType(int instructionType) {
		this.instructionType = instructionType;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return string;
	}
}
