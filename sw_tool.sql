-- auto-generated definition
create schema foxconn_sw collate utf8mb4_general_ci;

create table sw_announcement
(
    id                  int auto_increment comment '主键'
        primary key,
    title               varchar(100)  default '' null comment '公告标题',
    content             varchar(5000) default '' null comment '公告内容',
    operator            varchar(100)  default '' not null comment '操作者',
    status              int           default 0  not null comment '狀態：0-草稿，1-發佈',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) not null comment '登录时间',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment 'sw公告信息表' charset=utf8;

create table sw_append_resource
(
    id                  int auto_increment comment '主键'
        primary key,
    origin_name         varchar(50)  default '' null comment '文件原始名',
    file_path           varchar(100) default '' null comment '附件地址',
    upload_type         varchar(50)  default '' null,
    operator            varchar(50)  default '' null comment '处理人',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment 'SW-上传资源信息' charset=utf8;

create table sw_contact_gather
(
    id                  int auto_increment comment '主鍵'
        primary key,
    employee_no         varchar(50) default '' not null comment '用戶编号',
    gather_employee_no  varchar(50) default '' not null comment '收藏的用户',
    status              int         default 0  not null comment '1-收藏，0-废弃',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間',
    constraint sw_contact_gather_employee_no_gather_employee_no_uindex
        unique (employee_no, gather_employee_no)
) comment '用户角色关联表' charset=utf8;

create index sw_contact_gather_employee_no_index
    on sw_contact_gather (employee_no);

create table sw_department
(
    id                  int auto_increment comment '主键'
        primary key,
    name                varchar(100) default '' null comment '组织名称',
    manager_no          varchar(50)  default '' null comment '負責人工號',
    description         varchar(200) default '' null comment '组织描述',
    parent_id           int          default 0  not null comment '父组织 ID，用于建立层级关系，如果是顶级组织则为 NULL',
    status              int          default 0  not null comment '狀態：0-无效，1-有效',
    start_date          varchar(30)  default '' not null comment '组织的生效起始日期',
    end_date            varchar(30)  default '' not null comment '组织的失效结束日期，NULL 表示当前有效',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment '组织架构' charset=utf8;

create table sw_employee
(
    id                  int auto_increment comment '主键'
        primary key,
    employee_no         varchar(20)  default '' null comment '工号信息',
    name                varchar(100) default '' null comment '中文名',
    first_name          varchar(50)  default '' null comment '英文名 firstname',
    last_name           varchar(50)  default '' null comment '英文名 lastname',
    gender              int          default 0 null comment '0-未知，1-男，2-女',
    department_id       int          default 0 not null comment '所属部门 ID，关联组织表中的 id',
    post_id             int          default 0 null comment '职位名称',
    inner_email         varchar(100) default '' null comment '电子邮箱',
    outer_mail          varchar(100) default '' null comment '外部邮箱 ',
    land_line           varchar(11)  default '' null comment '座机号',
    phone_number        varchar(20)  default '' null comment '电话号码',
    hire_date           varchar(100) default '' null comment '入职日期',
    status              int          default 0 not null comment '狀態：1-无效，0-有效',
    outer_work_years    int          default 0 null comment '廠外工作年限',
    outer_abc_years     int          default 0 null comment '廠外ABC經驗',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間',
    constraint sw_employee_employee_no_uindex
        unique (employee_no)
) comment 'SW员工信息' charset=utf8;

create table sw_log
(
    id          int auto_increment comment '主鍵'
        primary key,
    operator    varchar(100) default '' not null comment '操作者',
    ip          varchar(50)  default '' null comment 'IP信息',
    operateType varchar(100) default '' not null comment '操作類型',
    remark      varchar(500) default '' not null comment '行為信息',
    create_time datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間'
) comment '操作日志记录信息' charset=utf8;

create table sw_menu
(
    id                  int auto_increment comment '主鍵'
        primary key,
    menu_name           varchar(100) default ''  not null comment '角色名',
    menu_url            varchar(100) default '0' not null comment '菜單地址',
    route               varchar(100) default '' null comment '页面路径',
    module_no           int          default 0 null comment '模块号：1-工具库系统，2-报价系统',
    is_module_index     int          default 0 null comment '是否模块首页',
    is_menu             int          default 0   not null comment '菜单：1-菜單，0-非菜单',
    parent_id           int          default 0   not null comment '父菜單ID',
    order_by            int          default 0 null comment '排序序号',
    status              int          default 0   not null comment '狀態：0-默認，1-不可用',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment '菜單表' charset=utf8;

create index sw_menu_module_no_index
    on sw_menu (module_no);

create table sw_property
(
    id                  int auto_increment comment '主鍵'
        primary key,
    category            int          default 0  not null comment '分類：0-默认，待分配，1-工具类型，2-任务类型',
    property_name       varchar(100) default '' not null comment '属性名称',
    icon                varchar(50)  default '' null comment '分類對應的圖標',
    order_by            int          default 0 null comment '排序信息',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) not null comment '創建時間',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '上次更新時間'
) comment '工具分类表';

create table sw_role
(
    id                  int auto_increment comment '主鍵'
        primary key,
    role_name           varchar(100) default '' not null comment '角色名',
    role_status         int          default 0  not null comment '狀態：0-默認，1-不可用',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment '角色表' charset=utf8;

create table sw_role_menu
(
    id                  int auto_increment comment '主鍵'
        primary key,
    role_id             int default 0 not null comment '角色ID',
    menu_id             int default 0 not null comment '菜单ID',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment '角色菜单关联表' charset=utf8;

create table sw_task
(
    id                  int auto_increment comment '主键'
        primary key,
    task_no             bigint        default 0 null comment '任务编号',
    top_category        varchar(100)  default '' null comment '父級任務分類-實際任務分類',
    category            varchar(50)   default '' null comment '任務分類-實際任務分類',
    title               varchar(500)  default '' null comment '任务标题',
    top_project         varchar(50)   default '' null comment '父所属任务',
    project             varchar(50)   default '' null comment '子所属任务，實際所屬任務',
    description         text null comment '任务描述',
    level               varchar(100)  default '' null comment '任务等级',
    progress_percent    int           default 0 null comment '百分比',
    status              int           default 0 not null comment '狀態：0-草稿，1-待处理，2-处理中，3-任务发布，4-待验收，5-已完成，6-已关闭',
    reject_status       int           default 0 null comment '駁回狀態：0-無駁回，1-發佈駁回，2-驗收駁回',
    proposer_eid        varchar(50)   default '' null comment '需求提出者',
    manager_eid         varchar(50)   default '' null comment '经办人',
    handle_eid          varchar(50)   default '' null comment '负责人',
    dead_line           varchar(10)   default '' null comment '任务期限',
    resource_ids        varchar(200)  default '' null comment '关联资源id信息，逗号分隔',
    start_date          varchar(100)  default '' null comment '任务开始时间',
    end_date            varchar(100)  default '' null comment '任务完成时间',
    reflection          varchar(1000) default '' null comment '任务的反思总结',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) not null comment '任务完成时间',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment 'SW-任务信息' charset=utf8;

create index sw_task_task_no_index
    on sw_task (task_no);

create table sw_task_employee_relation
(
    id                  int auto_increment comment '主键'
        primary key,
    task_id             int         default 0 null comment '任务ID',
    employee_no         varchar(50) default '' null comment '关联责任人员工信息',
    prev_id             int         default 0 null comment '上一个责任人',
    role_flag           int         default 0 null comment '角色标记位',
    is_active           int         default 0 null comment '1-表示 任务的当前负责人',
    is_delete           int         default 0 not null comment '状态：0-正常可用，1-无效数据',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
);

create index sw_task_employee_relation_task_id_employee_no_index
    on sw_task_employee_relation (task_id, employee_no);

create table sw_task_evaluation
(
    id          int auto_increment comment '主键'
        primary key,
    task_id     int          default 0 not null comment '任务ID',
    operator    varchar(50)  default '' null comment '评价人',
    completion  int          default 0 not null comment '完成度',
    efficiency  int          default 0 not null comment '效率',
    quality     int          default 0 not null comment '质量',
    content     varchar(500) default '' null comment '操作内容',
    create_time datetime(3) default CURRENT_TIMESTAMP (3) not null comment '创建时间'
) comment 'SW-任务评价记录' charset=utf8;

create table sw_task_follow
(
    id                  int auto_increment
        primary key,
    task_id             int          default 0 null comment 'taskID 任务表主键',
    content             varchar(200) default '' null comment '跟进内容',
    status              int          default 0 null comment '0-默认，1-已读',
    operator            varchar(50)  default '' null comment '操作人',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null,
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) null on update CURRENT_TIMESTAMP (3)
) comment '任务跟进表';

create table sw_task_log
(
    id                  int auto_increment comment '主键'
        primary key,
    task_id             int          default 0 not null comment '任务ID',
    operator            varchar(50)  default '' null comment '操作人',
    content             varchar(500) default '' null comment '操作内容',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment 'SW-任务操作记录' charset=utf8;

create table sw_task_progress
(
    id                  int auto_increment comment '主键'
        primary key,
    task_id             int           default 0 not null comment '任务ID',
    operate_eid         varchar(20)   default '' null comment '负责人',
    resource_ids        varchar(200)  default '' null comment '关联资源id信息，逗号分隔',
    progress            int           default 0 null comment '进度信息',
    content             varchar(2000) default '' null comment '更新内容',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment 'SW-任务进度记录' charset=utf8;

create table sw_tool_run_result
(
    id          int auto_increment comment '主鍵'
        primary key,
    operator    varchar(100) default '' not null comment '操作者',
    tool_name   varchar(100) default '' not null comment '操作類型',
    run_result  varchar(100) default '' not null comment '操作類型',
    `interval`  bigint       default 0 null comment '耗时',
    remark      varchar(500) default '' not null comment '备注信息',
    create_time datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間'
) comment '工具执行记录' charset=utf8;

create table sw_tools
(
    id                  int auto_increment comment '主鍵'
        primary key,
    tool_name           varchar(200) default '' null comment '工具名稱',
    tool_icon           varchar(100) default '' null comment '图标ICON',
    property_id         int          default 0 null comment '工具属性',
    version_no          varchar(50)  default '' null comment '版本號',
    file_path           varchar(200) default '' null comment '文件存儲路徑',
    resource_id         int          default 0 null comment '资源文件id',
    tool_size           double       default 0 null comment '文件大小
',
    introduction        varchar(500) default '' null comment '工具介紹',
    update_content      varchar(500) default '' null comment '更新內容',
    use_guide           varchar(500) default '' null comment '使用指南',
    operator            varchar(50)  default '' null comment '最近處理人',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) null on update CURRENT_TIMESTAMP (3) comment '上次更新時間',
    constraint sw_tools_tool_name_uindex
        unique (tool_name)
) comment '工具庫表' charset=utf8;

create table sw_tools_history
(
    id                  int auto_increment comment '主鍵'
        primary key,
    tool_id             int null comment '工具id',
    tool_name           varchar(200) default '' null comment '工具名稱',
    tool_icon           varchar(100) default '' null comment '工具图标ICON',
    property_id         int          default 0 null comment '工具属性分类',
    version_no          varchar(50)  default '' null comment '版本號',
    file_path           varchar(200) default '' null comment '文件存儲路徑',
    tool_size           double       default 0 null comment '文件大小',
    introduction        varchar(500) default '' null comment '工具介紹',
    update_content      varchar(500) default '' null comment '更新內容',
    use_guide           varchar(500) default '' null comment '使用指南',
    operator            varchar(50)  default '' null comment '處理人',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) null on update CURRENT_TIMESTAMP (3) comment '上次更新時間'
) comment '工具更新歷史';

create index sw_tools_history_tool_id_index
    on sw_tools_history (tool_id);

create table sw_user
(
    id                  int auto_increment comment '主鍵'
        primary key,
    employee_no         varchar(20)  default '' not null comment '用戶名',
    password            varchar(100) default '' not null comment '密碼',
    solt                varchar(50)  default '' null comment '密码 加盐信息',
    avatar_id           int          default 0 null comment '头像链接',
    signature           varchar(500) default '' null comment '签名信息
',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間',
    constraint sw_sys_user_user_name_index
        unique (employee_no)
) comment 'sw用戶表' charset=utf8;

create table sw_user_login
(
    id                  int auto_increment comment '主鍵'
        primary key,
    employee_no         varchar(100) default '' not null comment '用戶名',
    token               varchar(100) default '' not null comment '登录token',
    expire_time         datetime     default CURRENT_TIMESTAMP null comment '失效时间',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null comment '登录时间',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment 'sw用戶登录信息表' charset=utf8;

create table sw_user_role
(
    id                  int auto_increment comment '主鍵'
        primary key,
    user_id             int default 0 not null comment '用戶ID',
    role_id             int default 0 not null comment '角色ID',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment '用户角色关联表' charset=utf8;

create table sw_work_report
(
    id                  int auto_increment comment '主鍵'
        primary key,
    employee_no         varchar(50)   default '' not null comment '用戶ID',
    year_week           varchar(7)    default '0000-01' null comment ' 年+周数',
    week                int           default 0  not null comment '周数',
    num                 int           default 0  not null comment '工作序号',
    project             varchar(100)  default '' not null comment '项目',
    days                int           default 0  not null comment '用时天数',
    target              int           default 0  not null comment '目标百分比',
    current             int           default 0  not null comment '当前百分比',
    status              int           default 1  not null comment '状态：1-正常可用，0-无效数据',
    description         varchar(500)  default '' not null comment '内容描述信息',
    remark              varchar(2000) default '' not null comment '详情备注信息',
    create_time         datetime(3) default CURRENT_TIMESTAMP (3) null comment '創建時間',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間'
) comment '工作日志表' charset=utf8;

create table task_no_seed
(
    id                  int auto_increment comment '主鍵'
        primary key,
    seed                bigint default 0 not null comment '编码种子',
    status              int    default 1 null comment '1-已使用，0-未使用',
    datetime_lastchange datetime(3) default CURRENT_TIMESTAMP (3) not null on update CURRENT_TIMESTAMP (3) comment '最近更新時間',
    constraint task_no_seed_seed_uindex
        unique (seed)
) comment '编码' charset=utf8;

