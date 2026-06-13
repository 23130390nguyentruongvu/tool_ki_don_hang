package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.KeyGenModel;
import vn.edu.hcmuaf.fit.utils.FileUtils;
import vn.edu.hcmuaf.fit.view.GenKeyPairGUI;
import vn.edu.hcmuaf.fit.view.HomeGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class GenKeyPairController {

    private final GenKeyPairGUI view;
    private final KeyGenModel model;
    private HomeGUI homeGUI;
    private FileUtils fileUtils;
    public GenKeyPairController(GenKeyPairGUI view, HomeGUI homeGUI) throws HeadlessException {
        this.view = view;
        this.model = new KeyGenModel();
        initController();
        update();
        this.homeGUI = homeGUI;
    }
    private void initController() {
        view.getCbAlgorithm().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                update();
            }
        });
        view.getBtnGenKey().addActionListener(e-> {
            try {
                handleGenKey();
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeySpecException ex) {
                throw new RuntimeException(ex);
            }
        });
        view.getBtnExportKey().addActionListener(e-> handleExportKey());
        view.getBtnDelete().addActionListener(e-> handleDelete());
        view.getBtnReturn().addActionListener(e -> handleReturn());
    }

    private void handleReturn() {
        view.dispose();
        homeGUI.setVisible(true);
    }


    private void update() {
        boolean isRSA = "RSA".equals(view.getSelectedAlgorithm());
        view.setPaddingEnabled(isRSA);
        view.setModeEnabled(isRSA);
    }

    private void handleGenKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
        String algorithm = view.getSelectedAlgorithm();
        int keySize = view.getSelectedKeySize();

        model.genKey(algorithm,keySize);
        view.setPrivateKey(model.getPrivateKeyBase64());
        view.setPublicKey(model.getPublicKeyBase64());

    }catch (Exception e){
        JOptionPane.showMessageDialog(view,"Lỗi tạo khóa" + e.getMessage(),"Lỗi",JOptionPane.ERROR_MESSAGE);}
    }

    private void handleExportKey() {
        if(model.getPrivateKey() == null || model.getPublicKey() == null){
            JOptionPane.showMessageDialog(view,"Chưa có PrivateKey và PublicKey", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String path = FileUtils.getFolderPath();
        if(path == null){
            return;
        }
        try{
            FileUtils.exportKey(path + "\\" + "private_key.txt", model.getPrivateKeyBase64());
            FileUtils.exportKey(path + "\\" + "public_key.txt",  model.getPublicKeyBase64());
            JOptionPane.showMessageDialog(view, "Xuất khóa thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi xuất khóa: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void handleDelete() {
        view.setPrivateKey("");
        view.setPublicKey("");
    }
}