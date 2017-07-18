package si.xlab.research.emmy.demo.client;

import client.Client;
import client.CSPaillierClient;
import java.lang.Exception;


public class CSPaillier
{
    private CSPaillierClient client;

    public CSPaillier(String endpoint, String pubKeyPath, String m, String label)
            throws Exception {
        this.client = Client.newCSPaillierMobileClient(endpoint, pubKeyPath, m, label);
    }

    public void Run() throws Exception {
        this.client.run();
    }

}
