package com.tasks.infraestructure.csv;


import com.tasks.domain.model.ClientesPorUbicacion;
import com.tasks.domain.model.ProductosMasVendidos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
public class CsvWriterProductosMasVendidosAdapter {
    @Value("${csv.export.path}s")
    private String exportPath;


    public void write(List<ProductosMasVendidos> productos) throws IOException {
        Files.createDirectories(Paths.get(exportPath));

        String fileName = String.format("productos_mas_vendidos_%s.csv",
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Path filePath = Paths.get(exportPath, fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write("ID,Producto,Venta Total");
            writer.newLine();

            for (ProductosMasVendidos producto : productos) {

                writer.write(String.format("%s,%s,%s",
                        producto.getId() != null ? producto.getId() : "Empty",
                        producto.getProducto() != null ? producto.getProducto() : "Empty",
                        producto.getCantidadVendida() != null ? producto.getCantidadVendida() : "Empty"));
                writer.newLine();
            }
        }
    }
}

