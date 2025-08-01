# Microservicio de Procesamiento de Reportes

Este microservicio automatiza el procesamiento de reportes, validación de datos, cálculo estadístico para la toma de decisiones, consolidación de información y exportación en formato CSV. Utiliza **Quartz Scheduler** para la programación de tareas recurrentes y expone una API RESTful para la administración de procesos.

---

## Funcionalidades Principales

- Endpoints REST para procesar reportes y persistencia en la base de datos.
- Validación de reportes.
- Tareas programada con Quartz Scheduler : Persistencia en base de datos de cálculos estadísticos.
- Tareas programada con Quartz Scheduler : Exportación de los cálculos estadísticos emitidos a formato CSV.
- Endpoints REST para iniciar, pausar y reanudar todos los jobs programados.
---

## Tecnologías Utilizadas

- **Java 17** + **Spring Boot**
- **Quartz Scheduler**
- **Spring Data JPA**
- **PostgreSQL / MySQL** (configurable)
- **OpenCSV** para generación de archivos

---

---

## API REST - Endpoints Clave

| Método | Endpoint                   | Descripción                            |
|--------|----------------------------|----------------------------------------|
| POST   | `/jobs/start/{jobName}`    | Inicia un job específico               |
| POST   | `/jobs/pause/{jobName}`    | Pausa la ejecución del job             |
| POST   | `/jobs/resume/{jobName}`   | Reanuda un job pausado                 |
| POST   | `/reports/`                | Crear un registro de reporte           |
--------------------------------------------------------------------------------


##  Configuración Quartz

Quartz se configura en `application.yml` utilizando expresiones CRON para definir la frecuencia de ejecución de tareas.

---

##  Ejecución

```bash
# Compilar proyecto
mvn clean install

# Ejecutar microservicio
mvn spring-boot:run



