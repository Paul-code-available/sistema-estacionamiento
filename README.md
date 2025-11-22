Guía de Trabajo con Git y GitHub 

1.  Crear ramas individuales: git checkout -b nombre-rama

2.  Hacer commits en su propia rama: git add . git commit -m
    “Descripción del cambio”

3.  Subir la rama al repositorio: git push -u origin nombre-rama

4.  Crear Pull Requests para unir el trabajo:
 1. Subir tu rama (git push)
 2. Ir a Pull Requests en GitHub
 3. New Pull Request
 4. base: main / compare: tu-rama
 5. Escribir título y descripción
 6. Create Pull Request
 7. Se revisa y aprueba

5.  Actualizar tu rama para evitar conflictos: git checkout main git
    pull origin main git checkout nombre-rama git merge main
  
6. Lista de comandos necesarios durante todo el proyecto

-Ver el estado: git status
-Agregar archivos: git add .
-Hacer commits: git commit -m “mensaje”
-Crear ramas: git checkout -b nombre-rama
-Cambiar de rama: git checkout nombre-rama
-Subir la rama: git push -u origin nombre-rama
-Traer cambios del repositorio: git pull origin main

-Fusionar una rama con otra: git merge nombre-rama
