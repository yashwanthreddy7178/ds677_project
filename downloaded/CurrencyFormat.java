import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class CurrencyFormat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();

        // Write your code here.
        NumberFormat usd = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat inr = NumberFormat.getCurrencyInstance(new Locale("en","IN"));
        NumberFormat chn = NumberFormat.getCurrencyInstance(new Locale("zh","CN"));
        NumberFormat frn = NumberFormat.getCurrencyInstance(Locale.FRANCE);

        System.out.println("US: " + usd.format(payment));
        System.out.println("India: " + inr.format(payment));
        System.out.println("China: " + chn.format(payment));
        System.out.println("France: " + frn.format(payment));
    }

}
