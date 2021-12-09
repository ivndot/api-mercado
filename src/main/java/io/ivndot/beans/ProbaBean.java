package io.ivndot.beans;

public class ProbaBean {

	private String conjunta = null;
	private String bayesiana = null;
	private String condicional = null;

	/**
	 * Constructor of ProbaBean
	 * 
	 * @param conjunta    `Conjunta` probability
	 * @param bayesiana   `Bayesiana` probability
	 * @param condicional `Condicional` probability
	 */
	public ProbaBean(String conjunta, String bayesiana, String condicional) {
		this.conjunta = conjunta;
		this.bayesiana = bayesiana;
		this.condicional = condicional;
	}

	public String getConjunta() {
		return conjunta;
	}

	public void setConjunta(String conjunta) {
		this.conjunta = conjunta;
	}

	public String getBayesiana() {
		return bayesiana;
	}

	public void setBayesiana(String bayesiana) {
		this.bayesiana = bayesiana;
	}

	public String getCondicional() {
		return condicional;
	}

	public void setCondicional(String condicional) {
		this.condicional = condicional;
	}

}
