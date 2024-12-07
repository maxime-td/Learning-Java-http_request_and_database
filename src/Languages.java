import java.util.HashMap;
import java.util.Map;

//
public class Languages {

    private Map<String, String[]> translations;

    public Languages() {
        translations = new HashMap<>();

        translations.put("menu_1_city", new String[]{"\nDe quelle city voulez vous la température ? Tapez \"exit\" pour quitter.", "\nWhich city do you want the temperature of? Enter \"exit\" to exit."});
        translations.put("menu_2_language", new String[]{"Enter \"english\" to change language.", "Tapez \"français\" pour changer la langue."});
        translations.put("menu_3_choice", new String[]{"Votre choix : ", "Your choice: "});

        translations.put("empty_input", new String[]{"Veuillez entrer le nom d'une ville.", "Please enter a city name."});
    }

    // index 0 = French, index 1 = English
    public String getString(String key, int index) {
        String[] trans = translations.get(key);
        return trans != null ? trans[index] : key;
    }
}
