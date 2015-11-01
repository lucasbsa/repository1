import java.util.ArrayList;
import java.util.Calendar;


public class Apartamento{
	private int numero;
	private ArrayList<Cota> cotas;
	private ArrayList<Acordo> acordos;
	private ArrayList<Processo> processos;

	public Apartamento(int numero){
		this.numero = numero; cotas = new ArrayList<Cota>(); processos = new ArrayList<Processo>();
	}	
	
	public int getNumero() { return numero; }
	
	/******************************COTAS*****************************************************/

	public void addCota(double valOriginal, double valPago, Calendar dataVencimento, Calendar dataPagamento, String obs){
		int pos = 0;
		for(Cota aux : cotas){
			if(aux.getDataVencimento().after(dataVencimento)) break;
			pos++;
		}
		cotas.add(pos,new Cota(valOriginal, valPago, dataVencimento, dataPagamento, obs));
	}

	public void delCota(int lin){
		cotas.remove(lin);
	}	
	
	/******************************ACORDOS****************************************************/
	
	public void addAcordo(String devedor, String formaAtualizacao, Calendar dataAssinatura, Calendar inicio,
			Calendar fim, double valorTotal, double valorParcela, int numParcelas){
		int pos = 0;
		for(Acordo aux : acordos){
			if(aux.getDataAssinatura().after(dataAssinatura)) break;
			pos++;
		}
		Acordo acordo = new Acordo(devedor,formaAtualizacao,dataAssinatura,inicio,fim,valorTotal,valorParcela,numParcelas);
		acordos.add(pos, acordo);
	}

	public void addItemTabela(int numAcordo,String parcela, double valO, double valP, Calendar vencO, Calendar dataP){
		acordos.get(numAcordo).addItemTabela(parcela, valO, valP, vencO, dataP);
	}

	public void addItemDebitos(int numAcordo,Calendar c){
		acordos.get(numAcordo).addItemDebitos(c);
	}
	
	/******************************PROCESSOS****************************************************/
	public void addProcesso(String reu, String especie, int numProcesso, double valorAjuizado, Calendar dataAjuizamento,
			Calendar periodo, Calendar ultMov){
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
		return "Apartamento " + numero + " :\n" + "COTAS :\n" + cotas + "\nPROCESSOS :\n" + processos;
	}

	
}
