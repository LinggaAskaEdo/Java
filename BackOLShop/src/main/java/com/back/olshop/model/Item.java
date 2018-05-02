/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.model;

public class Item
{
    private Integer itemId;
    private Integer userId;
    private String itemCode;
    private String itemName;
    private String itemPicture;
    private String itemSize;
    private String itemDesc;
    private int itemStock;
    private int itemPrice;
    private int itemWeight;
    private int itemTotal;

    public Integer getItemId()
    {
        return itemId;
    }

    public void setItemId(Integer itemId)
    {
        this.itemId = itemId;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getItemCode()
    {
        return itemCode;
    }

    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemPicture()
    {
        return itemPicture;
    }

    public void setItemPicture(String itemPicture)
    {
        this.itemPicture = itemPicture;
    }

    public String getItemSize()
    {
        return itemSize;
    }

    public void setItemSize(String itemSize)
    {
        this.itemSize = itemSize;
    }

    public String getItemDesc()
    {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc)
    {
        this.itemDesc = itemDesc;
    }

    public int getItemStock()
    {
        return itemStock;
    }

    public void setItemStock(int itemStock)
    {
        this.itemStock = itemStock;
    }

    public int getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public int getItemWeight()
    {
        return itemWeight;
    }

    public void setItemWeight(int itemWeight)
    {
        this.itemWeight = itemWeight;
    }

    public int getItemTotal()
    {
        return itemTotal;
    }

    public void setItemTotal(int itemTotal)
    {
        this.itemTotal = itemTotal;
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "itemId=" + itemId +
                ", userId=" + userId +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPicture='" + itemPicture + '\'' +
                ", itemSize='" + itemSize + '\'' +
                ", itemDesc='" + itemDesc + '\'' +
                ", itemStock=" + itemStock +
                ", itemPrice=" + itemPrice +
                ", itemWeight=" + itemWeight +
                ", itemTotal=" + itemTotal +
                '}';
    }
}