/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beaneditor;

/**
 *
 * @author zoulida
 */
public class javabean extends Object{
     public String firstName, lastName;

    public javabean() { }    
    
    public  String setFirstName(String name) { firstName = name; return firstName;}
    public String getFirstName() { return firstName; }

    public String setLastName(String name) { lastName = name; return lastName;}
    public String getLastName() { return lastName; }

    public String toString() { return "firstNam:"+firstName + " " +"lastName:"+ lastName; }
}
