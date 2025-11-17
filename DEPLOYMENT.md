# 🚀 Guía de Despliegue - Sistema de Reservas de Gimnasio

## 📋 Requisitos Previos

### Para Desarrollo Local
- **Backend**: Java 17+, Gradle 8.x, MySQL 8.0+ (opcional)
- **Frontend**: Node.js 20+, npm 10+

### Para Despliegue en Render
- Cuenta en [Render](https://render.com)
- Repositorio en GitHub
- Docker (Render lo usa automáticamente)

## 🗄️ Base de Datos: H2 en Memoria

Este proyecto usa **H2 Database en memoria** para producción:

**Ventajas:**
- ✅ No necesitas configurar base de datos externa
- ✅ Despliegue súper simple
- ✅ Gratis (no costos adicionales)
- ✅ Ideal para demos y prototipos

**Desventajas:**
- ⚠️ Los datos se pierden al reiniciar
- ⚠️ No recomendado para producción real con datos permanentes

> **Nota**: Si necesitas persistencia, puedes migrar a MySQL, PostgreSQL o Railway fácilmente cambiando solo la variable `DATABASE_URL`.

---

## 🐳 Despliegue del Backend en Render (con Docker)

### Paso 1: Preparar el Repositorio

Asegúrate de que tu proyecto tenga estos archivos (ya están creados):
- ✅ `gimnasioreserva-spring/Dockerfile`
- ✅ `gimnasioreserva-spring/.dockerignore`
- ✅ `.github/workflows/ci.yml` (para CI/CD)

### Paso 2: Crear Web Service en Render

1. Ve a [Render Dashboard](https://dashboard.render.com/)
2. Click en **"New +"** → **"Web Service"**
3. Conecta tu repositorio de GitHub
4. Configura el servicio:

   ```
   Name: gimnasio-backend
   Region: Oregon (US West) o el más cercano
   Branch: master (o main)
   Root Directory: gimnasioreserva-spring
   Runtime: Docker
   ```

5. Render detectará automáticamente el `Dockerfile`

### Paso 3: Configurar Variables de Entorno

En la sección **"Environment Variables"**, agrega:

```bash
# ============================================
# BASE DE DATOS H2 (EN MEMORIA)
# ============================================
DATABASE_URL=jdbc:h2:mem:gimnasio;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
DATABASE_USERNAME=sa
DATABASE_PASSWORD=

# ============================================
# JWT (IMPORTANTE: Generar un secret seguro)
# ============================================
JWT_SECRET=<GENERAR_UNO_NUEVO_Y_SEGURO>
JWT_EXPIRATION=3600000
JWT_REFRESH_EXPIRATION=86400000

# ============================================
# CORS (Actualizar después de desplegar frontend)
# ============================================
CORS_ALLOWED_ORIGINS=https://tu-frontend.onrender.com

# ============================================
# CONFIGURACIÓN DE PRODUCCIÓN
# ============================================
DDL_AUTO=create-drop
SQL_INIT_MODE=always
SHOW_SQL=false
FORMAT_SQL=false
LOG_SQL_LEVEL=WARN
LOG_BINDER_LEVEL=WARN
LOG_ROOT_LEVEL=INFO
```

### Paso 4: Generar JWT Secret Seguro

**En Linux/Mac:**
```bash
openssl rand -base64 64
```

**En Windows PowerShell:**
```powershell
$bytes = New-Object byte[] 64
(New-Object Security.Cryptography.RNGCryptoServiceProvider).GetBytes($bytes)
[Convert]::ToBase64String($bytes)
```

Copia el resultado y úsalo como `JWT_SECRET`.

### Paso 5: Desplegar

1. Click en **"Create Web Service"**
2. Render construirá automáticamente la imagen Docker
3. Espera 5-10 minutos para el primer despliegue
4. Obtendrás una URL como: `https://gimnasio-backend.onrender.com`

### Paso 6: Verificar el Backend

Visita estas URLs para confirmar que funciona:

```
https://tu-backend.onrender.com/swagger-ui.html
https://tu-backend.onrender.com/api/auth/login (POST)
```

---

## ⚛️ Despliegue del Frontend en Render

### Paso 1: Crear Static Site en Render

1. En Render Dashboard → **"New +"** → **"Static Site"**
2. Conecta tu repositorio de GitHub
3. Configura:

   ```
   Name: gimnasio-frontend
   Branch: master (o main)
   Root Directory: Frontend-gimnasio
   Build Command: npm install && npm run build
   Publish Directory: dist
   ```

### Paso 2: Configurar Variable de Entorno

En **"Environment Variables"**:

```bash
VITE_API_BASE_URL=https://tu-backend.onrender.com/api
```

> **Importante**: Reemplaza `tu-backend.onrender.com` con la URL real de tu backend.

### Paso 3: Desplegar

1. Click en **"Create Static Site"**
2. Render construirá y desplegará tu frontend
3. Obtendrás una URL como: `https://gimnasio-frontend.onrender.com`

### Paso 4: Actualizar CORS en el Backend

1. Ve al servicio del backend en Render
2. Actualiza la variable `CORS_ALLOWED_ORIGINS`:
   ```
   CORS_ALLOWED_ORIGINS=https://gimnasio-frontend.onrender.com
   ```
3. Guarda y espera a que se redespliegue automáticamente

---

## 🧪 Verificar que Todo Funciona

### Backend
- [ ] Swagger UI funciona: `https://tu-backend.onrender.com/swagger-ui.html`
- [ ] Health check: `https://tu-backend.onrender.com/actuator/health` (si lo configuraste)

### Frontend
- [ ] La página carga: `https://tu-frontend.onrender.com`
- [ ] Puedes hacer login con: `admin@gimnasio.com` / `admin123`
- [ ] No hay errores CORS en la consola del navegador

---

## 🔐 Seguridad - Usuario Admin

El sistema crea automáticamente un usuario admin:

```
Email: admin@gimnasio.com
Password: admin123
```

**⚠️ MUY IMPORTANTE**:
1. Inicia sesión inmediatamente después del despliegue
2. Ve a configuración de perfil
3. **Cambia la contraseña del admin**

---

## 🐛 Troubleshooting

### Backend no inicia

**Error:** "Application failed to start"

**Solución:**
1. Revisa los logs en Render Dashboard
2. Verifica que todas las variables de entorno estén configuradas
3. Asegúrate de que `JWT_SECRET` sea suficientemente largo (mínimo 32 caracteres)

### Frontend no conecta con Backend

**Error:** CORS policy blocking requests

**Solución:**
1. Verifica que `CORS_ALLOWED_ORIGINS` tenga la URL exacta del frontend
2. No incluyas trailing slash: ✅ `https://app.com` ❌ `https://app.com/`
3. Si usas dominio custom, actualiza CORS también

### El servicio se "duerme" (Free Tier)

**Problema:** Primera petición tarda 30-60 segundos

**Explicación:** El free tier de Render duerme los servicios después de 15 minutos de inactividad.

**Soluciones:**
- Esperar pacientemente en la primera carga
- Usar un servicio de ping (UptimeRobot, Cron-job.org)
- Actualizar a plan de pago ($7/mes)

### Los datos desaparecen

**Explicación:** H2 en memoria pierde datos al reiniciar.

**Solución si necesitas persistencia:**
1. Configura MySQL externo (Railway, PlanetScale)
2. Actualiza `DATABASE_URL` a tu MySQL
3. Cambia `DDL_AUTO=validate` o `update`

---

## 🔄 CI/CD con GitHub Actions

El proyecto ya tiene configurado CI/CD automático:

### Qué hace el pipeline

Cada vez que haces `git push`:
1. ✅ Ejecuta todos los tests del backend (con H2)
2. ✅ Construye el JAR
3. ✅ Ejecuta linter del frontend
4. ✅ Construye el bundle de producción
5. ✅ Reporta si algo falla

### Ver resultados

1. Ve a tu repositorio en GitHub
2. Click en la pestaña **"Actions"**
3. Verás el historial de builds

---

## 📊 Monitoreo y Logs

### Ver logs en tiempo real

En Render Dashboard:
1. Selecciona tu servicio
2. Ve a la pestaña **"Logs"**
3. Verás los logs en vivo

### Logs importantes a revisar

```bash
# Backend iniciando
Started GimnasioreservaSpringApplication in X seconds

# Base de datos H2 conectada
HikariPool-1 - Starting...

# Usuario admin creado
INSERT INTO usuarios (nombre, correo, ...)
```

---

## 🚀 Actualizaciones y Redeploys

### Despliegue automático

Render redesplega automáticamente cuando:
- Haces push a la rama `master`/`main`
- Cambias variables de entorno
- Cambias el Dockerfile

### Despliegue manual

En Render Dashboard:
1. Selecciona tu servicio
2. Click en **"Manual Deploy"** → **"Deploy latest commit"**

---

## 💡 Consejos y Mejores Prácticas

### Para Producción Real

Si este proyecto va a producción real:

1. **Base de datos persistente**
   - Usa MySQL, PostgreSQL o Railway
   - Haz backups regulares

2. **Dominio custom**
   - Configura un dominio propio
   - Actualiza `CORS_ALLOWED_ORIGINS`

3. **Monitoreo**
   - Configura alertas en Render
   - Usa servicios como Sentry para errores

4. **Seguridad**
   - Cambia todas las contraseñas por defecto
   - Usa HTTPS siempre (Render lo hace automático)
   - Rota el `JWT_SECRET` periódicamente

### Para Desarrollo

```bash
# Backend local con MySQL
cd gimnasioreserva-spring
./gradlew bootRun

# Backend local con H2 (para probar)
DATABASE_URL=jdbc:h2:mem:test ./gradlew bootRun

# Frontend local
cd Frontend-gimnasio
npm run dev
```

---

## 📞 Soporte

Si encuentras problemas:

1. Revisa los logs en Render Dashboard
2. Verifica que todas las variables de entorno estén configuradas
3. Consulta la [documentación de Render](https://render.com/docs)
4. Revisa el `CHANGELOG_REVIEW.md` para ver todos los cambios realizados

---

## 📝 Checklist de Despliegue

Antes de desplegar, verifica:

- [ ] Código actualizado en GitHub
- [ ] Tests pasando (GitHub Actions verde ✅)
- [ ] Dockerfile presente en `gimnasioreserva-spring/`
- [ ] Variables de entorno configuradas en Render
- [ ] `JWT_SECRET` generado y seguro
- [ ] `CORS_ALLOWED_ORIGINS` configurado correctamente
- [ ] Frontend apuntando a la URL correcta del backend

¡Listo para desplegar! 🎉
