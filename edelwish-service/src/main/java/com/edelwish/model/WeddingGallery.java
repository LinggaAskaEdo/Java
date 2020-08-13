package com.edelwish.model;

public class WeddingGallery
{
    private Long id;
    private String categoryName;
    private String imageUrl;

    public WeddingGallery(Long id, String categoryName, String imageUrl)
    {
        this.id = id;
        this.categoryName = categoryName;
        this.imageUrl = imageUrl;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }
}