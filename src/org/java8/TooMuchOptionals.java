package org.java8;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 
 * Equals with Optional. From stackoverflow
 */
public class TooMuchOptionals {

	
	static boolean canRead(Type type, Class<?> contextClass) {
		if (!(type instanceof ParameterizedType)) {
			return false;
		}
		ParameterizedType parameterizedType = (ParameterizedType) type;
		if (!(parameterizedType.getRawType() instanceof Class)) {
			return false;
		}
		Class<?> rawType = (Class<?>) parameterizedType.getRawType();
		if (!(Collection.class.isAssignableFrom(rawType))) {
			return false;
		}
		if (parameterizedType.getActualTypeArguments().length != 1) {
			return false;
		}
		Type typeArgument = parameterizedType.getActualTypeArguments()[0];
		if (!(typeArgument instanceof Class)) {
			return false;
		}
		return true;
	}

	//@formatter:off
	static Optional<Type> componentType(Type type) {
		  return Optional.of(type)
		                 .filter(t -> t instanceof ParameterizedType)
		                 .map(t -> (ParameterizedType) t)
		                 .filter(t -> t.getActualTypeArguments().length == 1)
		                 .filter(t -> Optional.of(t.getRawType())
		                                      .filter(rt -> rt instanceof Class)
		                                      .map(rt -> (Class<?>) rt)
		                                      .filter(Stream.class::isAssignableFrom)
		                                      .isPresent())
		                 .map(t -> t.getActualTypeArguments()[0]);
	}
	//@formatter:on

}

class TestObj {

	private String f1;
	private int f2;

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public int getF2() {
		return f2;
	}

	public void setF2(int f2) {
		this.f2 = f2;
	}

	@Override
	public boolean equals(Object obj) {
		return Optional.ofNullable(obj).filter(that -> that instanceof TestObj)
				.map(that -> (TestObj) that)
				.filter(that -> Objects.equals(this.f1, that.f1))
				.filter(that -> Objects.equals(this.f2, that.f2)).isPresent();
	}
}
