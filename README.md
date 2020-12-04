`Testee.it` provides `E2E` base framework
=================================

|     |     |
------|------
| ![](https://user-images.githubusercontent.com/1389501/101088233-a21a9000-35b3-11eb-991c-4b9a29036b74.png) |  Testee.it is a simple framework around Selenium which helps to start with end-to-end testing in easy way. It is intended as a replacement for the default `Selenium from scratch` approach, because it is comprehensive and is not easy to understand it at a glance. Testee.it helps you to concise best practices for e2e testing. |
|    |      |

## Bullet points of `Testee.it` with `Selenium`

- [automation] ability to run test checks for application in an automated manner.
- [polymorphism] we are able to test on different browsers (Chrome, Firefox).
- [representative] test-suite solution [testNG](https://testng.org/doc/selenium.html), reporting solution [reportNG](https://github.com/hibissscus/reportng) with screenshots.

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

© 2019-2020 [Sergei Stepanov](https://github.com/hibissscus) (Initial idea & implementation & enhancement)

<img width="125" alt="testee it" src="https://user-images.githubusercontent.com/1389501/101087856-21f42a80-35b3-11eb-8935-6ac32fb29471.png">
