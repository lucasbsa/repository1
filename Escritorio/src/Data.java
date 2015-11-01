
public class Data {
	private int dia, mes, ano;
	
	public Data(String dataStr){
		String[] data = dataStr.split("/");
		if(data.length == 3){ //dia mes e ano
			dia = Integer.parseInt(data[0]);
			mes = Integer.parseInt(data[1]);
			ano = Integer.parseInt(data[2]);
		}
		else{
		//mes e ano
			dia = 0;
			mes = Integer.parseInt(data[0]);
			ano = Integer.parseInt(data[1]);
		}
	}
	
	public int getDia() { return dia; }
	public int getMes() { return mes; }
	public int getAno() { return ano; }
	
	public String toString() { 
		if(dia != 0) return dia + "/" + mes + "/" + ano;
		else return mes + "/" + ano;
	}
	
	public boolean after(Data d){
		if(this.ano > d.getAno()) return true;  //ano desse � maior
		else if(d.getAno() == this.ano){ //anos s�o iguais
			if(this.mes > d.getMes()) return true; //mes desse � maior
			else if(this.mes == d.getMes()){ //meses s�o iguais
				if(this.dia > d.getDia()) return true; //dia desse � maior
				return false; //sen�o vem antes
			}
			return false; //mes desse � menor
		}
		return false; //ano do outro � menor
	}
	
}
