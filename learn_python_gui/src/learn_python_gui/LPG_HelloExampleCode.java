/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn_python_gui;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import static java.lang.System.in;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author lau
 */
public class LPG_HelloExampleCode extends JPanel{
    
    LPG_Connect Connect = new LPG_Connect();
    
    JTextArea leftCodeTA = new JTextArea();
    JTextArea rightCodeTA = new JTextArea();
    
    TextLineNumber textLineNumbers = new TextLineNumber(leftCodeTA);
    
    JScrollPane lC = new JScrollPane(leftCodeTA);
    JScrollPane rC = new JScrollPane(rightCodeTA);
    
    JButton runButton = new JButton("Kør");
    
    private javax.swing.JList hello_debugList;
    
    String str, message;
    String[][] code_result;
    
    int debugLine,margin;
    
    PrintWriter writer;
    InputStreamReader ISR;
    BufferedReader in;
    
    String problem = "";
    
    String splitter = "((4nXQ6cBTFb33))";
    
    
    public LPG_HelloExampleCode(){
        
        setLayout(new GridBagLayout());
        GridBagConstraints exCode_gc = new GridBagConstraints();
        
        margin = 4;
        
        //////////////////// Left Code Area/////////////////////

        exCode_gc.weightx = 0.4;
        exCode_gc.weighty = 1;
        
        exCode_gc.gridx = 0; 
        exCode_gc.gridy = 0;

        exCode_gc.fill = GridBagConstraints.BOTH;
        
        
        leftCodeTA.setEditable(true);
        leftCodeTA.setMargin(new Insets(margin,margin,margin,margin));
        lC.setBorder(BorderFactory.createLineBorder(Color.blue));
        lC.setRowHeaderView( textLineNumbers );
        
        int padding = 5;
        exCode_gc.insets = new Insets(padding,padding,padding,padding);
        
        add(lC, exCode_gc);
        
        ////////////////////// Run Button ///////////////////////
        
        exCode_gc.weightx = 0.2;
        exCode_gc.weighty = 1;
        
        exCode_gc.gridx = 1; 
        exCode_gc.gridy = 0;

        exCode_gc.fill = GridBagConstraints.NONE;
        
        runButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                runHello_ButtonActionPerformed(evt);
            }
        
        });
        
        add(runButton);
        //////////////////// Right Code Area/////////////////////
        
        exCode_gc.weightx = 0.4;
        exCode_gc.weighty = 1;
        
        exCode_gc.gridx = 2; 
        exCode_gc.gridy = 0;

        exCode_gc.fill = GridBagConstraints.BOTH;
        
        exCode_gc.insets = new Insets(padding,padding,padding,padding);
        
        rightCodeTA.setEditable(false);
        rightCodeTA.setMargin(new Insets(margin,margin,margin,margin));
        
        rC.setBorder(BorderFactory.createLineBorder(Color.blue));
        
        add(rC, exCode_gc);
    }
    
    private void runHello_ButtonActionPerformed(ActionEvent evt) {                                                
        debugLine = 0;
        try {	
                    code_result = Connect.connect(leftCodeTA.getText(), problem);
            } 
        catch (Exception e1) {e1.printStackTrace();}
        
        String[] False1 = new String[]{"False"}; 
        String[][] False = new String[][]{False1};
        
        if(code_result[0][0].equals("True")){ 
            rightCodeTA.setText(code_result[1][0] + ""
                    + "\n**********************************************\n"
                    +   "**** Det er korrekt! Tillykke! Godt gået! ****\n"
                    +   "**********************************************");
            rightCodeTA.setBackground(Color.green);
        } 
        else{
            rightCodeTA.setText(code_result[1][0]);
            rightCodeTA.setBackground(Color.white);
        }
        
        //print "Hello world!"
        // Code to jList:
        /*DefaultListModel dListM = new DefaultListModel(); 
        String[] codeList = leftCodeTA.getText().split("\n",-1);
        for(int codeI = 0; codeI < codeList.length; codeI++){
            dListM.addElement(codeList[codeI]);
        }
        hello_debugList.setModel(dListM);*/
    }
    
    /*public String[] connect() throws Exception, IOException{
                String local_machine_add = "127.0.0.1";
		String host = "10.54.54.254";
                Socket sock = new Socket(local_machine_add, 6542);
		writer = new PrintWriter(sock.getOutputStream(), true);
		writer.println(problem + leftCodeTA.getText());
		ISR = new InputStreamReader(sock.getInputStream());
		in = new BufferedReader(ISR); 
		
		System.out.println("Client: Networking established");
		
		StringBuilder s = new StringBuilder();
		while((str = in.readLine()) != null){
                    s.append(str+"\n");   
		}
		message = s.toString();
                System.out.println(message.split("!!!", 0));
                return message.split("!!!",0);
	}*/
    public void setCodeText(String code){
        leftCodeTA.setText(code);
    }
    public void setProblem(String whichProblem){
        problem = whichProblem;
    }
    public void setEditable(Boolean bol){
        leftCodeTA.setEditable(bol);
    }
    
}
