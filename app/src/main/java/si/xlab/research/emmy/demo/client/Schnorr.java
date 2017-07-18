package si.xlab.research.emmy.demo.client;

import client.Client;
import client.SchnorrClient;

public class Schnorr {

        private SchnorrClient client;

        public Schnorr(String endpoint, String variant, String p, String g, String o, String s)
                throws Exception {
            this.client = Client.newSchnorrMobileClient(endpoint, variant, p, g, o, s);
        }

        public void Run() throws Exception {
            this.client.run();
        }
}
