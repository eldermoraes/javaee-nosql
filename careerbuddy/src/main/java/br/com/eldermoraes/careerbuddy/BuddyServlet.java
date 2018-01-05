package br.com.eldermoraes.careerbuddy;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eldermoraes
 */
@WebServlet(name = "BuddyServlet", urlPatterns = {"/BuddyServlet"})
public class BuddyServlet extends HttpServlet {
    
    @EJB
    private BuddyBean buddyBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try(PrintWriter writer = response.getWriter()){
            writer.print(buddyBean.getBuddies());
        }
    }

}
