import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegEx {


    public static void main(String[] args) {
        String content = "Let's find the symbols or currencies : $ Dollar, € Euro, ¥ Yen";


//        String sb = "<div class=\"price-content\">" +
//                "    <a href=\"#\" class=\"toggle-extra-costs icon\" data-func=\"toggler\" data-target=\"this\">" +
//                "        Επιπλέον χρεώσεις" +
//                "    </a>" +
//                "    " +
//                "    <a href=\"/products/show/33564574\" rel=\"nofollow\" " +
//                "        class=\"product-link js-product-link\" data-func=\"trigger_shop_uservoice\" data-uservoice-pid=\"33564574\" data-append-element=\".shop-details\" data-uservoice-shopid=\"18\" " +
//                "        data-type=\"net_price\">" +
//                "        88,00 €" +
//                "    </a>" +
//                "    " +
//                "    <span class=\"extra-cost cf\">" +
//                "        <em>+ 0,00 €</em> <span>Μεταφορικά</span>" +
//                "    </span>" +
//                "    <span class=\"extra-cost cf\">" +
//                "" +
//                "    </span>" +
//                "    " +
//                "    <span class=\"final-price \">" +
//                "        <a href=\"/products/show/33564574\" rel=\"nofollow\" class=\"js-product-link\" data-func=\"trigger_shop_uservoice\" data-uservoice-pid=\"33564574\" data-append-element=\".shop-details\" data-uservoice-shopid=\"18\" data-type=\"final_price\">" +
//                "            88,00 €" +
//                "        </a>" +
//                "    </span>" +
//                "</div>";



        // https://www.buildmystring.com


        String sb = "<div class=\"price-content\">" +
                "  <a href=\"#\" class=\"toggle-extra-costs icon\" data-func=\"toggler\" data-target=\"this\">" +
                "    Επιπλέον χρεώσεις" +
                "  </a>" +
                "    <a href=\"/products/show/40743878\" rel=\"nofollow\" class=\"product-link js-product-link\" data-func=\"trigger_shop_uservoice\" data-uservoice-pid=\"40743878\" data-append-element=\".shop-details\" data-uservoice-shopid=\"2032\" data-type=\"net_price\">" +
                "      74,75 €" +
                "    </a>" +
                "  <span class=\"extra-cost cf\">" +
                "    <em>+ 3,00 €</em> <span>Μεταφορικά</span>" +
                "  </span>" +
                "    <span class=\"extra-cost cf\">" +
                "" +
                "    </span>" +
                "    <span class=\"final-price \">" +
                "        <a href=\"/products/show/40743878\" rel=\"nofollow\" class=\"js-product-link\" data-func=\"trigger_shop_uservoice\" data-uservoice-pid=\"40743878\" data-append-element=\".shop-details\" data-uservoice-shopid=\"2032\" data-type=\"final_price\">" +
                "          80,75 €" +
                "        </a>" +
                "    </span>" +
                "</div>";




//        String regex = "\\p{Sc}";
        String regex =   "\\d+(,\\d+)?\\s+(\u20AC)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sb);
        while (matcher.find())
        {
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end() + " ");
            System.out.println(" : " + matcher.group());
        }
    }

}
