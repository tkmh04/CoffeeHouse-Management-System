
package BUS;
import java.util.ArrayList;
import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
/**
 *
 * @author asus
 */
public class SanPhamBUS {
     public final SanPhamDAO spDAO = new SanPhamDAO();
    public ArrayList<SanPhamDTO> listSP = new ArrayList<>();

    public SanPhamBUS() {
        listSP=spDAO.selectAll();
        
    }
    public ArrayList<SanPhamDTO> getAll() {
        
        return this.listSP;
    }
    public String imgLink(int index) {
        return this.listSP.get(index).getImgSp();
    }
    public String getName(int index)
    {
        return this.listSP.get(index).getTenSp();
    }
    public int getId(int index)
    {
        return this.listSP.get(index).getIdSp();
    }
    public double getPriceSp(String id, String size)
    {
        return spDAO.dbPriceSp(id, size);
    }
    public SanPhamDTO selectedById(String id) {
        try {
           
            return spDAO.selectById(id);
        } catch (Exception ex) {
       
            ex.printStackTrace();
            return null;
        }
    }
  

    
}
