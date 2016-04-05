package com.beltran.yhoseph.worktime;

import android.app.Fragment;

import com.erc.dal.Field;
import com.erc.dal.PrimaryKey;
import com.erc.dal.Table;

import java.util.Date;

/**
 * Created by yhoseph on 03/04/2016.
 */
@Table
public class TimerCustom {
    @PrimaryKey
    @Field
    public long id;

    @Field
    public boolean introCustom;

    @Field
    public Date date;
    public TimerCustom(Date date, boolean introCustom) {
        this.introCustom = introCustom;
        this.date = date;
    }
    @Override
    public String toString(){
        return ""+date.toString()+"-"+introCustom;
    }
}
