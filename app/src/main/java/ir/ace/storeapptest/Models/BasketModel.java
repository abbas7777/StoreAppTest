package ir.ace.storeapptest.Models;

import com.google.gson.annotations.SerializedName;

public class BasketModel {
    private String image;
    private String price;
    @SerializedName("product_id")
    private int productId;
    @SerializedName("basket_id")
    private int basketId;
    private String title;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
