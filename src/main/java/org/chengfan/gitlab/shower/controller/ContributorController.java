package org.chengfan.gitlab.shower.controller;

import lombok.extern.slf4j.Slf4j;
import org.chengfan.gitlab.shower.dto.ContributorDto;
import org.chengfan.gitlab.shower.entity.Commit;
import org.chengfan.gitlab.shower.service.CommitService;
import org.chengfan.gitlab.shower.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/contributions")
@Slf4j
public class ContributorController {

    @Autowired
    ContributorService contributorService;

    @Autowired
    CommitService commitService;

    @RequestMapping
    public List<ContributorDto> showContributors(Model model, @RequestParam(value = "orderByProperty") String orderByProperty) {
        List<ContributorDto> contributors =
                contributorService.getDtoContributors(orderByProperty);
        model.addAttribute("contributors", contributors);

        return contributors;
    }

    @RequestMapping("/{username}")
    public String showContributorDetail(Model model, @PathVariable String username) {
        List<Commit> commits = commitService.getAllCommits(username);
        model.addAttribute("username", username);
        model.addAttribute("commits", commits);
        return "contributor";
    }


}
