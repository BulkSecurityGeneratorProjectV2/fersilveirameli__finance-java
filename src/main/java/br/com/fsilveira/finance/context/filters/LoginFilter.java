package br.com.fsilveira.finance.context.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fsilveira.finance.context.security.SecurityManagerBean;

@WebFilter("/app/*")
public class LoginFilter extends HttpFilter {

	@Inject
	private SecurityManagerBean securityManagerBean;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (!request.getRequestURL().toString().contains("/login")) {

			if (securityManagerBean != null && securityManagerBean.getUsuario() != null) {

			
				chain.doFilter(request, response);

			} else {
				redirectsToLogin(request, response, chain);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	private void redirectsToLogin(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		request.setAttribute("br.com.fsilveira.finance.authenticator.login", "/login");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
