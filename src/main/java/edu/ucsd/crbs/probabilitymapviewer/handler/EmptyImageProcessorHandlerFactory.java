/*
 * COPYRIGHT AND LICENSE
 * 
 * Copyright 2015 The Regents of the University of California All Rights Reserved
 * 
 * Permission to copy, modify and distribute any part of this probabilitymapviewer for 
 * educational, research and non-profit purposes, without fee, and without a 
 * written agreement is hereby granted, provided that the above copyright notice, 
 * this paragraph and the following three paragraphs appear in all copies.
 * 
 * Those desiring to incorporate this probabilitymapviewer into commercial products
 * or use for commercial purposes should contact the Technology Transfer Office, 
 * University of California, San Diego, 9500 Gilman Drive, Mail Code 0910, 
 * La Jolla, CA 92093-0910, Ph: (858) 534-5815, FAX: (858) 534-7345, 
 * E-MAIL:invent@ucsd.edu.
 * 
 * IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR 
 * DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING 
 * LOST PROFITS, ARISING OUT OF THE USE OF THIS segmenter, EVEN IF THE UNIVERSITY 
 * OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * THE probabilitymapviewer PROVIDED HEREIN IS ON AN "AS IS" BASIS, AND THE UNIVERSITY 
 * OF CALIFORNIA HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, 
 * ENHANCEMENTS, OR MODIFICATIONS. THE UNIVERSITY OF CALIFORNIA MAKES NO 
 * REPRESENTATIONS AND EXTENDS NO WARRANTIES OF ANY KIND, EITHER IMPLIED OR 
 * EXPRESS, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE, OR THAT THE USE OF 
 * THE probabilitymapviewer WILL NOT INFRINGE ANY PATENT, TRADEMARK OR OTHER RIGHTS. 
 */

package edu.ucsd.crbs.probabilitymapviewer.handler;

import edu.ucsd.crbs.probabilitymapviewer.App;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jetty.server.handler.ContextHandler;

/**
 * Instances of this factory create 10 {@link ContextHandler}s that don't do
 * anything, but can be used later
 * 
 * @author Christopher Churas <churas@ncmir.ucsd.edu>
 */
public class EmptyImageProcessorHandlerFactory {
    
    public List<ImageProcessorHandler> getImageProcessorHandlers(){
        //add 10 more handlers that don't do anything just yet
        ArrayList<ImageProcessorHandler> iHandlers = new ArrayList<ImageProcessorHandler>();
        for (int i  = 1 ; i <= 10; i++){
            ContextHandler chmContext = new ContextHandler("/"+App.LAYER_HANDLER_BASE_DIR+"/"+i);
            ImageProcessorHandler iHandler = new ImageProcessorHandler(null);
            chmContext.setHandler(iHandler);
            iHandler.setContextHandler(chmContext);
            iHandlers.add(iHandler);
        }
        return iHandlers;
    }

}
