package org.example.modules.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class FileController {
    @Autowired
    private FileModel fileModel;

    @GetMapping("")
    public String loadUi(Model model) throws SQLException {
        List<FileCustom> fileList = fileModel.findAll();
        model.addAttribute("fileList", fileList);
        return "file-manager";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) throws SQLException {
        if(file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "redirect:/";
        }

        String uploadpath = "C:/Users/HELO BABE/Desktop/Fukuoka_Japan/module3_http/session6/hibernate_jpa/public/";
        String fileName = "file_" + System.currentTimeMillis() + "." + getFileExtension(file.getOriginalFilename());

        try {
            file.transferTo(new File(uploadpath + fileName));

            FileCustom newFile = new FileCustom();
            newFile.setName(file.getOriginalFilename());
            newFile.setUrl(fileName);
            newFile.setSize(lamTronSize(file.getSize()) + " MB");
            newFile.setCreateAt(String.valueOf(System.currentTimeMillis()));

            fileModel.create(newFile);

            return "redirect:/";
        }catch (Exception e) {
            return "redirect:/";
        }
    }


    public double lamTronSize(double value) {
        value = value / 1024 / 1024;
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }
}


