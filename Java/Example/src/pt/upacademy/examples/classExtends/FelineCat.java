package pt.upacademy.examples.classExtends;

public class FelineCat {

	public static void main(String[] args) {
		new Cat().getType();
		new Tiger().getType();

	}

}

abstract class Feline {
	public char type = 'f';

	public Feline() {
		System.out.println("Feline");
	}

	public Feline(char t) {
		this.type = t;
		System.out.println("Feline with params");
	}

	public void getType() {
		System.out.println("Type : " + type);
	}
}

class Cat extends Feline {
	public Cat() {
		type = 'c';
		System.out.println("Cat");
	}

//	@Override
//	public void getType() {
//		System.out.println("Type : " + type + " -> Super Type : " + super.type);
//	}
}

class Tiger extends Feline {
	public char type = 't';

	public Tiger() {
//		super('t');
		System.out.println("Tiger");
	}

//	@Override
//	public void getType() {
//		System.out.println("Type : " + type + " -> Super Type : " + super.type);
//	}
}