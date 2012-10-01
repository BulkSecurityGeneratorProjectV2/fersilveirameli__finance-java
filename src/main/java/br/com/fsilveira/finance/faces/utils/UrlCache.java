package br.com.fsilveira.finance.faces.utils;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class UrlCache implements Serializable {

	private String url;
	private String queryString;

	public boolean hasCache() {
		return url != null;
	}

	public void clear() {
		setUrl(null);
		setQueryString(null);
	}

	public String getUrlAndQueryString() {
		return url + queryString;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

}
