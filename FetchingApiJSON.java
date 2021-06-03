
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.util.Scanner;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class FetchingApiJSON {

    public static void main(String[] args) {
        System.out.println(getData());
    }
    static ArrayList<String> getData(){
        String inline = "";

        try {
            //URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221");
            URL url = new URL("https://api.coinbase.com/v2/currencies");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            con.connect();

            int responseCode = con.getResponseCode();
            System.out.println(responseCode);

            if (responseCode != 200) {
                throw new RuntimeException("didnt connect!");
            } else {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                //System.out.println(inline);

                JSONParser parse = new JSONParser();
                JSONObject obj = (JSONObject) parse.parse(inline);

                JSONArray arr = (JSONArray) obj.get("data");
                //System.out.println(arr);
                ArrayList arrList = new ArrayList();
                for (int i = 0; i < arr.size(); i++) {
                    JSONObject obj1 = (JSONObject) arr.get(i);
                    //System.out.println(obj1.get("id"));
                    arrList.add(obj1.get("id"));
                }
                return arrList;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
