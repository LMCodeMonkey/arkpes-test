# arkpes_test
arkpes_test is an implementation of the [Ark Dev Assignment](Ark_dev_assignment.pdf).

## Run Locally
_These steps are given from Intelij_

To run the backend app locally, right click and run the **ArkpesTestApplication** in _arkpes_test > src > main > java > com.arkpes.arkpes_test_.

To run the backend tests, right click the java directory in _arkpes_test > src > test_.

To run the PostMan collection of requests:
 * Start the backend app 
 * in Postman, select the _Import_ button in the top left
 * Select the file option
 * Navigate to the _arkpes_test.postman_collection.json_ file in this project.
 * Select import

You should now have the arkpes_test collection of requests in your postman workspace.

To run the frontend:
* Make sure the backend app is running
* Navigate to the UI project folder
* Run ```npm install```
* Run ```npm start```
* The app will now be running at ```http://localhost:3000```

## Thought Process

### Backend
I started my backend design process with the rough UML Class Diagram below. 

While creating this diagram, I
tried to see if there was any clear opportunity for a software design pattern to provide value.
Especially my favorite factory pattern. If there was more of a difference in fields or logic between the domestic and international 
investors, I would of had them be children inheriting from an investor super class.

[![N|Rough UML Class Diagram](/arkpes_test/Ark-Test-UML.png)]

The design evolved from there but maintained my following priorities:
* Layered separation of concerns
    * Control layer dealing with strictly all endpoint entry setup
    * Service layer dealing with strictly all the app's logic
    * Data layer dealing with strictly all interactions with the DB
* Clear path to follow through the layers for a given functionality
* Clear and concise self documenting code through proper naming and logical flow (more refactoring needed)
* Useful feedback to user on failed request (more work to be done see dev goals)
* Extendable and reusable code structure as much as possible (more work to be done)

The primary challenge I encountered with this assignment was that I had no previous experience using or testing Spring with a
SQL DB or an in memory DB.

### Frontend

I tackled the fronted last and wanted to focus on functional logic with component separation

I chose to use Ant Design as a library for quick, functional, and pleasant looking low level components.

The design does deviate slightly from the requirements by having a Client's investors and their funds displayed on the same page.
I did this because it seemed like an opportunity to reduce the number of user clicks
while still clearly displaying all the necessary information.

I did not have time to implement the client editing but I would have done so by either allowing direct editing of the client
in the table or by opening a modal containing a full form with input validation and feedback.

There is of course a lot more visual improvements and code reuse work to be done in future iterations.

## Future Development goals
* Find a better way for testing data access layer
* Use Spring Slices to speed testing that requires Spring startup
* Add unit testing for equality overrides, **InvestorRepository**
* Add end to end tests. Particularily for endpoints
* Build out API error responses to be more informative and versatile
* Create a central annotation based data validation
* Setup Swagger endpoint documentation
* Secure backend and frontend with HTTPS
* Filtering on UI
* Client editing from UI
* Jest function testing and snapshot testing for the UI
## Known Issues
* Issues updating or adding _funds_
* **ClientRepository** _testSaveUpdateClient_ test does not pass due to issues that I believe are related to updating _funds_
