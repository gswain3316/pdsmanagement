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
  - [The architecture](#the-architecture)
  - [Long description](#long-description)
  - [Project roadmap](#project-roadmap)
  - [Getting started](#getting-started)
  - [Live demo](#live-demo)
  - [Built with](#built-with)
  - [Contributing](#contributing)
  - [Authors](#authors)
  - [License](#license)

## Short description
Our project focuses on the problem of managing food & supplies across all the states/districts of the country. We came up with a model to minimize the human effort as much as possible so as to easily manage rations/supplies across multiple states by distribution through a portal.

### What's the problem?

Approximately 9 percent of the global population is suffering from hunger. And, much of the world’s food is grown by small-scale, independent farms and distributed through local community cooperatives who sell the surplus produce. The co-ops are a central point for quality control, deliveries, and enabling food commodity markets. However, these co-ops face a myriad of logistical challenges to get the right food to the right places with minimal time and cost.

### How can technology help?

By bringing the paper ledgers of food co-ops online, communities can harness data insights from their environment for better crop resilience and overall yield for sustainable food production systems. More crops mean better access to food for the community.

### The idea

To improve access to nutritious food in local communities, especially those suffering from acute hunger, co-operative systems can be digitized and enhanced. By aggregating and analyzing market, transportation, demand, horticultural, and environmental data, co-ops can optimize productivity, reduce overhead, and decrease volatility in the supply chain of the farming communities.

## The architecture

1. The user uses their non-smartphone device camera to capture a photo of their product yield for quality testing and analysis.
2. The user sends a camera image and/or a text message through their non-smartphone device MMS/SMS service.
3. The image and/or message is redirected to the Twilio Programmable Messaging service or to the Telstra Programmable Messaging service for users located in Australia.
4. The Twilio Programmable Messaging service or Telstra Programmable Messaging service will forward the message to the Node-RED app hosted on IBM Cloud.
5. The Node-RED app interacts with the IBM Cloud Pak for Data AI/ML service to get the response.
6. IBM Cloud Object Storage is provisioned to receive the images and/or message data.
7. The image and/or message data is added to the available IBM Cloud Object Storage.
8. The IBM Cloud Pak for Data AI/ML service does the necessary computations and returns a response.
9. The Node-RED app processes the response, converts it to a user-readable format, and forwards it to the digital co-operative management system app UI (Optional: to Twilio or Telstra).
10. The response is received by the digital co-operative management system app UI.
11. The co-op admin is able to view the response via the digital co-operative management system app UI.
12. (Optional: The Twilio or Telstra Programmable Messaging service forwards the response as a reply message to the User through their messaging APIs.)
13. (Optional: The user receives the reply message as a response from the IBM Cloud Pak for Data AI/ML service through their non-smartphone device MMS/SMS service.)

Please follow the link for the schema design for our project: [Click Here](https://gist.github.com/ayushgupta11/9b76468646249c01fc4f35d6a1ce4703)

## Project roadmap

The project currently does the following things.

- Able to fetch crop/food availability of all the states in the country
- Able to create request for a state requesting a 1 or more crop/food as per their required quantity.
- Able to grant request to a specific state which has enough crop available to be supplied to another state.

Below is the project roadmap implemented till now:
1. Epic 1- State Endpoint 
- Availability of rice, wheat, coarse grain, sugar endpoint 
- Capacity of state endpoint 
- Request for rice, wheat, coarse grains, sugar endpoint 
- Granting/ place order note for other states to accept request for rice, wheat, coarse grain, sugar

See below for our proposed schedule on next steps after Call for Code 2021 submission.

1. Epic 2- District Endpoint 
- Availability of rice, wheat, coarse grain, sugar endpoint 
- Capacity of district endpoint 
- Request for rice, wheat, coarse grains, sugar endpoint 
- Granting/ place order note for other states to accept request for rice, wheat, coarse grain, sugar

2. Epic 3- FPS/TPDS Endpoint 
- Availability of rice, wheat, coarse grain, sugar endpoint 
- Capacity of FPS/TPDS other 2 endpoints not needed as the Disctrict administration and state govt has purchasing power

3. Epic 4- Ration card holder/ BPL Endpoint 
- Register for ration card endpoint 
- Available quota for ration for the month endpoint 
- Acceptance of ration by PDS

## Getting started

In this section you add the instructions to run your project on your local machine for development and testing purposes. You can also add instructions on how to deploy the project in production.

- [sample-react-app](./sample-react-app/)
- [sample-angular-app](./sample-angular-app/)
- [Explore other projects](https://github.com/upkarlidder/ibmhacks)

## Live demo

You can find a running system to test at [callforcode.mybluemix.net](http://callforcode.mybluemix.net/).

## Built with

- [IBM Cloudant](https://cloud.ibm.com/catalog?search=cloudant#search_results) - The NoSQL database used
- [IBM Cloud Functions](https://cloud.ibm.com/catalog?search=cloud%20functions#search_results) - The compute platform for handing logic
- [IBM API Connect](https://cloud.ibm.com/catalog?search=api%20connect#search_results) - The web framework used
- [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
- [Maven](https://maven.apache.org/) - Dependency management
- [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

- **Ankit Kumar** - [PurpleBooth](https://github.com/PurpleBooth)
- **Ayush Gupta** - [ayushgupta11](https://github.com/ayushgupta11)
- **Gaurav Swain** - [gswain3316](https://github.com/gswain3316)
- **Kajal Rawal** - [Kajal Rawal](https://github.com/kajalrawal)
- **Ravi** - [gswain3316](https://github.com/gswain3316)

## License

This project is licensed under the Apache 2 License - see the [LICENSE](LICENSE) file for details.