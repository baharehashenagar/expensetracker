# ExpenseTracker

ExpenseTracker is a personal spending tracker application designed to provide you with a clear overview of your spending habits. With ExpenseTracker, you can track your transactions, set budget goals, 
and even get reminders for your upcoming dues.

## Features
- **User Authentication:** New user registration, change password, forget password, and login functionality. Authentication tokens are used to secure all APIs except user registration, forget password and login APIs. Also, users get 
informed about their account information via sms after user registration and forget password actions.
- **Transactions:** Add, delete, and view transactions. You can also filter transactions by date, amount, and category. Also, you can see sum of transactions amount in a month.
- **Categories:** Add new categories and view predefined categories.
- **Budget Goals:** Set budget goals for each category and user get an alert if the payment limit has been exceeded  .
- **Reminders:** Set reminders with due dates to stay on top of your expenses.

## Installation
ExpenseTracker is built using Java. To get started, you will need to have Java8 installed on your machine.

Clone this repository to your local machine.
git clone [https://github.com/baharehashenagar/expensetracker.git]
Run ExpenseTrackerApplication to compile and start the application.

## Usage 
Online documentation for APIs are provided by Swagger. You can see detailed information for them in [http://localhost:9999/swagger-ui.html]

## Support
For any issues or feature suggestions, please reach out to our development team at [bahareh.ashenagar@gmail.com].

## Security
ExpenseTracker takes data privacy seriously. All passwords are stored in an encrypted format. Additionally, authentication is needed
to validate users and give services to them. we use JWT(JSON Web Tokens) authentication for this evaluation.

## Roadmap
Future updates to ExpenseTracker include:

- Implementation of monthly charts and visualizations to provide an intuitive view of your spending habits.
- Addition of a comparison feature allowing users to compare their spending with others in a specific category.
- Integration of a monetization model through the recommendation of cost-saving shops and stores to users (ads).
- Dockerizing the application to automate development, testing and deployment processes to ensure consistency for development and production environment.  

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.