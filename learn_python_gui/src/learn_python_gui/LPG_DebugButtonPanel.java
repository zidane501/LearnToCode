/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn_python_gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 *
 * @author lau
 */
public class LPG_DebugButtonPanel extends JPanel{
    
    private LPG_DebugCodePanel debugCodePanel;
    
    private final JButton plusButton = new JButton("Frem");
    private final JButton minusButton = new JButton("Tilbage");
    private final JButton runButton = new JButton("Kør");
    private final JButton debugButton = new JButton("DeBug");
    
    private JList hello_debugList;
    
    private String str, message, dbLineOutput;
    private String[] code_result, variablesList;
    
    private static int debugLine;
    int margin;
    int lineNr = 0;
    private static boolean dontPrint = false; //Avoiding printing the last debugger line repeatedly
    List<Integer> colorPos;
    
    PrintWriter writer;
    InputStreamReader ISR;
    BufferedReader in;
    
    private LPG_DebugTableModel debugTbM;
    
    public LPG_DebugButtonPanel(){
        setLayout(new GridBagLayout());
        
        GridBagConstraints buttonsPanel_gc = new GridBagConstraints();
        
        ////////////////////// Run Button ///////////////////////
        
        buttonsPanel_gc.weightx = 0.8;
        buttonsPanel_gc.weighty = 1;
        
        buttonsPanel_gc.gridx = 0; 
        buttonsPanel_gc.gridy = 0;

        buttonsPanel_gc.fill = GridBagConstraints.CENTER;
        
        runButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                runHello_ButtonActionPerformed(evt);
            }
        
        });
        
        add(runButton, buttonsPanel_gc);
        
        ////////////////////// Debug Button ///////////////////////
        
        buttonsPanel_gc.weightx = 0.1;
        buttonsPanel_gc.weighty = 1;
        
        buttonsPanel_gc.gridx = 1; 
        buttonsPanel_gc.gridy = 0;

        buttonsPanel_gc.fill = GridBagConstraints.CENTER;
        
        debugButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent debugEV) {
                debugButtonActionPerformed(debugEV);
            }
        });
        
        add(debugButton, buttonsPanel_gc);
        
        ////////////////////// Minus Button ///////////////////////
        
        buttonsPanel_gc.weightx = 0.1;
        buttonsPanel_gc.weighty = 1;
        
        buttonsPanel_gc.gridx = 2; 
        buttonsPanel_gc.gridy = 0;

        buttonsPanel_gc.fill = GridBagConstraints.CENTER;
        
        minusButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent minusEv) {
                try {
                    minusButtonActionPerformed(minusEv);
                } catch (BadLocationException ex) {
                    Logger.getLogger(LPG_DebugButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        add(minusButton, buttonsPanel_gc);        
        
         ////////////////////// Plus Button ///////////////////////
        
        buttonsPanel_gc.weightx = 0.1;
        buttonsPanel_gc.weighty = 1;
        
        buttonsPanel_gc.gridx = 3; 
        buttonsPanel_gc.gridy = 0;

        buttonsPanel_gc.fill = GridBagConstraints.CENTER;
        
        plusButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent plusEV) {
                try {
                    plusButtonActionPerformed(plusEV);
                } catch (BadLocationException ex) {
                    Logger.getLogger(LPG_DebugButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        add(plusButton, buttonsPanel_gc);
        
        
        ///////////////////// END /////////////////////
        
    }
    
    public void setCodePanel(LPG_DebugCodePanel debugCodePanel){
        this.debugCodePanel = debugCodePanel;
    }
    
    public String[] connect() throws Exception, IOException{
        String local_machine_add = "127.0.0.1";
        String host = "10.54.54.254";
        Socket sock = new Socket(local_machine_add, 6542);
        writer = new PrintWriter(sock.getOutputStream(), true);
        writer.println(debugCodePanel.getDbText());
        ISR = new InputStreamReader(sock.getInputStream());
        in = new BufferedReader(ISR); 

        System.out.println("Client: Networking established");

        StringBuilder s = new StringBuilder();
        while((str = in.readLine()) != null){
                s.append(str + "\n");   
        }
        message = s.toString();
        System.out.println(message.split("!!!", 0));
        return message.split("!!!",0);
    }
    
    private void runHello_ButtonActionPerformed(ActionEvent evt) {                                                
        debugLine = 0;
        try {	
                    code_result = connect();
            } 
        catch (Exception e1) {e1.printStackTrace();}
        debugCodePanel.setDbText(code_result[0]);
        
        for(int i = 1; i<code_result.length; i++){
            System.out.println(code_result[i]);
        }
        
    }
     private void debugButtonActionPerformed(ActionEvent debugEV){
         debugCodePanel.clearTA();
     }
    private void minusButtonActionPerformed(ActionEvent minusEv) throws BadLocationException {
        
        debugLine--; if(debugLine<1){debugLine=1;}; 
        // debugLine's minimum is 1 because first index in code_result is the result and not the variable info
        String codeLineInfo = code_result[debugLine];
        
        lineNr = Integer.parseInt( codeLineInfo.split(">>>")[0]);
        
        variablesList = codeLineInfo.split(">>>")[1].split("---");
        
        ArrayList<ArrayList<String>> totalVars = createVarArrayList(variablesList);
        
        debugCodePanel.setTableData(totalVars);
        
        
        colorPos = calcPosOfLine(debugCodePanel.getDbText(), lineNr);
        debugCodePanel.setHighlightLineText(colorPos);
        
        debugCodePanel.removeDbResLine(calcDebugPMOutput(debugLine));
    }
    private void plusButtonActionPerformed(ActionEvent plusEv) throws BadLocationException {
        
        debugLine++; if(debugLine >= code_result.length){ debugLine = code_result.length-1; }
        
        lineNr = Integer.parseInt( code_result[debugLine].split(">>>")[0]);
        
        colorPos = calcPosOfLine(debugCodePanel.getDbText(), lineNr);
        debugCodePanel.setHighlightLineText(colorPos); 
        
        String codeLineInfo = code_result[debugLine];
        
        //Adding variable info to debugTable
        variablesList = codeLineInfo.split(">>>")[1].split("---");
        ArrayList<ArrayList<String>> totalVars = createVarArrayList(variablesList);
        debugCodePanel.setTableData(totalVars);
        
        debugCodePanel.appendDbResLine(calcDebugPMOutput(debugLine));
    }
    
    // Calculating position for words-highlighter in debugger
    public static List<Integer> calcPosOfLine(String codeToDebug, int lineNumber){
        
        String[] codeLines = codeToDebug.split("\n");
        int lineStart = 0;
        
        for( int i = 0 ; i < lineNumber-1; i++){
            lineStart += codeLines[i].length()+1;    
        }
        
        int lineEnd = lineStart + codeLines[lineNumber-1].length();
        
        List<Integer> startEnd;
        startEnd = new ArrayList<>();
        startEnd.add(lineStart);
        startEnd.add(lineEnd);
        
        return startEnd;
    };
    
    // Creating ArrayList for variables table
    private ArrayList<ArrayList<String>> createVarArrayList(String varsList[]){
        ArrayList<ArrayList<String>> totalVars = new ArrayList<ArrayList<String>>();
        
        int c = 0; // For avoiding last index of varsList (==:::printOut)
        for (String t : varsList) {
            //System.out.println(t.split("printOut:::")[0]);
            c++;
            if(c==variablesList.length){continue;}
            ArrayList<String> varInfo = new ArrayList<String>();
            for (String varInf : t.split("printOut:::")[0].split(":::")) {
                //if(t.split("printOut:::")[0].split(":::").length != 3){continue;}
                varInfo.add(varInf);
                //System.out.println("Adding to arraylist: " + varInf);
            }
            totalVars.add(varInfo);
        }
        return totalVars;
        
    };
    
    public String calcDebugPMOutput(int lineNum){
        
        String outPut = "";
        int j=1;// Because first index in code_result is the result and not the variable info

        while(j <= lineNum){
            j++;
            String res = code_result[j-1].split("printOut:::")[1];
            System.out.println(res);
            System.out.println("Length: " + res.length());
            if(res.length() <= 1){continue;}
            
            outPut = outPut + res;
            
        }
        System.out.println("outPut::" + outPut + "|| lineNum: " + lineNum);
        return outPut;
    };
    
}
