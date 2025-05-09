# Consultorio-Medico-Kosmos

### Prueba técnica: Existe una problemática en un hospital la cual consiste en que los doctores del área de Medicina Interna no saben cuántas ni a qué hora tienen citas durante el día.

## 💻 Tecnologías
- Java
- Spring Boot 3.x 
- Maven
- MySQL
- Swagger

## 📁 Estructura del proyecto
```
src/main/java/com/kosmos/consultorios_medicos
├── ConsultorioApplication.java
├── controller/
│   └── CitaController.java
├── model/
│   ├── Cita.java
│   ├── Doctor.java
│   └── Consultorio.java
├── repository/
│   ├── CitaRepository.java
│   ├── DoctorRepository.java
│   └── ConsultorioRepository.java
└── service/
    └── CitaService.java
```

## 📌 Endpoints disponibles
#### (Doctor)
| Método | Ruta                 | Descripción              |
| ------ | -------------------- | ------------------------ |
| GET    | `/api/doctores`      | Lista todos los doctores |
| GET    | `/api/doctores/{id}` | Obtiene uno por ID       |
| POST   | `/api/doctores`      | Crea nuevo doctor        |
| PUT    | `/api/doctores/{id}` | Actualiza doctor         |
| DELETE | `/api/doctores/{id}` | Elimina doctor           |



