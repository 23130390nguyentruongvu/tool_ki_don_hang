package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.utils.FileUtils;
import vn.edu.hcmuaf.fit.view.SignGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignController implements ActionListener {
    private SignGUI signGUI;

    public void setSignGUI(SignGUI signGUI) {
        this.signGUI = signGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if(src == signGUI.btn_sign_import_key) {
            handleImportKey();
        } else if (src == signGUI.btn_sign_execute) {
            handleSign();
        } else if (src == signGUI.btn_sign_copy) {
            handleCopyResult();
        }
    }

    private void handleSign() {
    }

    private void handleCopyResult() {
        String signatureText = signGUI.txtArea_sign_result.getText().trim();

        if (signatureText.isEmpty()) {
            signGUI.showMessage("Chưa có kết quả để sao chép");
            return;
        }

        try {
            java.awt.datatransfer.StringSelection stringSelection = new java.awt.datatransfer.StringSelection(signatureText);
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            signGUI.showMessage("Sao chép thành công");
        } catch (Exception ex) {
            signGUI.showMessage("Lỗi: " + ex.getMessage());
        }
    }

    private void handleImportKey() {
        String filePath = FileUtils.getFilePath();
        try {
            signGUI.jtf_private_key.setText(FileUtils.importKey(filePath));
        } catch (Exception e) {
            signGUI.showMessage("Lỗi nhập key từ file, vui lòng thử lại");
        }
    }
}
