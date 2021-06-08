package id.muhadif.service;

import com.google.common.annotations.VisibleForTesting;
import id.muhadif.core.Scrapper;
import id.muhadif.exception.FailedScrappingException;
import id.muhadif.model.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author muhadif
 * @create 08/06/21 11.31
 */
public class ScrapperService {
    private static String DIR = System.getProperty("user.dir");
    public static final String EXPORTED_FILE_PATH = DIR + File.separator+ "tokopedia-handphone.csv";

    private Scrapper scrapper;

    public ScrapperService(){
        this.scrapper = new Scrapper();
    }

    @VisibleForTesting
    public ScrapperService(Scrapper scrapper){
        this.scrapper = scrapper;
    }

    public List<Product> downloadData(int count){
        List<Product> products = new ArrayList<>();
        try {
            products = scrapper.extractProducts(count);

        } catch (FailedScrappingException e) {
            e.printStackTrace();
        }

        return products;
    }

    public String exportToFile(List<Product> products){
        String path = "";
        FileService fileService = new FileService();
        path = fileService.exportToCSV(products, EXPORTED_FILE_PATH);

        return path;
    }

    public String exportToFile(List<Product> products, String exportPath){
        String path;
        FileService fileService = new FileService();
        path = fileService.exportToCSV(products, exportPath);

        return path;
    }
}
