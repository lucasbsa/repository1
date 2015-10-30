import java.util.Calendar;
import java.util.ArrayList;
public class Processos {
	
	class Processo{
		private String reu, especie;
		private int numProcesso;
		private double valorAjuizado;
		private Calendar dataAjuizamento, periodo, ultMov;
		private ArrayList<String[]> ajuizado;
		private ArrayList<String[]> custas;
		
		public Processo(String reu, String especie, int numProcesso, double valorAjuizado, Calendar dataAjuizamento,
						Calendar periodo, Calendar ultMov){
			this.reu = reu; this.especie = especie; this.numProcesso = numProcesso; this.valorAjuizado = valorAjuizado;
			this.dataAjuizamento = dataAjuizamento; this.periodo = periodo; this.ultMov = ultMov;
			ajuizado = null; //chamar método
			custas = null; //chamar método
		}
		
		public void addAjuizado(String mes, String ano){ 
			String[] aux = new String[2]; 
			aux[0] = mes; 
			aux[1] = ano;
			ajuizado.add(aux);
		}
		
		public void addCustas(String data, String valor){
			String[] aux = new String[2];
			aux[0] = data;
			aux[1] = valor;
			custas.add(aux);
		}
		
		public String toString(){
			String msg = "PROCESSO :\n" + "Reu : " + reu + "\nEspecie : " + especie + "\nNumero Processo : " + numProcesso +
							"\nValor Ajuizado : " + valorAjuizado + "\nData Ajuizamento : " + dataAjuizamento +
							"\nPeriodo : " + periodo + "\nUltimo Movimento : " + ultMov + "Ajuizados : \n";
			for(int i=0; i<ajuizado.size(); i++){
				msg += "Ajuizado : " + ajuizado.get(i);
			}
			msg += "Custas :\n";
			for(int j=0; j<custas.size(); j++){
				msg += "Custas : " + custas.get(j);
			}
			return msg;
		}
	}
}