package id.muhadif.service;

import de.siegmar.fastcsv.writer.CsvWriter;
import de.siegmar.fastcsv.writer.LineDelimiter;
import de.siegmar.fastcsv.writer.QuoteStrategy;
import id.muhadif.model.Product;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @Author muhadif
 * @create 08/06/21 11.19
 */
public class FileService {

    public String exportToCSV(List<Product> products, String path){
        File exportedFile = new File(path);

        try {
            try (CsvWriter csv = CsvWriter.builder()
                    .fieldSeparator(';')
                    .quoteCharacter('\'')
                    .quoteStrategy(QuoteStrategy.ALWAYS)
                    .lineDelimiter(LineDelimiter.LF)
                    .build(exportedFile.toPath(), UTF_8)) {
                csv.writeRow("No", "Name", "Description", "Image Url", "Price (IDR)", "Rating", "Merchant");
                int numbering = 1;
                for (Product product : products){
                    csv.writeRow(numbering + "",
                            product.getName(),
                            product.getDescription()
                                    .replace("\n", " ")
                                    .replace("\t", " "),
                            product.getImgUrl(),
                            product.getPrice() + "",
                            product.getRating() + "",
                            product.getMerchant()
                    );
                    numbering += 1;
                }
            }


        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return exportedFile.getPath();
    }
}
