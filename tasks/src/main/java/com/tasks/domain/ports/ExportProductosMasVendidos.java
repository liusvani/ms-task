package com.tasks.domain.ports;


import com.tasks.domain.model.ProductosMasVendidos;

import java.util.List;

public interface ExportProductosMasVendidos {
    void export(List<ProductosMasVendidos> productos);
    List<ProductosMasVendidos> fetchProductosMasVendidos();

}
