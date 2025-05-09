# Consultorio-Medico-Kosmos

### Prueba técnica: Existe una problemática en un hospital la cual consiste en que los doctores del área de Medicina Interna no saben cuántas ni a qué hora tienen citas durante el día.

## 💻 Tecnologías
- Java
- Spring Boot 3.x 
- Maven
- H2 (Base de datos para pruebas)
- Hibernate JPA
- Swagger
- Postman

## 📁 Estructura del proyecto
```
src/main/java/com/kosmos/consultorios_medicos
├── ConsultoriosMedicosApplication.java
├── controller/
│   └── CitaController.java
│   └── DoctorController.java
│   └── ConsultorioController.java
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
    └── DoctorService.java
    └── ConsultorioService.java
```
## 🧑‍💻 Diagrama Entidad-Relación
![image](https://github.com/user-attachments/assets/1e268863-602b-4433-8f91-a3ffc5588bbe)

## 📌 Endpoints disponibles
### (**Doctor**)
| Método | Ruta                 | Descripción              |
| ------ | -------------------- | ------------------------ |
| GET    | `/api/doctores`      | Lista todos los doctores |
| GET    | `/api/doctores/{id}` | Obtiene uno por ID       |
| POST   | `/api/doctores`      | Crea nuevo doctor        |
| PUT    | `/api/doctores/{id}` | Actualiza doctor         |
| DELETE | `/api/doctores/{id}` | Elimina doctor           |

### (**Consultorio**)
| Método | Ruta                     | Descripción                  |
| ------ | ------------------------ | ---------------------------- |
| GET    | `/api/consultorios`      | Lista todos los consultorios |
| GET    | `/api/consultorios/{id}` | Consultorio por ID           |
| POST   | `/api/consultorios`      | Crear nuevo consultorio      |
| PUT    | `/api/consultorios/{id}` | Actualizar consultorio       |
| DELETE | `/api/consultorios/{id}` | Eliminar consultorio         |

### (**Cita**)
| **Método** | **Ruta**                   | **Descripción**                                               |
| ---------- | -------------------------- | ------------------------------------------------------------- |
| GET        | `/api/citas`               | Lista todas las citas                                         |
| POST       | `/api/citas`               | Agenda una nueva cita                                         |
| GET        | `/api/citas/{id}`          | Obtiene una cita por ID                                       |
| GET        | `/api/citas/fecha`         | Consulta citas por fecha (yyyy-MM-dd)                         |
| PUT        | `/api/citas/{id}`          | Actualiza una cita por ID                                     |
| PUT        | `/api/citas/{id}/cancelar` | Cancela una cita pendiente por ID                             |
| DELETE     | `/api/citas/{id}`          | Elimina una cita por ID                                       |
| GET        | `/api/citas/buscar`        | Consulta citas por filtros: fecha, doctorId y consultorioId   |

## 💻🕵️ Testing 
#### Se realizaron pruebas en Swagger y Postman
![image](https://github.com/user-attachments/assets/e67c6645-e0a8-40ca-bb0e-adc77582a456)

![image](https://github.com/user-attachments/assets/f71cd7ea-39ef-4eb4-a807-5b10f5a14a17)

![image](https://github.com/user-attachments/assets/09953ce6-33ca-4047-a637-181252c20dbf)


