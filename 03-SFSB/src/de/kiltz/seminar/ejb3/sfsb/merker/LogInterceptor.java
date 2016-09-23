package de.kiltz.seminar.ejb3.sfsb.merker;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptor implements Serializable {

	@AroundInvoke
	public Object drumherum(InvocationContext ctx) throws Exception {
		Object ret = null;

		// Man könnte aus getMethod die Annotations ermitteln und bei einer
		// Annotation @Zeitmessung eine Zeitmessung vornehmen.
		System.out.println("drumherum: " + ctx.getMethod().getName() + "()");

		// normale Methode ausführen
		ret = ctx.proceed();

		return ret;
	}

	@PostConstruct
	public void init(InvocationContext ctx) {
		System.out.println("PostConstruct " + info(ctx));
	}

	private String info(InvocationContext ctx) {
		StringBuffer txt = new StringBuffer();
		txt.append(ctx.getTarget().getClass().getSimpleName());
		Object o = ctx.getTarget();
		if (o instanceof Merker) {
			Merker m = (Merker) o;
			txt.append(" - Name " + m.getName());
		}

		return txt.toString();
	}

	@PreDestroy
	public void ende(InvocationContext ctx) {
		System.out.println("PreDestroy " + info(ctx));
	}

}
