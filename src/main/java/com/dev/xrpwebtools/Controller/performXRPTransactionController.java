package com.dev.xrpwebtools.Controller;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.xrpwebtools.Model.XRPConn;

public class performXRPTransactionController extends HttpServlet{
    //////////////////////
    //Utils
    private final Logger logger = Logger.getLogger(dashboardController.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        XRPConn cwallet = (XRPConn)session.getAttribute("dashboard");
        System.out.println(req.getParameter("transferaddress"));
        try {
            String transferaddress = req.getParameter("transferaddress");
            String transferamount = req.getParameter("transferamount");
            cwallet.setDestination(transferaddress);
            cwallet.setXrpamount(transferamount);
            cwallet.sendXRP();

            resp.sendRedirect("index-temp2.jsp");
        } catch (Exception e) {
            cwallet.setErrorString(e.getMessage());
            resp.sendRedirect("error.jsp");
        }
    }
}
