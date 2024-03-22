<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="任务主题" prop="taskTitle">
          <el-input
            v-model="queryParams.taskTitle"
            placeholder="请输入任务主题"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="任务内容" prop="taskDescr">
          <el-input
            v-model="queryParams.taskDescr"
            placeholder="请输入任务内容"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input
            v-model="queryParams.status"
            placeholder="1代表“未开始”，2代表“进行中"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
 
      <!-- <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['system:task:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['system:post:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:post:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:post:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row> -->
 
      <el-table v-loading="loading" :data="taskByInitiatorList" @selection-change="handleSelectionChange" @row-click="handleRowClick">
        <el-table-column type="selection" width="55" align="center" />
        <!-- <el-table-column label="任务ID" align="center" prop="taskId" /> -->
        <el-table-column label="任务主题" align="center" prop="taskTitle" />
        <el-table-column label="任务内容" align="center" prop="taskDescr" />
        <el-table-column label="任务进度" align="center" prop="taskProgress" >
        <template v-slot="scope"> 
            <el-progress :percentage="scope.row.taskProgress" :text-inside="true" :stroke-width="26"></el-progress> 
        </template>      
        </el-table-column>

        <el-table-column label="任务状态" align="center" prop="status">
          <template slot-scope="scope">
            <div>
                <div v-if="scope.row.status === 1">未开始</div>  
                <div v-else-if="scope.row.status === 2">进行中</div> 
                <div v-else-if="scope.row.status === 3">已完成</div> 
                <div v-else-if="scope.row.status === 4">已延期</div>
                <div v-else-if="scope.row.status === 5">已取消</div> 
            </div>
          </template>
        </el-table-column>

        <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>

        <el-table-column label="创建时间" align="center" prop="createTime" />
        <el-table-column label="预计完成时间" align="center" prop="enddate" />
        <el-table-column label="实际完成时间" align="center" prop="taskAf" />

        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="dialogVisible = true"
            >查看指派人</el-button>
             <el-dialog
                    title="被指派人" 
                    :visible.sync="dialogVisible" 
                    width="500px"
                    append-to-body
                    @close="handleDialogClose" 
                >
                  <el-table v-loading="loading" :data="selectedRow.bytesmartTasksAssignedList" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="55" align="center" />
                    <!-- <el-table-column label="被分配人ID" prop="assignedId"  width="180" > </el-table-column> -->
                    <el-table-column label="姓名" prop="assignedName"> </el-table-column>
                    <el-table-column label="性别" prop="assignedGender"> </el-table-column>
                    <el-table-column label="部门" prop="assigneDept"> </el-table-column>
                    <el-table-column label="岗位" prop="assignedPost"> </el-table-column>
                  </el-table>

                  <span slot="footer" class="dialog-footer">  
                    <el-button @click="dialogVisible = false">取消</el-button>  
                    <el-button type="primary" @click="dialogVisible = false">确定</el-button>  
                  </span>  

             </el-dialog>

          </template>
        </el-table-column>
      </el-table>
 
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />

    </div>
  </template>
 
  <script>
  import { getTaskByInitiatorList, updateTask} from "@/api/system/initiator";
 
  export default {
    name: "TaskByInitiator",
    dicts: ['sys_normal_disable'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 发起人任务表数据
        taskByInitiatorList: [],
        selectedRow: {},
        // 弹出层标题
        title:{},
        //对话框可视性
        dialogVisible: false,
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          taskTitle: undefined,
          taskDescr: undefined,
        //   enddate: undefined,
          status: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        // rules: {
        //   postName: [
        //     { required: true, message: "岗位名称不能为空", trigger: "blur" }
        //   ],
        //   postCode: [
        //     { required: true, message: "岗位编码不能为空", trigger: "blur" }
        //   ],
        //   postSort: [
        //     { required: true, message: "岗位顺序不能为空", trigger: "blur" }
        //   ]
        // }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询岗位列表 */
      getList() {
        this.loading = true;
        getTaskByInitiatorList(this.queryParams).then(response => {
            // console.log(response)  
          this.taskByInitiatorList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 将选中的行数据赋值给selectedRow 
      handleRowClick(row) {  
        this.selectedRow = row; 
        this.dialogVisible = true; 
      },  
      // 关闭对话框时触发，可以重置selectedRow或其他操作  
      handleDialogClose() {  
        this.dialogVisible = false; 
      },  


      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          taskTitle: undefined,
          taskDescr: undefined,
          status: "0",
          remark: undefined
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.postId)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },



      /** 导出按钮操作 */
      handleExport() {
        this.download('system/post/export', {
          ...this.queryParams
        }, `post_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>