package goldenBall.logica;

/**
 * 
 * @author Kannya
 * É a classe pai do relatorio, aquela com o identificador e o esforço
 * 
 */

public class Relatorio{

	/**
	 * 
	 */
	protected int esforco;
	protected int idRelatorio;
	protected int id;
	protected float afinidade;
	
	public Relatorio(int id, int idRelatorio, int esforco) {
		super();
		this.id = id;
		this.idRelatorio = idRelatorio;
		this.esforco = esforco;
	}
	
	public Relatorio(){
	}

	//getters y setters
	public double getEsforco() {
		return esforco;
	}

	public void setEsforco(int esforco) {
		this.esforco = esforco;
	}

	public int getIdRelatorio() {
		return idRelatorio;
	}

	public void setIdRelatorio(int idRelatorio) {
		this.idRelatorio = idRelatorio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAfinidade() {
		return afinidade;
	}

	public void setAfinidade(float afinidade) {
		this.afinidade = afinidade;
	}

	public String toString() {
		String result = "[" + this.idRelatorio + ", " + this.esforco + ", " + this.afinidade + "] ";
		
		return result;
 	}
}
