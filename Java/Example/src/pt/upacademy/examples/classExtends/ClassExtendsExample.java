package pt.upacademy.examples.classExtends;

public class ClassExtendsExample {

	public static void main(String[] args) {
		ClassA a = new ClassB();
//		ClassA a1 = new ClassA();
//		ClassA a2 = new ClassC();
		// ClassC c = new ClassA();
//		a.show();
//		a.display();
//		ClassC a2b = (ClassC) a2;
//		a2b.display();
//		a2b.show();
//		
		ClassB b = new ClassB();

//		b.show();
//		b.display();
//		
//		ClassC c = new ClassC();
//		c.show();

		System.out.println(b.num);
	}
}

class ClassA {
	int num = 1;

	public void show() {
		System.out.println("ClassA");
	}
}

class ClassB extends ClassA {
	int num = -1;

	public void display() {
		System.out.println("Display on classB");
	}
}

class ClassC extends ClassA {
	int num2 = 2;

	@Override
	public void show() {
		System.out.println("ClassC");
	}
}
