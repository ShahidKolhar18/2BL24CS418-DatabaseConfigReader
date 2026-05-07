package com.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DatabaseConfigReaderServlet extends HttpServlet {
    private String dbHost;
    private String dbPort;
    private String dbName;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        // Retrieve database configuration values from web.xml init-parameters
        this.dbHost = config.getInitParameter("dbHost");
        this.dbPort = config.getInitParameter("dbPort");
        this.dbName = config.getInitParameter("dbName");

        // Basic validation: Check if parameters are missing in web.xml
        if (dbHost == null) dbHost = "Not Configured";
        if (dbPort == null) dbPort = "Not Configured";
        if (dbName == null) dbName = "Not Configured";
    }

    /**
     * Handles the HTTP GET request to display the database configuration.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String jdbcUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        String servletName = getServletName();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Database Config Reader</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f9; }");
        out.println(".panel { border: 1px solid #ccc; padding: 20px; border-radius: 8px; background-color: #fff; box-shadow: 0 2px 4px rgba(0,0,0,0.1); max-width: 500px; }");
        out.println("h2 { color: #333; border-bottom: 2px solid #0056b3; padding-bottom: 10px; }");
        out.println(".info-item { margin-bottom: 10px; }");
        out.println(".label { font-weight: bold; color: #555; }");
        out.println(".value { color: #000; }");
        out.println(".jdbc-url { font-family: monospace; background-color: #eee; padding: 5px; border-radius: 4px; display: inline-block; margin-top: 5px; }");
        out.println(".footer { margin-top: 20px; font-size: 0.9em; color: #777; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='panel'>");
        out.println("<h2>Connection Info</h2>");
        
        out.println("<div class='info-item'>");
        out.println("<span class='label'>Database Host:</span> <span class='value'>" + dbHost + "</span>");
        out.println("</div>");
        
        out.println("<div class='info-item'>");
        out.println("<span class='label'>Database Port:</span> <span class='value'>" + dbPort + "</span>");
        out.println("</div>");
        
        out.println("<div class='info-item'>");
        out.println("<span class='label'>Database Name:</span> <span class='value'>" + dbName + "</span>");
        out.println("</div>");
        
        out.println("<div class='info-item'>");
        out.println("<span class='label'>Simulated JDBC URL:</span><br/>");
        out.println("<span class='jdbc-url'>" + jdbcUrl + "</span>");
        out.println("</div>");

        out.println("<div class='footer'>");
        out.println("Servlet Name: " + servletName);
        out.println("</div>");

        out.println("</div>"); // end panel
        
        out.println("</body>");
        out.println("</html>");
    }
}
