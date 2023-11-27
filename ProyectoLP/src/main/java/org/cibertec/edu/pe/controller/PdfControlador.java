package org.cibertec.edu.pe.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cibertec.edu.pe.interfaceService.IHistorialVentaService;
import org.cibertec.edu.pe.interfaceService.IVentaService;
import org.cibertec.edu.pe.modelo.HistorialVenta;
import org.cibertec.edu.pe.modelo.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class PdfControlador {

	@Autowired
	private IHistorialVentaService service;

	@Autowired
	private IVentaService ventaService;
	
	//HISTORIAL VENTAS
    @GetMapping("/generate-pdf")
    public void historialVentaPDF(HttpServletResponse response) throws JRException, IOException {
        List<HistorialVenta> lista = service.listarHis();

        // Load file and compile it
        File file = ResourceUtils.getFile("classpath:historial.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Set the response content type
        response.setContentType("application/pdf");

        // Specify the file name that will be used for the response
        response.setHeader("Content-Disposition", "inline; filename=HistorialVentas.pdf");

        // Write the PDF to the response output stream
        OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        outputStream.close();
    }
    
    //VENTAS:
    @GetMapping("/venta-pdf")
    public void ventaPDF(HttpServletResponse response, HttpSession session) throws JRException, IOException {
    	Integer usuario = (Integer) session.getAttribute("usuarioId");
        List<Venta> lista = ventaService.listar(usuario);

        // Load file and compile it
        File file = ResourceUtils.getFile("classpath:venta.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Set the response content type
        response.setContentType("application/pdf");

        // Specify the file name that will be used for the response
        response.setHeader("Content-Disposition", "inline; filename=venta.pdf");

        // Write the PDF to the response output stream
        OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        outputStream.close();
    }
	
}
