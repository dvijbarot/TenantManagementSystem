package main.SetClass.src;

import java.util.Scanner;

class SetDriver {

	private Scanner scanner;
	private SetClass<Identifiable> setClass;

	public SetDriver(Scanner scanner) {
		this.scanner = scanner;
		setClass = new SetClass<>();
	}

	public void run() {
		while (true) {
			System.out.println("Enter a command (add, remove, peek, size, display, equals, quit): ");
			String command = scanner.nextLine();

			if (command.equals("add")) {
				System.out.println("Enter an ID to add: ");
				int id = scanner.nextInt();
				scanner.nextLine(); // consume the newline character
				Identifiable element = new Identifiable(id);
				setClass.add(element);
			} else if (command.equals("remove")) {
				System.out.println("Enter an ID to remove: ");
				int id = scanner.nextInt();
				scanner.nextLine(); // consume the newline character
				setClass.remove(id);
			} else if (command.equals("peek")) {
				System.out.println("Enter an ID to peek: ");
				int id = scanner.nextInt();
				scanner.nextLine(); // consume the newline character
				if (setClass.peek(id)) {
					System.out.println("Element with ID " + id + " exists in the set");
				} else {
					System.out.println("Element with ID " + id + " does not exist in the set");
				}
			} else if (command.equals("size")) {
				System.out.println("Set size is: " + setClass.size());
			} else if (command.equals("display")) {
				setClass.display();
			} else if (command.equals("equals")) {
				System.out.println("Enter a set to compare: ");
				SetClass<Identifiable> otherSet = new SetClass<>();
				System.out.println("Enter the number of elements in the set: ");
				int numElements = scanner.nextInt();
				scanner.nextLine(); // consume the newline character
				for (int i = 0; i < numElements; i++) {
					System.out.println("Enter an ID for element " + (i + 1) + ": ");
					int id = scanner.nextInt();
					scanner.nextLine(); // consume the newline character
					Identifiable element = new Identifiable(id);
					otherSet.add(element);
				}
				if (setClass.equals(otherSet)) {
					System.out.println("The sets are equal");
				} else {
					System.out.println("The sets are not equal");
				}
			} else if (command.equals("quit")) {
				System.out.println("Exiting...");
				break;
			} else {
				System.out.println("Invalid command");
			}
		}
	}
}
