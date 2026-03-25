/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author asus
 */
public class SelectedTopping {
    private int id;
    private String name;
    private int quantity;

        public SelectedTopping(int id,String name, int quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

    public int getId() {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

        
        
}
