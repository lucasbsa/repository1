import java.util.Calendar;

public class Cota{
	public double valOriginal, valPago;
	public Calendar dataVencimento, dataPagamento;
	public String obs;
	
	public Cota(double valO, double valP, Calendar dVenc, Calendar dPag, String obs){
		valOriginal = valO; valPago = valP; dataVencimento = dVenc; dataPagamento = dPag; this.obs = obs;
	}
	
	public  Calendar getDataVencimento() { return dataVencimento; }
	
	public String toString(){
		return "Valor Original : " + valOriginal + "\tData Vencimento" + dataVencimento + 
				"\tValor Pago : " + valPago + "\tData Pagamento : " + dataPagamento +
					"Observação : " + obs;
	}
}
