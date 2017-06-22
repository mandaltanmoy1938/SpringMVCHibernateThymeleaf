package com.SpringMVCHibernateThymeleaf.users.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringMVCHibernateThymeleaf.users.model.UserEntity;
import com.SpringMVCHibernateThymeleaf.users.model.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public List<UserEntity> findAllUser(){
		return userRepository.findAll();
	}
	
	public void saveUser(UserEntity user){
		userRepository.save(user);
	}
	
	public UserEntity getUsers(int id){
		return userRepository.getOne(id);
	}
	
	public void deleteUser(int id){
		userRepository.delete(id);
	}

	public JasperPrint settingUpJasper(String reportNameWithPath){
        InputStream jasperStream=this.getClass().getResourceAsStream(reportNameWithPath);
        JasperDesign jasperDesign= null;
        JasperReport jasperReport= null;
        try {
            jasperDesign = JRXmlLoader.load(jasperStream);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Map<String,Object> parameterMap=new HashedMap();
            List<UserEntity> userEntities=findAllUser();
            JRDataSource jrDataSource=new JRBeanCollectionDataSource(userEntities);
            parameterMap.put("datasource",jrDataSource);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameterMap,jrDataSource);
            return jasperPrint;
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }
}
