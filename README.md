# CoffeeHouse-Management-System
Mô hình 3 lớp trong Java Desktop (Swing)
Các bước cài đặt

## SQL migration cho chi tiet phieu nhap

Da tach migration thanh 2 file rieng trong thu muc `sql/migrations`:

- `001_schema_chitietphieunhap.sql`: chua cau truc bang, rang buoc, chi muc va view.
- `002_seed_chitietphieunhap.sql`: chua du lieu mau INSERT de test.

### Thu tu chay

1. Chay file schema truoc:

```bash
mysql -u <user> -p < sql/migrations/001_schema_chitietphieunhap.sql
```

2. Chay file seed sau:

```bash
mysql -u <user> -p < sql/migrations/002_seed_chitietphieunhap.sql
```

### Kiem tra ket qua

Sau khi chay file 002, script tu dong SELECT de kiem tra:

- ban ghi moi trong `phieunhap`
- du lieu dong hang trong view `vw_chitietphieunhap_gui`

### Luu y

- Nen backup DB truoc khi chay migration.
- Nhan vien/NCC mau dang dung idNv=4 va idNcc=1 (khop dump hien tai).
- Cac script nay huong toi MySQL 8+/MariaDB 10.4+.
