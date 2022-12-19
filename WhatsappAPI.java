import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.Builder;

public class WhatsappAPI {

    private final URI uri;
    private final Builder builder;

    public WhatsappAPI(String token, String phoneNumberID, String version) throws URISyntaxException{
        this.uri = new URI("https://graph.facebook.com/" + version + "/" + phoneNumberID + "/messages");
        this.builder = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");
    }

    public HttpResponse<String> sendTextMessage(String recipient, String message, boolean preview_url) {
        HttpRequest request = builder
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\n" +
                                "    \"messaging_product\": \"whatsapp\",    \n" +
                                "    \"recipient_type\": \"individual\",\n" +
                                "    \"to\": \"" + recipient + "\",\n" +
                                "    \"type\": \"text\",\n" +
                                "    \"text\": {\n" +
                                "        \"preview_url\": " + preview_url + ",\n" +
                                "        \"body\": \"" + message + "\"\n" +
                                "    }\n" +
                                "}"
                )).build();
        return sendRequest(request);
    }

    public HttpResponse<String> sendImageMessage(String recipient, String imageURL) {
        HttpRequest request = builder
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\n" +
                                "    \"messaging_product\": \"whatsapp\",\n" +
                                "    \"recipient_type\": \"individual\",\n" +
                                "    \"to\": \"" + recipient + "\",\n" +
                                "    \"type\": \"image\",\n" +
                                "    \"image\": {\n" +
                                "        \"link\": \"" + imageURL + "\"\n" +
                                "    }\n" +
                                "}"
                )).build();
        return sendRequest(request);
    }

    public HttpResponse<String> sendLocationMessage(String recipient, String locationName, String locationAdress, double latitude, double longitude) {
        HttpRequest request = builder
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\n" +
                                "    \"messaging_product\": \"whatsapp\",\n" +
                                "    \"recipient_type\": \"individual\",\n" +
                                "    \"to\": \"" + recipient + "\",\n" +
                                "    \"type\": \"location\",\n" +
                                "    \"location\": {\n" +
                                "        \"latitude\": \"" + latitude + "\",\n" +
                                "        \"longitude\": \"" + longitude + "\",\n" +
                                "        \"name\": \"" + locationName + "\",\n" +
                                "        \"address\": \"" + locationAdress + "\"\n" +
                                "    }\n" +
                                "}"
                )).build();
        return sendRequest(request);
    }

    public HttpResponse<String> sendReplyTextMessage(String recipient, String message, boolean preview_url, String messageID) {
        HttpRequest request = builder
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\n" +
                                "    \"messaging_product\": \"whatsapp\",\n" +
                                "    \"recipient_type\": \"individual\",\n" +
                                "    \"to\": \"" + recipient + "\",\n" +
                                "    \"context\": {\n" +
                                "        \"message_id\": \"" + messageID +"\"\n" +
                                "    },\n" +
                                "    \"type\": \"text\",\n" +
                                "    \"text\": {\n" +
                                "        \"preview_url\": " + preview_url + ",\n" +
                                "        \"body\": \"" + message +"\"\n" +
                                "    }\n" +
                                "}"
                )).build();
        return sendRequest(request);
    }

    private HttpResponse<String> sendRequest(HttpRequest request) {
        try {
            return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch(IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
