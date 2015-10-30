import java.util.Calendar;
import java.util.ArrayList;

public class Cotas {
	private ArrayList<Cota> lista;
	
	class Cota{
		public double valOriginal, valPago;
		public Calendar dataVencimento, dataPagamento;
		public String obs;
		public Cota(double valO, double valP, Calendar dVenc, Calendar dPag, String obs){
			valOriginal = valO; valPago = valP; dataVencimento = dVenc; dataPagamento = dPag; this.obs = obs;
		}
		public String toString(){
			return "Valor Original : " + valOriginal + "\tData Vencimento" + dataVencimento + 
					"\tValor Pago : " + valPago + "\tData Pagamento : " + dataPagamento +
						"Observação : " + obs;
		}
	}
	
	public Cotas(){ lista = new ArrayList<Cota>(); }
	
	public void addCota(double valOriginal, double valPago, Calendar dataVencimento, Calendar dataPagamento, String obs){
		lista.add(new Cota(valOriginal, valPago, dataVencimento, dataPagamento, obs));
	}
	
	public void delCota(int lin){
		lista.remove(lin);
	}
	
	public int size(){
		return lista.size();
	}
	
	@Override
	public String toString(){
		String msg = "COTAS:\n";
		for(int i=0; i<lista.size(); i++){
			msg += lista.get(i).toString() + "\n";
		}
		return msg;
	}
}