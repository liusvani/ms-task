package com.tasks.application.service;

import com.tasks.domain.model.ClientesPorUbicacion;
import com.tasks.domain.model.Report;
import com.tasks.domain.model.ProductosMasVendidos;
import com.tasks.domain.ports.ClientesPorUbicacionRepository;
import com.tasks.domain.ports.ReportRepository;
import com.tasks.domain.ports.VentasPorProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository repository;
    private final ClientesPorUbicacionRepository clientesPorUbicacionRepository;
    private final VentasPorProductoRepository ventasPorProductoRepository;

    public ReportService(ReportRepository repository, ClientesPorUbicacionRepository clientesPorUbicacionRepository, VentasPorProductoRepository ventasPorProductoRepository) {
        this.repository = repository;
        this.clientesPorUbicacionRepository = clientesPorUbicacionRepository;
        this.ventasPorProductoRepository = ventasPorProductoRepository;
    }

    public Report createReport(Report report) {
        if (repository.existsByJobId(report.getJobId())) {
            throw new IllegalArgumentException("El id del job ya existe en la db.");
        }
        if (repository.existsByIdTransaccion(report.getIdTransaccion())) {
            throw new IllegalArgumentException("La transaccion ya existe en la db.");
        }
        return repository.save(report);
    }

    public List<Report> getAllReports() {
        return repository.findAll();
    }

    public Report getReportById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
    }



    /*
    * Permite persistir los productos mas vendidos en orden descendentes (mayor a menor)*/
    public List<String> getProductsOrderedBySales() {
        if (ventasPorProductoRepository.count() > 0) {
            ventasPorProductoRepository.deleteAll();
        }
        List<Report> reports = repository.findAll();
        if (reports == null || reports.isEmpty()) {
            System.out.println(reports);
            return List.of("No hay datos disponibles.");
        }

        Map<String, Integer> agrupado = reports.stream()
                .collect(Collectors.groupingBy(Report::getProduct, Collectors.summingInt(Report::getCantidadVendida)));

        // Guarda en base de datos
        agrupado.forEach((producto, cantidad) -> {
            ProductosMasVendidos venta = new ProductosMasVendidos();
            venta.setProducto(producto);
            venta.setCantidadVendida(cantidad);
            ventasPorProductoRepository.save(venta);
        });

        // Devuelve el listado ordenado
        return agrupado.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(entry -> entry.getKey() + " (" + entry.getValue() + ")")
                .collect(Collectors.toList());
    }


    public String getUbicationByClient() {
        if (clientesPorUbicacionRepository.count() > 0) {
            clientesPorUbicacionRepository.deleteAll();
        }

        List<Report> reports = repository.findAll();
        if (reports == null || reports.isEmpty()) {
            System.out.println(reports);
            return "No hay datos disponibles.";
        }
        Map<String, Long> ubicaciones = reports.stream()
                .collect(Collectors.groupingBy(Report::getUbicacion, Collectors.counting()));
        //System.out.println("Ubicaciones: " + ubicaciones);
        // Guarda en la base de datos
        ubicaciones.forEach((ubicacion, cantidad) -> {
            ClientesPorUbicacion registro = new ClientesPorUbicacion();
            registro.setUbicacion(ubicacion);
            registro.setCantidad(cantidad);
            clientesPorUbicacionRepository.save(registro);
        });
        // Devuelve el listado ordenado
        return ubicaciones.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(entry -> entry.getKey() + " (" + entry.getValue() + ")")
                .collect(Collectors.joining("\n"));
    }





}

