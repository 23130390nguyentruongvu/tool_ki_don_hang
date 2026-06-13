package vn.edu.hcmuaf.fit;

import vn.edu.hcmuaf.fit.controller.HomeController;
import vn.edu.hcmuaf.fit.view.HomeGUI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HomeGUI homeGUI = new HomeGUI();
        new HomeController(homeGUI);
    }
}