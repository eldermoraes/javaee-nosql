/*
 * Copyright 2018 Elder Moreas and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.eldermoraes.careerbuddy;

import br.com.eldermoraes.careerbuddy.Enums.Technology;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eldermoraes
 */
@WebServlet(name = "BuddyServlet", urlPatterns = {"/BuddyServlet"}, loadOnStartup = 1)
public class BuddyServlet extends HttpServlet {

    @EJB
    private BuddyBean buddyBean;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        buddyBean.loadData();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try(PrintWriter writer = response.getWriter()){
            writer.print(Technology.JAVA + " Buddies: " + buddyBean.getBuddiesByTechnology(Technology.JAVA) + "\n");
            writer.print(Technology.CLOUD + " Buddies: " + buddyBean.getBuddiesByTechnology(Technology.CLOUD) + "\n");
        }
    }

}
