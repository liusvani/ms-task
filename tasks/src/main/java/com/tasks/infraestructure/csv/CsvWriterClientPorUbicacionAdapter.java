package com.tasks.infraestructure.csv;


import com.tasks.domain.model.ClientesPorUbicacion;
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
public class CsvWriterClientPorUbicacionAdapter {
    @Value("${csv.export.path}s")
    private String exportPath;


    public void write(List<ClientesPorUbicacion> clientes) throws IOException {
        Files.createDirectories(Paths.get(exportPath));

        String fileName = String.format("clientes_por_ubicacion_%s.csv",
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Path filePath = Paths.get(exportPath, fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write("ID,Ubicación,Cantidad");
            writer.newLine();

            for (ClientesPorUbicacion cliente : clientes) {
                writer.write(String.format("%s,%s,%s",
                        cliente.getId() != null ? cliente.getId() : "Empty",
                        cliente.getUbicacion() != null ? cliente.getUbicacion() : "Empty",
                        cliente.getCantidad() != null ? cliente.getCantidad() : "Empty"));
                writer.newLine();
            }
        }
    }
}

