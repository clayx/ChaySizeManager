package com.chay.test.chaysizemanager.info;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chay on 2016/11/15.
 */

public class UserSizeDetailInfo implements Serializable {

    private SizeDetailInfo data;

    public SizeDetailInfo getData() {
        return data;
    }

    public void setData(SizeDetailInfo data) {
        this.data = data;
    }

    public class SizeDetailInfo implements Serializable {
        private String createDate;
        private String fullName;
        private String id;
        private String isDefault;
        private String operId;
        private String updateDate;
        private String userId;

        private String headPic;
        private String thumbnail;
        private String sex;
        private String height;
        private String weight;
        private String chest;
        private String waist;
        private String hip;
        private String headCircu;
        private String neckCircu;
        private String shoulder;
        private String handsLength;
        private String thighCircu;
        private String footLength;
        private String hat;
        private String coat;
        private String shirt;
        private String trousers;
        private String underpants;
        private String shoes;
        private String glove;

        private List<SizeAreaInfo> list;

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

        public String getOperId() {
            return operId;
        }

        public void setOperId(String operId) {
            this.operId = operId;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<SizeAreaInfo> getList() {
            return list;
        }

        public void setList(List<SizeAreaInfo> list) {
            this.list = list;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getChest() {
            return chest;
        }

        public void setChest(String chest) {
            this.chest = chest;
        }

        public String getWaist() {
            return waist;
        }

        public void setWaist(String waist) {
            this.waist = waist;
        }

        public String getHip() {
            return hip;
        }

        public void setHip(String hip) {
            this.hip = hip;
        }

        public String getHeadCircu() {
            return headCircu;
        }

        public void setHeadCircu(String headCircu) {
            this.headCircu = headCircu;
        }

        public String getNeckCircu() {
            return neckCircu;
        }

        public void setNeckCircu(String neckCircu) {
            this.neckCircu = neckCircu;
        }

        public String getShoulder() {
            return shoulder;
        }

        public void setShoulder(String shoulder) {
            this.shoulder = shoulder;
        }

        public String getHandsLength() {
            return handsLength;
        }

        public void setHandsLength(String handsLength) {
            this.handsLength = handsLength;
        }

        public String getThighCircu() {
            return thighCircu;
        }

        public void setThighCircu(String thighCircu) {
            this.thighCircu = thighCircu;
        }

        public String getFootLength() {
            return footLength;
        }

        public void setFootLength(String footLength) {
            this.footLength = footLength;
        }

        public String getHat() {
            return hat;
        }

        public void setHat(String hat) {
            this.hat = hat;
        }

        public String getCoat() {
            return coat;
        }

        public void setCoat(String coat) {
            this.coat = coat;
        }

        public String getShirt() {
            return shirt;
        }

        public void setShirt(String shirt) {
            this.shirt = shirt;
        }

        public String getTrousers() {
            return trousers;
        }

        public void setTrousers(String trousers) {
            this.trousers = trousers;
        }

        public String getUnderpants() {
            return underpants;
        }

        public void setUnderpants(String underpants) {
            this.underpants = underpants;
        }

        public String getShoes() {
            return shoes;
        }

        public void setShoes(String shoes) {
            this.shoes = shoes;
        }

        public String getGlove() {
            return glove;
        }

        public void setGlove(String glove) {
            this.glove = glove;
        }

        public class SizeAreaInfo implements Serializable {
            private String areaFlag;
            private String cateName;
            private String id;
            private String operId;

            public String getAreaFlag() {
                return areaFlag;
            }

            public void setAreaFlag(String areaFlag) {
                this.areaFlag = areaFlag;
            }

            public String getCateName() {
                return cateName;
            }

            public void setCateName(String cateName) {
                this.cateName = cateName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOperId() {
                return operId;
            }

            public void setOperId(String operId) {
                this.operId = operId;
            }
        }
    }
}
