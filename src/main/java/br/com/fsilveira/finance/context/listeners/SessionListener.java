package br.com.fsilveira.finance.context.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

@WebListener
public class SessionListener implements HttpSessionListener {

	private Logger logger = Logger.getLogger(SessionListener.class);

	public void sessionCreated(HttpSessionEvent event) {
		logger.debug("Sess�o criada: " + event.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		logger.debug("Sess�o destruida: " + event.getSession().getId());
	}

}
