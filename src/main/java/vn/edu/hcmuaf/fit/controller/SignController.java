package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.SigningModel;
import vn.edu.hcmuaf.fit.utils.FileUtils;
import vn.edu.hcmuaf.fit.view.SignGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Date;

public class SignController implements ActionListener {
    private SignGUI signGUI;
    private SigningModel signingModel;

    public void setSignGUI(SignGUI signGUI) {
        this.signGUI = signGUI;
        signingModel = new SigningModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == signGUI.btn_sign_import_key) {
            handleImportKey();
        } else if (src == signGUI.btn_sign_execute) {
            try {
                handleSign();
            } catch (Exception ex) {
                signGUI.showMessage(ex.getMessage());
            }
        } else if (src == signGUI.btn_sign_copy) {
            handleCopyResult();
        } else if (src == signGUI.btn_input_file) {
            handleInputFile();
        } else if (src == signGUI.btn_export_file) {
            try {
                handleExportFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void handleExportFile() throws IOException {
        String data = signGUI.txtArea_sign_result.getText();
        if (data.isEmpty()) {
            signGUI.showMessage("Chưa có dữ liệu");
            return;
        }
        String path = FileUtils.getFolderPath();
        try {
            String pathDetail = path +File.separator + "chu_ki_so" + new Date().getTime() + ".txt";
            FileUtils.exportKey(pathDetail, data);
            signGUI.showMessage("Xuất file thành công!");
        } catch (Exception e) {
            signGUI.showMessage("Lỗi xuất file: " + e.getMessage());
        }
    }

    private void handleInputFile() {
        String path = FileUtils.getFilePath();
        if (path == null){
            return;
        }
        try {
            String data  = FileUtils.importKey(path);
            signGUI.txtArea_sign_hash.setText(data);
        } catch (IOException e) {
            signGUI.showMessage("Không thể tải file: " + e.getMessage());
        }

    }

    private void handleSign() throws Exception {
        String privateKey = signGUI.jtf_private_key.getText();
        String hashData = signGUI.txtArea_sign_hash.getText();
        if (!validateInputData(privateKey, hashData))
            return;

       String result = signingModel.signOrder(privateKey, hashData, signGUI.cb_sign_algo.getSelectedItem().toString());

       signGUI.txtArea_sign_result.setText(result);
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

    private boolean validateInputData(String privateKey, String hashData) {
        if (privateKey == null || privateKey.trim().isEmpty()) {
            signGUI.showMessage("Lỗi: Khóa bí mật không được để trống!");
            return false;
        }

        if (hashData == null || hashData.trim().isEmpty()) {
            signGUI.showMessage("Lỗi: Mã băm đơn hàng không được để trống!");
            return false;
        }

        String cleanHash = hashData.trim();
        if (cleanHash.length() != 64) {
            signGUI.showMessage("Cảnh báo: Mã băm SHA-256 dạng Hex chuẩn phải có độ dài đúng 64 ký tự.\n" +
                    "Hiện tại chuỗi của bạn có " + cleanHash.length() + " ký tự. Vui lòng kiểm tra lại!");
            return false;
        }

        if (!cleanHash.matches("^[0-9a-fA-F]+$")) {
            signGUI.showMessage("Lỗi: Mã băm đơn hàng chứa ký tự không hợp lệ! Chuỗi Hex chỉ bao gồm các ký tự từ 0-9 và từ A-F.");
            return false;
        }

        return true;
    }
}
