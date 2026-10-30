# control-gastos: librería de finanzas en Kotlin

Librería en **Kotlin puro** (sin Android) con la lógica de la app
[Mis Finanzas](https://github.com/RobertoLeiva15/ControlGastosApp).

Proyecto del curso **Programación 2**, Universidad Mariano Gálvez de Guatemala.

## Paquetes

| Paquete | Clases | Para qué |
|---|---|---|
| `modelo` | `Movimiento`, `TipoMovimiento`, `Categoria`, `ResumenMes`, `NivelAlerta` | Los datos |
| `datos` | `MovimientoRepository` (interface), `MovimientoRepositoryEnMemoria` | Guardar, listar, actualizar y eliminar |
| `servicio` | `AdministradorFinanzas`, `Fechas` | Reglas: validar, totales, saldo, control por mes |
| `biblico` | `Versiculo`, `GeneradorVersiculos` | Versículos sin repetir el anterior |

## Reglas principales

- **Saldo** = ingresos − egresos
- **Alerta**: cuidado al llegar al 80 % de los ingresos; excedido si los egresos los superan
- **Validación**: descripción no vacía, monto mayor a 0, categoría del tipo correcto y fecha `yyyy-MM-dd`
- **Control mensual**: cada movimiento pertenece al mes de su fecha

## Tecnologías

Kotlin 2.1.20 · Java 11 · Maven · funciones `suspend` (corrutinas)

## Instalación

```
mvn clean install
```

Coordenadas: `edu.umg.controlgastos:control-gastos:1.0.0`

## Autor

**José Roberto Leiva Salazar**

**Carnet: 0905-25-11678**
