# 🧴 Perfulandia SPA

Perfulandia SPA es un sistema de gestión de pedidos, pagos, productos, clientes, usuarios y sucursales desarrollado en Java con Spring Boot. Este sistema simula el funcionamiento de una tienda de perfumes que opera con usuarios administrativos y clientes que realizan pedidos.

---

## 🚀 Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- H2 Database (en memoria)  
- Lombok  
- Maven  
- Postman  
- JUnit 5 + MockMvc (para pruebas unitarias)

---

## 📦 Estructura del Proyecto

- 📁 `controller` → Controladores REST de cada entidad  
- 📁 `services` → Lógica de negocio  
- 📁 `repository` → Repositorios personalizados  
- 📁 `jparepository` → Repositorios JPA (`CrudRepository`, `JpaRepository`)  
- 📁 `model` → Entidades JPA  
- 📁 `test/controller` → Pruebas unitarias de cada controlador  

---

## 🧪 Pruebas realizadas

### ✅ Pruebas con Postman

Se probaron los endpoints principales de:

- Cliente  
- Usuario  
- Sucursal  
- Producto  
- Pedido  
- Pago   

### ✅ Pruebas unitarias (JUnit + MockMvc)

Se implementaron pruebas unitarias completas para todos los controladores del sistema:

| Controlador                 | Estado        |
|-----------------------------|---------------|
| `ClienteController`         | ✅ Completado  |
| `ProductoController`        | ✅ Completado  |
| `PagoController`            | ✅ Completado  |
| `PedidoController`          | ✅ Completado  |
| `SucursalController`        | ✅ Completado  |
| `UsuarioController`         | ✅ Completado  |

---

## ✔ Ejemplo de flujo completo

1. Crear una sucursal.
2. Crear varios productos (perfumes). 
3. Crear un usuario asociado a esa sucursal (trabajador).
4. Crear un cliente asociado a la misma sucursal.   
5. Crear un pedido con productos asociados al cliente.  
6. Registrar un pago para ese pedido.  
7. Consultar el total generado automáticamente.  
8. Consultar historial de compras del cliente.

---

## 🛠 Configuración de la base de datos H2

- **URL**: `jdbc:h2:mem:testdb`  
- **Usuario**: `sa`  
- **Contraseña**: *(dejar en blanco)*  
- **Consola**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## 🧠 Observaciones

- Solo se aceptan productos tipo **perfume**.  
- Los totales se calculan automáticamente al crear **pedidos** y **pagos**.  
- El historial de compras del cliente se actualiza cada vez que se registra una nueva compra.  
- Todas las relaciones JPA están correctamente implementadas con anotaciones como `@ManyToOne`, `@OneToOne`, `@ManyToMany`.

---

## 👨‍💻 Autores

Proyecto académico realizado por:

- Cristóbal Segovia  
- Felipe Espinoza  
- Bastian Sepúlveda
