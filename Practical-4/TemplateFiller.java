import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateFiller {

    public static String fill(String template,
                              String[] names,
                              String[] values) {

        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(template);

        StringBuilder result = new StringBuilder();

        while (matcher.find()) {

            String placeholder = matcher.group(1);
            String value = "[?]";

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(placeholder)) {
                    value = values[i];
                    break;
                }
            }

            matcher.appendReplacement(
                result,
                Matcher.quoteReplacement(value)
            );
        }

        matcher.appendTail(result);

        return result.toString();
    }
}