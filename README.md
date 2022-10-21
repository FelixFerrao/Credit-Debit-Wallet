# Credit-Debit-Wallet

### Execute the project

#### Running the spingboot backend server

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the right project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application
- The Backend Server is ready.

#### Running the angular frontend server

- Open Command Prompt and Change directory (cd) to wallet-frontend folder and execute `npm audit fix`
- This will install all the necessary node modules that would be required
- After the node modules are downloaded run `npm start` command to start the angular frontend project
- On your web browser go to `http://localhost:4200/`
- You are all set.
