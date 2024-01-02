package com.bytesmart.webadmin.controller;

import com.bytesmart.common.security.annotation.RequiresPermissions;
import com.bytesmart.webadmin.domain.BytesmartPost;
import com.bytesmart.webadmin.service.IBytesmartPostService;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class BytesmartPostController extends BaseController {

    @Autowired
    private IBytesmartPostService postService;


//    @RequiresPermissions("webadmin:post:list")
    @GetMapping("/list")
    public TableDataInfo list(BytesmartPost post)
    {
        startPage();
        List<BytesmartPost> list = postService.selectPostList(post);
        return getDataTable(list);
    }

    @GetMapping(value = "/{postId}")
    public AjaxResult getInfo(@PathVariable Long postId)
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


     //获取岗位选择框列表
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<BytesmartPost> posts = postService.selectPostAll();
        return success(posts);
    }


}