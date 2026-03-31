-- ============================================================
-- 002_seed_chitietphieunhap.sql
-- Muc tieu: tao du lieu mau cho phieunhap va chitietphieunhap.
-- ============================================================

USE quanliquantrasua;

START TRANSACTION;

-- Dung idNv=4 va idNcc=1 vi co trong dump hien tai.
INSERT INTO phieunhap (idNv, idNcc, timeNhap, Total, note)
VALUES (4, 1, NOW(), 0, 'Seed test chi tiet phieu nhap');

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

-- Kiem tra nhanh ket qua seed
SELECT *
FROM phieunhap
WHERE idPN = @new_idPN;

SELECT *
FROM vw_chitietphieunhap_gui
WHERE idPN = @new_idPN
ORDER BY idNL;
