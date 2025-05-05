# Projet RH & Assurance Vie

Ce projet est une application de démonstration construite autour d'un scénario RH : un conseiller RH peut modifier les informations du bénéficiaire de la police d'assurance-vie d'un employé, après validation de son identité. L'application est conçue en architecture **microservices** et utilise **GraphQL** pour les communications entre les services.

---

## 📌 Description Fonctionnelle

- Un employé appelle le service RH pour modifier le nom du bénéficiaire de sa police d’assurance-vie.
- Le conseiller RH vérifie son identité via : nom, numéro d’employé, adresse et NAS.
- Si l'identité est confirmée :
  - Le conseiller met à jour le dossier dans le système.
  - Un courriel de confirmation est envoyé à l’employé.
  - Une notification est envoyée automatiquement à la compagnie d’assurance.
- Sinon, l’appel est interrompu.

---

## 🧪 Technologies Utilisées

- ✅ Java 17
- ✅ Spring Boot
- ✅ GraphQL Java / Spring GraphQL
- ✅ Maven
- ✅ Lombok
- ✅ Spring Security (optionnel)
- ✅ Mail Sender (Spring Boot Starter Mail)

---

## 🧱 Architecture

Le projet suit une architecture **microservices** et chaque service peut être déployé indépendamment. Le point central de communication est **GraphQL Gateway**, qui agrège les données des microservices.

