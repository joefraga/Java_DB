CREATE PROCEDURE SPS_ARTICULOS() 
SELECT
	a.DescArticulo,
    a.Seccion,
    c.DescColor,
    a.Precio
FROM articulos a INNER JOIN colores c
ON a.IdColor = c.IdColor
