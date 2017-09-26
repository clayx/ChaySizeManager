package com.chay.test.chaysizemanager.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by Chay on 2016/11/10.
 */

public class ConnectUtils {

    public static String[] getPhoneContacts(Context context, Uri uri){
        String[] contact=new String[2];
        //得到ContentResolver对象
        ContentResolver cr = context.getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor=cr.query(uri,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            //只需取得联系人姓名
            int nameFieldColumnIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0]=cursor.getString(nameFieldColumnIndex);

            cursor.close();
        }
        else
        {
            return null;
        }
        return contact;
    }
}
