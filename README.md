# 🎮 Agente Gamer

**Asistente financiero para gestionar gastos en videojuegos**

Agente Gamer es una aplicación Android desarrollada en **Java** bajo arquitectura **Clean Architecture**, diseñada para ayudar a los jugadores a gestionar su presupuesto mensual en videojuegos, registrar gastos, buscar juegos en una base de datos externa y recibir recomendaciones financieras personalizadas.

---

## 📋 Descripción del proyecto

Agente Gamer surge como una solución integral para gamers que quieren controlar su spending en videojuegos. La aplicación permite:

- **Gestionar presupuesto mensual** establecido por el usuario
- **Registrar gastos** en compras de videojuegos (físicos, digitales, DLCs, suscripciones)
- **Explorar un catálogo** de juegos mediante la API de RAWG.io
- **Crear una wishlist** personalizada de juegos deseados
- **Visualizar próximos lanzamientos** y recibir notificaciones
- **Recibir recomendaciones financieras** basadas en el presupuesto disponible
- **Sincronizar datos** en la nube con Firebase Firestore
- **Autenticarse** con Firebase Auth (email/password)

El "agente" interno analiza el presupuesto y los gastos para proporcionar recomendaciones como: "Si compras este juego, excederás tu presupuesto en X€" o "Has gastado el 80% del presupuesto este mes".

---

## ✨ Características implementadas

### Autenticación y Usuario
- ✅ Registro de usuario con email/password
- ✅ Inicio de sesión con Firebase Auth
- ✅ Perfil de usuario editable (nombre, avatar, presupuesto mensual)
- ✅ Cerrar sesión

### Gestión Financiera
- ✅ CRUD completo de gastos (crear, leer, actualizar, eliminar)
- ✅ Sistema de presupuesto mensual configurable
- ✅ Dashboard con gráfico circular de gastos (MPAndroidChart)
- ✅ Agente financiero con recomendaciones personalizadas
- ✅ Indicadores de estado del presupuesto (% gastado)

### Catálogo de Juegos
- ✅ Búsqueda de juegos via API RAWG.io
- ✅ Lista de juegos con imagen, fecha de lanzamiento y precio estimado
- ✅ Vista detallada de información del juego
- ✅ Wishlist personal (añadir/quitar juegos)

### Próximos Lanzamientos
- ✅ Listado de juegos próximos a lanzar
- ✅ Notificaciones automáticas cuando un juego de la wishlist está por salir
- ✅ Información de fecha exacta y precio estimado

### Sistema de Notificaciones
- ✅ Notificaciones de Recordatorio de presupuesto (WorkManager)
- ✅ Notificaciones de Próximos lanzamientos (WorkManager)
- ✅ Alertas cuando se alcanza el 80% del presupuesto

### Datos y Sincronización
- ✅ Persistencia local con Room Database
- ✅ Sincronización en la nube con Firestore
- ✅ Soporte multiusuario

---

## 🛠 Tecnologías utilizadas

| Tecnología / Librería | Versión | Uso |
|----------------------|---------|-----|
| **Java** | 17 | Lenguaje de programación |
| **Android Studio** | Ladybug | IDE de desarrollo |
| **Room** | 2.6.1 | Base de datos local |
| **Hilt / Dagger** | 2.50 | Inyección de dependencias |
| **Firebase Auth** | 22.3.1 | Autenticación de usuarios |
| **Firebase Firestore** | 24.10.0 | Base de datos en la nube |
| **Retrofit** | 2.9.0 | Cliente HTTP para APIs |
| **Gson** | 2.10.1 | Serialización JSON |
| **MPAndroidChart** | 3.1.0 | Gráficos financieros |
| **WorkManager** | 2.9.0 | Tareas en background |
| **Material Components** | 1.11.0 | Componentes UI |
| **ViewModel / LiveData** | 2.7.0 | Ciclo de vida y reactividad |
| **Glide** | 4.16.0 | Carga de imágenes |

---

## 🧱 Arquitectura del proyecto

El proyecto sigue el patrón **Clean Architecture** con separación clara de responsabilidades:

```
com.miapp.agentegamer/
│
├── data/                    # Capa de datos
│   ├── local/
│   │   ├── dao/            # Interfaces DAO (Room)
│   │   ├── entity/         # Entidades Room
│   │   └── database/       # AppDatabase
│   ├── remote/
│   │   ├── api/           # Interfaces Retrofit
│   │   ├── model/         # Modelos de respuesta API
│   │   └── dto/           # DTOs para mapeo
│   └── repository/        # Implementaciones de repositorios
│
├── domain/                  # Capa de dominio
│   ├── model/              # Modelos de dominio (Gasto, GameInfo, Usuario)
│   ├── repository/        # Interfaces de repositorios
│   └── usecase/           # Casos de uso (lógica de negocio)
│
├── di/                      # Módulos de inyección (Hilt)
│   ├── FirebaseModule.java
│   ├── DatabaseModule.java
│   ├── NetworkModule.java
│   └── RepositoryModule.java
│
├── ui/                      # Capa de presentación
│   ├── main/               # MainActivity y navegación
│   ├── auth/               # Login y Registro
│   ├── perfil/             # Perfil y edición de perfil
│   ├── ajustes/            # Configuración de la app
│   ├── games/              # Búsqueda y lista de juegos
│   ├── gastos/             # Lista y gestión de gastos
│   ├── wishlist/           # Wishlist del usuario
│   ├── lanzamientos/       # Próximos lanzamientos
│   ├── viewmodel/          # ViewModels (MVVM)
│   ├── worker/             # WorkManagers para notificaciones
│   └── dialogs/            # Diálogos reutilizables
│
└── util/                    # Utilidades varias
    ├── MoneyUtils.java     # Formateo de dinero
    ├── FechaUtils.java     # Fechas y formatos
    ├── PlatformUtils.java  # Plataformas de juegos
    └── Constants.java      # Constantes globales
```

