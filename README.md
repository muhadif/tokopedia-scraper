# Tokopedia-Scrapper-Products Tools

Project is created by Java8 and using some scrapper library.

## Description

Tokopedia is one of lagerst e-commnerce in Indonesia. The tools have goal to get products list in spesific category on Tokopedia.
The project created with love from Yogyakarta

## Getting Started

### Dependencies

* Installed ChromeWebDriver and Chrome/Chromium

### Installing

* Change ChromeWebDriver location on your local PC in file util/ScrapperWebDriver
 ```
 public ScrapperWebDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriverlocation");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(USER_AGENT);
        chromeOptions.setHeadless(true);

        webDriver = new ChromeDriver(chromeOptions);
        webDriverWait = new WebDriverWait(webDriver, 10);
        jsExecutor = (JavascriptExecutor) webDriver;
    }
```
* Run maven compiler:compile
* Run maven assembly:single

### Executing program

* Run the program on Jar Files. Count parameter is optional which is have default value 100.
```
java -jar program.jar [count]
```


## Authors

Contributors names and contact info

ex. Dominique Pizzie  
ex. [@DomPizzie](https://twitter.com/dompizzie)

## Version History

* 0.2
    * Various bug fixes and optimizations
    * See [commit change]() or See [release history]()
* 0.1
    * Initial Release

## License

This project is licensed under the [NAME HERE] License - see the LICENSE.md file for details

## Acknowledgments
