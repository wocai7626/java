/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REGEX02;

/**
 *
 * @author zoulida
 */
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class REGEX02 
{
    public static void main(String [] args) throws Exception
    {
        String name1="Frankenstein.txt";
        String name2="shortwords.txt";
        ArrayList dt=new ArrayList();
        
        ArrayList dx=new ArrayList();
        ArrayList check=new ArrayList();
        HashMap<String,Integer> hp=new HashMap();
        String re= "[-—]";
        String replace="";
        Scanner s0 = new Scanner(new BufferedReader(new FileReader(name1)));
        Scanner s1 = new Scanner(new BufferedReader(new FileReader(name2)));
        FileReader f=new FileReader(name1);
        String ch="[\\d\"\\(\\)\\s\\“\\”\\’\\‘\\@\\#\\$\\%\\[\\]\\?\\!\\,\\.\\:\\;\\*\\_\\/\\\\]+";
        Pattern p=Pattern.compile(re);
        while(s1.hasNext()){
            dx.add(s1.next());
            if(s1.hasNext()==false)
                break;
        }
        while(s0.hasNext()){
            String [] l= s0.next().split(ch);
            for(String w : l)
                dt.add(w);
            if(s0.hasNext()==false)
                break;
        }
        int x=0;
        for(Integer i=0;i<dt.size();i++){
            if(dx.contains(dt.get(i))){
                int n=i;
                //if(x!=n-1)
                //System.out.println((String)dt.get(n-1));
                //System.out.println((String)dt.get(n+1));
                if(dt.get(x)!=dt.get(n)){
                    check.add((String) dt.get(n-1)+" "+(String) dt.get(n));
                    check.add((String) dt.get(n)+" "+(String) dt.get(n+1));
                }
                else
                check.add((String) dt.get(n)+" "+(String) dt.get(n+1));
                    x=n+1;
            }
        }
        
        for(Object w : check){
            if(!hp.containsKey((String) w))
                hp.put((String) w, 1);
            else
                hp.put((String) w, hp.get((String)w)+1);
        }
       Set<String> key = hp.keySet();
       for(Iterator i=key.iterator(); i.hasNext();){
           Object k=i.next();
           if(hp.get(k)>=50)
           System.out.println(k+"==>"+hp.get(k));
       }
           
    }
}

/*
while(s0.hasNext())
        {
            
            dt.add(s0.next());
            if(s0.hasNext()==false){
                //dt.add(s0.next());
                break;
            }
        }
        for(Object s : dt)
            System.out.println(s);
*/