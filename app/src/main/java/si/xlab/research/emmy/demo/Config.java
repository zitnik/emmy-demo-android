package si.xlab.research.emmy.demo;

import java.util.Map;

public class Config
{
    private String IP;
    private int port;
    private float timeout;
    private String key_folder;
    private Map<String,String> pedersen;
    private Map<String,String> schnorr;

    private Map<String,String> pseudonymsys;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public float getTimeout() {
        return timeout;
    }

    public void setTimeout(float timeout) {
        this.timeout = timeout;
    }

    public String getKey_folder() {
        return key_folder;
    }

    public void setKey_folder(String key_folder) {
        this.key_folder = key_folder;
    }

    public Map<String, String> getPedersen() {
        return pedersen;
    }

    public void setPedersen(Map<String, String> pedersen) {
        this.pedersen = pedersen;
    }

    public Map<String, String> getSchnorr() {
        return schnorr;
    }

    public void setSchnorr(Map<String, String> schnorr) {
        this.schnorr = schnorr;
    }

    public String getEndpoint() {
        return this.IP + ":" + this.port;
    }

    public Map<String, String> getPseudonymsys() {
        return pseudonymsys;
    }

    public void setPseudonymsys(Map<String, String> pseudonymsys) {
        this.pseudonymsys = pseudonymsys;
    }
}
