package main;

import java.util.List;
import java.util.StringTokenizer;

public class Interpreter {
	private int ac; // inutil!
	private int pc;
	private int r1, r2 = 0;
	private Number data;
	private Instruction currentInstruction;
	private boolean runBit;
	private int instructionType;
	private int dataLocation;
	List<Data> memory;

	public Interpreter() {
		runBit = true;
	}

	public void interpret(List<Data> memory, int startAddress) {
		this.memory = memory;
		pc = startAddress;
		while (runBit) {
			currentInstruction = (Instruction) this.memory.get(pc);
			pc++;
			instructionType = getInstructionType(currentInstruction);
			dataLocation = findData(currentInstruction, instructionType);
			if (dataLocation > 0) {
				data = (Number) this.memory.get(dataLocation);
			}
			execute(currentInstruction, data);
		}
		System.out.println("#########################");
	}

	private void execute(Instruction instruction, Number data) {

		if (instructionType == InstructionType.HALT.getInstructionType()) {
			runBit = false;
		} else if (instructionType == InstructionType.ADD.getInstructionType()) {

			String[] st = instruction.getString().split(" ");
			String op1, op2;
			op1 = st[1].toLowerCase();
			op2 = st[2].toLowerCase();

			switch (op1) {
			case "r1":
				if (op2.matches("\\d*")) {
					r1 += Integer.parseInt(op2);
				} else if (op2.matches("m\\[\\d*\\]")) {
					r1 += data.getValue();
				} else {
					r1 += r2;
				}
				System.out.println("r1 = " + r1);
				break;
			case "r2":
				if (op2.matches("\\d*")) {
					r2 += Integer.parseInt(op2);
				} else if (op2.matches("m\\[\\d*\\]")) {
					r2 += data.getValue();
				} else {
					r2 += r1;
				}
				System.out.println("r2 = " + r2);
				break;
			default:
				break;
			}
		} else if (instructionType == InstructionType.MOVE.getInstructionType()) {
			String[] st = instruction.getString().split(" ");
			String op1, op2;
			op1 = st[1].toLowerCase();
			op2 = st[2].toLowerCase();

			switch (op1) {
			case "r1":
				if (op2.matches("\\d*")) {
					r1 = Integer.parseInt(op2);
				} else {
					r1 = r2;
				}
				System.out.println("r1 = " + r1);
				break;
			case "r2":
				if (op2.matches("\\d*")) {
					r2 = Integer.parseInt(op2);
				} else {
					r2 = r1;
				}
				System.out.println("r2 = " + r2);
				break;
			default:
				break;
			}
		} else if (instructionType == InstructionType.STORE
				.getInstructionType()) {
			String[] st = instruction.getString().split(" ");
			String op2;
			op2 = st[2].toLowerCase();

			if (op2.matches("\\d*")) {
				memory.set(dataLocation, new Number(Integer.parseInt(op2), dataLocation));
			} else if (op2.matches("r1")) {
				memory.set(dataLocation, new Number(r1, dataLocation));
			} else if (op2.matches("r2")) {
				memory.set(dataLocation, new Number(r2, dataLocation));
			}
			System.out.println("m[" + dataLocation + "] = "
					+ memory.get(dataLocation));
		} else if (instructionType == InstructionType.LOAD.getInstructionType()) {
			String[] st = instruction.getString().split(" ");
			String op1;
			op1 = st[1].toLowerCase();
			
			String[] aux = instruction.getString().split("m\\[");
			String dataAux = aux[1].split("\\]")[0];
			data.setLocation(Integer.parseInt(dataAux));

			if (op1.equals("r1")) {
				
				r1 = data.getValue();
				System.out.println("r1 = " + r1);
			} else if (op1.equals("r2")) {
				r2 = data.getValue();
				
				System.out.println("r2 = " + r2);
			}
		}
	}

	private int findData(Instruction instruction, int instructionType) {
		int data = -1;
		if (instruction.getString().matches(".*m\\[\\d*\\].*")) {
			String[] aux = instruction.getString().split("m\\[");
			String dataAux = aux[1].split("\\]")[0];
			data = Integer.parseInt(dataAux);
		}
		return data;
	}

	private int getInstructionType(Instruction instruction) {
		System.out.println(instruction.getString().toString());
		StringTokenizer st = new StringTokenizer(instruction.getString());
		String type = st.nextToken();
		switch (type.toUpperCase()) {
		case "ADD":
			instruction.setInstructionType(InstructionType.ADD
					.getInstructionType());
			return InstructionType.ADD.getInstructionType();
		case "AND":
			instruction.setInstructionType(InstructionType.AND // TODO
																// implementar
					.getInstructionType());
			return InstructionType.AND.getInstructionType();
		case "LOAD":
			instruction.setInstructionType(InstructionType.LOAD // TODO
																// implementar
					.getInstructionType());
			return InstructionType.LOAD.getInstructionType();
		case "STORE":
			instruction.setInstructionType(InstructionType.STORE
					.getInstructionType());
			return InstructionType.STORE.getInstructionType();
		case "MOVE":
			instruction.setInstructionType(InstructionType.MOVE
					.getInstructionType());
			return InstructionType.MOVE.getInstructionType();
		case "JUMP":
			instruction.setInstructionType(InstructionType.JUMP // TODO
																// implementar
					.getInstructionType());
			return InstructionType.JUMP.getInstructionType();
		case "HALT":
			instruction.setInstructionType(InstructionType.HALT
					.getInstructionType());
			return InstructionType.HALT.getInstructionType();
		default:
			return -1;
		}
	}
}
