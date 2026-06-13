package vn.edu.hcmuaf.fit.view;

import javax.swing.*;
import java.awt.*;

public class GenKeyPairGUI extends JFrame {
    private JPanel panelMain, panelHeader, panelBody;
    private JLabel lb_privateKey, lb_publicKey, lb_algorithm, lb_padding, lb_mode, lb_keySize;
    private JTextField tf_privateKey, tf_publicKey;
    private JButton btn_genKeyPair, btn_exportKeyPair, btn_return, btn_delete;
    private JComboBox<String> algorithm, padding,mode;
    private JComboBox<Integer> keySize;

    public GenKeyPairGUI() throws HeadlessException {
        initView();
    }

    private void initView() {

        this.setTitle("GenKeyPair");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        panelMain = new JPanel(new BorderLayout());

        panelHeader = new JPanel(new GridLayout(4,4));
        lb_algorithm = new JLabel("Algorithm:");
        lb_mode = new JLabel("Mode:");
        lb_padding = new JLabel("Padding:");
        lb_keySize = new JLabel("Key Size:");

        String[] algorithms = new String[]{"RSA", "DSA"};
        algorithm = new JComboBox<>(algorithms);
        String[] modes = new String[]{"ECB"};
        mode = new JComboBox<>(modes);
        String[] paddings = new String[]{"PKCS1Padding","OAEPWithSHA-256AndMGF1Padding","SHA256withRSA"};
        padding = new JComboBox<>(paddings);
        Integer[] keySizes = new Integer[]{1024,2048,3072};
        keySize = new JComboBox<>(keySizes);

        panelHeader.add(lb_algorithm);
        panelHeader.add(algorithm);
        panelHeader.add(lb_mode);
        panelHeader.add(mode);
        panelHeader.add(lb_padding);
        panelHeader.add(padding);
        panelHeader.add(lb_keySize);
        panelHeader.add(keySize);
        panelHeader.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelHeader.setBorder(BorderFactory.createTitledBorder("Key"));

        panelBody = new JPanel(new BorderLayout());
        JPanel panelKey = new JPanel(new GridLayout(2,3));
        lb_privateKey = new JLabel("Private Key:");
        lb_publicKey = new JLabel("Public Key:");
        tf_privateKey = new JTextField();
        tf_publicKey = new JTextField();
        btn_genKeyPair = new JButton("Gen Key");
        btn_exportKeyPair = new JButton("Export Key");
        panelKey.add(lb_privateKey);
        panelKey.add(tf_privateKey);
        panelKey.add(btn_genKeyPair);
        panelKey.add(lb_publicKey);
        panelKey.add(tf_publicKey);
        panelKey.add(btn_exportKeyPair);
        JPanel panelHelper = new JPanel(new FlowLayout());
        btn_return = new JButton("Return");
        btn_delete = new JButton("Delete");
        panelHelper.add(btn_return);
        panelHelper.add(btn_delete);
        panelBody.add(panelKey, BorderLayout.NORTH);
        panelBody.add(panelHelper, BorderLayout.CENTER);
        panelBody.setBorder(BorderFactory.createTitledBorder("Gen Key"));
        panelMain.add(panelHeader, BorderLayout.NORTH);
        panelMain.add(panelBody, BorderLayout.CENTER);
        this.setContentPane(panelMain);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GenKeyPairGUI();
    }
}
