package cn.nyse.entity;

import org.apache.solr.client.solrj.beans.Field;

public class Good {

    @Field("id")
    private String id;
    @Field("name")
    private String name;
    @Field("title")
    private String title;
    @Field("pic")
    private String pic;
    @Field("price")
    private Double price;

    public Good() {
    }

    public Good(String id, String name, String title, String pic, Double price) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.pic = pic;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                ", price=" + price +
                '}';
    }
}
