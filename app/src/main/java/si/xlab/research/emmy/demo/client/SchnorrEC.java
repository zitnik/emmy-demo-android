package si.xlab.research.emmy.demo.client;


import client.Client;
import client.SchnorrECClient;

public class SchnorrEC {
    private SchnorrECClient client;

    public SchnorrEC(String endpoint, String variant, String secret)
        throws Exception {
        this.client = Client.newSchnorrECMobileClient(endpoint, variant, secret);
    }

    public void Run() throws Exception {
        this.client.run();
    }
}
