import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TesteRegex{
    public static void main(String[] args){
        
        // Pattern pattern = Pattern.compile("(\\d\\d)(\\w)");
        // Matcher matcher = pattern.matcher("11a22b33c");

        // while(matcher.find()) {
        //     String match = matcher.group();
        //     String group1 = matcher.group(1);
        //     String group2 = matcher.group(2);
        //     int start = matcher.start();
        //     int end = matcher.end(2);

        //     System.out.println(match + " " + group1 + " " + group2 + " [" + start + "," + end + "]");
        // }

        Pattern pattern = Pattern.compile("(\\d+)(-)(\\d+)(-)(\\d+)");
        Matcher matcher = pattern.matcher("2007-12-31");

        if(matcher.find()) {
            String ano = matcher.group(1);
            String mes = matcher.group(3);
            String dia = matcher.group(5);
            
            String separador1 = matcher.group(2);
            String separador2 = matcher.group(4);

            String novaData = dia + separador1 + mes + separador2 + ano;

            System.out.println(novaData.replaceAll("[-]", "/"));
        }
    }
}