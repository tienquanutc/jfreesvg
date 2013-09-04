/* ===================================================
 * JFreeSVG : an SVG library for the Java(tm) platform
 * ===================================================
 * 
 * (C)opyright 2013, by Object Refinery Limited.  All rights reserved.
 *
 * Project Info:  http://www.jfree.org/jfreesvg/index.html
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 * 
 */

package org.jfree.graphics2d.canvas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility methods related to the {@link CanvasGraphics2D} implementation.
 */
public class CanvasUtils {
    
    private CanvasUtils() {
        // no need to instantiate
    }
    
    /**
     * Writes HTML output containing a script (usually generated by 
     * {@link CanvasGraphics2D}) to the specified file.  This method is used 
     * in the demo applications.
     * 
     * @param f  the file.
     * @param title  the title for the HTML.
     * @param canvasID  the canvas ID.
     * @param width  the canvas width.
     * @param height  the canvas height.
     * @param canvasScript  the canvas script.
     * 
     * @throws IOException 
     */
    public static void writeToHTML(File f, String title, String canvasID,
            int width, int height, String canvasScript) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(f));
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<head>\n");
            writer.write("<title>" +title + "</title>\n");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"); 
            writer.write("<script>\n");
            writer.write("window.onload = function() {\n");
            writer.write("var canvas = document.getElementById(\"" + canvasID 
                    + "\");\n");
            writer.write("var ctx = canvas.getContext(\"2d\");\n");
            writer.write("if (!ctx.setLineDash) {\n");
            writer.write("ctx.setLineDash = function() {};\n");
            writer.write("}\n");
            writer.write(canvasScript + "\n");
            writer.write("}\n");
            writer.write("</script>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");

            writer.write("<canvas id=\"" + canvasID + "\" width=\"" + width 
                    + "\" height=\"" + height + "\"></canvas>");
            writer.write("</body>\n");
            writer.write("</html>\n");
            writer.flush();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(CanvasUtils.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        } 
    }
    
}
