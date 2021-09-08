package grupid1.artifact1;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String SITE_DOWN = "Site is Down";
    private final String SITE_UP = "Site is UP";
    private final String INCORRECT_URL = "URL is incorrect";

    @GetMapping("/check")
    public String getURLStatusMessage(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlobj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            System.out.println(responseCodeCategory);
            if (responseCodeCategory == 2 || responseCodeCategory == 3) {
                System.out.println("under if block");
                returnMessage = SITE_UP;

            } else {
                returnMessage = SITE_DOWN;
            }
        } catch (MalformedURLException e) {
            System.out.println("catched MalformedURLException");
            return INCORRECT_URL;
        } catch (IOException e) {
            System.out.println("catched IO Exception");
            return SITE_DOWN;
        }
        return returnMessage;

    }
}
