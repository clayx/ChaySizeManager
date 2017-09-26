package com.chay.test.chaysizemanager.info;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chay on 2016/11/9.
 *
 * @Description:用户列表数据
 */

public class SizeListInfo implements Serializable {

    private List<UserSizeInfo> data;

    public List<UserSizeInfo> getData() {
        return data;
    }

    public void setData(List<UserSizeInfo> data) {
        this.data = data;
    }

    public class UserSizeInfo implements Serializable {
        private String id;
        private String fullName;
        private String sex;
        private String headPic;
        private String isDefault;
        private String userId;
        private String operId;
        private String thumbnail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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
    }
}
