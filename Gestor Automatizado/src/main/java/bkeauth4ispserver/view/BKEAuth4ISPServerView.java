/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkeauth4ispserver.view;

import bkeauth4ispserver.BKEAuth4ISPServerContracts;
import bkeauth4ispserver.presenter.BKEAuth4ISPServerPresenter;
import java.util.Scanner;

public class BKEAuth4ISPServerView implements bkeauth4ispserver.BKEAuth4ISPServerContracts.View {

    BKEAuth4ISPServerContracts.Presenter presenter;

    public static void main(String[] args) {
        BKEAuth4ISPServerView view = new BKEAuth4ISPServerView();
        view.presenter = new BKEAuth4ISPServerPresenter(view);
        view.presenter.downloadM2Information();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
    @Override
    public void showMessage2(String decrypted) {
        //System.out.println("Decrypted: " +decrypted);
    }

}
