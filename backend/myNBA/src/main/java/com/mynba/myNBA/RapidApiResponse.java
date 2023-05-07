package com.mynba.myNBA;

import java.util.List;

public class RapidApiResponse<T extends Object> {
	public String get;
	public Object parameters;
	public List<Object> errors;
	public Integer results;
	public List<T> response;
}
