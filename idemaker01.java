/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idemaker01;

/**
 *
 * @author zoulida
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;

public class idemaker01 extends JFrame implements ActionListener,KeyListener{
    public JTextArea jta;
    private JPanel panel;
    private File file;
    public String name;
    private int i=0;
    public ArrayList<String> al=new ArrayList<>();
    public idemaker01(){
        init();
    }
    public void init(){
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 500);
        panel=new JPanel();
        panel.setSize(500, 500);
        panel.setLayout(new GridLayout(1,1));
        panel.setBackground(Color.white);
        jta=new JTextArea();
        jta.setLineWrap(true);
        JScrollPane jscp =new JScrollPane(jta);
        jta.addKeyListener(this);
        panel.add(jscp);
        
        JMenuBar jmb=new JMenuBar();
        jmb.setLayout(new FlowLayout(FlowLayout.LEFT));
        JMenu jm0=new JMenu("file");
        JMenu jm1=new JMenu("Compile");
        JMenuItem jmi00=new JMenuItem("save");
        JMenuItem jmi01=new JMenuItem("open");
        JMenuItem jmi10=new JMenuItem("Run");
        JMenuItem jmi11=new JMenuItem("check F4");
        
        jmi00.addActionListener(this);
        jmi01.addActionListener(this);
        jmi10.addActionListener(this);
        jmi11.addActionListener(this);
        
        jm0.add(jmi00);
        jm0.add(jmi01);
        jm1.add(jmi10);
        jm1.add(jmi11);
        
        jmb.add(jm0);
        jmb.add(jm1);
        
        this.setJMenuBar(jmb);
        this.add(panel);
        this.setVisible(true);
    }
    public static void main(String [] args){
         new idemaker01();
    }

    public void actionPerformed(ActionEvent e) {
        String cmd =e.getActionCommand();
        //if("save".equals(cmd)) save();
        if("open".equals(cmd)) read();
        if("Run".equals(cmd)) {try {
            Rx();
        } catch (Exception ex) {
            Logger.getLogger(idemaker01.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        if("check F4".equals(cmd)) 
            check();
    }
    public void read(){
        JFileChooser jfc=new JFileChooser();
        jfc.showOpenDialog(jfc);
        file=jfc.getSelectedFile();
        name=file.getAbsolutePath();
        try{
            if(file !=null){
                FileInputStream in =new FileInputStream(file);
                InputStreamReader ipr=new InputStreamReader(in,"GBK");
                BufferedReader bf=new BufferedReader(ipr);
                String str="";
                while((str=bf.readLine())!=null){
                    jta.append(str+"\n");
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("Open fail");
        }catch(IOException e){
            System.out.println("Open fail");
        }
    }
    public void check(){
        int start=0;
        int end=0;
        int check;
        check=Integer.parseInt(al.get(i));
            System.out.println(check);
            try {
                start = jta.getLineStartOffset(check-1);
                 end = jta.getLineEndOffset(check-1);
            } catch (BadLocationException ex) {
                Logger.getLogger(idemaker01.class.getName()).log(Level.SEVERE, null, ex);
            }
            jta.requestFocus();
            jta.setSelectionStart(start);
            jta.setSelectionEnd(end);
            i++;
            if(i==al.size()){
                i=0;
            }
    }
    public void Rx() throws Exception{
        new R();
        System.out.print(name);
    }
    class R{
        public R()throws Exception{
            runProcessUsingRuntime();
        }
    public void runProcessUsingRuntime() throws Exception {
        String ch=":[\\d]+:";
        Pattern pa=Pattern.compile(ch);
        Runtime r= Runtime.getRuntime();
        Process p = r.exec("javac "+name);
        BufferedReader isr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        BufferedReader br= new BufferedReader(new FileReader(name));
        String line1;
        while ((line1 = isr.readLine()) != null){
            Matcher m=pa.matcher(line1);
            if(m.find()){
                Pattern pa1=Pattern.compile("\\d+");
                Matcher m1=pa1.matcher(m.group());
                if(m1.find())
                    al.add(m1.group());
            }
        }
    }
    
    }
    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_F4)
            check();
            //jta.requestFocus();
            //jta.setCaretPosition(jta.getText().length());
            /*jta.setColumns(Integer.parseInt(al.get(i)));
            i++;
            if(i==al.size())
                i=0;*/
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
