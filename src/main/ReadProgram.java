package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadProgram {
	private static Interpreter interpreter;
	static List<Data> memory;

	public static void main(String[] args) {
		ReadProgram rp = new ReadProgram();
		rp.allocateMemory();
		rp.readFile();

		interpreter = new Interpreter();
		interpreter.interpret(memory, 0);
		rp.printMemory(memory);
	}

	private void allocateMemory() {
		memory = new ArrayList<Data>();

		for (int i = 0; i < 100; i++) {
			memory.add(i, null);
		}
	}

	private void readFile() {

		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader("files/input"));

			String brLine = br.readLine();

			int i = 0;
			while (brLine != null) {
				memory.set(i, new Instruction(brLine, i));
				brLine = br.readLine();
				i++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printMemory(List<Data> memory) {
		for (int i = 0; i < memory.size(); i++) {
			if(memory.get(i) !=  null) {
				System.out.println("[" + i + "]" + memory.get(i).toString());
			} else {
				System.out.println("[" + i + "]" + "null");
			}
		} 
		
	}
}
