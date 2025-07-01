🧴 Perfulandia SPA – Sistema de Gestión de Perfumería
Perfulandia SPA es un sistema de gestión integral para una tienda de perfumes. Permite administrar pedidos, pagos, productos, clientes, usuarios y sucursales. Está construido en Java usando Spring Boot y cuenta con pruebas unitarias completas para asegurar la calidad del software.

🚀 Tecnologías Utilizadas
Java 17

Spring Boot

Spring Data JPA

H2 Database (en memoria)

Lombok

Maven

Postman

JUnit 5 + MockMvc (para pruebas unitarias)

📂 Estructura del Proyecto
plaintext
Copiar
Editar
📂controller           # Controladores REST para cada entidad
📂services             # Lógica de negocio por entidad
📂repository           # Repositorios lógicos personalizados
📂jparepository        # Interfaces que extienden JpaRepository
📂model                # Entidades del modelo de dominio
📂test/controller      # Pruebas unitarias por controlador

🔁 Flujo de Uso
Crear una sucursal.

Registrar un usuario asociado (por ejemplo, un gerente o empleado).

Crear un cliente vinculado a la sucursal.

Agregar productos (solo perfumes).

Generar un pedido con productos seleccionados.

Registrar un pago para ese pedido.

Verificar el total calculado automáticamente.

Consultar historial de compras del cliente.

🧪 Pruebas Realizadas
🧭 Pruebas manuales (Postman)
Se probaron todos los endpoints principales de:

Cliente

Usuario

Sucursal

Producto

Pedido

Pago

HistorialCliente

✅ Pruebas unitarias automatizadas
Pruebas desarrolladas con JUnit 5 y MockMvc para todos los controladores:

Controlador	Estado
ClienteController	✅ Completado
ProductoController	✅ Completado
HistorialClienteController	✅ Completado
PagoController	✅ Completado
PedidoController	✅ Completado
SucursalController	✅ Completado
UsuarioController	✅ Completado

🗄 Configuración de la Base de Datos (H2)
URL: jdbc:h2:mem:testdb

Usuario: sa

Contraseña: (dejar en blanco)

Consola: http://localhost:8080/h2-console

📌 Observaciones
Solo se aceptan productos tipo “perfume”.

Los totales se calculan automáticamente al crear pedidos y pagos.

Las relaciones entre entidades están implementadas correctamente usando JPA:

Cliente ↔️ Sucursal (@ManyToOne)

Usuario ↔️ Sucursal (@ManyToOne)

Pedido ↔️ Cliente / Producto

Pago ↔️ Pedido / Cliente / Producto

HistorialCliente ↔️ Cliente (@OneToOne)

👨‍💻 Autores
Proyecto académico desarrollado por:

Cristóbal Segovia

Felipe Espinoza

Bastian Sepúlveda

