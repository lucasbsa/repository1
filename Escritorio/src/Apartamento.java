import java.util.ArrayList;

public class Apartamento{
	private String nome;
	private int numero, box;
	private ArrayList<Cota> cotas;
	private ArrayList<Acordo> acordos;
	private ArrayList<Processo> processos;

	public Apartamento(int numero){
		nome = " "; this.box = -1; this.numero = numero; 
		cotas = new ArrayList<Cota>(); acordos = new ArrayList<Acordo>(); processos = new ArrayList<Processo>();
	}	
	
	public int getNumero() { return numero; }
	
	public void setNome(String nome) { this.nome = nome; }
	
	public void setBox(int box) { this.box = box; }
	
	/******************************COTAS*****************************************************/

	public void addCota(String nome, double valOriginal, double valPago, Data dataVencimento, Data dataPagamento, String obs){
		int pos = 0;
		for(Cota aux : cotas){
			if(aux.getDataVencimento().after(dataVencimento)) break;
			pos++;
		}
		cotas.add(pos,new Cota( valPago, dataVencimento, dataPagamento, obs));
	}
	
	public void addCota(Cota cota){
		int pos = 0;
		for(Cota aux : cotas){
			if(aux.getDataVencimento().after(cota.getDataVencimento())) break;
			pos++;
		}
		cotas.add(pos,cota);
	}

	public void delCota(int lin){
		cotas.remove(lin);
	}	
	
	/******************************ACORDOS****************************************************/
		
	public void addAcordo(Acordo acordo){
		int pos = 0;
		for(Acordo aux : acordos){
			if(aux.getDataAssinatura().after(acordo.getDataAssinatura())) break;
			pos++;
		}
		acordos.add(pos, acordo);
	}


	public void addItemTabela(int numAcordo,String parcela, double valO, double valP, Data vencO, Data dataP){
		acordos.get(numAcordo).addItemTabela(parcela, valO, valP, vencO, dataP);
	}

	public void addItemDebitos(int numAcordo,Data c){
		acordos.get(numAcordo).addItemDebitos(c);
	}
	
	/******************************PROCESSOS****************************************************/
	public void addProcesso(String reu, String especie, int numProcesso, double valorAjuizado, Data dataAjuizamento,
			Data periodo, Data ultMov){
		int pos = 0;
		for(Processo aux : processos){
			if(aux.getNumProcesso() > numProcesso) break;
			pos++;
		}
		Processo p = new Processo(reu, especie, numProcesso, valorAjuizado, dataAjuizamento, periodo, ultMov);
		processos.add(pos,p);
	}
	
	public void delProcesso(int numProcesso){
		Processo aux = null;
		for(Processo p : processos){
			if(p.getNumProcesso() == numProcesso) aux = p;
		}
		processos.remove(aux);
	}
	
	/*******************************************************************************************/
	
	
	public String toString(){
		if(box != -1) return "Apartamento " + numero + " - Box " + box + " :\n" + "NOME : " + nome + "\nCOTAS :\n" + cotas  +  "\nACORDOS :\n" + acordos + "\nPROCESSOS :\n" + processos;
		else return "Apartamento " + numero + " :\n" + "NOME : " + nome + "\nCOTAS :\n" + cotas +  "\nACORDOS :\n" + acordos + "\nPROCESSOS :\n" + processos;
		
	}

	
}
