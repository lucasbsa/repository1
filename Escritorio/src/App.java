import java.util.Scanner;

public class App {
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String args[]){
		System.out.println("Digite o nome do condominio:");
		Condominio cond = new Condominio(in.nextLine());
		System.out.println("Digite o numero do bloco:");
		int i = in.nextInt();
		cond.addBloco(i);
		System.out.println("Digite o numero do apartamento:");
		cond.getBloco(i).addApartamento(in.nextInt());
		
		System.out.println("IMPRESSAO ::\n " + cond);
	}
}
