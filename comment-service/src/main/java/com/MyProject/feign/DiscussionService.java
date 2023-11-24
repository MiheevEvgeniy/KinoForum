package com.MyProject.feign;

import com.MyProject.dto.CategoryDto;
import com.MyProject.dto.DiscussionDto;
import com.MyProject.dto.UpdateDiscussionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("DISCUSSION-SERVICE")
@RequestMapping("/discussions")
public interface DiscussionService {
    @GetMapping("/{id}")
    DiscussionDto getDiscussion(@PathVariable("id") long id);
    @PatchMapping("/{id}")
    DiscussionDto patchDiscussion(@RequestBody UpdateDiscussionDto upd, @PathVariable("id") long discId);
}
