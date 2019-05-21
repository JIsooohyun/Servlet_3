package com.sh.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//List
	public abstract ActionForward list(HttpServletRequest request, HttpServletResponse response);

	//select
	public abstract ActionForward select(HttpServletRequest request, HttpServletResponse response);
	
	//insert
	public abstract ActionForward insert(HttpServletRequest request, HttpServletResponse response);
	
	//update
	public abstract ActionForward update(HttpServletRequest request, HttpServletResponse response);
	
	//delete
	public abstract ActionForward delete(HttpServletRequest request, HttpServletResponse response);
}
