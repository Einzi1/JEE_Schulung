package de.kiltz.kv.aop;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ZeitStopperAspect {

	@AroundInvoke
	public Object info(InvocationContext ctx) throws Exception
	{
		long start = System.nanoTime();
		// Die eigentliche Ausführung
		Object o = ctx.proceed();
		long zeit = System.nanoTime() - start;
		System.out.println("Rückgabe der Methode: "+o);
		System.out.println("Vor Ausführung von "+ctx.getMethod().getName() +" benötigte "+zeit+" ns.");
		
		return o;
	}

}
