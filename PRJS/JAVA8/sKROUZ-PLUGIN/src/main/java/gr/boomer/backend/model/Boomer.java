package gr.boomer.backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Boomer {

    private Long id;
    private String searchId;
    private LocalDateTime creationDate;

    private String sourceId;

    private String productId;
    private String productUrl;
    private String category;

    private BigDecimal netPrice;
    private BigDecimal finalPrice;
    private BigDecimal courierCost;
    private BigDecimal payOnDeliveryCost;

    private String shopName;
    private String itemName;
    private String specs;
    private String itemUrl;
    private String availability;
    private String areacode;
    private String rating;



public Boomer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public BigDecimal getCourierCost() {
        return courierCost;
    }

    public void setCourierCost(BigDecimal courierCost) {
        this.courierCost = courierCost;
    }

    public BigDecimal getPayOnDeliveryCost() {
        return payOnDeliveryCost;
    }

    public void setPayOnDeliveryCost(BigDecimal payOnDeliveryCost) {
        this.payOnDeliveryCost = payOnDeliveryCost;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Boomer{" +
                "id=" + id +
                ", searchId='" + searchId + '\'' +
                ", creationDate=" + creationDate +
                ", sourceId='" + sourceId + '\'' +
                ", productId='" + productId + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", category='" + category + '\'' +
                ", netPrice=" + netPrice +
                ", finalPrice=" + finalPrice +
                ", courierCost=" + courierCost +
                ", payOnDeliveryCost=" + payOnDeliveryCost +
                ", shopName='" + shopName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", specs='" + specs + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                ", availability='" + availability + '\'' +
                ", areacode='" + areacode + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
