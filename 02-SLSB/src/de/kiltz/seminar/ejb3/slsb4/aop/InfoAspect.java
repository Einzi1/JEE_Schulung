package de.kiltz.seminar.ejb3.slsb4.aop;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InfoAspect {

	@AroundInvoke
	public Object info(InvocationContext ctx) throws Exception {
		System.out.println("Vor Ausführung von " + ctx.getMethod().getName());
		// Die eigentliche Ausführung
		Object o = ctx.proceed();

		System.out.println("Rückgabe der Methode: " + o);

		return o;
	}
}
