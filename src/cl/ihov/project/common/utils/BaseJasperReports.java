package cl.ihov.project.common.utils;

import java.net.URL;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class BaseJasperReports {

    public static void createReport(String name, HashMap hm) throws JRException {
        JasperReport report;
        JasperPrint reportFilled;
        JasperViewer viewer;
        ReportConnection reportConn = null;
        try {
            reportConn = new ReportConnection();
            reportConn.doConnection();
            URL jasper = URL.class.getResource("/cl/ihov/project/reports/compiled/" + name + ".jasper");
            report = (JasperReport) JRLoader.loadObject(jasper);
            reportFilled = JasperFillManager.fillReport(report, hm, reportConn.getConection());
            viewer = new JasperViewer(reportFilled,false);     
            viewer.setTitle(name.toUpperCase());
            viewer.setSize(1200, 1000);
            viewer.setVisible(true);  
        } finally {
            if (reportConn != null) {
                reportConn.doDisconnection();
                reportConn = null;
            }
        }
    }
}
