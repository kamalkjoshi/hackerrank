package org.invokeDynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MHD {
	public static void main(String[] args) throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mh = lookup.findStatic(MHD.class, "hello",
				MethodType.methodType(void.class));
		mh.invokeExact();

		mh = lookup.findVirtual(HW.class, "hello1",
				MethodType.methodType(void.class));
		HW hw = new HW();
		// Public method
		mh.invoke(hw);

		mh = lookup.findVirtual(HW.class, "hello2",
				MethodType.methodType(void.class));
		// private method - IllegalStateException
		mh.invoke(hw);

		// Setters and getters
		mh = lookup.findSetter(Point.class, "x", int.class);
		Point point = new Point();
		mh = lookup.findSetter(Point.class, "y", int.class);
		mh.invoke(point, 30);

		mh = lookup.findGetter(Point.class, "x", int.class);
		int x = (int) mh.invoke(point);
		System.out.printf("x = %d%n", x);

		mh = lookup.findGetter(Point.class, "y", int.class);
		int y = (int) mh.invoke(point);
		System.out.printf("y = %d%n", y);

		// Modifying arguments
		lookup = MethodHandles.lookup();
		mh = lookup.findStatic(Math.class, "pow", MethodType
				.methodType(double.class, double.class, double.class));
		// combinator (a method that combines or transforms a pre-existing
		// method handle into a new method handle)
		mh = MethodHandles.insertArguments(mh, 1, 10);
		System.out.printf("2^10 = %d%n", (int) (double) mh.invoke(2.0));

	}

	static void hello() {
		System.out.println("hello");
	}
}

class HW {
	public void hello1() {
		System.out.println("hello from hello1");
	}

	@SuppressWarnings("unused")
	private void hello2() {
		System.out.println("hello from hello2");
	}
}

class Point {
	int x;
	int y;
}
