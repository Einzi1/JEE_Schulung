package de.kiltz.kv.tools;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.ejb.ApplicationException;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Interceptor-Implementierung zu {@link TransactionRequired}.
 * 
 * @author dw
 */
@TransactionRequired
@Interceptor
public class TransactionRequiredInterceptor implements Serializable {

	@Inject
	UserTransaction userTransaction;

	/**
	 * Interceptor-Arbeitsmethode.
	 * 
	 * @param ctx
	 *            InvocationContext
	 * @return Returnwert
	 * @throws Exception
	 *             bei Fehlern
	 */
	@AroundInvoke
	public Object manageTransaction(InvocationContext ctx)
			throws Exception {
		// Falls schon eine TX aktiv, Methode direkt aufrufen
		if (isTransactionActive()) {
			return ctx.proceed();
		}

		// TX beginnen
		this.userTransaction.begin();

		// Methode aufrufen
		Object result = null;
		Exception exception = null;
		try {
			result = ctx.proceed();
		} catch (Exception e) // CHECKSTYLE:IGNORE Allgemeine Exception ist hier
								// OK
		{
			exception = e;
		}

		boolean rollback = exception != null
				&& getRollbackDesignation(exception);

		if (!rollback) {
			// TX committen
			this.userTransaction.commit();
		} else {
			// TX zurückrollen
			try {
				this.userTransaction.rollback();
			} catch (Throwable ignore) {
			}
		}

		if (exception != null) {
			throw exception;
		}

		return result;
	}

	// Ist die TX aktiv?
	private boolean isTransactionActive() throws SystemException {
		return this.userTransaction.getStatus() == Status.STATUS_ACTIVE;
	}

	// TX-Rollback aufgrund der angegebenen Exception?
	private boolean getRollbackDesignation(Exception exception) {
		// Falls die Exception mit ApplicationException annotiert ist, bestimmt
		// deren Parameter, ob Rollback
		ApplicationException applicationException = extractApplicationExceptionAnnotation(exception
				.getClass());
		if (applicationException != null) {
			return applicationException.rollback();
		} else {
			// sonst: System Exception --> Rollback
			return exception instanceof RuntimeException
					|| exception instanceof RemoteException;
		}
	}

	// ApplicationException-Annotation besorgen. Kann an der Klasse oder einer
	// Basisklasse (dort aber mit inherited=true) stehen.
	private ApplicationException extractApplicationExceptionAnnotation(
			Class<?> clazz) {
		ApplicationException applicationException = clazz
				.getAnnotation(ApplicationException.class);
		if (applicationException != null) {
			return applicationException;
		}

		Class<?> superClass = clazz.getSuperclass();
		if (superClass == null) {
			return null;
		}

		applicationException = extractApplicationExceptionAnnotation(superClass);
		if (applicationException != null && applicationException.inherited()) {
			return applicationException;
		}

		return null;
	}
}