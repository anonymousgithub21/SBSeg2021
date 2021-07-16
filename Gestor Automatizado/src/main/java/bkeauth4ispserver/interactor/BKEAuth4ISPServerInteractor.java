/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkeauth4ispserver.interactor;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anonymou
 */
public class BKEAuth4ISPServerInteractor implements bkeauth4ispserver.BKEAuth4ISPServerContracts.Interactor {

    bkeauth4ispserver.BKEAuth4ISPServerContracts.Presenter presenter;

    public BKEAuth4ISPServerInteractor(bkeauth4ispserver.BKEAuth4ISPServerContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void downloadM2Information(String otac) {
        FileInputStream serviceAccount;
        try {
//            serviceAccount = new FileInputStream("C:\\Users\\vagne\\Downloads\\BKE_Auth4isp_Server-master\\BKE_Auth4isp_Server-master\\inovaisp-firebase-adminsdk-urcvf-1b5492eea5.json");
            serviceAccount = new FileInputStream("inovaisp-firebase-adminsdk-urcvf-1b5492eea5.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://inovaisp-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            DatabaseReference database;
            database = FirebaseDatabase.getInstance().getReference().child("PROTOCOL").child("active_session").child(otac).child("M2").child("payload");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot ds) {

                    String encryptedM2 = ds.getValue(String.class);
                    presenter.onM2InformationRetrivied(encryptedM2);
                }

                @Override
                public void onCancelled(DatabaseError de) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }); //     }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(BKEAuth4ISPServerInteractor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(BKEAuth4ISPServerInteractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendM3Information(String payload, String otac) {

        DatabaseReference database;
        database = FirebaseDatabase.getInstance().getReference().child("PROTOCOL").child("active_session").child(otac).child("M3").child("payload");
        database.setValueAsync(payload).addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("Run");
            }
        }, new Executor() {
            @Override
            public void execute(Runnable r) {
                System.out.println("Executer" + r);
            }
        });
    }

    @Override
    public void listenForM5(String otac) {
        FileInputStream serviceAccount;
        //System.out.println("Ouvindo M5");

        //serviceAccount = new FileInputStream("C:\\Users\\vagne\\Documents\\NetBeansProjects\\BKEAuth4ISP\\inovaisp-firebase-adminsdk-urcvf-1b5492eea5.json");
        //FirebaseOptions options = new FirebaseOptions.Builder()
        //        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        //        .setDatabaseUrl("https://inovaisp-default-rtdb.firebaseio.com")
        //       .build();
        //FirebaseApp.initializeApp(options);
        DatabaseReference database;
        database = FirebaseDatabase.getInstance().getReference().child("PROTOCOL").child("active_session").child(otac).child("M5").child("payload");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {

                String encryptedM5 = ds.getValue(String.class);
                // System.out.println("M5 Encryptado: " + encryptedM5);
                presenter.onM5InformationRetrivied(encryptedM5);
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }); //     }

    }

    @Override
    public void removeActiveSession(String otac) {
        FileInputStream serviceAccount;
        DatabaseReference database;
        database = FirebaseDatabase.getInstance().getReference().child("PROTOCOL").child("active_session").child(otac);

        database.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError de, DatabaseReference dr) {
                System.out.println("Sessão removida!");
            }
        });
    }
}
