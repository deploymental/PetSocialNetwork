package com.getjavajob.utils;

import com.getjavajob.common.Account;
import com.getjavajob.common.Group;

import java.lang.reflect.Field;

public class GlueTogether {

    public static void glueTogether(Object ac, Object update) throws IllegalAccessException {
        Class acClaz = ac.getClass();
        Class updateClaz = update.getClass();
        Field[] acFields = acClaz.getDeclaredFields();
        Field[] updateFields = updateClaz.getDeclaredFields();
        for (Field f : updateFields) {
            f.setAccessible(true);
            Object obj = f.get(update);
            if (obj != null) {
                for (Field fi : acFields) {
                    fi.setAccessible(true);
                    if (f.getName().equals(fi.getName())) {
                        fi.set(ac, obj);
                    }
                }
            }
        }// TODO: почему не работает
    }

    public static void glueAccountsTogether(Account ac, Account update) {
        if (update.getImage() != null) {
            ac.setImage(update.getImage());
        }
        if (update.getPassword() != null) {
            ac.setPassword(update.getPassword());
        }
        if (update.getIcq() != null) {
            ac.setIcq(update.getIcq());
        }
        if (update.getSkype() != null) {
            ac.setSkype(update.getSkype());
        }
        if (update.getHomeAddress() != null) {
            ac.setHomeAddress(update.getHomeAddress());
        }
        if (update.getWorkAddress() != null) {
            ac.setWorkAddress(update.getWorkAddress());
        }
        if (update.getAdditionalInfo() != null) {
            ac.setAdditionalInfo(update.getAdditionalInfo());
        }
    }

    public static void glueGroupsTogether(Group gr, Group update) {
        if (update.getImage() != null) {
            gr.setImage(update.getImage());
        }
        if (update.getName() != null) {
            gr.setName(update.getName());
        }
        if (update.getDescription() != null) {
            gr.setDescription(update.getDescription());
        }
    }
}
