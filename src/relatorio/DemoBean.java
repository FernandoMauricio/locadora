package relatorio;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.fucapi.entity.Veiculo;
import br.com.fucapi.util.JPAUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ramki
 */
@ManagedBean
@SessionScoped

public class DemoBean {
    
     public void pdf() throws JRException, IOException {
     
    	 EntityManager em = JPAUtil.getEntityManager();
        Query query= em.createQuery("Select p from Veiculo p where situacaoveiculo='Restrito'");
         List<Veiculo> listOfShoppingCart=(List<Veiculo>)query.getResultList();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listOfShoppingCart);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/relatorio.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
}
