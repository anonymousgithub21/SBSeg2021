package com.tcc2.bke_auth4isp.entity;

import com.tcc2.bke_auth4isp.common.SecurityUtilities;

import java.security.NoSuchAlgorithmException;

public class AuthSolicitation {

    String technicianUsername;
    String ISPId;
    String nonceTechnician;
    String clientUsername;
    String nonceClient;
    String HMAC;
    String OTAC;

    public AuthSolicitation(String technicianUsername, String ISPId, String nonceTechnician,
                            String clientUsername, String nonceClient,
                            String OTAC) {
        this.technicianUsername = technicianUsername;
        this.ISPId = ISPId;
        this.nonceTechnician = nonceTechnician;
        this.clientUsername = clientUsername;
        this.nonceClient = nonceClient;
        this.OTAC = OTAC;
        this.HMAC = getGenerateHMAC();
    }

    public String getGenerateHMAC() {
        String input = technicianUsername.concat("=").concat(ISPId).concat("=").concat(nonceTechnician).
                concat("=").concat(clientUsername).concat("=").concat(nonceClient);
        try {
            HMAC = SecurityUtilities.hash256(input.concat(OTAC));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return HMAC;
    }

    public String getPayload() {
        String input = technicianUsername.concat("=").concat(ISPId).concat("=").concat(nonceTechnician).
                concat("=").concat(clientUsername).concat("=").concat(nonceClient).concat("=").concat(getGenerateHMAC());
        return input;
    }

    public String getGenerateHMACM2() {
        String input = clientUsername.concat("=").concat(technicianUsername).concat("=").concat(nonceTechnician).
                concat("=").concat(nonceClient);
        try {
            HMAC = SecurityUtilities.hash256(input.concat(OTAC));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return HMAC;
    }

    public String getPayloadM2() {
        String input = clientUsername.concat("=").concat(technicianUsername).concat("=").concat(nonceTechnician).
                concat("=").concat(nonceClient).concat("=").concat(getGenerateHMACM2());
                return input;
    }

    public String getTechnicianUsername() {
        return technicianUsername;
    }

    public void setTechnicianUsername(String technicianUsername) {
        this.technicianUsername = technicianUsername;
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
