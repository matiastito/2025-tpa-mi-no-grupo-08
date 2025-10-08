## URLs Base

|  URL  |                         Contenido                         | Requerimiento |
|:-----:|:---------------------------------------------------------:|:-------------:|
| **/** | Sí tiene sesión, redirige a **/home**, si no **/landing** |       -       |

## URLs ANONIMAS

|     URL      |            Contenido            | Requerimiento |
|:------------:|:-------------------------------:|:-------------:|
| **/landing** |            Propósito            |       1       |
| **/landing** |              Login              |       5       |
| **/landing** |            Registro             |       4       |
| **/landing** |            Ejemplos             |       1       |
| **/landing** | Link a **/anonimo/colecciones** |       1       |
| **/legales** |   Link a **/doc/legales.pdf**   |       1       |

|             URL             |                Contenido                | Requerimiento |
|:---------------------------:|:---------------------------------------:|:-------------:|
|  **/anonimo/colecciones**   |           Mostrar colecciones           |      2,3      |
| **/anonimo/coleccion/{id}** |         Tabla de Hechos y Mapa          |      2,3      |
| **/anonimo/coleccion/{id}** |        Tabla de Hechos (Filtros)        |       7       |
| **/anonimo/coleccion/{id}** |              Detalle Hecho              |       9       |
| **/anonimo/coleccion/{id}** | Tabla de Hechos (Solicitud Eliminacion) |      10       |
| **/anonimo/coleccion/{id}** |        Tabla de Hechos (Filtros)        |       7       |
| **/anonimo/coleccion/{id}** |              Detalle Hecho              |       9       |
| **/anonimo/coleccion/{id}** | Tabla de Hechos (Solicitud Eliminacion) |      10       |

## URLs Protegidas (con autenticación)

|    URL    |                         Contenido                          | Requerimiento |
|:---------:|:----------------------------------------------------------:|:-------------:|
| **/home** | Redirige a **/admin/home**, si no **/sonctribuyente/home** |       -       |

## URLs Contribyente (ROL_CONTRIBUYENTE)

|                       URL                       |             Contenido              | Requerimiento |
|:-----------------------------------------------:|:----------------------------------:|:-------------:|
|            **/contribuyente/hecho/**            |        Crear un hecho nuevo        |      11       |
|          **/contribuyente/hecho/{id}**          |          Editar un hecho           |      12       |
| **/contribuyente/hecho/{id}/solicitarEliminar** | Crear una Solicitud de Eliminación |      13       |
