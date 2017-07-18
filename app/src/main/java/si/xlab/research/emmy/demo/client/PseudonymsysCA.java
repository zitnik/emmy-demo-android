package si.xlab.research.emmy.demo.client;

import mobile.CACertificate;
import mobile.Mobile;

public class PseudonymsysCA
{
    private mobile.PseudonymsysCAClientWrapper client;

    public PseudonymsysCA(String endpoint)
            throws Exception {
        this.client = Mobile.newPseudonymsysCAClientWrapper(endpoint);
    }

    public CACertificate getCertificate(String secret, String pseudonymA, String pseudonymB)
            throws Exception {
        return this.client.obtainCertificate(secret, pseudonymA, pseudonymB);
    }
}
