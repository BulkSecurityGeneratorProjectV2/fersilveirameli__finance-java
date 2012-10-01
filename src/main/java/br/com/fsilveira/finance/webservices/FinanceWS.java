package br.com.fsilveira.finance.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class FinanceWS {

	@WebMethod()
	public String doCreate(Lancamento lancamento) {
		System.out.println(lancamento.getDescricao());
		return lancamento.getDescricao();
	}

}
