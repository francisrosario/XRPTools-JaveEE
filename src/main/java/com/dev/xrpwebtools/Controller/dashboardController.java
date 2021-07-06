package com.dev.xrpwebtools.Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Utilities;

import com.dev.xrpwebtools.Model.Utility;
import com.dev.xrpwebtools.Model.xrp4j;
import org.xrpl.xrpl4j.client.JsonRpcClientErrorException;


public class dashboardController extends HttpServlet {
    //////////////////////
    //Utils
    private final Logger logger = Logger.getLogger(dashboardController.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Utility.mainUtilities utlt = new Utility.mainUtilities();
        xrp4j bll = new xrp4j();
        session.setAttribute("dashboard", bll);
        try {
            String walletseed = req.getParameter("walletseed");
            walletseed = utlt.removeWhiteSpace(walletseed);
            bll.setWalletseed(walletseed);
            if(!bll.isValidated()){
                bll.setErrorString("Account not found, Kindly activate your account...");
                resp.sendRedirect("view/error.jsp");
            }
            resp.sendRedirect("view/dashboard.jsp");
        } catch (Exception err) {
            logger.log(Level.INFO, err.getMessage());
            bll.setErrorString("Error Code: 00-"+err.getMessage());
            resp.sendRedirect("view/error.jsp");
        }
    }
}
