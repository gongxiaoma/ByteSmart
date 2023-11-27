package com.bytesmart.admincenter.domain;

import com.bytesmart.common.core.annotation.Excel;
import com.bytesmart.common.core.annotation.Excel.ColumnType;
import com.bytesmart.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BytesmartPost extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 岗位序号 */
    @Excel(name = "岗位序号", cellType = ColumnType.NUMERIC)
    private Integer postId;

    private String postName;

    /** 岗位权重 */
    @Excel(name = "岗位权重")
    private Integer postWeight;

    /** 岗位类型 全职，兼职等 */
    @Excel(name = "岗位类型")
    private String postType;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String postStatus;


    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Integer getPostWeight() {
        return postWeight;
    }

    public void setPostWeight(Integer postWeight) {
        this.postWeight = postWeight;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("postId", getPostId())
                .append("postName", getPostName())
                .append("postWeight", getPostWeight())
                .append("postType", getPostType())
                .append("postStatus", getPostStatus())
                .toString();
    }
}
