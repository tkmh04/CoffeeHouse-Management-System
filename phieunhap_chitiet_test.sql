-- ============================================================
-- File: phieunhap_chitiet_test.sql
-- Muc tieu:
-- 1) De xuat MySQL cho phieunhap/chitietphieunhap de dung on dinh.
-- 2) Tao du lieu test chi tiet phieu nhap.
-- 3) Co san truy van view phuc vu man hinh chi tiet dong hang.
--
-- Luu y:
-- - Nen backup DB truoc khi chay.
-- - Script nay duoc viet cho MySQL 8+/MariaDB 10.4+.
-- ============================================================

USE quanliquantrasua;

START TRANSACTION;

-- ------------------------------------------------------------
-- A) DE XUAT CHUAN HOA BANG PHIEUNHAP
-- ------------------------------------------------------------
-- Co the bo qua cot note neu ban muon giu schema cu.
ALTER TABLE phieunhap
    MODIFY idNv INT NOT NULL,
    MODIFY idNcc INT NOT NULL,
    MODIFY timeNhap DATETIME NOT NULL,
    MODIFY Total DOUBLE NOT NULL DEFAULT 0;

-- Lam sach du lieu cu de tranh vo FK (dump cu co the co idNv/idNcc khong hop le).
UPDATE phieunhap
SET idNv = (SELECT MIN(nv.idNv) FROM nhanvien nv)
WHERE idNv NOT IN (SELECT nv2.idNv FROM nhanvien nv2);

UPDATE phieunhap
SET idNcc = (SELECT MIN(ncc.idNcc) FROM nhacungcap ncc)
WHERE idNcc NOT IN (SELECT ncc2.idNcc FROM nhacungcap ncc2);

UPDATE phieunhap
SET Total = 0
WHERE Total IS NULL OR Total < 0;

ALTER TABLE phieunhap
    ADD COLUMN note VARCHAR(255) NULL AFTER Total;

ALTER TABLE phieunhap
    ADD INDEX idx_phieunhap_timeNhap (timeNhap),
    ADD INDEX idx_phieunhap_idNv (idNv),
    ADD INDEX idx_phieunhap_idNcc (idNcc);

ALTER TABLE phieunhap
    ADD CONSTRAINT fk_phieunhap_nhanvien
        FOREIGN KEY (idNv) REFERENCES nhanvien(idNv)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    ADD CONSTRAINT fk_phieunhap_nhacungcap
        FOREIGN KEY (idNcc) REFERENCES nhacungcap(idNcc)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    ADD CONSTRAINT chk_phieunhap_total_non_negative
        CHECK (Total >= 0);

-- ------------------------------------------------------------
-- B) CHUAN HOA BANG CHITIETPHIEUNHAP
-- ------------------------------------------------------------
-- Buoc 1: dam bao du lieu hop le truoc khi them rang buoc.
DELETE FROM chitietphieunhap
WHERE idPN IS NULL
   OR idNL IS NULL
   OR soLuong IS NULL
   OR priceNL IS NULL
   OR soLuong <= 0
   OR priceNL < 0;

-- Buoc 2: gom dong trung theo (idPN, idNL).
CREATE TEMPORARY TABLE tmp_ctpn AS
SELECT
    idPN,
    idNL,
    MAX(priceNL) AS priceNL,
    SUM(soLuong) AS soLuong
FROM chitietphieunhap
GROUP BY idPN, idNL;

TRUNCATE TABLE chitietphieunhap;

INSERT INTO chitietphieunhap (idNL, idPN, priceNL, soLuong)
SELECT idNL, idPN, priceNL, soLuong
FROM tmp_ctpn;

DROP TEMPORARY TABLE tmp_ctpn;

ALTER TABLE chitietphieunhap
    MODIFY idNL INT NOT NULL,
    MODIFY idPN INT NOT NULL,
    MODIFY priceNL DOUBLE NOT NULL,
    MODIFY soLuong DOUBLE NOT NULL;

ALTER TABLE chitietphieunhap
    ADD PRIMARY KEY (idPN, idNL),
    ADD INDEX idx_ctpn_idnl (idNL);

ALTER TABLE chitietphieunhap
    ADD CONSTRAINT fk_ctpn_pn
        FOREIGN KEY (idPN) REFERENCES phieunhap(idPN)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    ADD CONSTRAINT fk_ctpn_nl
        FOREIGN KEY (idNL) REFERENCES nguyenlieu(idNL)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    ADD CONSTRAINT chk_ctpn_price_non_negative
        CHECK (priceNL >= 0),
    ADD CONSTRAINT chk_ctpn_quantity_positive
        CHECK (soLuong > 0);

-- ------------------------------------------------------------
-- C) VIEW GOI Y CHO MAN HINH CHI TIET DONG HANG
-- ------------------------------------------------------------
-- View nay hien thi du lieu theo kieu line-item (giong luong chi tiet san pham).
CREATE OR REPLACE VIEW vw_chitietphieunhap_gui AS
SELECT
    pn.idPN,
    pn.timeNhap,
    pn.idNv,
    pn.idNcc,
    pn.note,
    ct.idNL,
    nl.nameNL,
    nl.unit,
    ct.soLuong,
    ct.priceNL,
    (ct.priceNL * ct.soLuong) AS lineTotal,
    pn.Total AS phieuTotal
FROM phieunhap pn
JOIN chitietphieunhap ct ON ct.idPN = pn.idPN
JOIN nguyenlieu nl ON nl.idNL = ct.idNL;

-- ------------------------------------------------------------
-- D) DU LIEU TEST CHI TIET PHIEU NHAP
-- ------------------------------------------------------------
-- Dung idNv=4 va idNcc=1 vi co trong dump hien tai.
INSERT INTO phieunhap (idNv, idNcc, timeNhap, Total, note)
VALUES (4, 1, NOW(), 0, 'Test phieu nhap tu script');

SET @new_idPN := LAST_INSERT_ID();

INSERT INTO chitietphieunhap (idNL, idPN, priceNL, soLuong)
VALUES
    (1, @new_idPN, 5200, 5),
    (3, @new_idPN, 4800, 10),
    (15, @new_idPN, 6200, 4);

UPDATE phieunhap pn
SET pn.Total = (
    SELECT COALESCE(SUM(ct.priceNL * ct.soLuong), 0)
    FROM chitietphieunhap ct
    WHERE ct.idPN = pn.idPN
)
WHERE pn.idPN = @new_idPN;

COMMIT;

-- ------------------------------------------------------------
-- E) TRUY VAN KIEM TRA NHANH
-- ------------------------------------------------------------
SELECT *
FROM phieunhap
WHERE idPN = @new_idPN;

SELECT *
FROM vw_chitietphieunhap_gui
WHERE idPN = @new_idPN
ORDER BY idNL;
