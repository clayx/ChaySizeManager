package com.chay.test.chaysizemanager.info;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * Created by Chay
 * Date on 2017/9/26.
 */

@Entity
public class SizeDaoInfo implements Serializable {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "fullName")
    private String fullName;

    @Property(nameInDb = "sex")
    private String sex;

    @Property(nameInDb = "height")
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
}
