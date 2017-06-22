package com.SpringMVCHibernateThymeleaf.users.controller;

import com.SpringMVCHibernateThymeleaf.users.model.UserEntity;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsHtmlView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        List<UserEntity> userList = userService.findAllUser();
        model.addAttribute("userList", userList);
        return "users/index";
    }

    @GetMapping(value = "/createUser")
    public String createUser(Model model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("title", "Create User");
        return "users/saveUser";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute UserEntity user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/updateUser/{id}")
    public String updateUser(Model model, @PathVariable int id) {
        UserEntity user = userService.getUsers(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "Update User");
        return "users/saveUser";
    }

    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/user/list.pdf", method = RequestMethod.GET)
    public
    @ResponseBody
    void userListPdf(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType("applicaton/x-pdf");
            httpServletResponse.setHeader("Content-Disposition", "inline;filename=user.pdf");
            final OutputStream outputStream = httpServletResponse.getOutputStream();
            JasperPrint jasperPrint = userService.settingUpJasper("/report/userListReport.jrxml");
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }


    //    @Bean @Qualifier("htmlview2")
//    public JasperReportsHtmlView pdfView(){
//        JasperReportsHtmlView v = new JasperReportsHtmlView();
//        v.setUrl("classpath:report/userListReport.jasper");
//        v.setReportDataKey("datasource");
//        return v;
//    }
//    @Autowired @Qualifier("htmlview2")
//    JasperReportsHtmlView helloReport;
    @RequestMapping(value = "/htmlview")
    public ModelAndView getRpt2(ModelAndView modelAndView) {
        Map<String, Object> parameterMap = new HashedMap();
        List<UserEntity> userEntities = userService.findAllUser();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(userEntities);
        parameterMap.put("datasource", jrDataSource);
        parameterMap.put("format", "html");
        modelAndView = new ModelAndView("/report/o", parameterMap);
        return modelAndView;
    }

    @RequestMapping(value = "/user/list.html", method = RequestMethod.GET)
    public
    @ResponseBody
    void userListHtml(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType("text/html");
            httpServletResponse.setHeader("Content-Disposition", "inline,filename=user.html");
//            final OutputStream outputStream=httpServletResponse.getOutputStream();
            JasperPrint jasperPrint = userService.settingUpJasper("/report/userListReport.jrxml");
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "o");
            JasperReportsHtmlView jasperReportsHtmlView = new JasperReportsHtmlView();
        }/*catch (IOException e) {
            e.printStackTrace();
        }*/ catch (JRException e) {
            e.printStackTrace();
        }
//        return "redirect:/";
    }

    @RequestMapping("/ok")
    public String u() {
        return "/report/userList";
    }

    @RequestMapping(value = "/pagedidex")
    public String pagedIndex(HttpServletRequest httpServletRequest, Model model) {
        List<UserEntity> userList = userService.findAllUser();
//        PagedListHolder pagedListHolder=new PagedListHolder(userList);
//        int page= ServletRequestUtils.getIntParameter(httpServletRequest,"p",0);
//        pagedListHolder.setPage(page);
//        pagedListHolder.setPageSize(4);
//        model.addAttribute("userList", userList);
//        model.addAttribute("pagedListHolder", pagedListHolder);
        return "users/index";
    }
}
