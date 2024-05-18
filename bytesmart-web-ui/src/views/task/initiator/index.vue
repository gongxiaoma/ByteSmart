<template>
    <!-- 定义一个容器 -->
    <div class="app-container">
        <!-- 新建el-form表单元素,绑定对象"queryParams,给表单定义一个ref(引用名),名字是queryForm,该表单是行内表单模式, v-show="showSearch"实现搜索条件,如果是true表示表单显示 -->
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        
        <!-- 新建el-from表单子项,标签为“任务主题”,属性值对应表单对象queryParams的taskTitle属性 -->
        <el-form-item label="任务主题" prop="taskTitle">
        <!-- 新建一个输入框组件,绑定queryParams对象下taskTitle属性, 设置输入框输入提示信息"请输入任务主题", 设置属性clearable即清除按钮
        设置键盘监听事件,即在输入框中按下Enter回车键时调用handleQuery 方法 -->
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
            placeholder="1代表“未开始”,2代表“进行中"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <!-- 在el-from表单子项,新建按钮“搜索” 图标,点击事件,当按钮被点击时,触发 handleQuery 方法；新建按钮“重置”图标,点当按钮被点击时,触发 resetQuery方法。 -->
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
 
      <!-- 新建el-table表格组件,使用v-loading 指令来显示表格的加载状态,data属性绑定表格的数据源taskByInitiatorList
      @selection-change监听表格的选择变化事件,当选中项发生变化时,调用 handleSelectionChange 方法
      @row-click监听表格行的点击事件,当点击某一行时,调用 handleRowClick 方法 -->
      <el-table v-loading="loading" :data="taskByInitiatorList" @selection-change="handleSelectionChange" @row-click="handleRowClick">
        <!-- //定义表格组件的子组件,用于定义表格的列, type属性为selection实现选择多行数据,设置列,宽度,内容居中。 -->
        <el-table-column type="selection" width="55" align="center" />
        <!-- 新建表格的列,该列标题名称"任务主题",绑定表格的数据源taskByInitiatorList的字段taskTitle -->
        <el-table-column label="任务主题" align="center" prop="taskTitle" />
        <el-table-column label="任务内容" align="center" prop="taskDescr" />

        <el-table-column label="任务进度" align="center" prop="taskProgress" >
        <!-- //设置一个插槽,把v-slot赋值为scope,让scope作为参数传递。 -->
        <template v-slot="scope"> 
            <!-- //新建进度条组件el-progress,把当前行的数据(scope.row)对象的字段taskProgress绑定到属性percentage上。text-inside="true"表示进度条文本会显示在进度条内部,进度条宽度像素26 -->
            <el-progress :percentage="scope.row.taskProgress" :text-inside="true" :stroke-width="26"></el-progress> 
        </template>      
        </el-table-column>

        <el-table-column label="任务状态" align="center" prop="status">
          <!-- //新建一个插槽（自定义表格中某一列的内容）,把slot-scope赋值scope,根据当前行的 status 字段值显示不同的任务状态 -->
          <template slot-scope="scope">
            <!-- //使用 v-if 指令来检查当前行的status字段值( scope.row.status) 是否等于1,如果等于1,则渲染一个 <div> 元素,并显示文本“未开始” -->
            <div>
                <div v-if="scope.row.status === 1">未开始</div>  
                <div v-else-if="scope.row.status === 2">进行中</div> 
                <div v-else-if="scope.row.status === 3">已完成</div> 
                <div v-else-if="scope.row.status === 4">已延期</div>
                <div v-else-if="scope.row.status === 5">已取消</div> 
            </div>
          </template>
        </el-table-column>

        <el-table-column label="创建时间" align="center" prop="createTime" />
        <el-table-column label="预计完成时间" align="center" prop="enddate" />
        <el-table-column label="实际完成时间" align="center" prop="taskAf" />

        <!-- 新建表格列组件,显示名称为“操作”,设置样式;把slot-scope赋值为scope,让scope作为参数传递,新建按钮el-button触发对话框弹出来,按钮的文本是查看指派人 -->
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="dialogVisible = true"
            >查看指派人</el-button>

<!-- 新建对话框,标题是被指派,双向绑定 dialogVisible对话框,当dialogVisible为true时,对话框显示,false时对话框隐藏。点击对话框关闭时调用 handleDialogClose方法。 -->
             <el-dialog
                    title="被指派人" 
                    :visible.sync="dialogVisible" 
                    width="500px"
                    append-to-body
                    @close="handleDialogClose" 
                >
 <!-- 对话框新建表格组件,v-loading指令显示表格的加载状态,data属性绑定表格的数据源selectedRow.bytesmartTasksAssignedList,当选中项发生变化时,调用 handleSelectionChange 方法
 新建表格组件的子组件,用于定义表格的列, type属性为selection实现选择多行数据,设置列,宽度,内容居中。   
 新建表格的列,该列标题名称"姓名",绑定表格的数据源selectedRow.bytesmartTasksAssignedList的字段assignedName           -->
                  <el-table v-loading="loading" :data="selectedRow.bytesmartTasksAssignedList" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="55" align="center" />
                    <el-table-column label="姓名" prop="assignedName"> </el-table-column>
                    <el-table-column label="性别" prop="assignedGender"> </el-table-column>
                        <!-- <template slot-scope="scope"> 
                            <div>
                                {{scope.row.assignedGender === 0 ? '男' : '女'}}
                            </div>
                        </template> -->
                    <el-table-column label="部门" prop="assigneDept"> </el-table-column>
                    <el-table-column label="岗位" prop="assignedPost"> </el-table-column>
                  </el-table>
                  <!-- span定义了一个对话框的底部,新建插槽页脚容器,属性dialog-footer -->
                  <span slot="footer" class="dialog-footer">  
                    <!-- 当取消按钮被点击时，会执行dialogVisible = false关闭对话框,当确定按钮被点击时，会执行dialogVisible = false关闭对话框, -->
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
//   引入@/api/system/initiator目录下方法,这些方法都是调用后端接口
  import { getTaskByInitiatorList, updateTask} from "@/api/system/initiator";
 
//   定义一个组件，组件名字是TaskByInitiator
  export default {
    name: "TaskByInitiator",
    dicts: ['sys_normal_disable'],
    // 数据
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
    // 页面自动跳转先调用created()
    created() {
      this.getList();
    },
    // 定义方法体
    methods: {
        // 定义getList方法,调用方法getTaskByInitiatorList，参数queryParams，后端返回值rows赋值给taskByInitiatorList，后端返回值的total赋值给total
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
      // 当dialogVisible = false时，关闭对话框
      handleDialogClose() {  
        this.dialogVisible = false; 
      },  


      //定义取消方法，组件的open属性（或数据属性）为false,调用reset方法，重置组件的某些状态或数据
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
    //   定义handleQuery方法,设置queryParams对象里面的页码pageNum属性值为1，调用getList方法
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
    //   定义resetQuery方法,调用resetForm方法，把queryForm作为方法参数传递,调用handleQuery方法
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 定义多选框选中数据方法handleSelectionChange,selection为参数,使用了数组的 map 方法来遍历 selection 数组中的每一个item, 对于每一个 assignedId属性，并返回一个新的数组，
    //  这个新数组包含了所有item的assignedId。这个新数组被赋值给 this.ids。这意味着 this.ids 现在包含了 selection中所有元素的assignedId,
      
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.assignedId)
        // 检查 selection 数组的长度是否不等于1。如果 selection 数组的长度不是1（即它包含0个元素或超过1个元素)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },


    }
  };
  </script>