package org.chengfan.gitlab.shower.utils;

import org.chengfan.gitlab.shower.entity.Record;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ToolsTest {

    @Test
    public void testIsNewer() {
        boolean newer = Tools.isNewer(new Date(), null);
        assertTrue(newer);

        Record record = new Record();
        record.setSyncDate(new Date(1, 1, 1));
        newer = Tools.isNewer(new Date(), record);
        assertTrue(newer);
        record.setSyncDate(new Date());
        newer = Tools.isNewer(new Date(1, 1, 1), record);
        assertFalse(newer);

    }
}