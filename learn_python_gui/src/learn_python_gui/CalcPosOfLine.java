/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn_python_gui;

import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author lau
 */
public class CalcPosOfLine {
    
    public static List<Integer> CalcPosOfLine(String codeToDebug, int lineNumber){
        
        String[] codeLines = codeToDebug.split("\n");
        int lineStart = 0;
        
        for( int i = 0 ; i < lineNumber ; i++){
            lineStart += codeLines[i].length();    
        }
        
        int lineEnd = lineStart + codeLines[lineNumber-1].length();
        
        List<Integer> startEnd;
        startEnd = new ArrayList<>();
        startEnd.add(lineStart);
        startEnd.add(lineEnd);
        
        return startEnd;
    };
}
