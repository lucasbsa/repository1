import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App{
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String args[]){
		System.out.println("Digite o nome do arquivo de cotas:");
		String arqCotas = in.nextLine();
		System.out.println("Digite o nome do arquivo de acordos:");
		String arqAcordos = in.nextLine();
		
		System.out.println("Digite o nome do condominio:");
		Condominio cond = new Condominio(in.nextLine());
		
		leCotas(cond,arqCotas);
		leAcordos(cond,arqAcordos);
		
		System.out.println("IMPRESSAO ::\n " + cond);
	}
	
	public static void leCotas(Condominio cond, String nomeArquivo){
		Path path = Paths.get(nomeArquivo);
		try (BufferedReader rd = Files.newBufferedReader(path,
				Charset.defaultCharset())) {
			
			String line = "";
			String nome = "";
			Bloco bloco = null;
			Apartamento apartamento = null;
			
			while ((line = rd.readLine()) != null) {
				if(line == "" || line == " " || line == "\t" || line == "\n"){ //linha vazia
					continue;
				}
				else if(line.contains("BLOCO")){
					if(bloco != null) cond.addBloco(bloco);
					bloco = new Bloco(Integer.parseInt("" + line.charAt(6))); //pega num do bloco
					rd.readLine(); //pula uma linha
					
					//pega nome da pessoa
					String linhaNome = rd.readLine();
					String[] auxnome = linhaNome.split(" ");
					nome = "";
					for(String s : auxnome){
						nome += s + " ";
					}
					
					String apStr = rd.readLine(); //pega numbloco/ap
					String[] aux = apStr.split("/"); //separa
					
					int numAp = Integer.parseInt(aux[1]); //numap
					apartamento = new Apartamento(numAp); //cria ap
					apartamento.setNome(nome);
					
					//box
					String numBoxStr = rd.readLine();
					String[] aux2 = numBoxStr.split(" ");
					if(aux2.length > 1){
						int numBox = Integer.parseInt(aux2[1]);
						apartamento.setBox(numBox);
					}
					
					bloco.addApartamento(apartamento); //adiciona
					rd.readLine(); //pula uma linha
				}
				else if(line.contains("NOME:")){ //est� em um nome					
					//pega nome da pessoa
					String[] auxnome = line.split(" ");
					nome = "";
					for(String s : auxnome){
						nome += s + " ";
					}
					
					String apStr = rd.readLine(); //pega numbloco/ap
					String[] aux = apStr.split("/"); //separa
					
					int numAp = Integer.parseInt(aux[1]); //numap
					apartamento = new Apartamento(numAp); //cria ap
					apartamento.setNome(nome);
					
					//box
					String numBoxStr = rd.readLine();
					String[] aux2 = numBoxStr.split(" ");
					if(aux2.length > 1){
						int numBox = Integer.parseInt(aux2[1]);
						apartamento.setBox(numBox);
					}
					bloco.addApartamento(apartamento); //adiciona
					rd.readLine(); //pula uma linha
				}

				else if(line.contains("0")){ //esta em uma linha da tabela
					String[] aux3 = line.split("\t");
					Data dataVencimento = new Data(aux3[0]);
					Data dataPagamento = new Data(aux3[1]);
					String obs = aux3[3];
					
					String[] valorStr = aux3[2].split(",");
					double valor = Double.parseDouble(valorStr[0]) + Math.pow(10, -(valorStr[1].length()))*
									Double.parseDouble(valorStr[1]);
					
					Cota c = new Cota(valor, dataPagamento, dataVencimento,obs);
					apartamento.addCota(c);
				}
			}
			cond.addBloco(bloco);
		} catch (IOException x) {
			System.err.format("Erro de E/S: %s%n", x);
		}
	}

	
	public static void leAcordos(Condominio cond, String nomeArquivo){
		Path path = Paths.get(nomeArquivo);
		try (BufferedReader rd = Files.newBufferedReader(path,
				Charset.defaultCharset())) {
			
			String line = "";
			int nAp = -1, nBloco = -1, nParcelas = -1;
			double valorTotal = -1, valorParcela = -1;
			String processo = "", devedor = "", formaAtualizacao = "";
			ArrayList<Data> datas = null;
			Data data = null, inicio = null, fim = null;
			
			Data dataVencimento = null, dataPagamento = null;
			double valorO = -1, valorP = -1;
			String parcela = "";
			
			Acordo acordo = null;
			
			while ((line = rd.readLine()) != null) {
				if(line.contains("ACORDO:")){
					String[] aux = line.split(" ");
					nBloco = Integer.parseInt(aux[2]);		//bloco
					nAp = Integer.parseInt(aux[5]);		//apartamento
					
					line = rd.readLine();	//processo
					aux = line.split(" ");
					processo = aux[1];
					
					line = rd.readLine();	//devedor
					aux = line.split(" ");
					for(int i=1; i<aux.length; i++){
						devedor += aux[i] + " ";
					}
					
					line = rd.readLine();	//data assinatura
					aux = line.split(" ");
					String strAux = aux[5];
					data = new Data(strAux);
					
					line = rd.readLine();	//valor total do acordo
					aux = line.split(" ");
					strAux = aux[5];
					String[] aux2 = strAux.split(",");
					int centavo = Integer.parseInt(aux2[1]);
	
					String strMilhar = "", strCentena = "";
					strAux = "";
					for(int i=0; i<aux2[0].length(); i++){
						if(i == aux2[0].length()-1) { strCentena = strAux + aux2[0].charAt(i); }
						else if(aux2[0].charAt(i) == '.') { strMilhar = strAux; strAux = "";}
						else strAux += "" + aux2[0].charAt(i);
					}				
					int mil = Integer.parseInt(strMilhar);
					int centena = Integer.parseInt(strCentena);
					valorTotal = (mil*1000) + centena + (centavo * Math.pow(10, -2));
					
					line = rd.readLine(); //debitos, datas
					aux = line.split(" ");
					datas = new ArrayList<Data>();
					for(int i=4; i<aux.length; i++){
						Data dAux = new Data(aux[i]);
						datas.add(dAux);
					}

					line = rd.readLine(); //numero de parcelas
					aux = line.split(" ");
					nParcelas = Integer.parseInt(aux[3]);
					
					line = rd.readLine(); //valor da parcela
					aux = line.split(" ");
					strAux = aux[4];
					aux2 = strAux.split(",");
					centena = Integer.parseInt(aux2[0]);
					centavo = Integer.parseInt(aux2[1]);
					valorParcela = centena + (centavo * Math.pow(10,-2));
					
					line = rd.readLine();	//data inicio
					aux = line.split(" ");
					strAux = aux[1];
					inicio = new Data(strAux);
					
					line = rd.readLine();	//data fim
					aux = line.split(" ");
					strAux = aux[1];
					fim = new Data(strAux);
					
					line = rd.readLine();	//forma atualizacao
					aux = line.split(" ");
					for(int j=3; j<aux.length; j++){
						formaAtualizacao += aux[j] + " ";
					}
					
					for(int i=0; i<4; i++){
						line = rd.readLine(); //pular 4 linhas
					}
					
					acordo = new Acordo(devedor, processo, formaAtualizacao, data, 
							inicio, fim, valorTotal, valorParcela, nParcelas);
					for(Data d : datas){
						acordo.addItemDebitos(d);	
					}
					
					line = rd.readLine();
					while(line != null && line.contains("0")){
		System.out.println("linha : " + line);
						aux = line.split("\t");
						parcela = aux[0];
						dataVencimento = new Data(aux[1]);
						
						aux2 = aux[2].split(" ");
						String[] aux3 = aux2[1].split(",");
						centena = Integer.parseInt(aux3[0]);
						centavo = Integer.parseInt(aux3[1]);
						valorO = centena + (centavo * Math.pow(10, -2));
						
						if(aux.length>3){
							dataPagamento = new Data(aux[3]);
						
							aux2 = aux[4].split(" ");
							aux3 = aux2[1].split(",");
							centena = Integer.parseInt(aux3[0]);
							centavo = Integer.parseInt(aux3[1]);
							valorP = centena + (centavo * Math.pow(10, -2));
						}
						acordo.addItemTabela(parcela, valorO, valorP, dataVencimento, dataPagamento);
						line = rd.readLine();
					}	
					try{
						cond.getBloco(nBloco).getApartamento(nAp).addAcordo(acordo);
					}catch(Exception e){
						System.out.println("Algo errado na entrada! N�o encontrou apartamento!");
					}
					acordo = null;
				}
			}
		} catch (IOException x) {
			System.err.format("Erro de E/S: %s%n", x);
		}
	}

}
