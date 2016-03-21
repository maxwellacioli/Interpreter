package main;
public enum InstructionType {
	ADD(00000001), AND(00000010), LOAD(00000011), STORE(00000100), MOVE(
			00000101), JUMP(00000110), HALT(00000111);

	private int instructiontype;

	private InstructionType(int type) {
		instructiontype = type;
	}

	public int getInstructionType() {
		return instructiontype;
	}

}
