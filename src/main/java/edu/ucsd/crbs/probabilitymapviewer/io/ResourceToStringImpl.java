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

import java.util.List;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Christopher Churas <churas@ncmir.ucsd.edu>
 */
public class ResourceToStringImpl implements ResourceToString {

    @Override
    public String getResourceAsString(String resourcePath, StringReplacer replacer) throws Exception {
          if (resourcePath == null){
            throw new IllegalArgumentException("resourcePath method parameter cannot be null");
        }
        
        //load script
        List<String> scriptLines = IOUtils.readLines(Class.class.getResourceAsStream(resourcePath));
        
        StringBuilder sb = new StringBuilder();
                        
        for (String line : scriptLines){
            if (replacer != null){
                sb.append(replacer.replace(line));
            }
            else {
                sb.append(line);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
