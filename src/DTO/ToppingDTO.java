/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author asus
 */
public class ToppingDTO {
    private int idTopping;
    private String nameTopping;
    private double priceTopping;
    private int quantTopping;

    public ToppingDTO(int idTopping, String nameTopping, double priceTopping, int quantTopping) {
        this.idTopping = idTopping;
        this.nameTopping = nameTopping;
        this.priceTopping = priceTopping;
        this.quantTopping = quantTopping;
    }

    public int getQuantTopping() {
        return quantTopping;
    }

    public void setQuantTopping(int quantTopping) {
        this.quantTopping = quantTopping;
    }

    public int getIdTopping() {
        return idTopping;
    }

    public void setIdTopping(int idTopping) {
        this.idTopping = idTopping;
    }

    public String getNameTopping() {
        return nameTopping;
    }

    public void setNameTopping(String nameTopping) {
        this.nameTopping = nameTopping;
    }

    public double getPriceTopping() {
        return priceTopping;
    }

    public void setPriceTopping(double priceTopping) {
        this.priceTopping = priceTopping;
    }

    @Override
    public String toString() {
        return "ToppingDTO{" + "idTopping=" + idTopping + ", nameTopping=" + nameTopping + ", priceTopping=" + priceTopping + ", quantTopping=" + quantTopping + '}';
    }


   
    
}
