/*
 * COPYRIGHT AND LICENSE
 * 
 * Copyright 2014 The Regents of the University of California All Rights Reserved
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

package edu.ucsd.crbs.probabilitymapviewer.io;

import static edu.ucsd.crbs.probabilitymapviewer.App.DIR_ARG;
import static edu.ucsd.crbs.probabilitymapviewer.App.TEMP_DIR_CREATED_FLAG;
import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates Working directory
 * @author Christopher Churas <churas@ncmir.ucsd.edu>
 */
public class WorkingDirCreatorImpl implements WorkingDirCreator{

        private static final Logger _log = Logger.getLogger(WorkingDirCreatorImpl.class.getName());

    
    /**
     * Examines <b>props</b> looking for {@value #DIR_ARG} property, if found
     * method checks if directory exists, if it does not the code attempts to
     * create it and if successful the <b>props</b> has a new property 
     * {@value #TEMP_DIR_CREATED_FLAG} is added and set to <b>true</b>
     * @param props
     * @return Working Directory even if method did not create it
     * @throws Exception If <b>props</b> is null or {@value #DIR_ARG} is not set
     *  or if there is a problem creating the directory
     */
    @Override
    public File createWorkingDir(Properties props) throws Exception {
        if (props == null){
            throw new NullPointerException("Properties is null");
        }
        String dirArg = props.getProperty(DIR_ARG);
        if (dirArg == null || dirArg.trim().equals("")){
            throw new Exception(DIR_ARG+" property not set");
        }
        
        File workingDir = new File(dirArg);

        if (workingDir.exists() == false) {
            
            _log.log(Level.INFO,"--"+DIR_ARG+" " + workingDir.getAbsolutePath() 
                    + " does not exist.  Creating directory");
            if (workingDir.mkdirs() == false) {
                throw new Exception("Unable to create " 
                        + workingDir.getAbsolutePath());
            }
            props.setProperty(TEMP_DIR_CREATED_FLAG, "true");
        }
        return workingDir;
    }

}
