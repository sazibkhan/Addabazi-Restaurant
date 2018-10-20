package com.addabazi.jasper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperUtil {

	public  boolean jasperPrint(List<?> list,Map<String, Object> map,String jasperFilePath, String downLoadFileName) throws Exception{

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(jasperFilePath);
		
        JasperPrint jasperPrint = JasperFillManager.fillReport(is, map, beanCollectionDataSource);
        
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
       
        StringBuilder stringBuilder= new StringBuilder();
        
        stringBuilder.append(downLoadFileName);
       
        httpServletResponse.addHeader("Content-disposition","attachment; filename=" + stringBuilder + ".pdf");
		
        FacesContext.getCurrentInstance().responseComplete();
		
		try (ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
			
			JasperExportManager.exportReportToPdfStream(jasperPrint,servletOutputStream);
			
			System.out.println("All done the report is done");
			
			servletOutputStream.flush();
			
			FacesContext.getCurrentInstance().responseComplete();
			return true;
		}

	}
}
