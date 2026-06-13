package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.view.GenKeyPairGUI;
import vn.edu.hcmuaf.fit.view.HomeGUI;
import vn.edu.hcmuaf.fit.view.SignGUI;

import javax.swing.text.View;

public class HomeController {
    private HomeGUI view;
    private SignController signController;

    public HomeController(HomeGUI view){
        this.view = view;
        initController();
        signController = new SignController();
    }

    private void initController() {
        view.getBtn_Sign().addActionListener(e -> showSignGUI());
        view.getBtn_GenKey().addActionListener(e -> showGenKeyUI());
    }

    private void showGenKeyUI() {
        view.setVisible(false);
        GenKeyPairGUI genKeyPairGUI = new GenKeyPairGUI(this.view);
        new GenKeyPairController(genKeyPairGUI, view);
    }

    private void showSignGUI() {
        SignGUI signGUI = new SignGUI(signController);
        signGUI.setVisible(true);
    }
}
