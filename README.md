# UI Routing

## URLs Base

|  URL  |                     Contenido                      | Requerimiento |
|:-----:|:--------------------------------------------------:|:-------------:|
| **/** | Sí tiene sesión, redirige a **/home**, si no **/** |       -       |

## URLs ANONIMAS

|      URL       |            Contenido            | Requerimiento |
|:--------------:|:-------------------------------:|:-------------:|
|     **/**      |            Propósito            |       1       |
|     **/**      | Link a **/anonimo/colecciones** |       1       |
|     **/**      | Link a **/anonimo/hecho/crear** |       1       |
|     **/**      |   Link a **/doc/legales.pdf**   |       1       |
|     **/**      |            Ejemplos             |       1       |
|   **/login**   |          Form de Login          |       5       |
| **/registrar** |        Form de Registro         |       4       |

|             URL             |                Contenido                | Requerimiento |
|:---------------------------:|:---------------------------------------:|:-------------:|
|  **/anonimo/colecciones**   |           Mostrar colecciones           |      2,3      |
| **/anonimo/coleccion/{id}** |         Tabla de Hechos y Mapa          |      2,3      |
| **/anonimo/coleccion/{id}** |        Tabla de Hechos (Filtros)        |       7       |
| **/anonimo/coleccion/{id}** |              Detalle Hecho              |       9       |
| **/anonimo/coleccion/{id}** | Tabla de Hechos (Solicitud Eliminacion) |      10       |
| **/anonimo/coleccion/{id}** |        Tabla de Hechos (Filtros)        |       7       |

## URLs Protegidas (con autenticación)

|    URL    |                         Contenido                          | Requerimiento |
|:---------:|:----------------------------------------------------------:|:-------------:|
| **/home** | Redirige a **/admin/home**, si no **/sonctribuyente/home** |       -       |

## URLs Contribyente (ROL_CONTRIBUYENTE)

|                         URL                          |             Contenido              | Requerimiento |
|:----------------------------------------------------:|:----------------------------------:|:-------------:|
|            **GET /contribuyente/hecho/**             |        Crear un hecho nuevo        |      11       |
|            **POST /contribuyente/hecho/**            |        Crear un hecho nuevo        |      11       |
|          **GET /contribuyente/hecho/{id}**           |          Editar un hecho           |      12       |
|          **PUT /contribuyente/hecho/{id}**           |          Editar un hecho           |      12       |
| **POST /contribuyente/hecho/{id}/solicitarEliminar** | Crear una Solicitud de Eliminación |      13       |

## URLs Administrador (ROL_ADMIN)

    TODO

# Auth Routing

|            URL             |  Contenido   |     ROL     |
|:--------------------------:|:------------:|:-----------:|
|     **POST /api/auth**     | AccessToken  |      -      |
| **POST /api/auth/refresh** |   Refresca   |      -      |
|       **POST /user**       | Crea Usuario |      -      |
|    **GET /user/roles**     |   Refresca   | Autenticado |

# Agregador

- Agregar filtro de JWT.
- Compartir KEY entre Agregador y Auth.

|                    URL                    |      Contenido       | ROL |
|:-----------------------------------------:|:--------------------:|:---:|
|           **GET /colecciones**            |     Colecciones      |  -  |
| **GET /colecciones/{coleccionId}/hechos** | Hechos por Coleccion |  -  |

### Agregador (ToDO)

|       URL        |    Contenido    |        ROL        |
|:----------------:|:---------------:|:-----------------:|
| **POST /hechos** | Crear un Hecho  | ROL_CONTRIBUYENTE |
| **PUT /hechos**  | Editar un Hecho | ROL_CONTRIBUYENTE |
