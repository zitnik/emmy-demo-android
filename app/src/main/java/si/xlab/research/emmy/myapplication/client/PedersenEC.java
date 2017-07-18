package si.xlab.research.emmy.myapplication.client;

import client.Client;
import client.PedersenECClient;
import java.lang.Exception;

public class PedersenEC {

    private PedersenECClient client;

    public PedersenEC(String endpoint, String val)
            throws Exception {
        this.client = Client.newPedersenECMobileClient(endpoint, val);
    }

    public void Run() throws Exception {
        this.client.run();
    }
}
