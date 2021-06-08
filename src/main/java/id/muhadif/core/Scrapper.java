package id.muhadif.core;

import id.muhadif.AppMain;
import id.muhadif.exception.FailedScrappingException;
import id.muhadif.model.Product;
import id.muhadif.util.ScrapperWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author muhadif
 * @create 07/06/21 20.12
 */

public class Scrapper {
    private static final String TOKOPEDIA_BASE_URL = "https://www.tokopedia.com";
    private static final String HANDPHONE_PATH = "/p/handphone-tablet/handphone?page=";
    private static final String PROMO_URL = "https://ta.tokopedia.com/promo";;

    private static final String XPATH_PRODUCT_LIST = "//div[@data-testid='lstCL2ProductList']/div";
    private static final String XPATH_PRODUCT_LINK = "a[@data-testid='lnkProductContainer']";
    private static final String XPATH_MERCHANT_NAME = "//a[@data-testid='llbPDPFooterShopName']//h2";
    private static final String XPATH_PRODUCT_NAME = "//h1[@data-testid='lblPDPDetailProductName']";
    private static final String XPATH_PRODUCT_DESC = "//div[@data-testid='lblPDPDescriptionProduk']";
    private static final String XPATH_PRODUCT_IMG_URL = "//div[@data-testid='PDPImageMain']//img";
    private static final String XPATH_PRODUCT_PRICE = "//div[@data-testid='lblPDPDetailProductPrice']";
    private static final String XPATH_PRODUCT_RATING = "//*[@data-testid='lblPDPDetailProductRatingNumber']";


    public List<Product> extractProducts(int count) throws FailedScrappingException {

        final String baseUrl = TOKOPEDIA_BASE_URL + HANDPHONE_PATH;
        final ScrapperWebDriver webDriver = new ScrapperWebDriver();
        final List<Product> products = new ArrayList<>(count);

        try{
            int page = 1;
            List<String> tabs = webDriver.prepareTwoTabs();

            AppMain.downloadStart();

            while (products.size() < count) {
                String url = baseUrl + page;
                final List<WebElement> items = webDriver.getElementListByScrollingDown(url, XPATH_PRODUCT_LIST, tabs.get(0) );

                for (WebElement item : items) {

                    String path = item.findElement(By.xpath(XPATH_PRODUCT_LINK)).getAttribute("href");

                    if (path.contains(PROMO_URL)){
                        path = URLDecoder.decode(path.split("r=")[1], StandardCharsets.UTF_8.name());
                    }

                    webDriver.openNewTab(path, tabs.get(1));
                    webDriver.scrollDown();
                    webDriver.waitOnElement(XPATH_MERCHANT_NAME);

                    products.add(extractSingleProduct(webDriver, path));
                    AppMain.updateDownloadProgress();
                    if (products.size() == count){
                        break;
                    }
                    webDriver.switchTab(tabs.get(0));
                }
                page += 1;
            }
            AppMain.downloadFinished();

        } catch (Exception e) {
            throw new FailedScrappingException(e.getMessage());
        }

        return products;
    }

    private Product extractSingleProduct(ScrapperWebDriver webDriver, String path) {
        String name = webDriver.getText(XPATH_PRODUCT_NAME);
        String desc = webDriver.getText(XPATH_PRODUCT_DESC);
        String imgUrl = webDriver.getText(XPATH_PRODUCT_IMG_URL, "src");
        String price = webDriver.getText(XPATH_PRODUCT_PRICE)
                .replace("Rp", "")
                .replace(".", "");;
        String rating = webDriver.getText(XPATH_PRODUCT_RATING);
        String merchant = webDriver.getText(XPATH_MERCHANT_NAME);

        if (price.equals("")){
            price = "0";
        }

        if (rating.equals("")) {
            rating = "0";
        }

        return new Product(name,
                desc,
                imgUrl,
                Integer.parseInt(price),
                Double.parseDouble(rating),
                merchant);

    }

}
