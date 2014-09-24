package org.training.error;

public class ImpossibleQueryResultException extends RuntimeException {

	private static final long serialVersionUID = 2290432596908571795L;

	public ImpossibleQueryResultException( String message ) {
		super( message );
	}
}
