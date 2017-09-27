package com.chay.test.chaysizemanager.info;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Chay
 * Date on 2017/9/26.
 */

@Entity
public class SizeDaoInfo {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "fullName")
    private String fullName;

    @Property(nameInDb = "sex")
    private String sex;

    @Property(nameInDb = "height")
    private String height;

    @Property(nameInDb = "weight")
    private String weight;

    @Property(nameInDb = "chest")
    private String chest;

    @Property(nameInDb = "waist")
    private String waist;

    @Property(nameInDb = "hip")
    private String hip;

    @Property(nameInDb = "headCircu")
    private String headCircu;

    @Property(nameInDb = "neckCircu")
    private String neckCircu;

    @Property(nameInDb = "shoulder")
    private String shoulder;

    @Property(nameInDb = "handsLength")
    private String handsLength;

    @Property(nameInDb = "thighCircu")
    private String thighCircu;

    @Property(nameInDb = "footLength")
    private String footLength;

    @Property(nameInDb = "hat")
    private String hat;

    @Property(nameInDb = "coat")
    private String coat;

    @Property(nameInDb = "shirt")
    private String shirt;

    @Property(nameInDb = "trousers")
    private String trousers;

    @Property(nameInDb = "underpants")
    private String underpants;

    @Property(nameInDb = "shoes")
    private String shoes;

    @Property(nameInDb = "glove")
    private String glove;

    @Property(nameInDb = "isDefault")
    public String isDefault;

    @Generated(hash = 1493407443)
    public SizeDaoInfo(Long id, String fullName, String sex, String height,
            String weight, String chest, String waist, String hip, String headCircu,
            String neckCircu, String shoulder, String handsLength,
            String thighCircu, String footLength, String hat, String coat,
            String shirt, String trousers, String underpants, String shoes,
            String glove, String isDefault) {
        this.id = id;
        this.fullName = fullName;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this.headCircu = headCircu;
        this.neckCircu = neckCircu;
        this.shoulder = shoulder;
        this.handsLength = handsLength;
        this.thighCircu = thighCircu;
        this.footLength = footLength;
        this.hat = hat;
        this.coat = coat;
        this.shirt = shirt;
        this.trousers = trousers;
        this.underpants = underpants;
        this.shoes = shoes;
        this.glove = glove;
        this.isDefault = isDefault;
    }

    @Generated(hash = 1133716765)
    public SizeDaoInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getChest() {
        return this.chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getWaist() {
        return this.waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHip() {
        return this.hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getHeadCircu() {
        return this.headCircu;
    }

    public void setHeadCircu(String headCircu) {
        this.headCircu = headCircu;
    }

    public String getNeckCircu() {
        return this.neckCircu;
    }

    public void setNeckCircu(String neckCircu) {
        this.neckCircu = neckCircu;
    }

    public String getShoulder() {
        return this.shoulder;
    }

    public void setShoulder(String shoulder) {
        this.shoulder = shoulder;
    }

    public String getHandsLength() {
        return this.handsLength;
    }

    public void setHandsLength(String handsLength) {
        this.handsLength = handsLength;
    }

    public String getThighCircu() {
        return this.thighCircu;
    }

    public void setThighCircu(String thighCircu) {
        this.thighCircu = thighCircu;
    }

    public String getFootLength() {
        return this.footLength;
    }

    public void setFootLength(String footLength) {
        this.footLength = footLength;
    }

    public String getHat() {
        return this.hat;
    }

    public void setHat(String hat) {
        this.hat = hat;
    }

    public String getCoat() {
        return this.coat;
    }

    public void setCoat(String coat) {
        this.coat = coat;
    }

    public String getShirt() {
        return this.shirt;
    }

    public void setShirt(String shirt) {
        this.shirt = shirt;
    }

    public String getTrousers() {
        return this.trousers;
    }

    public void setTrousers(String trousers) {
        this.trousers = trousers;
    }

    public String getUnderpants() {
        return this.underpants;
    }

    public void setUnderpants(String underpants) {
        this.underpants = underpants;
    }

    public String getShoes() {
        return this.shoes;
    }

    public void setShoes(String shoes) {
        this.shoes = shoes;
    }

    public String getGlove() {
        return this.glove;
    }

    public void setGlove(String glove) {
        this.glove = glove;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}
