package main.SetClass.src;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Identifiable {
	private int id;

	public Identifiable(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}
}

class SetClass<T extends Identifiable> {
	private Set<T> set;

	public SetClass() {
		set = new HashSet<>();
	}

	public void add(T element) {
		if (!set.contains(element)) {
			set.add(element);
		} else {
			System.out.println("Element already exists in the set");
		}
	}

	public T remove(int id) {
		for (T element : set) {
			if (element.getID() == id) {
				set.remove(element);
				return element;
			}
		}
		System.out.println("Element with ID " + id + " not found in the set");
		return null;
	}

	public boolean peek(int id) {
		for (T element : set) {
			if (element.getID() == id) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return set.size();
	}

	public boolean equals(SetClass<T> otherSet) {
		if (set.size() != otherSet.size()) {
			return false;
		}
		for (T element : set) {
			if (!otherSet.peek(element.getID())) {
				return false;
			}
		}
		return true;
	}

	public void display() {
		for (T element : set) {
			System.out.println("Element with ID " + element.getID());
		}
	}
}

public class SetClassView {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		SetDriver driver = new SetDriver(scanner);

		// Call the run method of the SetDriver instance
		driver.run();

		// Close the scanner
		scanner.close();

	}
}

//Scanner scanner = new Scanner(System.in);
//SetClass<Identifiable> set = new SetClass<>();
//
//// Ask the user to input the number of elements to add to the set
//System.out.print("Enter the number of elements to add to the set: ");
//int numElements = scanner.nextInt();
//
//// Ask the user to input the ID for each element and add it to the set
//for (int i = 0; i < numElements; i++) {
//	System.out.print("Enter the ID for element " + (i + 1) + ": ");
//	int id = scanner.nextInt();
//	set.add(new Identifiable(id));
//}
//
//// Display the set
//set.display();
//
//// Ask the user to input the ID of an element to remove from the set
//System.out.print("Enter the ID of an element to remove from the set: ");
//int idToRemove = scanner.nextInt();
//set.remove(idToRemove);
//set.display();
//
//// Ask the user to input the ID of an element to peek for in the set
//System.out.print("Enter the ID of an element to peek for in the set: ");
//int idToPeek = scanner.nextInt();
//System.out.println("Element with ID " + idToPeek + " exists in the set: " + set.peek(idToPeek));
//
//// Display the size of the set
//System.out.println("Size of the set is " + set.size());
//
//// Create another set and ask the user to input the number of elements to add to
//// it
//SetClass<Identifiable> otherSet = new SetClass<>();
//System.out.print("Enter the number of elements to add to the other set: ");
//int numElementsOtherSet = scanner.nextInt();
//
//// Ask the user to input the ID for each element and add it to the other set
//for (int i = 0; i < numElementsOtherSet; i++) {
//	System.out.print("Enter the ID for element " + (i + 1) + ": ");
//	int id = scanner.nextInt();
//	otherSet.add(new Identifiable(id));
//}
//// Display the other set
//otherSet.display();
//
//// Compare the two sets for equality
//System.out.println("The two sets are equal: " + set.equals(otherSet));
//
//// Close the scanner
//scanner.close();