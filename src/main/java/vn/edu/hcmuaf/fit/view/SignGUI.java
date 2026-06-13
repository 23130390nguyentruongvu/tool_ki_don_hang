package vn.edu.hcmuaf.fit.view;

import vn.edu.hcmuaf.fit.controller.SignController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignGUI extends JFrame {
    public JTextField jtf_private_key;
    public JComboBox<String> cb_sign_algo;

    public JTextArea txtArea_sign_hash;
    public JTextArea txtArea_sign_result;

    private JScrollPane scroll_sign_hash;
    private JScrollPane scroll_sign_result;

    public JButton btn_sign_import_key, btn_sign_execute, btn_sign_copy;

    private SignController signController;

    public SignGUI(SignController signController) {
        this.signController = signController;
        this.signController.setSignGUI(this);
        init();
        registerController(this.signController);
    }

    public void init() {
        initComponents();
        buildLayout();

        this.setSize(700, 520);
        this.setTitle("Ký đơn hàng");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void initComponents() {
        jtf_private_key = new JTextField();
        btn_sign_import_key = new JButton("Nhập key từ file");
        cb_sign_algo = new JComboBox<>(new String[]{"SHA256withRSA", "SHA256withDSA"});

        //vùng nhập mã hash
        txtArea_sign_hash = new JTextArea(4, 20);
        txtArea_sign_hash.setLineWrap(true);
        txtArea_sign_hash.setWrapStyleWord(true);

        scroll_sign_hash = new JScrollPane(txtArea_sign_hash);
        scroll_sign_hash.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll_sign_hash.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //vùng hiển thị chữ ký
        txtArea_sign_result = new JTextArea(5, 20);
        txtArea_sign_result.setLineWrap(true);
        txtArea_sign_result.setWrapStyleWord(true);
        txtArea_sign_result.setEditable(false);
        txtArea_sign_result.setBackground(new Color(245, 246, 250));

        scroll_sign_result = new JScrollPane(txtArea_sign_result);
        scroll_sign_result.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll_sign_result.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btn_sign_execute = new JButton("THỰC HIỆN KÝ ĐƠN HÀNG");
        btn_sign_execute.setFont(new Font("Arial", Font.BOLD, 14));
        btn_sign_execute.setBackground(new Color(52, 152, 219));
        btn_sign_execute.setForeground(Color.WHITE);

        btn_sign_copy = new JButton("Sao chép Chữ ký số");
    }

    private void buildLayout() {
        this.getContentPane().setLayout(new GridBagLayout());

        ((JPanel) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.BOTH;

        //dòng chọn private key và nút nhập key
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.25;
        gbc.weighty = 0;
        this.add(new JLabel("Khóa bí mật:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.75;
        JPanel p_key = new JPanel(new BorderLayout(5, 0));

        p_key.add(jtf_private_key, BorderLayout.CENTER);
        p_key.add(btn_sign_import_key, BorderLayout.EAST);
        this.add(p_key, gbc);

        //dòng chọn thuật toán đẻ ký
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        this.add(new JLabel("Chọn thuật toán ký:"), gbc);
        gbc.gridx = 1;
        this.add(cb_sign_algo, gbc);

        //dòng nhập mã băm từ web
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(new JLabel("Mã băm đơn hàng (SHA-256):"), gbc);
        gbc.gridx = 1;
        gbc.weighty = 0.25;
        this.add(scroll_sign_hash, gbc);

        //dòng chứa nút ký đơn
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        gbc.insets = new Insets(15, 8, 15, 8);
        this.add(btn_sign_execute, gbc);

        //dòng xuất kết quả ký
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.weighty = 0.35;
        gbc.insets = new Insets(8, 8, 8, 8);
        this.add(new JLabel("Chữ ký số kết quả:"), gbc);
        gbc.gridx = 1;
        this.add(scroll_sign_result, gbc);

        //dòng chứa nút sao chép
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weighty = 0;
        this.add(btn_sign_copy, gbc);
    }

    public void registerController(ActionListener actionListener) {
        btn_sign_import_key.addActionListener(actionListener);
        btn_sign_execute.addActionListener(actionListener);
        btn_sign_copy.addActionListener(actionListener);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(
                this,
                msg,
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {
        new SignGUI(new SignController());
    }
}