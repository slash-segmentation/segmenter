/*
 * COPYRIGHT AND LICENSE
 * 
 * Copyright 2015 The Regents of the University of California All Rights Reserved
 * 
 * Permission to copy, modify and distribute any part of this realtime-segmentation for 
 * educational, research and non-profit purposes, without fee, and without a 
 * written agreement is hereby granted, provided that the above copyright notice, 
 * this paragraph and the following three paragraphs appear in all copies.
 * 
 * Those desiring to incorporate this realtime-segmentation into commercial products
 * or use for commercial purposes should contact the Technology Transfer Office, 
 * University of California, San Diego, 9500 Gilman Drive, Mail Code 0910, 
 * La Jolla, CA 92093-0910, Ph: (858) 534-5815, FAX: (858) 534-7345, 
 * E-MAIL:invent@ucsd.edu.
 * 
 * IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR 
 * DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING 
 * LOST PROFITS, ARISING OUT OF THE USE OF THIS realtime-segmentation, EVEN IF THE UNIVERSITY 
 * OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * THE realtime-segmentation PROVIDED HEREIN IS ON AN "AS IS" BASIS, AND THE UNIVERSITY 
 * OF CALIFORNIA HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, 
 * ENHANCEMENTS, OR MODIFICATIONS. THE UNIVERSITY OF CALIFORNIA MAKES NO 
 * REPRESENTATIONS AND EXTENDS NO WARRANTIES OF ANY KIND, EITHER IMPLIED OR 
 * EXPRESS, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE, OR THAT THE USE OF 
 * THE realtime-segmentation WILL NOT INFRINGE ANY PATENT, TRADEMARK OR OTHER RIGHTS. 
 */

package edu.ucsd.crbs.realtimeseg.handler.ccdb;

import edu.ucsd.crbs.realtimeseg.handler.ImageProcessorHandler;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;

/**
 *
 * @author Christopher Churas <churas@ncmir.ucsd.edu>
 */
public class CcdbAddChmTrainedModelHandler extends AbstractHandler {

    private static final Logger _log = Logger.getLogger(CcdbAddChmTrainedModelHandler.class.getName());
    
    public static final String ID_KEY = "id";
    public static final String COLOR_KEY = "color";
    
    
    private String _restURL;
    private List<ContextHandler> _contextHandlerList;
    
    
    public CcdbAddChmTrainedModelHandler(final String url){
        _restURL = url;
    }
    
    public void setProcessingContextHandlers(List<ContextHandler> contextHandlerList){
        _contextHandlerList = contextHandlerList;
    }
    
    @Override
    public void handle(String string, Request request, 
            HttpServletRequest servletRequest, 
            HttpServletResponse servletResponse) throws IOException, ServletException {
        
         _log.log(Level.INFO, servletRequest.getRequestURI());
        _log.log(Level.INFO,servletRequest.getQueryString());
        
        //need to parse out id and color 
        Map<String,String[]> queryParameters = servletRequest.getParameterMap();
        String id = parseIdFromMap(queryParameters);
        String color = parseColorFromMap(queryParameters);
        
        servletResponse.setContentType("application/json");
        servletResponse.setCharacterEncoding("UTF-8");
        
        if (id == null){
            String resp = "{ \"error\": \"No id passed in\"}";
            servletResponse.getWriter().write(resp);
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setHandled(true);
            return;
        }
        if (color == null){
            String resp = "{ \"error\": \"No color set\"}";
            servletResponse.getWriter().write(resp);
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setHandled(true);
            return;
        }
        
        //find an available handler 
        ContextHandler cHandler = getAvailableContextWithCHMHandler();
        if (cHandler == null){
            String resp = "{ \"error\": \"Unable to find handler\"}";
            servletResponse.getWriter().write(resp);
            servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setHandled(true);
            return;
        }
        
        //download the model
        
        //Update the handler with a new image processor
        
        //generate json response so client can add the layer
        String resp = "{ \"layerPath\": \"foo\","
                    + "\"minZoom\": 0,"
                    + "\"maxZoom\": 0,"
                    + "\"maxNativeZoom\": 0,"
                    + "\"tileSize\": 128,"
                    + "\"opacity\": 0.3}";
            
        servletResponse.getWriter().write(resp);
        servletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);
    }
    
    private String parseIdFromMap(Map<String,String[]> theMap){
        return parseFirstElementFromMap(theMap,ID_KEY);
    }
    
    private String parseColorFromMap(Map<String,String[]> theMap){
        return parseFirstElementFromMap(theMap,COLOR_KEY);
    }
    
    private String parseFirstElementFromMap(Map<String,String[]> theMap,final String key){
        if (theMap == null || theMap.isEmpty()){
            return null;
        }
        
        if (theMap.containsKey(key)){
            String[] vals = theMap.get(key);
            if (vals == null || vals.length == 0){
                return null;
            }
            return vals[0];
        }
        return null;
    }
    
    private ContextHandler getAvailableContextWithCHMHandler(){
        
        if (_contextHandlerList == null || _contextHandlerList.isEmpty()){
            return null;
        }
        
       return null; 
    }

}
