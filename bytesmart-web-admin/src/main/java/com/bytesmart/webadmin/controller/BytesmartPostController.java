package com.bytesmart.webadmin.controller;

import com.bytesmart.common.core.utils.poi.ExcelUtil;
import com.bytesmart.common.log.annotation.Log;
import com.bytesmart.common.log.enums.BusinessType;
import com.bytesmart.common.security.annotation.RequiresPermissions;
import com.bytesmart.common.security.utils.SecurityUtils;
import com.bytesmart.webadmin.domain.BytesmartPost;
import com.bytesmart.webadmin.service.IBytesmartPostService;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/post")
public class BytesmartPostController extends BaseController {

    @Autowired
    private IBytesmartPostService postService;


    //@RequiresPermissions("webadmin:post:list")
    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @GetMapping("/list")
    public TableDataInfo list(BytesmartPost post)
    {
        startPage();
        List<BytesmartPost> list = postService.selectPostList(post);
        return getDataTable(list);
    }


    //根据id查询
    //@RequiresPermissions("webadmin:post:query")
    @GetMapping(value = "/{postId}")
    public AjaxResult getInfo(@PathVariable Long postId)
    {
        return success(postService.selectPostById(postId));
    }

    //修改
    //@RequiresPermissions("webadmin:post:edit")
    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody BytesmartPost post){
        if (!postService.checkPostNameUnique(post))
        {
            return error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        }
        else if (!postService.checkPostCodeUnique(post))
        {
            return error("新增岗位'" + post.getPostName() + "'失败，岗位类型已存在");
        }
        post.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(postService.updatePost(post));
    }

    //新增
    //@RequiresPermissions("webadmin:post:add")
    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BytesmartPost post){
        if (!postService.checkPostNameUnique(post))
        {
            return error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        }
        else if (!postService.checkPostCodeUnique(post))
        {
            return error("新增岗位'" + post.getPostName() + "'失败，岗位类型已存在");
        }
        post.setCreateBy(SecurityUtils.getUsername());
        return toAjax(postService.insertPost(post));
    }

    //删除
    //@RequiresPermissions("webadmin:post:remove")
    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
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

    @Log(title = "岗位管理", businessType = BusinessType.EXPORT)
    //@RequiresPermissions("system:post:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, BytesmartPost post)
    {
        List<BytesmartPost> list = postService.selectPostList(post);
        ExcelUtil<BytesmartPost> util = new ExcelUtil<BytesmartPost>(BytesmartPost.class);
        util.exportExcel(response, list, "岗位数据");
    }

}
