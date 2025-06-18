# 🧴 Perfulandia SPA

Perfulandia SPA es un sistema de gestión de pedidos, pagos, productos, clientes, usuarios y sucursales desarrollado en Java con Spring Boot. Este sistema simula el funcionamiento de una tienda de perfumes que opera con usuarios administrativos y clientes que realizan pedidos.

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (en memoria)
- Lombok
- Postman (para pruebas de API)
- Maven

## 📦 Estructura del Proyecto

/controller
├── ClienteController.java
├── PagoController.java
├── PedidoController.java
├── ProductoController.java
├── SucursalController.java
└── UsuarioController.java

/jparepository
├── ClienteJpaRepository.java
├── PagoJpaRepository.java
├── PedidoJpaRepository.java
├── ProductoJpaRepository.java
├── SucursalJpaRepository.java
└── UsuarioJpaRepository.java

/model
├── Cliente.java
├── Pago.java
├── Pedido.java
├── Producto.java
├── Sucursal.java
└── Usuario.java

/repository
├── ClienteRepository.java
├── PagoRepository.java
├── PedidoRepository.java
├── ProductoRepository.java
├── SucursalRepository.java
└── UsuarioRepository.java

/services
├── ClienteService.java
├── PagoService.java
├── PedidoService.java
├── ProductoService.java
├── SucursalService.java
└── UsuarioService.java

## 🧪 Pruebas realizadas

Las pruebas se realizaron usando Postman. Se probaron todos los endpoints principales de:

- **Cliente**
- **Usuario**
- **Sucursal**
- **Producto**
- **Pedido**
- **Pago**

### ✔ Ejemplo de flujo completo:

1. Crear una Sucursal.
2. Crear un Usuario asociado a esa sucursal (trabajador)
3. Crear un Cliente asociado a esa sucursal.
4. Crear varios Productos (perfumes).
5. Crear un Pedido con productos asociados al Cliente.
6. Crear un Pago para ese Pedido.
7. Consultar el total calculado automáticamente.
8. Ver registros en la consola de H2 Database (`/h2-console`).

## 🛠 Configuración de la base de datos H2

- URL: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Contraseña: *(dejar en blanco)*
- Consola: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## 🧠 Observaciones

- Solo se aceptan productos tipo “perfume”.
- Los totales se calculan automáticamente al crear Pedidos y Pagos.
- Relación Cliente-Sucursal y Usuario-Sucursal implementada con `@ManyToOne`.

## 📌 Autor

Proyecto académico realizado por 
  
  - Cristóbal Segovia.
  - Felipe Espinoza.
  - Bastian Sepúlveda.
