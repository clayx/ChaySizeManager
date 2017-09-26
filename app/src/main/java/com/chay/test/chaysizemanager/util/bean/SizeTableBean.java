package com.chay.test.chaysizemanager.util.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chay on 2016/11/8.
 * 尺码表的整体数据
 */

public class SizeTableBean implements Serializable {
    private List<PersonSizeDataBean> sizeTable;

    public List<PersonSizeDataBean> getSizeTable() {
        return sizeTable;
    }


    public void setSizeTable(ArrayList<PersonSizeDataBean> sizeTable) {
        this.sizeTable = sizeTable;
    }

    /**
     * 个人尺码数据 区分男/女 不同性别
     */
    public static class PersonSizeDataBean implements Serializable {
        /* M -- 男 ;F--->女  */
        private String sex;
        /*具体的尺码*/
        private List<CategorySizeBean> sizeData;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public List<CategorySizeBean> getSizeData() {
            return sizeData;
        }

        public void setSizeData(ArrayList<CategorySizeBean> sizeData) {
            this.sizeData = sizeData;
        }
    }
}
