package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import id.muhadif.core.Scrapper;
import id.muhadif.model.Product;
import id.muhadif.service.ScrapperService;
import id.muhadif.util.LetterCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

/**
 * @Author muhadif
 * @create 08/06/21 15.50
 */

public class ScrapperServiceTest {
    private ScrapperService scrapperService;
    private Scrapper scrapper;
    List<Product> products;
    int count = 2;
    String path;

    @BeforeEach
    public void setUp() throws Exception {
        scrapper = new Scrapper();
        scrapperService = new ScrapperService(scrapper);
        products = scrapperService.downloadData(count);
//        path = scrapperService.exportToFile(products, "tokopedia-handphone-test.csv");
    }

    @Test
    @DisplayName("Simple Test for Data Query")
    public void testGetData(){
        assertEquals(count, products.size(), "Download data work");
    }

//    @ParameterizedTest
//    @CsvFileSource(resources = "/tokopedia-handphone-test.csv", numLinesToSkip = 1)
//    public void testCountExportedCsv(String value, long letterCount){
//        assertEquals(letterCount, LetterCounter.countLetters(value));
//    }

}
