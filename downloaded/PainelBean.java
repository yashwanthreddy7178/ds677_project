package br.edu.fsma.bancogerente.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.edu.fsma.bancogerente.util.Redirecionador;
import br.edu.fsma.bancogerente.util.Secao;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;

@ManagedBean(name = "PainelBean")
@ViewScoped
public class PainelBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	private UsuarioGerente usuarioGerente;
	private Redirecionador redirecionador;
	
	public PainelBean() {
		this.usuarioGerente = Secao.getUsuarioGerente();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioGerente()==null) {
			redirecionador.redireciona("/bancogerente/view/index/index.xhtml");
		}
	}
	
	public String nomeUsuario() {
		if(usuarioGerente!=null) {
			return "Gerente: " + usuarioGerente.getPessoaFisica().getNome() + " ";
		}
		return "";
	}
	
	
	public void abrirContaClick() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancogerente/view/abrirconta/abrirconta.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void encerrarContaClick() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancogerente/view/encerrarconta/encerrarconta.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void adicionarTitularClick() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancogerente/view/adicionartitular/adicionartitular.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verExtratoClick() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancogerente/view/verextrato/verextrato.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sair() {
		Secao.setUsuarioGerente(null);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancogerente/view/index/index.xhtml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void painelClick() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancogerente/view/painel/painel.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UsuarioGerente getUsuarioGerente() {
		return usuarioGerente;
	}

	public void setUsuarioGerente(UsuarioGerente usuarioGerente) {
		this.usuarioGerente = usuarioGerente;
	}
	

}
