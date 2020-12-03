`Testee.it` provides `E2E` base framework
=================================

Testee.it is a simple framework around Selenium which helps to start with end-to-end testing in easy way. It is intended as a replacement for the
default `Selenium from scratch` approach, because it is comprehensive and is not so easy to understand at-a-glance. Testee.it helps you to concise
best practices for e2e testing.

<img width="125" alt="testee it" src="https://user-images.githubusercontent.com/1389501/101088233-a21a9000-35b3-11eb-991c-4b9a29036b74.png">

## Bullet points of `Testee.it` with `Selenium`

- [automation] ability to run test checks for application in automation manner
- [polymorphism] we are able to test on different browsers (Chrome, Firefox)
- [representative] test-suite solution (testNG), reporting solution [reportNG](https://github.com/hibissscus/reportng), screenshot solution (
  integrated into reportNG)

## How to run E2E tests

1. We are using `gradle` for building this `E2E` project
2. To be able to run E2E tests locally we need to install `chromedriver` or `geckodriver`
    - `brew cask install chromedriver` (or `brew upgrade --cask chromedriver `)
    - `brew install geckodriver` (or `brew upgrade geckodriver`)
3. Go to `testee.it.e2e.tests` and run any of the test via IDEA with `test` profile (ex.: [MatryoshkaTest])
4. For `Selenium` dockerization we can use [docker-compose.yml]
    - `docker-compose up e2e`
    - `docker-compose down`

### Authors

© 2019-2020 Sergei Stepanov https://github.com/hibissscus (Initial idea & implementation & enhancement
![😎](https://user-images.githubusercontent.com/1389501/101087856-21f42a80-35b3-11eb-8935-6ac32fb29471.png)