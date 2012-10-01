package br.com.fsilveira.finance.faces.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

public class Bundle {

	private static Bundle instance;

	protected Bundle() {

	}

	public static Bundle getInstance() {
		if (instance == null)
			instance = new Bundle();
		return instance;
	}

	private ClassLoader getCurrentClassLoader(Object defaultObject) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}

	public String getMessageResourceString(String key, Object... params) {

		String message = null;

		ResourceBundle bundle;
		if (params == null || params.length == 0) 
				bundle = ResourceBundle.getBundle(this.getBaseName(), getLocale());
		else
				bundle = ResourceBundle.getBundle(this.getBaseName(), getLocale(), getCurrentClassLoader(params));

		try {
			message = bundle.getString(key);
		} catch (MissingResourceException e) {
			message = "???????? - " + key + " - ?????????";
		}

		if (params != null) {
			MessageFormat mf = new MessageFormat(message, getLocale());
			message = mf.format(params, new StringBuffer(), null).toString();
		}

		return message;
	}

	public Application getApplication() {
		return getFacesContext().getApplication();
	}

	public Locale getLocale() {
		try {
			return getFacesContext().getViewRoot().getLocale();
		} catch (NullPointerException e) {
			return Locale.getDefault();
		}
	}

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	private String getBaseName(){
		try {
			return getApplication().getMessageBundle();
		} catch (NullPointerException e) {
			return "br.com.jexperts.siga.resources.ApplicationResources";
		}
		
	}

}
