# MediHome

Sistema de consola en Java para representar la programación y gestión de servicios de atención médica domiciliaria. El proyecto implementa el modelo de clases elaborado para el ejercicio de Diseño de Software y demuestra el ciclo de una atención desde su programación hasta la generación del reporte.

## Contenido

- [Descripción](#descripción)
- [Funcionalidades](#funcionalidades)
- [Modelo de clases](#modelo-de-clases)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Requisitos](#requisitos)
- [Compilar y ejecutar](#compilar-y-ejecutar)
- [Ejemplo de ejecución](#ejemplo-de-ejecución)
- [Diagrama de clases](#diagrama-de-clases)
- [Autor](#autor)

## Descripción

MediHome modela una empresa que presta servicios médicos domiciliarios. El programa de ejemplo crea un paciente, un profesional de salud, un equipo de atención, un servicio domiciliario, una atención médica y una medición de signos vitales. Después de completar la atención, muestra en la consola un reporte con la información registrada.

Este es un ejercicio académico orientado a objetos. Los datos se crean en memoria durante la ejecución; el proyecto no utiliza base de datos ni servicios externos.

## Funcionalidades

- Representar pacientes y profesionales como usuarios del sistema.
- Enviar notificaciones de consola a pacientes y profesionales.
- Asociar profesionales a equipos de atención y permitir su cambio de equipo.
- Programar, asignar, iniciar, finalizar o cancelar un servicio domiciliario.
- Comprobar la disponibilidad de un profesional para la fecha programada.
- Registrar observaciones, recomendaciones y una o varias mediciones de signos vitales durante una atención.
- Generar un reporte de la atención prestada al paciente.

Los estados definidos para un servicio son: `solicitado`, `programado`, `en atención`, `finalizado` y `cancelado`.

## Modelo de clases

| Clase o interfaz | Responsabilidad |
| --- | --- |
| `Usuario` | Datos comunes de identificación, nombre y correo. |
| `Paciente` | Datos de contacto y notificaciones del paciente. Hereda de `Usuario`. |
| `ProfesionalSalud` | Registro profesional, especialidad, equipo y disponibilidad. Hereda de `Usuario`. |
| `INotificable` | Contrato para los objetos que pueden recibir notificaciones. |
| `EquipoAtencion` | Información del equipo y gestión de los profesionales asociados. |
| `ServicioDomiciliario` | Datos, estado y ciclo de vida del servicio; genera el reporte de atención. |
| `AtencionMedica` | Fechas, observaciones, recomendaciones y mediciones registradas. |
| `MedicionSignosVitales` | Fecha y valores de signos vitales de una medición. |
| `Main` | Crea los objetos de ejemplo y ejecuta el flujo demostrativo. |

Una atención está asociada a un servicio domiciliario. Cada atención puede contener cero o varias mediciones de signos vitales.

## Estructura del proyecto

```text
MediHome/
├── src/
│   ├── AtencionMedica.java
│   ├── EquipoAtencion.java
│   ├── INotificable.java
│   ├── Main.java
│   ├── MedicionSignosVitales.java
│   ├── Paciente.java
│   ├── ProfesionalSalud.java
│   ├── ServicioDomiciliario.java
│   └── Usuario.java
├── Class Diagram1.png
├── MediHome.vpp
└── README.md
```

## Requisitos

- Java Development Kit (JDK) 11 o superior.
- Terminal o entorno de desarrollo compatible con Java. El proyecto también puede abrirse en IntelliJ IDEA.

## Compilar y ejecutar

Desde la carpeta raíz del proyecto, ejecuta:

```powershell
New-Item -ItemType Directory -Force out
javac -encoding UTF-8 -d out src\*.java
java -cp out Main
```

La clase de entrada es `Main`, ubicada en `src/Main.java`.

## Ejemplo de ejecución

Al ejecutar el programa se crea un servicio domiciliario de ejemplo, se registra una medición de signos vitales, se finaliza la atención y se imprime un reporte con:

- Código y estado del servicio.
- Identificación y nombre del paciente.
- Nombre y especialidad del profesional.
- Fecha programada, dirección y motivo.
- Horas de inicio y finalización de la atención.
- Observaciones clínicas y recomendaciones.
- Mediciones registradas, incluyendo temperatura, frecuencia cardíaca, presión arterial y saturación de oxígeno.

Los datos del ejemplo están definidos en `Main`; se pueden cambiar allí para probar otros valores.

## Diagrama de clases

El diagrama exportado como imagen:

![Diagrama de clases de MediHome](./Class%20Diagram1.png)

El archivo editable del proyecto de Visual Paradigm:

[Descargar o abrir el fuente del diagrama: MediHome.vpp](./MediHome.vpp)

Para visualizar o editar el `.vpp` se requiere Visual Paradigm.

## Autor

- **Cristhian Santiago Parra Erazo**
- **ID:** 926222
