package bkeauth4ispserver.entity;

import bkeauth4ispserver.common.SecurityUtilies;
import java.security.NoSuchAlgorithmException;

public class M3AuthSolicitation {

    String nonceClient;
    String nonceTechnician;
    String nonceIsp;
    String clientUsername;
    String ISPId;
    String HMAC;
    String OTAC;

    public M3AuthSolicitation(String nonceTechnician, String nonceClient, String nonceIsp, String clientUsername, String ISPId, String OTAC) {
        this.nonceClient = nonceClient;
        this.nonceTechnician = nonceTechnician;
        this.nonceIsp = nonceIsp;
        this.clientUsername = clientUsername;
        this.ISPId = ISPId;
        this.OTAC = OTAC;
        this.HMAC = getGenerateHMAC();
    }

    public String getGenerateHMAC() {   

        String input = nonceTechnician.concat("=").concat(nonceClient).concat("=").concat(nonceIsp).
                concat("=").concat(clientUsername).concat("=").concat(ISPId);
        try {
            HMAC = SecurityUtilies.hash256(input.concat(OTAC));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        HMAC = input;
        return HMAC;
    }

    public String getPayload() {
        String input = nonceTechnician.concat("=").concat(nonceClient).concat("=").concat(nonceIsp).
                concat("=").concat(clientUsername).concat("=").concat(ISPId).concat("=").concat(getGenerateHMAC());
        return input;
    }

    public String getISPId() {
        return ISPId;
    }

    public void setISPId(String ISPId) {
        this.ISPId = ISPId;
    }

    public String getNonceTechnician() {
        return nonceTechnician;
    }

    public void setNonceTechnician(String nonceTechnician) {
        this.nonceTechnician = nonceTechnician;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getNonceClient() {
        return nonceClient;
    }

    public void setNonceClient(String nonceClient) {
        this.nonceClient = nonceClient;
    }

    public void setHMAC(String HMAC) {
        this.HMAC = HMAC;
    }

    public String getOTAC() {
        return OTAC;
    }

    public void setOTAC(String OTAC) {
        this.OTAC = OTAC;
    }
}
