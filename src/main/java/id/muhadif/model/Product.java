package id.muhadif.model;

/**
 * @Author muhadif
 * @create 07/06/21 20.15
 */
public class Product {
    private String name;
    private String description;
    private String imgUrl;
    private int price;
    private double rating;
    private String merchant;

    public Product(String name, String description, String imgUrl, int price, double rating, String merchant) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
        this.rating = rating;
        this.merchant = merchant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
}
