package com.SpringMVCHibernateThymeleaf.signPdf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateException;

/**
 * Created by tanmoy on 5/15/2017.
 */
@Controller
public class SignPdfController {
    @RequestMapping(value = "/signpdf", method = RequestMethod.GET)
    public String signPdf() {
        return "/signPdf/signPdf";
    }

    @RequestMapping(value = "/signpdf", method = RequestMethod.POST)
    public String SignPdf(MultipartFile multipartFile) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, SignatureException {
        return "redirect:/signpdf";
    }
}