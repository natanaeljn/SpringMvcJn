package curso.springboot.service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import javassist.bytecode.ByteArray;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtil implements Serializable {


	private static final long serialVersionUID = 1L;
	
	public byte[] gerarRelatorio (List listDados,String relatorio,ServletContext servletContext)  throws Exception{
		JRBeanCollectionDataSource collectionDataSource = new JRBeanCollectionDataSource(listDados);
		String caminho = servletContext.getRealPath("relatorios")+File.separator + relatorio + ".jasper";
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminho, new HashMap() , collectionDataSource);
		return  JasperExportManager.exportReportToPdf(impressoraJasper);
	}

}
