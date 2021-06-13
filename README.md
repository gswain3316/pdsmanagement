# PDS Management

[![License](https://img.shields.io/badge/License-Apache2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0) [![Community](https://img.shields.io/badge/Join-Community-blue)](https://developer.ibm.com/callforcode/get-started/) [![Website](https://img.shields.io/badge/View-Website-blue)](https://sample-project.s3-web.us-east.cloud-object-storage.appdomain.cloud/)

An API Application for Food Management via FPS/TDPS and FCI India. 
Our project is based on the theme of Zero Hunger problem and is intended for the submission of [IBM Call for Code 2021](https://developer.ibm.com/callforcode/get-started/).

## Contents

- [PDS Management](#pds-management)
  - [Contents](#contents)
  - [Short description](#short-description)
    - [What's the problem?](#whats-the-problem)
    - [How can technology help?](#how-can-technology-help)
    - [The idea](#the-idea)
  - [Schema](#schema)
  - [Long description](#long-description)
  - [Project roadmap](#project-roadmap)
  - [Getting started](#getting-started)
  - [Live demo](#live-demo)
  - [Built with](#built-with)
  - [Contributing](#contributing)
  - [Authors](#authors)
  - [License](#license)

## Short description
Our project focuses on the problem of managing food & supplies across all the states/districts of the country. We came up with a model to minimize the human effort as much as possible so as to easily manage rations/supplies across multiple states by distribution through our application.

### What's the problem?

Approximately 9 percent of the global population is suffering from hunger. And, much of the world’s food is grown by small-scale, independent farms and distributed through local community cooperatives who sell the surplus produce. The co-ops are a central point for quality control, deliveries, and enabling food commodity markets. However, these co-ops face a myriad of logistical challenges to get the right food to the right places with minimal time and cost.

### How can technology help?

By bringing the paper ledgers of food co-ops online, communities can harness data insights from their environment for better crop resilience and overall yield for sustainable food production systems. More crops mean better access to food for the community.

### The idea

To improve access to nutritious food in local communities, especially those suffering from acute hunger, co-operative systems can be digitized and enhanced. By aggregating and analyzing market, transportation, demand, horticultural, and environmental data, co-ops can optimize productivity, reduce overhead, and decrease volatility in the supply chain of the farming communities.

## Schema

Please follow the link for the schema design for our project: [Click Here](https://gist.github.com/ayushgupta11/9b76468646249c01fc4f35d6a1ce4703)

## Project roadmap

The project currently does the following things.

- Able to fetch crop/food availability of all the states in the country
- Able to create request for a state requesting a 1 or more crop/food as per their required quantity.
- Able to grant request to a specific state which has enough crop available to be supplied to another state.

Below is the project roadmap implemented till now:
1. Epic 1- State Endpoint 
- Endpoint 1 : Availability of rice, wheat, coarse grain, sugar endpoint 
- Endpoint 2 : Capacity of state endpoint 
- Endpoint 3 : Request for rice, wheat, coarse grains, sugar endpoint 
- Endpoint 4 : Granting/ place order note for other states to accept request for rice, wheat, coarse grain, sugar
- Endpoint 5 : Order status endpoint for showing the current status of the order placed by the state

See below for our proposed schedule on next steps after Call for Code 2021 submission.

1. Epic 2- District Endpoint 
- Availability of rice, wheat, coarse grain, sugar endpoint 
- Capacity of district endpoint 
- Request for rice, wheat, coarse grains, sugar endpoint 
- Granting/ place order note for other states to accept request for rice, wheat, coarse grain, sugar
- Order status endpoint for showing the current status of the order placed by the district

2. Epic 3- FPS/TPDS Endpoint 
- Availability of rice, wheat, coarse grain, sugar endpoint 
- Capacity of FPS/TPDS other 2 endpoints not needed as the Disctrict administration and state govt has purchasing power

3. Epic 4- Ration card holder/ BPL Endpoint 
- Register for ration card endpoint 
- Available quota for ration for the month endpoint 
- Acceptance of ration by PDS

## Getting started

In this section you add the instructions to run your project on your local machine for development and testing purposes. You can also add instructions on how to deploy the project in production.
1. Import the project as a maven project
2. Run the project as spring boot application
3. Swagger will be available at `http://localhost:8181/swagger-ui.html`
4. Refer to endpoints in swagger. You can directly run endpoints in swagger or from tools like postman, etc.

## Live demo

You can find a running system to test at [callforcode.mybluemix.net](http://callforcode.mybluemix.net/).

## Built with

- [Spring Boot 2.4.4](https://spring.io/projects/spring-boot) - Rest API developed on springboot framework.
- [Swagger 3.0.3](https://swagger.io/) - Rest API documentation and design tool
- [Spring Webflux 5.2.6](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html) - Reactive-stack web framework.
- [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) - NoSql Cloud database
- [Docker 19.0.3](https://docs.docker.com/) - Help to build, manage, and deploy containerized applications
- [Kubernetes 1.2.0](https://kubernetes.io/) - Help to automating deployment, scaling, and management of containerized applications.
- [Maven](https://maven.apache.org/) - Dependency management


## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

- **Ankit Kumar** - [itank-z](https://github.com/itank-z)
- **Ayush Gupta** - [ayushgupta11](https://github.com/ayushgupta11)
- **Gaurav Swain** - [gswain3316](https://github.com/gswain3316)
- **Kajal Rawal** - [Kajal Rawal](https://github.com/kajalrawal)
- **Ravi Prakash** - [ravii-1996](https://github.com/ravii-1996)

## License

This project is licensed under the Apache 2 License - see the [LICENSE](LICENSE) file for details.
