package vn.edu.hcmuaf.fit.utils;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class FileUtils {
    //method này nhận vào đường dẫn đến file văn bản để đọc ra 1 dòng
    public static String importKey(String source) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        String key = br.readLine();

        br.close();
        isr.close();
        fis.close();

        return key;
    }

    //method này giúp xuất 1 đoạn văn bản ra file
    public static void exportKey(String dest, String key) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(dest, StandardCharsets.UTF_8));
        bw.write(key);

        bw.close();
    }

    //method này hỗ trợ mở một dialog chọn file và trả về đường dẫn file đó
    private static String getFilePath() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Chọn file");
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            return fileToOpen.getAbsolutePath();
        }

        return null;
    }

    //method này giúp lấy ra đường dẫn thư mục, ứng dụng trong việc lưu khóa ra file
    private static String getFolderPath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn thư mục");

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File folderSelected = fileChooser.getSelectedFile();
            return folderSelected.getAbsolutePath();
        }

        return null;
    }
}
