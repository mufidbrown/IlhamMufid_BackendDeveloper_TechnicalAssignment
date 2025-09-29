# 🚀 Java Spring Boot Backend – Master Data Management

Proyek ini adalah implementasi backend menggunakan **Java Spring Boot** untuk mengelola data master:  
- **Provinsi**  
- **Cabang (Branch)**  
- **Toko (Store)**  

---

## ▶️ 1. Menjalankan Aplikasi

### 1.1 Clone project dari GitHub saya
- **git clone https://github.com/mufidbrown/IlhamMufid_BackendDeveloper_TechnicalAssignment.git**
- **cd repo-name** : sesuaikan dengan nama repository clonningan github.
- **Mengisi Query SQL dibawah ini 👇 untuk data awal (seeding) ke dalam tabel roles**: Data dasar (master data) yang wajib ada sebelum sistem jalan.
```yaml
INSERT INTO roles
(id, created_at, created_by, is_active, is_deleted, updated_at, updated_by, name, type)
VALUES
(1, '2025-07-13 10:00:00', 'system', true, false, '2025-07-13 10:00:00', 'system', 'Super Admin', 0),
(2, '2025-07-13 10:00:00', 'system', true, false, '2025-07-13 10:00:00', 'system', 'HR Manager', 1),
(3, '2025-07-13 10:00:00', 'system', true, false, '2025-07-13 10:00:00', 'system', 'HR Staff', 1),
(4, '2025-07-13 10:00:00', 'system', true, false, '2025-07-13 10:00:00', 'system', 'Finance Officer', 2),
(5, '2025-07-13 10:00:00', 'system', true, false, '2025-07-13 10:00:00', 'system', 'Area Manager', 3),
(6, '2025-07-13 10:00:00', 'system', true, false, '2025-07-13 10:00:00', 'system', 'Store Supervisor', 4),
(7, '2025-07-13 10:00:00', 'system', true, false, '2025-07-13 10:00:00', 'system', 'Store Crew / Kasir', 5),
(8, '2025-07-13 10:00:00', 'system', true, false, '2025-07-13 10:00:00', 'system', 'IT Support', 6);
```
- **Proyek ini mengelola **Master Data** dengan struktur **hierarki** sebagai berikut:**
  
Provinsi  
   ⬇️  
   Branch (Cabang)  
       ⬇️  
       Store (Toko)
       
- **Aplikasi saya berjalan di port 8089 : http://localhost:8089**




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




## 📖 4. Dokumentasi API (Swagger)
**🔗 Akses di browser: http://localhost:8089/swagger-ui/index.html**



