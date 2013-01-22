package com.tobeface.modules.domain.spec;

/**
 * 
 * @author loudyn
 * 
 * @param <T>
 */
public abstract class AbstractSpec<T> implements Spec<T> {

	public final Spec<T> and(Spec<T> another) {
		return new AndSpec<T>(this, another);
	}

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	static class AndSpec<T> extends AbstractSpec<T> {
		private final Spec<T> one;
		private final Spec<T> another;

		public AndSpec(final Spec<T> one, final Spec<T> another) {
			this.one = one;
			this.another = another;
		}

		public boolean isSatisfiedBy(T entity) {
			return one.isSatisfiedBy(entity) && another.isSatisfiedBy(entity);
		}

	}

	public final Spec<T> or(Spec<T> another) {
		return new OrSpec<T>(this, another);
	}

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	static class OrSpec<T> extends AbstractSpec<T> {
		private final Spec<T> one;
		private final Spec<T> another;

		public OrSpec(final Spec<T> one, final Spec<T> another) {
			this.one = one;
			this.another = another;
		}

		public boolean isSatisfiedBy(T entity) {
			return one.isSatisfiedBy(entity) || another.isSatisfiedBy(entity);
		}

	}

	public final Spec<T> not(Spec<T> another) {
		return new NotSpec<T>(another);
	}

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	static class NotSpec<T> extends AbstractSpec<T> {

		private final Spec<T> another;

		public NotSpec(final Spec<T> another) {
			this.another = another;
		}

		public boolean isSatisfiedBy(T entity) {
			return !another.isSatisfiedBy(entity);
		}
	}

}
