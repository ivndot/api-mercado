package io.ivndot.beans;

public class ProbaResponseBean {

	private int code = 0;
	private String status = null;
	private String description = null;
	private ProbaBean proba = null;

	/**
	 * Constructor of `proba` end point
	 * 
	 * @param code        Code number [100 = All good; 200 = Error, there was an
	 *                    error in the creation of the view or in the probabilities
	 *                    computing; 201 = Error, some fields are empty]
	 * @param status      "ok" or "error"
	 * @param description A little description about the response
	 * @param proba       Object containing all the probabilities [conjunta,
	 *                    bayesiana, condicional]
	 */
	public ProbaResponseBean(int code, String status, String description, ProbaBean proba) {
		this.code = code;
		this.status = status;
		this.description = description;
		this.proba = proba;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProbaBean getProba() {
		return proba;
	}

	public void setProba(ProbaBean proba) {
		this.proba = proba;
	}

}
