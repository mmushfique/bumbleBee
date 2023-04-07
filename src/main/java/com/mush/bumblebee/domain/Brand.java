package com.mush.bumblebee.domain;

public class Brand {
    long id;
    String brandName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


    public Brand(String brandName) {
        this.brandName = brandName;
    }

    public Brand(Long id,String brandName) {
        this.id=id;
        this.brandName = brandName;
    }
    public Brand() {
    }
}
