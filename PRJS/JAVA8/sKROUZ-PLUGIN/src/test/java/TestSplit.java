import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestSplit {


    public static void main(String[] args) {
       String str1 ="435,0 43Καλαμαριά, Θεσσαλονίκη";

      //  String str1  = areaCodeText.replaceAll("[0-9]","");

        str1 = str1.replaceAll("[^\\d.]", "");
        if (str1!= null &&  !str1.isEmpty() &&  str1.startsWith(",") && str1.length()>2) {
            str1 = str1.substring(1);
        }
        System.out.println(" str1:   " + str1 );


// 435043




//        String str1  = areaCodeText.replaceAll("[0-9]","").replaceAll(" ", "");
//        if (str1!= null &&  !str1.isEmpty() &&  str1.startsWith(",") && str1.length()>2) {
//            str1 = str1.substring(1);
//        }
//        System.out.println(" str1:   " + str1 );



//        String votes     =  areaCodeText.substring(0,  areaCodeText.indexOf(' ') ) ;
//        //System.out.println(" votes:   " + votes );
//
//        String rating    =  votes.substring( votes.indexOf(',')-1  ) ;
//        System.out.println(" rating:   " + rating );
//        votes = votes.replace(rating,""  );
//        System.out.println(" votes:   " + votes );
//
//        String removeStr= votes+rating+" " + votes;
//
//        areaCodeText = areaCodeText.replace( removeStr, "");
//        System.out.println(" areaCodeText:   " + areaCodeText );
    }

}
