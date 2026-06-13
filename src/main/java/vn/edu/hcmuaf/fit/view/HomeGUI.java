package vn.edu.hcmuaf.fit.view;

import javax.swing.*;
import java.awt.*;

public class HomeGUI extends JFrame {
    private JPanel panel, mainPanel;
    private JButton btn_Sign, btn_GenKey;
    private CardLayout cardLayout;
    public HomeGUI() {
        this.setTitle("Tool chữ ký điện tử");
        this.setSize(700, 520);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btn_Sign = new JButton("Ký Đơn Hàng");
        btn_GenKey = new JButton("Tạo Khóa");
        panel.add(btn_Sign);
        panel.add(btn_GenKey);
        this.setContentPane(mainPanel);
        mainPanel.add(panel, "MAIN");
        cardLayout.show(mainPanel, "MAIN");
        this.setVisible(true);
    }
    public void showPanel(String name){
        cardLayout.show(mainPanel, name);
        revalidate();
        repaint();
    }

    public JButton getBtn_Sign() {
        return btn_Sign;
    }

    public JButton getBtn_GenKey() {
        return btn_GenKey;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
