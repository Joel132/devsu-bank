# DEVSU BANK


API Rest para realizar movimientos de DEBITO o CREDITO en una cuenta bancaria.

La aplicacion esta escrita en Spring boot usando SPRING JPA.

## Configuracion

Las siguientes configuraciones deben ser cambiadas en orden a poder ejecutar correctamente la aplicacion:

- `spring.jpa.properties.hibernate.default_schema`: si se cuenta con un esquema en donde se albergara las tablas
- `spring.datasource.url`: la url de la bd
- `spring.datasource.username`: el usuario con el que cual se conectara a la bd
- `spring.datasource.password`: el password del usuario
- `devsubank.limite-diario-retiro`: donde se establece el limite que se podra retirar tener diariamente
- `devsubank.secret`: secret salt para cifrar las contrasenas

Estas propiedades se pueden colocar como enviroment o cambiarlas directamente en el [application.yml](config/application.yaml)

Se provee un script para la creacion de tablas para el SGBD Postgresql [BaseDatos.sql](BaseDatos.sql). El script no incluye sentencias para gestionar permisos a las tablas

Se provee un postman collection para probar los servicios


## Servicios

Se cuenta con los siguientes servicios:

### Clientes

Entidad que representa la información básica y personal de un cliente.

- Agregar Cliente: `POST /clientes`
    
    Restricciones:
    
    - Tipo de documento y numero de documento deben ser únicos
- Actualizar Cliente: `PUT /clientes/{cliente_id_bd}`
- Editar Cliente: `PATCH /clientes/{cliente_id_bd}`
- Eliminar Cliente: `DELETE /clientes/{cliente_id_bd}`
    
    Restricciones
    
    - Solo se permite eliminar clientes que no posean una cuenta

### Cuentas

Entidad que representa la información de una cuenta, su tipo y un saldo inicial.

- Agregar Cuenta a un Cliente: `POST /clientes/{cliente_id_bd}/cuentas`
    
    Restricciones:
    
    - Si un cliente se encuentra inactivo no podrá agregar cuentas nuevas
- Actualizar cuenta: `PATCH /cuentas/{cuenta_id}`
    
    Restricciones:
    
    - Solo se puede modificar el tipo de cuenta, el número de cuenta y el estado.
- Eliminar cuenta: `DELETE /cuentas/{cuenta_id}`
    
    Restricciones:
    
    - Solo se permite eliminar cuentas que no posean un movimiento

### Movimientos

Entidad que representa los movimientos de una cuenta. Hay de dos tipos CREDITO o DEBITO

- Agregar Movimiento a una cuenta: `POST /cuentas/{cuenta_id}/movimientos`
    
    Restricciones
    
    - No se podran agregar movimientos nuevos si la cuenta o el cliente de la cuenta se encuentra inactivo
    - Movimiento de tipo débito no puede exceder límite maximo diario
    - Movimiento de tipo debito no puede exceder el saldo de la cuenta
    - Solo se pueden agregar movimientos que sean mayor a la fecha del ultimo movimiento
- Actualizar Movimiento: `PATCH /movimiento/{movimiento_id}`
    
    Restricciones
    
    - No se podran agregar movimientos nuevos si la cuenta o el cliente de la cuenta se encuentra inactivo
    - Solo se puede actualizar el tipo de movimiento o el valor, no asi su fecha
    - Movimiento de tipo debito no puede exceder limite maximo diario
    - Movimiento de tipo debito no puede exceder el saldo de la cuenta
- Eliminar movimiento: `DELETE /movimiento/{movimiento_id}`
    
    Restricciones
    
    - Solo se puede eliminar el ultimo movimiento de una cuenta

### Reportes

Servicio para generar extracto de los movimientos realizado en las cuentas de un cliente en un rango de fecha

- Generar reporte: `GET /reportes/{cliente_id_bd}?to={fecha_inicial}&t1={fecha_final}`
    
    El parametro `fecha_final` es opcional, si no especifica, solo se mostraran registros de la `fecha_inicial`
    
    Ambos parametros son inclusivos

#