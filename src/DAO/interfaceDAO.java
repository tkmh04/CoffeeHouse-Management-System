/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author asus
 */
public interface interfaceDAO<T> {
    public  int ADD(T t);
    public int UPDATE(T t);
    public int DELETE(T t);
    public T selectById(T t);
    public ArrayList<T> selectAll ();
    public ArrayList<T> selectByCondition (String condition);
}
