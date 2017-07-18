package si.xlab.research.emmy.demo.client;

import client.Client;
import client.PedersenClient;
import java.lang.Exception;

public class Pedersen {

    private PedersenClient client;

    public Pedersen(String endpoint, String variant, String p, String g, String o, String val)
            throws Exception {
        this.client = Client.newPedersenMobileClient(endpoint, variant, p, g, o, val);
    }

    public void Run() throws Exception {
        this.client.run();
    }
}
