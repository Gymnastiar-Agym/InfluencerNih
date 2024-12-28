# Influencer Management App

This is a Java application for managing influencers. The application allows you to add, update, delete, and display influencers in a table. It also supports exporting the list of influencers to a Word document. The influencers are represented with attributes like name, platform, username, followers, status, and image path.

## Features
- Add, update, delete, and view influencer details.
- Display influencers in a table format.
- Export the list of influencers to a Word document.

## Technologies Used
- Java Swing for GUI
- Apache POI for Word document export
- Java Collections for managing influencer data

## Project Setup

### Prerequisites
To run this project, you need the following:
- Java 11 or higher
- Maven (for dependency management)
- IDE (IntelliJ IDEA, Eclipse, etc.)

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/influencer-management-app.git
    ```
   
2. Navigate to the project directory:
    ```bash
    cd influencer-management-app
    ```

3. Open the project in your preferred IDE.

4. If you're using Maven, run the following command to install dependencies:
    ```bash
    mvn install
    ```

### Adding Dependencies (for Apache POI)
If you're using Maven, add the following dependency to your `pom.xml` to include Apache POI for Word document export:

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.3</version> <!-- Use the latest version -->
</dependency>
