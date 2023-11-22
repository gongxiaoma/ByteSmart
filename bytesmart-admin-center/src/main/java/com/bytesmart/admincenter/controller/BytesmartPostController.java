package com.bytesmart.admincenter.controller;

import com.bytesmart.admincenter.domain.BytesmartPost;
import com.bytesmart.admincenter.service.IBytesmartPostService;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class BytesmartPostController extends BaseController {

    @Autowired
    private IBytesmartPostService postService;

    @GetMapping("/list")
    public TableDataInfo list(BytesmartPost post)
    {
        startPage();
        List<BytesmartPost> list = postService.selectPostList(post);
        return getDataTable(list);
    }

    @GetMapping(value = "/{postId}")
    public AjaxResult getInfo(@PathVariable Integer postId)
    {
        return success(postService.selectPostById(postId));
    }

    @PutMapping
    public AjaxResult edit(@Validated @RequestBody BytesmartPost post){
        return toAjax(postService.updatePost(post));
    }

    @PostMapping
    public AjaxResult add(@Validated @RequestBody BytesmartPost post){
        return toAjax(postService.insertPost(post));
    }


    @DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds){
        return toAjax(postService.deletePostByIds(postIds));
    }


}
