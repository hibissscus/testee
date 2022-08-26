`Testee` provides `E2E` base framework [![e2e](https://github.com/hibissscus/testee/actions/workflows/e2e.yml/badge.svg)](https://github.com/hibissscus/testee/actions/workflows/e2e.yml) 
=================================

|     |     |
------|------
| ![](https://user-images.githubusercontent.com/1389501/122058312-c7524b80-cdeb-11eb-8e7b-861a3caa9132.png) |  `Testee` is a simple framework around Selenium which helps to start with end-to-end testing in easy way. It is intended as a replacement for the default `Selenium from scratch` approach, because it is comprehensive and is not easy to understand it at a glance. `Testee` helps you to umbrella best practices for e2e testing. |

![e2e_result](https://user-images.githubusercontent.com/1389501/97297276-14c87b00-1852-11eb-89a3-31c69ade0960.png)
## Bullet points of `Testee` with `Selenium`

- [automation] ability to run test checks for application in an automated manner.
- [polymorphism] we are able to test on different browsers (Chrome, Firefox).
- [representative] test-suite solution based on [testNG](https://testng.org/doc/selenium.html), reporting solution [reportNG](https://github.com/hibissscus/reportng) with screenshots.
- [parallelism] we are able to run end-to-end tests in parallel.


![dino](https://user-images.githubusercontent.com/1389501/181028016-c19b61eb-18a4-476d-a50b-d2f6c40a4bc6.gif)

## How to run E2E tests

1. `gradle` is used for building this `E2E` project
2. To run `E2E` tests locally we need to install `chromedriver` or `geckodriver`
    - `brew cask install chromedriver` (or `brew upgrade --cask chromedriver`)
    - `brew install geckodriver` (or `brew upgrade geckodriver`)
3. Go to `testee.it.e2e.tests` and run any of the test via IDEA with `test` profile (ex.: [MatryoshkaTest](https://github.com/hibissscus/testee/blob/master/src/test/kotlin/testee/it/tests/example/matryoshka/MatryoshkaTest.kt))
4. For `Selenium` dockerization use [docker-compose.yml](https://github.com/hibissscus/testee/blob/master/docker-compose.yml)
    - `docker-compose up e2e`
    - `docker-compose down`

### Authors

© 2019-2022 [Sergei Stepanov](https://github.com/hibissscus) (Initial idea, implementation & enhancement)

<img width="125" alt="testee it" src="https://user-images.githubusercontent.com/1389501/101087856-21f42a80-35b3-11eb-8935-6ac32fb29471.png">
