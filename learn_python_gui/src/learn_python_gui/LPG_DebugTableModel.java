/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn_python_gui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lau
 */
public class LPG_DebugTableModel extends AbstractTableModel {
    
    private ArrayList<ArrayList<String>> varsList = new ArrayList<ArrayList<String>>();
    private ArrayList<String> initRow = new ArrayList<String>();
    private final String fill = "";
    private int clear = 0;
    
    public LPG_DebugTableModel(){
        initRow.add(fill); initRow.add(fill); initRow.add(fill);
        varsList.add(initRow);
    }
    public void setData(ArrayList<ArrayList<String>> varsList){
        this.varsList = varsList;
        
        /*System.out.println("*** This is tablemodel! ***");
        System.out.println("How many variables: " + varsList.size());
        for(ArrayList<String> AL : varsList){
            System.out.println("AL.size: " + AL.size() + "\t" + "Vars: ");
            
            for(int i = 0; i<AL.size(); i++){
                System.out.print(AL.get(i) + "\t");
            }
        }
        System.out.println("\n**********tablemodel end**************");*/
    }
    
    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int len = varsList.size();
        
        return len;
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //System.out.println("**** This is friggin getValueAt-method ****");
        ArrayList<String> row = varsList.get(rowIndex);
        /*for(String s : row){System.out.println(s);}*/
        //System.out.println("**********getValueAt end************");
         
        switch(columnIndex){
            
            case 0:
                return row.get(columnIndex);
            case 1:
                return row.get(columnIndex);
            case 2:
                return row.get(columnIndex);
        
        }
        return null;
    
    }
    
    private static final String[] COLUMN_NAMES = {"Variabel", "Værdi", "Type"};
    @Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }
    
}
