import java.util.ArrayList;
import java.util.Scanner;

class producto{
    String nombre;
    String marca;
    double numerSerie;
    boolean existencia;
    int puntoDeReorden;
    int unidad;
}
class LectorDeTeclado{
    public int LecInt() {
        Scanner sc = new Scanner(System.in);
        boolean valor = true;
        int x = 0;
        do {
            try {
                x = Integer.parseInt(sc.nextLine());
                valor = true;
            } catch (Exception ex) {
                System.out.println("Introdzca un valor valido");
                valor = false;
            }
        } while (valor == false);
        return x;

    }
    public String LecString() {
        Scanner sc = new Scanner(System.in);
        boolean valor = true;
        String x = null;
        do {
            try {
                x = sc.next();
                valor = true;
            } catch (Exception ex) {
                System.out.println("Introdzca un valor valido");
                valor = false;
            }
        } while (valor == false);
        return x;
    }
    public double LecDou() {
        Scanner sc = new Scanner(System.in);
        boolean valor = true;
        double x = 0;
        do {
            try {
                x = Integer.parseInt(sc.nextLine());
                valor = true;
            } catch (Exception ex) {
                System.out.println("Introdzca un valor valido");
                valor = false;
            }
        } while (valor == false);
        return x;

    }

}

public class main {
    static ArrayList<producto> ListadeProdutos = new ArrayList<>();
    public static void main(String[] args) {

        producto pd = new producto();
        producto pd2 = new producto();
        boolean resp = true;
        while (resp = true) {
            System.out.println("\n\t\t\t\t\tInventario\n1) Agregar producto\n2) Ver reporte\n3) Salir");
            LectorDeTeclado lc = new LectorDeTeclado();
            int x = lc.LecInt();
            Scanner sca = new Scanner(System.in);
            switch (x) {
                case 1: {
                    nuevoProducto();
                    break;
                }
                case 2: {
                    System.out.println("\t\t\t\t REPORTE");
                    for (int i = 0; i < ListadeProdutos.size(); i++) {
                        pd2 = ListadeProdutos.get(i);
                        System.out.println(i+1+".-\tNOMBRE:\t"+pd2.nombre+"\t\tMARCA:\t"+ pd2.marca+"\t\tNO. DE SERIE:\t"+ pd2.numerSerie+"\t\tEXISTENCIA\t"+pd2.existencia+"\t\tPUNTO DE REORDEN\t"+pd2.puntoDeReorden+"\t\tUNIDAD\t"+ pd2.unidad);
                    }
                    break;

                }
                case 3:{
                    break;
                }

            }
        }

    }
    private static void nuevoProducto (){
        LectorDeTeclado lc = new LectorDeTeclado();
        producto pd = new producto();
        System.out.print("Ingresar el nombre del proucto: ");
        pd.nombre = lc.LecString();
        System.out.print("Ingrsear la marca: ");
        pd.marca = lc.LecString();
        System.out.print("Ingresar el numero de serie: ");
        pd.numerSerie = lc.LecDou();
        System.out.print("Ingresar el numero de re-orden: ");
        pd.puntoDeReorden = lc.LecInt();
        System.out.print("Ingresar el nummero de unidad: ");
        pd.unidad = lc.LecInt();
        if (pd.unidad == 0 || pd.unidad < 0) {
            pd.existencia = false;
        }
        ListadeProdutos.add(pd);
    }
}
