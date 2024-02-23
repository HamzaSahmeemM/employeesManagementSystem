package com.hsd.ems.model;

public class InsufficientDataException extends RuntimeException{

	private static final long serialVersionUID = 5840498413951341641L;

	public InsufficientDataException(String error) {
		super(error);
	}
}
