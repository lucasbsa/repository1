import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App{
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String args[]){
		System.out.println("Digite o nome do arquivo:");
		String arq = in.nextLine();
		System.out.println("Digite o nome do condominio:");
		Condominio cond = new Condominio(in.nextLine());
		
		leCotas(cond,arq);
		leAcordos(cond,arq);
		
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
			int nAp = -1;
			int nBloco = -1;
			Acordo acordo = null;
			
			while ((line = rd.readLine()) != null) {
				if(line.contains("ACORDO:")){
					if(acordo != null) cond.getBloco(nBloco).getApartamento(nAp).addAcordo(acordo);
					String[] aux1 = line.split(" ");
					nBloco = Integer.parseInt(aux1[2]);
				}
				
			}
		} catch (IOException x) {
			System.err.format("Erro de E/S: %s%n", x);
		}
	}

}
