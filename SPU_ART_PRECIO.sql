DROP PROCEDURE IF EXISTS SPU_ART_PRECIO; 
CREATE PROCEDURE `SPU_ART_PRECIO`(Id int,p decimal(12,2))
UPDATE articulos SET precio=p WHERE idArticulo=id