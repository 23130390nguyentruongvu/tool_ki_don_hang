package vn.edu.hcmuaf.fit.view;

import vn.edu.hcmuaf.fit.controller.GenKeyPairController;

import javax.swing.*;
import java.awt.*;

public class GenKeyPairGUI extends JFrame {

    private JComboBox<String> cb_algorithm, cb_mode, cb_padding;
    private JComboBox<Integer> cb_keySize;
    private JTextField jtf_privateKey, jtf_publicKey;
    private JButton btn_genKey, btn_exportKey, btn_return, btn_delete;
    private GenKeyPairController controller;
    public GenKeyPairGUI(HomeGUI homeGUI) throws HeadlessException {
        init();
        controller = new GenKeyPairController(this, homeGUI);
    }

    public void init() {
        initComponents();
        buildLayout();
        this.setSize(700, 520);
        setTitle("Tạo cặp khóa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {
        cb_algorithm = new JComboBox<>(new String[]{"RSA", "DSA"});
        cb_mode      = new JComboBox<>(new String[]{"ECB"});
        cb_padding   = new JComboBox<>(new String[]{"PKCS1Padding", "OAEPWithSHA-256AndMGF1Padding", "SHA256withRSA"});
        cb_keySize   = new JComboBox<>(new Integer[]{2048, 3072});

        jtf_privateKey = new JTextField();
        jtf_publicKey  = new JTextField();

        btn_genKey    = new JButton("Tạo khóa");
        btn_exportKey = new JButton("Xuất khóa");
        btn_genKey.setFont(new Font("Arial", Font.BOLD, 13));
        btn_genKey.setBackground(new Color(52, 152, 219));
        btn_genKey.setForeground(Color.WHITE);

        btn_return = new JButton("Quay lại");
        btn_delete = new JButton("Xóa");
    }

    private void buildLayout() {
        getContentPane().setLayout(new GridBagLayout());
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.25;
        add(new JLabel("Thuật toán:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.75;
        add(cb_algorithm, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.25;
        add(new JLabel("Mode:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.75;
        add(cb_mode, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.25;
        add(new JLabel("Padding:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.75;
        add(cb_padding, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.25;
        add(new JLabel("Key Size:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.75;
        add(cb_keySize, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.25;
        add(new JLabel("Khóa bí mật:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.75;
        JPanel p_private = new JPanel(new BorderLayout(5, 0));
        p_private.add(jtf_privateKey, BorderLayout.CENTER);
        p_private.add(btn_genKey,     BorderLayout.EAST);
        add(p_private, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.25;
        add(new JLabel("Khóa công khai:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.75;
        JPanel p_public = new JPanel(new BorderLayout(5, 0));
        p_public.add(jtf_publicKey, BorderLayout.CENTER);
        p_public.add(btn_exportKey, BorderLayout.EAST);
        add(p_public, gbc);

        gbc.gridx = 1; gbc.gridy = 6;
        gbc.insets = new Insets(15, 8, 8, 8);
        JPanel p_actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        p_actions.add(btn_return);
        p_actions.add(btn_delete);
        add(p_actions, gbc);
    }

    public JComboBox<String>  getCbAlgorithm()  { return cb_algorithm; }
    public JComboBox<String>  getCbMode()        { return cb_mode;      }
    public JComboBox<String>  getCbPadding()     { return cb_padding;   }
    public JComboBox<Integer> getCbKeySize()     { return cb_keySize;   }
    public JButton  getBtnGenKey()     { return btn_genKey;}
    public JButton getBtnExportKey()  { return btn_exportKey;}
    public JButton getBtnReturn()     { return btn_return;}
    public JButton getBtnDelete()     { return btn_delete;}



    public String  getSelectedAlgorithm() { return (String)  cb_algorithm.getSelectedItem(); }
    public String  getSelectedMode()      { return (String)  cb_mode.getSelectedItem();      }
    public String  getSelectedPadding()   { return (String)  cb_padding.getSelectedItem();   }
    public Integer getSelectedKeySize()   { return (Integer) cb_keySize.getSelectedItem();   }
    public String  getPrivateKey()        { return jtf_privateKey.getText().trim();           }
    public String  getPublicKey()         { return jtf_publicKey.getText().trim();            }


    public void setPrivateKey(String key)        { jtf_privateKey.setText(key);  }
    public void setPublicKey(String key)         { jtf_publicKey.setText(key);   }
    public void setPaddingEnabled(boolean flag)  { cb_padding.setEnabled(flag);  }
    public void setModeEnabled(boolean flag)     { cb_mode.setEnabled(flag);     }


}