/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beaneditor;

import static beaneditor.reflection.getAttributes;
import static beaneditor.reflection.setAttributes;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



/**
 *
 * @author zoulida
 */
public class beaneditor extends JFrame{
    private JTextField a,b ;
    private JPanel p1,p2;
    private JLabel l1,l2;
    public String FirstName,LastName,fn="a",ln="b";
    private static Object obj,objc;
    private Boolean fboo,lboo;
    private ArrayList<String> set1;
    private HashMap<String, Method> Attri = new HashMap<>();
    private Class c;
    
    public beaneditor(Object x) throws Exception{
        super("beaneditor"); 
        c = Class.forName("beaneditor.javabean");
        set1 = getAttributes(c);
        Attri = setAttributes(c);
        init();
    }
    
    public void init(){   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    setBounds(200, 200, 350, 250);
    p1=new JPanel();
    setContentPane(p1);

    p2=new JPanel();
    p2.setLayout(new GridLayout(2,2));

    l1=new JLabel("FirstName：");
    a=new JTextField();
    a.addActionListener(new MyListener01());
    a.setColumns(10);
    p2.add(l1);
    p2.add(a);
    l2=new JLabel("LastName：");
    b=new JTextField();
    b.addActionListener(new MyListener02());
    b.setColumns(10);
    p2.add(l2);
    p2.add(b);
    p1.add(p2);
    //c.add(p2);
    setVisible(true);
    }
   class MyListener01 implements ActionListener{
       public void actionPerformed(ActionEvent e) {
           try {
                    System.out.println("FirstName:"+reflection.invokeMethod(c, "setFirstName", a.getText()));
                    
                } catch (Exception ex) {
                    Logger.getLogger(beaneditor.class.getName()).log(Level.SEVERE, null, ex);
                }
       }
      
   }
   class MyListener02 implements ActionListener{
       public void actionPerformed(ActionEvent e) {
           try {
                    System.out.println("setLastName"+reflection.invokeMethod(c, "setLastName", b.getText()));
                } catch (Exception ex) {
                    Logger.getLogger(beaneditor.class.getName()).log(Level.SEVERE, null, ex);
                }
       }
   }
   
   
    public static void main(String[] args) throws Exception{
        new beaneditor(new javabean());
    }
}
class reflection{
    public static ArrayList<String> getAttributes(Class c) throws NoSuchMethodException {
        Method[] methods = c.getDeclaredMethods();
        ArrayList<String> getters = new ArrayList<>();
        for (int i = 0; i < methods.length; i++) {
            String name = methods[i].getName();
            if (name.startsWith("get"))
                getters.add(name);
        }
        
        for(String n: getters) {
            System.out.println(n);
        }
        return getters;
    }
    
    public static HashMap<String, Method> setAttributes(Class c) throws NoSuchMethodException {
        Method[] methods = c.getDeclaredMethods();
        ArrayList<String> getters = new ArrayList<>();
        HashMap<String, Method> setters = new HashMap<>();
        
        for (int i = 0; i < methods.length; i++) {
            String name = methods[i].getName();
            if (name .startsWith("get"))
                getters.add(name);
            else if (name.startsWith("set"))
                setters.put(name, c.getMethod(name, String.class));
        }
            
        
        for (int i = 0; i < getters.size(); i++) {
            String name = getters.get(i).substring(3);
            Method setter = setters.get("set"+name);
            if(setter != null) {
                System.out.println(setter); 
            }
        }  
        System.out.println();
        return setters;
    }
    
    public static Object invokeMethod(Class owner, String MethodName, String name) throws Exception {
        Constructor constructor = owner.getConstructor();
        Object obj = constructor.newInstance();
        Method m = owner.getDeclaredMethod(MethodName, java.lang.String.class);
        return  m.invoke(obj,name) ;
    }
    
}