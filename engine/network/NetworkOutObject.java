package engine.network;

import java.io.DataOutputStream;

public interface NetworkOutObject {
    public void send(DataOutputStream dataOut);
}
