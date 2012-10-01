package br.com.fsilveira.finance.context.listeners;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DeployListener implements ServletContextListener {

	public void init(ServletContext context) {

		Locale locale = new Locale("pt", "BR");
		Locale.setDefault(locale);

	}

	public void contextInitialized(ServletContextEvent contextEvent) {

		init(contextEvent.getServletContext());
	}

	public void contextDestroyed(ServletContextEvent contextEvent) {

	}

}
