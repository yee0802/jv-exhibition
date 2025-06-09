# Exhibition Curation Platform

## Project Structure
```shell
jv-exhibition/
│
├── frontend/       # Android app (Java, Android SDK, MVVM)
│
├── backend/        # API (Java, Spring Boot, MVC)
│
└── README.md       # You're here!
```

## Table Of Contents

- [Summary](#summary)
- [Preview](#preview)
- [Setup Instructions](#setup-instructions)
- [Credits](#credits)

## Summary
The Exhibition Curation project is a full-stack monorepo combining an Android app using MVVM architecture with a RESTful API built on the MVC pattern. 
Users can browse and filter through a list of artworks, manage exhibitions, and add/remove artworks to or from custom exhibitions. 
This architecture supports clean separation of concerns, reactive UI updates, and maintainable backend logic.

## Preview
To watch a video demonstration of this app, click on the image below:

<a href="https://youtube.com/shorts/HB1TlTy7R70?feature=share"><img src="https://res.cloudinary.com/dpqhn9i37/image/upload/v1749479668/exhibition_showcase_mgxron.png" height="800"/></a>


## Setup Instructions
### Prerequisites:
Before running this project locally, ensure you have the following installed:
1. **Android Studio:** Installed and properly configured. [Download here]().
2. **An IDE:** An IDE to run the API. (e.g. Intellij IDEA, Eclipse, or VS code)
3. **Java Development Kit (JDK) 17+**

### Steps to Clone and Run the App:
To clone the repository and change into the root directory:
```bash
git clone https://github.com/yee0802/jv-exhibition.git
cd jv-exhibition
```

### Running the API:
1. **Open the `backend` folder in your IDE of choice.**
2. **Configure the database:** (Optional)
    * By default, the API uses H2 in-memory database for development.
    * To switch to a production ready database like PostgreSQL:
        1. create a `application-rds.properties` file:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/jvexhibition
      spring.datasource.driverClassName=org.postgresql.Driver
      spring.datasource.username=<your_username>
      spring.datasource.password=<your_password>
      spring.jpa.hibernate.ddl-auto=update
      ```
        2. Set the active profile to `rds` for external database configuration in the `application.properties` file:
      ```properties
      spring.profiles.active=rds
      ```
3. **Build the project:**
```bash
mvn clean install
```
4. **Run the application:**
    * Open the project in your chosen IDE.
    * Navigate to the `JvExhibitionApiApplication` class:\
      `src/main/java/com/example/jv_exhibition_api/JvExhibitionApiApplication.java`
    * Run the `main` method.

### Running the Android application:
1. **Open the `frontend` folder in Android Studio:**
   * Launch Android Studio.
   * Select **File > Open** and navigate to the `frontend` folder.
   * Click **OK** to load the project.
2. **Sync Gradle:**
   * When prompted, click **Sync Now** to download dependencies and configure the project.
3. **Run the API:** 
   * Ensure the API is running locally.
4. Build and Run the App:
   * Connect a physical device (via USB) or start an emulator.
   * Click the Run button or press `Shift + F10` to build and run the app.

## Credits
Created by Kye Yee | 2025