package org.chengfan.gitlab.shower.utils;

import org.chengfan.gitlab.shower.entity.Record;

import java.util.Date;

/**
 * @author chengfan
 */
public final class Tools {

    public static boolean isNewer(Date createdDate, Record record) {
        if (record == null) {
            return true;
        }
        return createdDate.after(record.getSyncDate());
    }
}
