# Architecture Service


# Installation

- docker 
- npm
- vue

# Démarrer le docker-compose

Dans un terminal, placez-vous dans le dossier TP_GAM/docker et tapez la commande suivante :
```docker-compose -p TP_GAM up --build```
La commande va lancer notre docker-compose qui va créer la stack. On retrouve :
- front sur le port 8080
- postgres sur le port 5432
- mirth connect en 8445
- back sur le port 8081
# Démarrer le projet
  ## Back 
  
  Les détails des routes se trouvent dans le swagger qui se trouve à l'adresse suivante :
  > http://localhost:8081/swagger-ui.html
  
  ## Front
  
  - IPP non connu -> Création du patient
  - IPP connu -> Mise à jour du patient, l'entrée qui apparaît est celle où aucune date de sortie n'est ajoutée. 

  ## Config-Server
  Projet réalisé mais ne fonctionne pas.
  
  ## ZULL
  Projet réalisé mais ne fonctionne pas.
  
  ## Mirth Connect
  Création des fichiers hl7 mais ne fonctionne pas dans Mirth Connect.
 
   ## Crédits
   
   RIBAUT THOMAS : https://github.com/Dopey990.  
   DEREZE Alexandra : https://github.com/Alex221100
   
  
