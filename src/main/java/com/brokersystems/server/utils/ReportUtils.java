package com.brokersystems.server.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportUtils {
	
	  public static JasperReport getCompiledFile(String fileName, HttpServletRequest request) throws JRException {
	   File reportFile = new File( request.getSession().getServletContext().getRealPath("/reports/" + fileName + ".jasper"));
	     if (reportFile.exists()) {
	         reportFile.delete();      
	      }
	       JasperCompileManager.compileReportToFile(request.getSession().getServletContext().getRealPath("/reports/" + fileName + ".jrxml"),request.getSession().getServletContext().getRealPath("/reports/" + fileName + ".jasper"));
	       JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	       return jasperReport;
	   }
  
	  public static void generateReportPDF (HttpServletResponse resp, Map parameters, JasperReport jasperReport, Connection conn,String reportFileName)throws JRException, NamingException, SQLException, IOException {
	      byte[] bytes = null;
	      bytes = JasperRunManager.runReportToPdf(jasperReport,parameters,conn);
	      resp.reset();
	      resp.resetBuffer();
	      resp.setContentType("application/pdf");
	      resp.setContentLength(bytes.length);
	      resp.setHeader("Content-Disposition",
	              "attachment; filename="+reportFileName+".pdf");
	      ServletOutputStream ouputStream = resp.getOutputStream();
	      ouputStream.write(bytes, 0, bytes.length);
	      ouputStream.flush();
	      ouputStream.close();
	  } 
  

}
