package com.chay.test.chaysizemanager.util.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chay on 2016/11/8.
 * 尺码助手分类数据
 */

public class CategorySizeBean implements Serializable {


    /**
     * catagary : CAT_HAT
     * name : 帽子
     * cataData : {"bodyMeasure":[{"type":"TYPE_HEAD","name":"头围","selectedSize":"21.5","sizeRange":"45-75","step":"0.1","sizeList":["0-52","52-54","54-56","56-58","58-999"]}],"showSize":[{"type":"INTL","name":"国际","sizeList":["XXS","XS","S","M","L"]}],"bodyDefualt":[{"type":"TYPE_HEAD","name":"头围","sizeList":["51","52","53","54","55"]}]}
     */

    private String catagary;
    private String name;
    private CataDataBean cataData;
    private String defaultCountry;

    public String getDefaultCountry() {
        return defaultCountry;
    }

    public void setDefaultCountry(String defaultCountry) {
        this.defaultCountry = defaultCountry;
    }

    public String getCatagary() {
        return catagary;
    }

    public void setCatagary(String catagary) {
        this.catagary = catagary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CataDataBean getCataData() {
        return cataData;
    }

    public void setCataData(CataDataBean cataData) {
        this.cataData = cataData;
    }

    public static class CataDataBean implements Serializable {
        /**
         * type : TYPE_HEAD
         * name : 头围
         * selectedSize : 21.5
         * sizeRange : 45-75
         * step : 0.1
         * sizeList : ["0-52","52-54","54-56","56-58","58-999"]
         */

        private List<BodyMeasureBean> bodyMeasure;
        /**
         * type : INTL
         * name : 国际
         * sizeList : ["XXS","XS","S","M","L"]
         */

        private List<ShowSizeBean> showSize;
        /**
         * type : TYPE_HEAD
         * name : 头围
         * sizeList : ["51","52","53","54","55"]
         */

        private List<BodyDefualtBean> bodyDefualt;

        public List<BodyMeasureBean> getBodyMeasure() {
            return bodyMeasure;
        }

        public void setBodyMeasure(List<BodyMeasureBean> bodyMeasure) {
            this.bodyMeasure = bodyMeasure;
        }

        public List<ShowSizeBean> getShowSize() {
            return showSize;
        }

        public void setShowSize(List<ShowSizeBean> showSize) {
            this.showSize = showSize;
        }

        public List<BodyDefualtBean> getBodyDefualt() {
            return bodyDefualt;
        }

        public void setBodyDefualt(List<BodyDefualtBean> bodyDefualt) {
            this.bodyDefualt = bodyDefualt;
        }

        /*身体测量数据*/
        public static class BodyMeasureBean implements Serializable {
            private String type;
            private String name;
            private String selectedSize;
            private String sizeRange;
            private String step;
            private List<String> sizeList;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSelectedSize() {
                return selectedSize;
            }

            public void setSelectedSize(String selectedSize) {
                this.selectedSize = selectedSize;
            }

            public String getSizeRange() {
                return sizeRange;
            }

            public void setSizeRange(String sizeRange) {
                this.sizeRange = sizeRange;
            }

            public String getStep() {
                return step;
            }

            public void setStep(String step) {
                this.step = step;
            }

            public List<String> getSizeList() {
                return sizeList;
            }

            public void setSizeList(List<String> sizeList) {
                this.sizeList = sizeList;
            }
        }

        /*滚动条数据*/
        public static class ShowSizeBean implements Serializable {
            private String type;
            private String name;
            private List<String> sizeList;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getSizeList() {
                return sizeList;
            }

            public void setSizeList(List<String> sizeList) {
                this.sizeList = sizeList;
            }
        }

        /*默认数据*/
        public static class BodyDefualtBean implements Serializable {
            private String type;
            private String name;
            private List<String> sizeList;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getSizeList() {
                return sizeList;
            }

            public void setSizeList(List<String> sizeList) {
                this.sizeList = sizeList;
            }
        }
    }
}
