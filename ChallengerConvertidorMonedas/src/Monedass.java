import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Monedass {

    public double obtenerConversion(String jsonResponse, String toCurrency){
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        return conversionRates.get(toCurrency).getAsDouble();
    }

}
