package DTO;

import java.sql.Timestamp;

public class HoaDonDTO {
    private int idHd;
    private Integer idNv;
    private Integer idKhMember;
    private Timestamp timeHd;
    private double total;

    public HoaDonDTO() {
    }

    public HoaDonDTO(int idHd, Integer idNv, Integer idKhMember, Timestamp timeHd, double total) {
        this.idHd = idHd;
        this.idNv = idNv;
        this.idKhMember = idKhMember;
        this.timeHd = timeHd;
        this.total = total;
    }

    public int getIdHd() {
        return idHd;
    }

    public void setIdHd(int idHd) {
        this.idHd = idHd;
    }

    public Integer getIdNv() {
        return idNv;
    }

    public void setIdNv(Integer idNv) {
        this.idNv = idNv;
    }

    public Integer getIdKhMember() {
        return idKhMember;
    }

    public void setIdKhMember(Integer idKhMember) {
        this.idKhMember = idKhMember;
    }

    public Timestamp getTimeHd() {
        return timeHd;
    }

    public void setTimeHd(Timestamp timeHd) {
        this.timeHd = timeHd;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
