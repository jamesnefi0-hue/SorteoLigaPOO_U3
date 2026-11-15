# Sorteo de equipos para una liga profesional (POO – Unidad 3)

**Autor:** James Nefi Valenzuela Zuñiga — Carrera de Software, Universidad Politécnica Salesiana

Aplicación de consola en Java que sortea los partidos de una liga de 16 equipos
(octavos, cuartos, semifinales y final) usando **MVC**, **excepciones propias**,
**sorteo recursivo** y **serialización** (`torneo.ser`).

## Estructura (MVC)

```
src/
├── app/Main.java                      -> arranque: une Vista, Modelo y Controlador
├── modelo/                            -> MODELO (datos, reglas, sorteo, persistencia)
│   ├── Equipo.java
│   ├── Partido.java
│   ├── Etapa.java
│   ├── Torneo.java                    -> Serializable, estado del torneo
│   ├── Sorteador.java                 -> emparejarRecursivo(...)
│   ├── GestorPersistencia.java        -> ObjectOutputStream / ObjectInputStream
│   └── LigaFactory.java               -> 16 equipos iniciales
├── vista/VistaConsola.java            -> VISTA (solo entrada/salida)
├── controlador/ControladorTorneo.java -> CONTROLADOR (mediación + try-catch)
└── excepciones/
    ├── NumeroEquiposInvalidoException.java
    └── SorteoInvalidoException.java
```

## Compilar y ejecutar

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.Main
```

(En NetBeans / IntelliJ / Eclipse: abrir la carpeta, marcar `src` como carpeta de fuentes y ejecutar `app.Main`.)

## Cómo cumple cada requisito

| Requisito | Dónde |
|---|---|
| 16 equipos | `LigaFactory.crearEquiposIniciales()` |
| Sorteo recursivo | `Sorteador.emparejarRecursivo()` (empareja 2 equipos y se llama con el resto) y `Torneo.sortearEtapasRecursivo()` (avanza de etapa en etapa) |
| Sin partidos repetidos / sin equipo contra sí mismo | validaciones dentro de `emparejarRecursivo()` → `SorteoInvalidoException` |
| Potencia de 2 | `Sorteador.validarNumeroEquipos()` → `NumeroEquiposInvalidoException` (probar con la opción 6 del menú) |
| Serialización | `GestorPersistencia.guardar()` / `cargar()`; el torneo se carga automáticamente al iniciar si existe `torneo.ser` |
| try-catch | `ControladorTorneo` captura las excepciones propias, `IOException`, `FileNotFoundException` y `ClassNotFoundException` |
# Sorteo de equipos para una liga profesional (POO – Unidad 3)

**Autor:** James Nefi Valenzuela Zuñiga — Carrera de Software, Universidad Politécnica Salesiana

Aplicación de consola en Java que sortea los partidos de una liga de 16 equipos
(octavos, cuartos, semifinales y final) usando **MVC**, **excepciones propias**,
**sorteo recursivo** y **serialización** (`torneo.ser`).

## Estructura (MVC)

```
src/
├── app/Main.java                      -> arranque: une Vista, Modelo y Controlador
├── modelo/                            -> MODELO (datos, reglas, sorteo, persistencia)
│   ├── Equipo.java
│   ├── Partido.java
│   ├── Etapa.java
│   ├── Torneo.java                    -> Serializable, estado del torneo
│   ├── Sorteador.java                 -> emparejarRecursivo(...)
│   ├── GestorPersistencia.java        -> ObjectOutputStream / ObjectInputStream
│   └── LigaFactory.java               -> 16 equipos iniciales
├── vista/VistaConsola.java            -> VISTA (solo entrada/salida)
├── controlador/ControladorTorneo.java -> CONTROLADOR (mediación + try-catch)
└── excepciones/
    ├── NumeroEquiposInvalidoException.java
    └── SorteoInvalidoException.java
```

## Compilar y ejecutar

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.Main
```

(En NetBeans / IntelliJ / Eclipse: abrir la carpeta, marcar `src` como carpeta de fuentes y ejecutar `app.Main`.)

## Cómo cumple cada requisito

| Requisito | Dónde |
|---|---|
| 16 equipos | `LigaFactory.crearEquiposIniciales()` |
| Sorteo recursivo | `Sorteador.emparejarRecursivo()` (empareja 2 equipos y se llama con el resto) y `Torneo.sortearEtapasRecursivo()` (avanza de etapa en etapa) |
| Sin partidos repetidos / sin equipo contra sí mismo | validaciones dentro de `emparejarRecursivo()` → `SorteoInvalidoException` |
| Potencia de 2 | `Sorteador.validarNumeroEquipos()` → `NumeroEquiposInvalidoException` (probar con la opción 6 del menú) |
| Serialización | `GestorPersistencia.guardar()` / `cargar()`; el torneo se carga automáticamente al iniciar si existe `torneo.ser` |
| try-catch | `ControladorTorneo` captura las excepciones propias, `IOException`, `FileNotFoundException` y `ClassNotFoundException` |
