import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConvertidorMonedas {

    private static Scanner teclado = new Scanner(System.in);
    private static final String API_KEY = "8664cde733e1cd5f062f2150";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";


    public static void main(String[] args) {
//        int opcion = 0;

        System.out.println("**********************************************************");
        System.out.println("\n Welcome, qué monedas quieres convertir?? \n");

        var opcion = -1;
        while (opcion !=0) {
        var menu = """
                1) Dólar --> Peso Mexicano
                2) Peso Mexicano --> Dólar
                3) Dólar --> Euro
                4) Euro --> Dólar
                5) Dólar --> Rublo Ruso
                6) Rublo Ruso --> Dólar  
                
                7) Salir 
                """;

        System.out.println("**********************************************************\n");

            // Menu
            System.out.println(menu);
            System.out.println("\nEscoge un opción:");
            System.out.println("\n**********************************************************");
            opcion = teclado.nextInt();
            teclado.nextLine();

            // Salida opcion 7
            if (opcion == 7){
                System.out.println("Saliendo del convertidor...");
                break;
            }

            // cantidad a convertir
            System.out.println("Escribe la cantidad a convertir: ");
            double valor = teclado.nextDouble();

            try{
                switch (opcion){
                    case 1:
                        convertirMonedas("USD", "MXN", valor);
                        break;
                    case 2:
                        convertirMonedas("MXN", "USD", valor);
                        break;
                    case 3:
                        convertirMonedas("USD", "EUR", valor);
                        break;
                    case 4:
                        convertirMonedas("EUR", "USD", valor);
                        break;
                    case 5:
                        convertirMonedas("USD", "RUB", valor);
                        break;
                    case 6:
                        convertirMonedas("RUB", "USD", valor);
                        break;
                    default:
                        System.out.println("Opción No valida!!");
                }
            } catch (IOException | InterruptedException e){
                System.out.println("Error de conversión!!: " + e.getMessage());
            }
        }
        teclado.close();
    }

    public static void convertirMonedas (String from, String to, double valor) throws IOException, InterruptedException {
        String url = API_URL + from;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Monedass monedass = new Monedass();
        double tasa = monedass.obtenerConversion(response.body(), to);

        double resultado = valor * tasa;


        System.out.println("El valor" + " " + valor + " " + "(" + from + ")" + " " + "Equivale al valor de -->" + " " + resultado + " " + "(" + to + ")\n");

    }
}
