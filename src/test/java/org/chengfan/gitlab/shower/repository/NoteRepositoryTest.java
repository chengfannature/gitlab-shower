package org.chengfan.gitlab.shower.repository;

import org.chengfan.gitlab.shower.dto.NoteDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.chengfan.gitlab.shower.Globle.endDateTime;
import static org.chengfan.gitlab.shower.Globle.startDateTime;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    NoteRepository repository;

    @Test
    public void countNoteGroupByAuthorName() {
        List<NoteDto> noteList = repository.countNoteGroupByAuthorName(startDateTime, endDateTime);
        assertTrue(noteList.size() > 0);
    }
}