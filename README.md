# 🚀 Java Spring Boot Backend – Master Data Management

Proyek ini adalah implementasi backend menggunakan **Java Spring Boot** untuk mengelola data master:  
- **Provinsi**  
- **Cabang (Branch)**  
- **Toko (Store)**  

---

## ▶️ 1. Menjalankan Aplikasi

### 1.1 Clone project dari GitHub saya
- **git clone https://github.com/mufidbrown/IlhamMufid_BackendDeveloper_TechnicalAssignment.git**
- **cd repo-name**
- *Aplikasi saya berjalan di port 8089*



## 📦 2. Persiapan Lingkungan

### 2.1 Prerequisites
Pastikan sudah ter-install:
- **Java**: versi 17  
- **Database**: PostgreSQL  
- **Maven**

### 2.2 Tools yang Direkomendasikan
- **IntelliJ IDEA** → IDE untuk development  
- **DBeaver / PGAdmin** → Database management  
- **Postman** → API testing  

---

## ⚙️ 3. Konfigurasi Aplikasi

### 3.1 Aktifkan Profile `dev`
Buka file `application.yml` dan atur agar **active profile** menjadi `dev`.

```yaml
spring:
  profiles:
    active: dev
```

### 3.2 application-dev.yml
Buka file `application-dev.yml` dan masukan username dan password anda untuk bisa koneksikan backend ke database

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/mtdb
    username: postgres
    password: bismillah
    driver-class-name: org.postgresql.Driver
```

### 3.3 application-dev.yml
Buka file `application-dev.yml` dan masukan username serta password untuk bisa koneksikan backend ke database




## Swagger
> **Access : http://localhost:8089/swagger-ui/index.html**



