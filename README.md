# CoffeeHouse-Management-System
Mô hình 3 lớp trong Java Desktop (Swing)
Các bước cài đặt

## Ke hoach thay doi SQL (bang chitietphieunhap)

Muc tieu: chuan hoa bang `chitietphieunhap` de phu hop luong nhap hang, dam bao du lieu hop le va de truy van.

### 1) Quy uoc schema

- Giu nguyen ten bang: `chitietphieunhap`.
- Giu ten cot hien co de tranh vo code: `idNL`, `idPN`, `priceNL`, `soLuong`.
- Them rang buoc khoa chinh ket hop: (`idPN`, `idNL`) de moi nguyen lieu chi xuat hien 1 lan trong 1 phieu nhap.
- Them khoa ngoai:
	- `idPN` -> `phieunhap(idPN)`
	- `idNL` -> `nguyenlieu(idNL)`
- Them rang buoc du lieu:
	- `priceNL >= 0`
	- `soLuong > 0`

### 2) SQL de xuat

```sql
-- Nen backup truoc khi chay migration

-- 1. Dam bao cot khong null
ALTER TABLE chitietphieunhap
	MODIFY idNL INT NOT NULL,
	MODIFY idPN INT NOT NULL,
	MODIFY priceNL DOUBLE NOT NULL,
	MODIFY soLuong DOUBLE NOT NULL;

-- 2. Loai bo dong trung (neu co) truoc khi them PK
-- Gom nhom theo (idPN, idNL), giu 1 dong duy nhat
CREATE TEMPORARY TABLE tmp_ctpn AS
SELECT
	idPN,
	idNL,
	MAX(priceNL) AS priceNL,
	SUM(soLuong) AS soLuong
FROM chitietphieunhap
WHERE idPN IS NOT NULL AND idNL IS NOT NULL
GROUP BY idPN, idNL;

TRUNCATE TABLE chitietphieunhap;

INSERT INTO chitietphieunhap (idPN, idNL, priceNL, soLuong)
SELECT idPN, idNL, priceNL, soLuong
FROM tmp_ctpn;

DROP TEMPORARY TABLE tmp_ctpn;

-- 3. Them chi muc + khoa chinh
ALTER TABLE chitietphieunhap
	ADD INDEX idx_ctpn_idnl (idNL),
	ADD PRIMARY KEY (idPN, idNL);

-- 4. Them khoa ngoai
ALTER TABLE chitietphieunhap
	ADD CONSTRAINT fk_ctpn_pn
		FOREIGN KEY (idPN) REFERENCES phieunhap(idPN)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	ADD CONSTRAINT fk_ctpn_nl
		FOREIGN KEY (idNL) REFERENCES nguyenlieu(idNL)
		ON DELETE RESTRICT
		ON UPDATE CASCADE;

-- 5. Them CHECK (MySQL 8+/MariaDB ho tro)
ALTER TABLE chitietphieunhap
	ADD CONSTRAINT chk_ctpn_price_non_negative CHECK (priceNL >= 0),
	ADD CONSTRAINT chk_ctpn_quantity_positive CHECK (soLuong > 0);
```

### 3) Thu tu thuc hien an toan

1. Backup DB.
2. Chay script tren moi truong test.
3. Fix du lieu loi (NULL, trung PK, gia/so luong am).
4. Chay tren production trong maintenance window ngan.

### 4) Ghi chu tuong thich

- Cac cot khong doi ten nen khong anh huong den code dang dung ten cot cu.
- PK kep (`idPN`, `idNL`) phu hop nghiep vu chi tiet phieu nhap.
