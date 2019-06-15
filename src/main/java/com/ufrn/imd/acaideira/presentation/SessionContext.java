package com.ufrn.imd.acaideira.presentation;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.ufrn.imd.acaideira.domain.Client;

public class SessionContext {
	private static SessionContext sessionContext;

	/**
	 * Default Constructor
	 */
	private SessionContext () { }
	
	public synchronized static SessionContext getInstance() {
		if (sessionContext == null) sessionContext = new SessionContext();
		return sessionContext;
	}

	private ExternalContext currentExternalContext(){
         if (FacesContext.getCurrentInstance() == null)
             throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
         else return FacesContext.getCurrentInstance().getExternalContext();
    }

	public Client getUsuarioLogado() {
		return (Client) getAttribute("clientLogged");
	}

	public void setUsuarioLogado(Client client) {
		setAttribute("clientLogged", client);
	}

	public void encerrarSessao() {
		currentExternalContext().invalidateSession();
	}

	public Object getAttribute(String nome) {
		return currentExternalContext().getSessionMap().get(nome);
	}

	public void setAttribute(String nome, Object valor) {
		currentExternalContext().getSessionMap().put(nome, valor);
	}

}