### Flujo de datos

```
UI (Activities/Adapters)
    ↓↑
ViewModels (LiveData)
    ↓↑
UseCases (Lógica de negocio)
    ↓↑
Repository (Interfaces)
    ↓↑
Data Sources (Room / Firestore / API RAWG)
```

---

## ⚠️ Requisitos previos

Para que la aplicación funcione correctamente, necesitas configurar los siguientes servicios externos:

### 1. Firebase Console

1. Ve a [Firebase Console](https://console.firebase.google.com/)
2. Crea un nuevo proyecto
3. Habilita **Authentication**:
   - Proveedor: Email/Password
4. Habilita **Firestore Database**:
   - Crea la base de datos en modo prueba (o configura reglas)
5. Descarga el archivo `google-services.json`

### 2. Google Services JSON

El archivo `google-services.json` debe colocarse en:
```
app/google-services.json
```

Este archivo contiene las credenciales de tu proyecto Firebase.

### 3. API Key de RAWG.io

1. Regístrate en [RAWG.io](https://rawg.io/apidocs)
2. Obtén tu API Key gratuita
3. Añádela al archivo `gradle.properties` (ver abajo)

---

## 🚀 Pasos de instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/javilesaca/AgenteGamer-App.git
cd AgenteGamer
```

### 2. Configurar Firebase

- Descarga `google-services.json` desde Firebase Console
- Colócalo en: `app/google-services.json`

### 3. Configurar API Key de RAWG

Crea o edita el archivo `gradle.properties` en la raíz del proyecto:

```properties
# Configuración de la API de RAWG.io
RAWG_API_KEY=tu_api_key_aqui

# Configuración adicional (opcional)
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
android.useAndroidX=true
android.nonTransitiveRClass=true
```

> **Nota**: Reemplaza `tu_api_key_aqui` con tu clave API de RAWG.io

### 4. Abrir en Android Studio

1. Abre Android Studio
2. File → Open →Selecciona la carpeta del proyecto
3. Espera a que se sincronice Gradle

### 5. Compilar el proyecto

```bash
Build → Rebuild Project
```

O desde terminal:
```bash
./gradlew assembleDebug
```

### 6. Ejecutar

- Conecta un dispositivo/emulador
- Run → Run 'app'

---

## 📱 Requisitos del sistema

- **Android SDK**: 34 (Android 14)
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34
- **Java**: JDK 17

---

## 🔧 Estructura de la base de datos

### Room Entities

- **Gasto**: id, nombre, precio, plataforma, fecha, categoria, sincronizado
- **Usuario**: id, nombre, email, presupuestoMensual, avatarUrl
- **WishlistItem**: id, gameId, gameName, fechaAgregado
- **GameCache**: id, gameId, name, backgroundImage, released, addedDate

---

## 📄 Notas para el evaluador

### Configuración de Firestore (importante)

Para que la app pueda escribir en Firestore, usa estas reglas de prueba:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /usuarios/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
    match /gastos/{gastoId} {
      allow read, write: if request.auth != null;
    }
    match /wishlist/{itemId} {
      allow read, write: if request.auth != null;
    }
  }
}
```

### Solución de problemas comunes

- **Error "Default FirebaseApp not initialized"**: Verifica que `google-services.json` esté en la ubicación correcta
- **Error 401 en RAWG**: Verifica que `RAWG_API_KEY` esté configurada en `gradle.properties`
- **Error de compilación**: Asegúrate de usar JDK 17 y tener las variables de entorno correctas

---

## 📝 Licencia

Este proyecto se distribuye bajo licencia **MIT**.

---

## 👤 Autor

**Javier Lesaca Medina**

Ciclo Formativo de Grado Superior SAFA — Desarrollo de Aplicaciones Multiplataforma (DAM)

Proyecto Final de Grado — Agente Gamer

---

## 🙏 Agradecimientos

- [RAWG.io](https://rawg.io/) - Base de datos de videojuegos
- [Firebase](https://firebase.google.com/) - Backend como servicio
- [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) - Gráficos
- Comunidad de código abierto

---

*Última actualización: Marzo 2026*
