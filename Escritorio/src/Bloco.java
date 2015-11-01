import java.util.ArrayList;

public class Bloco {
	private int numero;
	private ArrayList<Apartamento> apartamentos;
	
	public Bloco(int numero){
		this.numero = numero; apartamentos = new ArrayList<Apartamento>();
	}
	
	public void addApartamento(int num){
		int pos = 0;
		for(Apartamento ap : apartamentos){
			if(ap.getNumero() > num) break;
			pos++;
		}
		apartamentos.add(pos, new Apartamento(num));
	}
	
	public Apartamento getApartamento(int numAp){
		for(Apartamento aux : apartamentos ){
			if(aux.getNumero() == numAp) return aux;
		}
		return null;
	}
	
	public int getNumero() { return numero; }
	
	public String toString(){
		String msg =  "--BLOCO " + numero + "--\n";
		for(Apartamento ap : apartamentos){
			msg += ap + "\n";
		}
		return msg;
	}
}
