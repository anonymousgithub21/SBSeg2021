/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkeauth4ispserver.common;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 *
 * @author anonymous
 */
public class CommonDatabaseReferences {
    public static DatabaseReference getBaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseReference getLoginReference(String username) {
        DatabaseReference reference = getBaseRef().child("ISP").child("INTERNEITH").child("User").child(username);
        return reference;
    }

    public static DatabaseReference getCallsReference(String username) {
        DatabaseReference reference = getBaseRef().child("ISP").child("INTERNEITH").child("Calls").child(username);
        return reference;
    }

    public static DatabaseReference getFeedbacksReference(String username) {
        DatabaseReference reference = getBaseRef().child("ISP").child("INTERNEITH").child("Feedback").child(username);
        return reference;
    }

    public static DatabaseReference getProfileReference(String username, String role) {
        DatabaseReference reference = getBaseRef().child("ISP").child("INTERNEITH").child(role).child(username);
        return reference;
    }

    public static DatabaseReference getTechnicianReference(String username) {
        DatabaseReference reference = getBaseRef().child("ISP").child("INTERNEITH").child("Technician").child(username);
        return reference;
    }

    public static DatabaseReference getIspsReference() {
        DatabaseReference reference = getBaseRef().child("ISP_LIST");
        return reference;
    }

    public static DatabaseReference getClientReference(String username) {
        DatabaseReference reference = getBaseRef().child("ISP").child("INTERNEITH").child("Client").child(username);
        return reference;
    }

    public static DatabaseReference getClientAPIReference(String cpf) {
        DatabaseReference reference = getBaseRef().child("API").child("Client").child(cpf);
        return reference;
    }

    public static DatabaseReference getTechnicianAPIReference(String cpf) {
        DatabaseReference reference = getBaseRef().child("API").child("Technician").child(cpf);
        return reference;
    }

    public static DatabaseReference getClientReference() {
        DatabaseReference reference = getBaseRef().child("ISP").child("INTERNEITH").child("Client");
        return reference;
    }

    public static DatabaseReference getTechnicianListReference() {
        DatabaseReference reference = getBaseRef().child("ISP").child("INTERNEITH").child("Technician");
        return reference;
    }

    public static DatabaseReference getOTACReference(String username) {
        DatabaseReference reference = getBaseRef().child("PROTOCOL").child("session").child(username);
        return reference;
    }

    public static DatabaseReference getActiveSessionReference(String OTAC) {
        DatabaseReference reference = getBaseRef().child("PROTOCOL").child("active_session").child(OTAC);
        return reference;
    }
}
