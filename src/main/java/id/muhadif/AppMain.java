package id.muhadif;

import de.siegmar.fastcsv.writer.CsvWriter;
import id.muhadif.core.Scrapper;
import id.muhadif.model.Product;
import id.muhadif.service.ScrapperService;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;


/**
 * @Author muhadif
 * @create 07/06/21 20.08
 */
public class AppMain {
    private static final String HELP = "USAGE \n"
            + "program.jar [count] : extract <count> product to CSV. default count is 100\n";

    public static void main(String[] args){
        int count = 100;

        if (args.length > 0 ){
            count = Integer.parseInt(args[0]);
        }

        System.out.println("The programm will extract " + count + " data from Tokopedia");
        ScrapperService scrapperService = new ScrapperService();
        List<Product> products = scrapperService.downloadData(count);
        String exportedData = scrapperService.exportToFile(products);
        System.out.println("Your downloaded CSV is here => " + exportedData);
    }

    public static void updateDownloadProgress(){
        System.out.print("*");
    }

    public static void downloadStart() {
        System.out.println("Downloading Data from Tokopedia");
        System.out.print("Progress [");
    }

    public static void downloadFinished(){
        System.out.println("]");
    }
}
