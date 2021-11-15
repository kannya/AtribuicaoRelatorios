package goldenBall.logica;

import java.util.Collection;

/**
 * 
 * @author Kannya
 * É a classe pai do desenvolvedor, aquela com o afinidade e o cargaTrabalho
 * 
 */

public class Desenvolvedor{

	/**
	 * 
	 */
	protected double cargaTrabalho;
	protected double afinidade;
	protected int idRelatorio;
	protected int idDesenvolvedor;
	protected int id;
	protected Collection<Relatorio> relatorios;
	protected double cargaTrabalhoAposAtribuicao;
	
	public Desenvolvedor(int idDesenvolvedor, int idRelatorio, double afinidade, double cargaTrabalho) {
		super();
		this.idDesenvolvedor = idDesenvolvedor;
		this.idRelatorio = idRelatorio;
		this.afinidade = afinidade;
		this.cargaTrabalho = cargaTrabalho;
	}
	
	public Desenvolvedor(int idDesenvolvedor) {
		super();
		this.idDesenvolvedor = idDesenvolvedor;
	}
	
	public Desenvolvedor(){
	}

	//getters y setters	
	public double getCargaTrabalho() {
		return cargaTrabalho;
	}

	public void setCargaTrabalho(double cargaTrabalho) {
		this.cargaTrabalho = cargaTrabalho;
	}

	public double getAfinidade() {
		return afinidade;
	}

	public void setAfinidade(double afinidade) {
		this.afinidade = afinidade;
	}

	public int getIdRelatorio() {
		return idRelatorio;
	}

	public void setIdRelatorio(int idRelatorio) {
		this.idRelatorio = idRelatorio;
	}

	public int getIdDesenvolvedor() {
		return idDesenvolvedor;
	}

	public void setIdDesenvolvedor(int idDesenvolvedor) {
		this.idDesenvolvedor = idDesenvolvedor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Collection<Relatorio> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(Collection<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	public double getCargaTrabalhoAposAtribuicao() {
		return cargaTrabalhoAposAtribuicao;
	}

	public void setCargaTrabalhoAposAtribuicao(double cargaTrabalhoAposAtribuicao) {
		this.cargaTrabalhoAposAtribuicao = cargaTrabalhoAposAtribuicao;
	}

	public String toString() {
		String result = "[" + this.idDesenvolvedor + ", " + this.idRelatorio + ", " + this.afinidade + ", " + this.cargaTrabalho + "] ";
		
		return result;
 	}
	
	public boolean equals(Object o) {
		 if(o instanceof Desenvolvedor) {
			 Desenvolvedor d = (Desenvolvedor) o;
		     return this.idDesenvolvedor == d.getIdDesenvolvedor();
		 } else {
			 return false;
		 }
	}

}
