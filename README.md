# Financial-Data-Evaluator
A robust backend system using Java, SQL, Spring Boot, RestAPI, and Mongo DB. Creating a backend system that integrates the GPT-4 API for generating investment insights for the Indian capital market.

## Microservices Architecture

This project employs a microservices architecture to enhance scalability. The architecture comprises three distinct microservices: one for authentication and user details, another for processing financial data, and an API Gateway responsible for handling and authenticating requests before forwarding them to the GPT service. 


The API Gateway is responsible for handling and authenticating requests before forwarding them to the GPT service.

## Authentication and User Details Service

The User Service uses a MySQL database to store user details securely. It employs bcrypt encryption for passwords, utilizing a 256-bit key (S2) and generates JWT tokens for session management. Users can log in, obtain a token, and submit requests to the GPT service with the token included in the header. The API Gateway validates and authenticates the token, appending the extracted username to the request before forwarding it to the GPT service.

## GPT Service

The GPT service processes requests based on user preferences, data, and provided username. It saves the processed data to a MongoDB database, with the user ID serving as the key for data retrieval.

## Architecture and Best Practices

The project follows a layered architecture within each service. Additionally, a separate folder structure is implemented for each external API component, maintaining clarity and organization. Centralized exception handling is achieved through the use of controller advice, defining multiple custom exceptions.

## Global Configuration Project

A global configuration project has been added, containing settings for all microservices and common configurations. Future enhancements, such as rate limiting, IP whitelisting, and API security improvements, are planned. Data format checking before API submission and custom unit test cases for endpoint testing have been implemented.

## Swagger Documentation

Swagger is integrated into both the User and GPT services, providing comprehensive documentation for API endpoints.

## Next Steps and Future Improvements

1. **Global Config Project:** Store common configurations centrally.
2. **Rate Limiting and IP Whitelisting:** Enhance security by implementing rate limiting and IP whitelisting.
3. **Data Format Checking:** Add pre-submission data format checking for enhanced security.
4. **Custom Unit Tests:** Implement unit tests to ensure the robustness of endpoints.
5. **Swagger Documentation:** Continue to update and improve Swagger documentation for clarity and ease of use.
