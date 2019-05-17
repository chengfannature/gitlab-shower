package org.chengfan.gitlab.shower.service.scheduled;

import org.chengfan.gitlab.shower.GitlabConfig;
import org.chengfan.gitlab.shower.entity.User;
import org.chengfan.gitlab.shower.repository.UserRepository;
import org.chengfan.gitlab.shower.service.CommitService;
import org.chengfan.gitlab.shower.service.NoteService;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ScheduledTasksService {

    public static final String DEFAULT_BRANCH = "develop";

    @Autowired
    GitlabAPI gitlabAPI;

    @Autowired
    CommitService commitService;

    @Autowired
    NoteService noteService;

    @Autowired
    GitlabConfig gitlabConfig;

    @Autowired
    UserRepository userRepository;


    @Scheduled(cron = "0 0 7 * * *")
    public void reloadProjectData() {
        GitlabGroup group = null;
        try {
            group = gitlabAPI.getGroup(gitlabConfig.getGroup());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (group == null) {
            return;
        }
        List<String> configProjects = gitlabConfig.getProjects();
        List<GitlabProject> projects = group.getProjects();
        projects.stream().filter(p->configProjects.contains(p.getName())).forEach(p -> {
//            commitService.saveCommits(p.getId());
            noteService.saveNotes(p.getId());
        });
    }

    @Scheduled(cron = "0 0 7 * * *")
    public void reloadUsers() {
        List<GitlabUser> userList = gitlabAPI.getUsers();
        userList.stream().map(this::mapperToUser).forEach(user -> userRepository.save(user));
    }

    private User mapperToUser(GitlabUser gitlabUser) {
        User user = new User();
        user.setId(gitlabUser.getId());
        user.setUsername(gitlabUser.getUsername());
        user.setEmail(gitlabUser.getEmail());
        return user;
    }


}
