# Instrucciones para Crear Usuario Administrador

## Opción 1: Crear Admin desde la Base de Datos (Primer Admin)

Si no tienes ningún usuario admin aún, puedes crear el primero directamente en la base de datos:

```sql
INSERT INTO usuarios (nombre, correo, contrasena, rol, activo)
VALUES ('Admin Principal', 'admin@gimnasio.com', '$2a$10$TuHashAqui', 'ADMIN', true);
```

**Nota:** Necesitas generar el hash de la contraseña usando BCrypt. Puedes usar un generador online o crear un usuario normal primero y luego cambiar su rol.

## Opción 2: Crear Admin desde la Aplicación (Requiere ser Admin)

1. Inicia sesión como administrador
2. Ve al Dashboard
3. Haz clic en "Crear Usuario Admin" en las Acciones Rápidas
4. Completa el formulario:
   - Selecciona "Administrador" en el campo Rol
   - Ingresa nombre, correo y contraseña
   - Confirma la contraseña
5. Haz clic en "Crear Usuario"

## Opción 3: Cambiar Rol de Usuario Existente (Requiere ser Admin)

1. Inicia sesión como administrador
2. Ve al Dashboard
3. Accede a la gestión de usuarios (si está disponible)
4. Selecciona un usuario y cambia su rol a "ADMIN"

## Reservar Clases para Otros Usuarios (Solo Admin)

1. Inicia sesión como administrador
2. Ve al Dashboard
3. Haz clic en "Reservar Clase para Usuario" en las Acciones Rápidas
4. En el panel izquierdo, busca y selecciona el usuario
5. En el panel derecho, selecciona la clase que deseas reservar
6. Confirma la reserva

## Notas Importantes

- Solo los usuarios con rol "ADMIN" pueden crear otros administradores
- Solo los administradores pueden reservar clases para otros usuarios
- Los usuarios normales solo pueden reservar clases para sí mismos
- El primer admin debe crearse manualmente en la base de datos o usando un script de inicialización

