package curso_java_completo;

import java.util.Locale;
import java.util.Scanner;

public class CondicaoTernaria {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		System.out.println("Digite o Valor do prešo:");
		Scanner sc = new Scanner(System.in);
		double preco = sc.nextDouble();
		double desconto = (preco < 20.0) ? preco * 0.1:preco * 0.05;
		System.out.println("O valor do desconto Ú "+desconto);
		sc.close();

	}

}
