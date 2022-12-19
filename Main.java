import java.net.URISyntaxException;

public class Main
{
    public static void main( String[] args )
    {
        try {
            WhatsappAPI api = new WhatsappAPI("<YOUR TOKEN>", "<YOUR PHONE NUMBER ID>", "<API VERSION>");
            api.sendTextMessage("<RECIPIENT PHONE NUMBER>", "<MESSAGE>", false);
            api.sendImageMessage("<RECIPIENT PHONE NUMBER", "<IMAGE URL>");
            api.sendLocationMessage("<RECIPIENT PHONE NUMBER>", "<LOCATION NAME>", "<LOCATION ADRESS>", 10.0, 10.0);
            api.sendReplyTextMessage("<RECIPIENT PHONE NUMBER>", "<MESSAGE>", false, "<MESSAGE ID>");
        }
        catch(URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
