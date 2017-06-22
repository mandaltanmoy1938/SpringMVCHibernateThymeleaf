package com.SpringMVCHibernateThymeleaf.Crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Tanmoy on 5/9/2017.
 */
@Controller
public class CryptoController {

    @RequestMapping(value = "/crypto", method = RequestMethod.GET)
    public String crypto() {
        return "cryptos/crypto";
    }

    @RequestMapping(value = "/crypto", method = RequestMethod.POST)
    public String crypto(MultipartFile multipartFile) throws IOException, CryptoException {
        String fileName[] = (multipartFile.getOriginalFilename()).split("\\.");
        String extension=fileName[fileName.length - 1];
        System.out.println(multipartFile.getOriginalFilename());
        //multipartfile to file
        File file=new File(multipartFile.getOriginalFilename());
//        file.createNewFile();
        //writing multipart file to file
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        //create file to write encrypt data
        File encrytedFile=new File("encryted."+extension);
//        encrytedFile.createNewFile();
        //write encrypted file
        AESEncDec.encrypt("it's crypto time",file,encrytedFile);

        //create file to write decrypt data
        File decrytedFile=new File("decryted."+extension);
        decrytedFile.createNewFile();
        //write decrypted file
        AESEncDec.decrypt("it's crypto time",encrytedFile,decrytedFile);
        encrytedFile.delete();
        return "redirect:/crypto";
    }
}
