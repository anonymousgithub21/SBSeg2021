package bkeauth4ispserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anonymous
 */
public interface BKEAuth4ISPServerContracts {
    
    interface View {

        public void showMessage2(String decrypted);
    }
    
    interface Router{}
    
    interface Presenter{

        public void onM2InformationRetrivied(String encryptedM2);

        public void downloadM2Information();

        public void onM5InformationRetrivied(String encryptedM5);
        
    }
    
    interface Interactor{

        public void downloadM2Information(String otac);
        
        public void sendM3Information(String payload, String otac);

        public void listenForM5(String otac);
        
        public void removeActiveSession(String otac);
    }
}
