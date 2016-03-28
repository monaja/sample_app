package com.brokersystems.server.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleDocxExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class ReportUtils {
	
	  public static JasperReport getCompiledFile(String fileName, HttpServletRequest request) throws JRException {
	   File reportFile = new File( request.getSession().getServletContext().getRealPath("/reports/" + fileName + ".jasper"));
	     if (!reportFile.exists()) {
	    	 JasperCompileManager.compileReportToFile(request.getSession().getServletContext().getRealPath("/reports/" + fileName + ".jrxml"),request.getSession().getServletContext().getRealPath("/reports/" + fileName + ".jasper"));
		        
	      }
	       JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	       return jasperReport;
	   }
  
	  public static void generateReportXls (HttpServletResponse resp, Map parameters, JasperReport jasperReport, Connection conn,String reportFileName)throws JRException, NamingException, SQLException, IOException {
		  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap(),conn); 
		  ServletOutputStream ouputStream = resp.getOutputStream();
		  JRXlsExporter exporter = new JRXlsExporter();
		  resp.setHeader("Content-Disposition",
	              "attachment; filename="+reportFileName+".xls");
		  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		  exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ouputStream));
		  SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		  configuration.setOnePagePerSheet(true);
		  configuration.setDetectCellType(true);
		  configuration.setCollapseRowSpan(false);
		  exporter.setConfiguration(configuration);
		  exporter.exportReport();   
	  } 
	  
	  
	  public static void generateReportPDF  (HttpServletResponse resp, Map parameters, JasperReport jasperReport, Connection conn,String reportFileName)throws JRException, NamingException, SQLException, IOException {
		  JRPdfExporter exporter = new JRPdfExporter();
		  ServletOutputStream ouputStream = resp.getOutputStream();
		  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap(),conn); 
		  resp.setHeader("Content-Disposition",
	              "attachment; filename="+reportFileName+".pdf");
		  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		  exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ouputStream));
		  SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
	      exporter.setConfiguration(configuration);
	      exporter.exportReport();
	  } 
	  
	  public static void generateReportWord  (HttpServletResponse resp, Map parameters, JasperReport jasperReport, Connection conn,String reportFileName)throws JRException, NamingException, SQLException, IOException {
		  JRDocxExporter exporter = new JRDocxExporter();
		  ServletOutputStream ouputStream = resp.getOutputStream();
		  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap(),conn); 
		  resp.setHeader("Content-Disposition",
	              "attachment; filename="+reportFileName+".doc");
		  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		  exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ouputStream));
		  SimpleDocxExporterConfiguration configuration = new SimpleDocxExporterConfiguration();
	      exporter.setConfiguration(configuration);
	      exporter.exportReport();
	  } 
  

}
