package si.xlab.research.emmy.demo.client;

import mobile.PseudonymsysCAClientWrapper;
import mobile.PseudonymsysClientWrapper;
import mobile.CACertificate;
import mobile.Pseudonym;
import mobile.Credential;
import mobile.OrgPubKeys;

public class Pseudonymsys {

    private PseudonymsysClientWrapper client;

    public Pseudonymsys(String endpoint) {
        client = new PseudonymsysClientWrapper(endpoint);
    }

    public Pseudonym registerWithOrg(String secret, CACertificate cert) throws Exception {
       return client.generateNym(secret, cert);
    }

    public Credential obtainCredential(String secret, Pseudonym pseudonym, OrgPubKeys orgPubKeys)
        throws Exception {
        return client.obtainCredential(secret, pseudonym, orgPubKeys);
    }

    public boolean transferCredential(String org, String secret, Pseudonym pseudonym,
        Credential credential) throws Exception {
        return client.transferCredential(org, secret, pseudonym, credential);
    }
}
