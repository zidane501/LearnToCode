/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn_python_gui;

/**
 *
 * @author lau
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author lau
 */
public class LPG_DebugCodePanel extends JPanel{
    
    String initCode = new String("print \"this is new\"\n" +
"a = [\"G\",\"hello\", \"polly\", \"norwegian blue\"]\n" +
"for i in a:\n" +
"     print i\n" +
"def bicycle_repairman(a):\n" +
"    a = [[\"cheeseshop\", \"spam\", \"viking\"], \"idle\"]\n" +
"    secret_dentist = \"cheeseshop\"\n" +
"    return secret_dentist \n" +
"c = []\n" +
"argument_clinic = bicycle_repairman(c)\n" +
"spanish = \"inquisition\"");
    
    
    public  JTextArea codeTA = new JTextArea(initCode);
    public  JTextArea resultTA = new JTextArea("");
    
    private JScrollPane codeSC = new JScrollPane(codeTA);
    private JScrollPane resultSC = new JScrollPane(resultTA);
    
    private TextLineNumber textLineNumbers = new TextLineNumber(codeTA);
    
    private int margin;
    private double weightX1;
    
    public LPG_DebugButtonPanel debugButtonPanel = new  LPG_DebugButtonPanel();
    
    private Highlighter.HighlightPainter painter= new DefaultHighlighter.DefaultHighlightPainter(Color.PINK);
    
    private LPG_DebugVarsTable varsTable = new LPG_DebugVarsTable();
    
    public LPG_DebugCodePanel(){
        
        setLayout(new GridBagLayout());
        GridBagConstraints debugCode_gc = new GridBagConstraints();
        
        margin = 4;
        weightX1 = 0.4;
        ////////////////////  Code Area/////////////////////

        debugCode_gc.weightx = weightX1;
        debugCode_gc.weighty = 0.8;
        
        debugCode_gc.gridx = 0; 
        debugCode_gc.gridy = 0;

        debugCode_gc.fill = GridBagConstraints.BOTH;
        
        codeTA.setEditable(true);
        codeTA.setMargin(new Insets(margin,margin,margin,margin));
        
        codeSC.setBorder(BorderFactory.createLineBorder(Color.blue));
        codeSC.setRowHeaderView( textLineNumbers );
        
        int padding = 5;
        debugCode_gc.insets = new Insets(padding,padding,padding,padding);
        
        add(codeSC, debugCode_gc);
        
        /////// JTable ///////
        debugCode_gc.weightx = 0.4;
        debugCode_gc.weighty = 0.8;
        
        debugCode_gc.gridx = 1; 
        debugCode_gc.gridy = 0;

        debugCode_gc.fill = GridBagConstraints.BOTH;
        
        add(varsTable, debugCode_gc);
        
        //////////////////// Buttons Area/////////////////////
        
        debugCode_gc.weightx = weightX1;
        debugCode_gc.weighty = 0.1;
        
        debugCode_gc.gridx = 0; 
        debugCode_gc.gridy = 1;

        debugCode_gc.fill = GridBagConstraints.BOTH;
        
        debugButtonPanel.setCodePanel(this);
        
        add(debugButtonPanel, debugCode_gc);
        
        //////////////////// Result Area/////////////////////
        
        debugCode_gc.weightx = weightX1;
        debugCode_gc.weighty = 0.2;
        
        debugCode_gc.gridx = 0; 
        debugCode_gc.gridy = 2;

        debugCode_gc.fill = GridBagConstraints.BOTH;
        
        debugCode_gc.insets = new Insets(padding,padding,padding,padding);
        
        resultTA.setEditable(false);
        resultTA.setMargin(new Insets(margin,margin,margin,margin));
        
        resultSC.setBorder(BorderFactory.createLineBorder(Color.blue));
        
        add(resultSC, debugCode_gc);
    }
    public void setHighlightLineText(List<Integer> startEndPos) throws BadLocationException{
        codeTA.getHighlighter().removeAllHighlights();
        codeTA.getHighlighter().addHighlight(startEndPos.get(0), startEndPos.get(1), painter);
    }
    
    public String getDbText(){
        //System.out.println("codeTA: " + codeTA.getText());
        return codeTA.getText();
    }
    void setDbText(String result){
        //System.out.println("resultTA: " + result);
        resultTA.setText(result);
    }
    void removeDbResLine(String dbResline){
        /*String resText = resultTA.getText();
        String[] resTextList = resText.split("\n");
        //int rTListLength = resTextList.length;
        //int rest = resText.length();
        String newText = "";
        int j = 0;
        for(String s : resTextList){
            j++;
            if(j==resTextList.length){continue;}
            newText = newText + s + "\n";
        }
        //if (rTListLength != 0){
        //resultTA.setText(resText.substring(0,resText.length()-resTextList[rTListLength-1].length()));
        //}*/
        resultTA.setText(dbResline);
    }
    void appendDbResLine(String dbResLine){
        //if(dbResLine.length() != 1){
            resultTA.setText(dbResLine );
            //System.out.println("dbResLine.length: " + dbResLine.length());
            //System.out.println("***|" +"\\"+dbResLine + "|***");
        //}
    }
    void clearTA(){
        resultTA.setText("");
    }
    void setTableData(ArrayList<ArrayList<String>> varsList){
        varsTable.setData(varsList); // Adds debugdata to the tableModel
        //System.out.println("*** Now hittin setTableData-method. VarsList.size(): " + varsList.size());
        
        varsTable.refresh(); //Updates the table
    }
}


    

