CREATE TABLE `sw_announcement` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `title` varchar(100) DEFAULT '' COMMENT '公告标题',
                                   `content` varchar(5000) DEFAULT '' COMMENT '公告内容',
                                   `operator` varchar(100) NOT NULL DEFAULT '' COMMENT '操作者',
                                   `status` int(11) NOT NULL DEFAULT '0' COMMENT '狀態：0-草稿，1-發佈',
                                   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
                                   `datetime_lastchange` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新時間',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sw公告信息表';

CREATE TABLE `sw_append_resource` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                      `source_type` int(11) NOT NULL DEFAULT '0' COMMENT '上传文件类型：1-任务资源，2-任务日志资源，3-工具',
                                      `file_path` varchar(100) DEFAULT '' COMMENT '附件地址',
                                      `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最近更新時間',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SW-上传资源信息';

CREATE TABLE `sw_employee` (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `name` varchar(100) DEFAULT '' COMMENT '组织名称',
                               `employee_no` varchar(20) DEFAULT '' COMMENT '工号信息',
                               `email` varchar(100) DEFAULT '' COMMENT '电子邮箱',
                               `phone_number` varchar(100) DEFAULT '' COMMENT '电话号码',
                               `hire_date` varchar(100) DEFAULT '' COMMENT '入职日期',
                               `job_title` varchar(100) DEFAULT '' COMMENT '职位名称',
                               `department_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属部门 ID，关联组织表中的 id',
                               `status` int(11) NOT NULL DEFAULT '0' COMMENT '狀態：0-无效，1-有效',
                               `position_start_date` varchar(10) NOT NULL DEFAULT '' COMMENT '担任当前职位的起始日期',
                               `position_end_date` varchar(10) NOT NULL DEFAULT '' COMMENT '担任当前职位的结束日期，NULL 表示当前在职',
                               `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最近更新時間',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `sw_employee_employee_no_uindex` (`employee_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='SW员工信息';

CREATE TABLE `sw_log` (
                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
                          `operator` varchar(100) NOT NULL DEFAULT '' COMMENT '操作者',
                          `operateType` varchar(100) NOT NULL DEFAULT '' COMMENT '操作類型',
                          `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '行為信息',
                          `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '創建時間',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志记录信息';

CREATE TABLE `sw_menu` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
                           `module_no` int(11) DEFAULT '0' COMMENT '模块号：1-工具库系统，2-报价系统',
                           `menu_name` varchar(100) NOT NULL DEFAULT '' COMMENT '角色名',
                           `menu_url` varchar(100) NOT NULL DEFAULT '0' COMMENT '菜單地址',
                           `route` varchar(100) DEFAULT '' COMMENT '页面路径',
                           `is_menu` int(11) NOT NULL DEFAULT '0' COMMENT '菜单：1-菜單，0-非菜单',
                           `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父菜單ID',
                           `order_by` int(11) DEFAULT '0' COMMENT '排序序号',
                           `status` int(11) NOT NULL DEFAULT '0' COMMENT '狀態：0-默認，1-不可用',
                           `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '創建時間',
                           `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最近更新時間',
                           PRIMARY KEY (`id`),
                           KEY `sw_menu_module_no_index` (`module_no`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='菜單表';

CREATE TABLE `sw_org` (
                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                          `name` varchar(100) DEFAULT '' COMMENT '组织名称',
                          `description` varchar(200) DEFAULT '' COMMENT '组织描述',
                          `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父组织 ID，用于建立层级关系，如果是顶级组织则为 NULL',
                          `status` int(11) NOT NULL DEFAULT '0' COMMENT '狀態：0-无效，1-有效',
                          `start_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '组织的生效起始日期',
                          `end_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '组织的失效结束日期，NULL 表示当前有效',
                          `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最近更新時間',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织架构';

CREATE TABLE `sw_property` (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
                               `category` int(11) NOT NULL DEFAULT '0' COMMENT '分類：0-默认，待分配，1-工具类型，2-任务类型',
                               `property_name` varchar(100) NOT NULL DEFAULT '' COMMENT '属性名称',
                               `icon` varchar(50) DEFAULT '' COMMENT '分類對應的圖標',
                               `order_by` int(11) DEFAULT '0' COMMENT '排序信息',
                               `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '創建時間',
                               `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '上次更新時間',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='工具分类表';

CREATE TABLE `sw_role` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
                           `role_name` varchar(100) NOT NULL DEFAULT '' COMMENT '角色名',
                           `role_status` int(11) NOT NULL DEFAULT '0' COMMENT '狀態：0-默認，1-不可用',
                           `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '創建時間',
                           `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最近更新時間',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表'

CREATE TABLE `sw_role_menu` (
                                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
                                `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
                                `menu_id` int(11) NOT NULL DEFAULT '0' COMMENT '菜单ID',
                                `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '創建時間',
                                `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最近更新時間',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

CREATE TABLE `sw_task` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `category` varchar(100) DEFAULT '' COMMENT '任务类别',
                           `title` varchar(500) DEFAULT '' COMMENT '任务标题',
                           `project` varchar(100) DEFAULT '' COMMENT '所属任务',
                           `description` varchar(2000) DEFAULT '' COMMENT '任务描述',
                           `level` varchar(100) DEFAULT '' COMMENT '任务等级',
                           `status` int(11) NOT NULL DEFAULT '0' COMMENT '狀態：0-草稿，1-待处理，2-处理中，3-任务发布，4-待验收，5-已完成，6-已关闭',
                           `proposer_eid` varchar(100) DEFAULT '' COMMENT '需求提出者',
                           `handle_eid` varchar(100) DEFAULT '' COMMENT '负责人',
                           `dead_line` varchar(10) DEFAULT '' COMMENT '任务期限',
                           `resource_ids` varchar(200) DEFAULT '' COMMENT '关联资源id信息，逗号分隔',
                           `start_date` varchar(100) DEFAULT '' COMMENT '任务开始时间',
                           `end_date` varchar(100) DEFAULT '' COMMENT '任务完成时间',
                           `evaluation` int(11) NOT NULL DEFAULT '0' COMMENT '任务评价星级：1-5星',
                           `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '任务完成时间',
                           `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最近更新時間',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SW-任务信息';

CREATE TABLE `sw_task_progress` (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `taskId` int(11) NOT NULL DEFAULT '0' COMMENT '任务ID',
                               `operate_eid` varchar(20) DEFAULT '' COMMENT '负责人',
                               `resource_ids` varchar(200) DEFAULT '' COMMENT '关联资源id信息，逗号分隔',
                               `content` varchar(500) DEFAULT '' COMMENT '更新内容',
                               `remark` varchar(2000) DEFAULT '' COMMENT '详细内容',
                               `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最近更新時間',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SW-任务进度记录';

CREATE TABLE `sw_task_log` (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `taskId` int(11) NOT NULL DEFAULT '0' COMMENT '任务ID',
                               `operator` varchar(20) DEFAULT '' COMMENT '操作人',
                               `content` varchar(500) DEFAULT '' COMMENT '操作内容',
                               `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最近更新時間',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SW-任务操作记录';

CREATE TABLE `sw_task_evaluation` (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `task_id` int(11) NOT NULL DEFAULT '0' COMMENT '任务ID',
                               `completion` int(11) NOT NULL DEFAULT '0' COMMENT '完成度',
                               `efficiency` int(11) NOT NULL DEFAULT '0' COMMENT '效率',
                               `quality` int(11) NOT NULL DEFAULT '0' COMMENT '质量',
                               `content` varchar(500) DEFAULT '' COMMENT '操作内容',
                               `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SW-任务评价记录';

CREATE TABLE `sw_tool_run_result` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
                                      `operator` varchar(100) NOT NULL DEFAULT '' COMMENT '操作者',
                                      `tool_name` varchar(100) NOT NULL DEFAULT '' COMMENT '操作類型',
                                      `run_result` varchar(100) NOT NULL DEFAULT '' COMMENT '操作類型',
                                      `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注信息',
                                      `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '創建時間',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='工具执行记录';

CREATE TABLE `sw_tools` (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
                            `tool_name` varchar(200) DEFAULT '' COMMENT '工具名稱',
                            `tool_icon` varchar(100) DEFAULT '' COMMENT '图标ICON',
                            `property_id` int(11) DEFAULT '0' COMMENT '工具属性',
                            `version_no` varchar(50) DEFAULT '' COMMENT '版本號',
                            `file_path` varchar(200) DEFAULT '' COMMENT '文件存儲路徑',
                            `tool_size` double DEFAULT '0' COMMENT '文件大小\n',
                            `introduction` varchar(500) DEFAULT '' COMMENT '工具介紹',
                            `update_content` varchar(500) DEFAULT '' COMMENT '更新內容',
                            `use_guide` varchar(500) DEFAULT '' COMMENT '使用指南',
                            `operator` varchar(50) DEFAULT '' COMMENT '最近處理人',
                            `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '創建時間',
                            `datetime_lastchange` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '上次更新時間',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `sw_tools_tool_name_uindex` (`tool_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='工具庫表';

CREATE TABLE `sw_tools_history` (
                                    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
                                    `tool_id` int(11) DEFAULT NULL COMMENT '工具id',
                                    `tool_name` varchar(200) DEFAULT '' COMMENT '工具名稱',
                                    `tool_icon` varchar(100) DEFAULT '' COMMENT '工具图标ICON',
                                    `property_id` int(11) DEFAULT '0' COMMENT '工具属性分类',
                                    `version_no` varchar(50) DEFAULT '' COMMENT '版本號',
                                    `file_path` varchar(200) DEFAULT '' COMMENT '文件存儲路徑',
                                    `tool_size` double DEFAULT '0' COMMENT '文件大小',
                                    `introduction` varchar(500) DEFAULT '' COMMENT '工具介紹',
                                    `update_content` varchar(500) DEFAULT '' COMMENT '更新內容',
                                    `use_guide` varchar(500) DEFAULT '' COMMENT '使用指南',
                                    `operator` varchar(50) DEFAULT '' COMMENT '處理人',
                                    `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '創建時間',
                                    `datetime_lastchange` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '上次更新時間',
                                    PRIMARY KEY (`id`),
                                    KEY `sw_tools_history_tool_id_index` (`tool_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='工具更新歷史';

CREATE TABLE `sw_user` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
                           `user_name` varchar(100) NOT NULL DEFAULT '' COMMENT '用戶名',
                           `name` varchar(100) DEFAULT '' COMMENT '用户名',
                           `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密碼',
                           `solt` varchar(50) DEFAULT '' COMMENT '密码 加盐信息',
                           `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '電話號碼',
                           `email` varchar(100) NOT NULL DEFAULT '' COMMENT '郵箱地址',
                           `dept_id` int(11) NOT NULL DEFAULT '0' COMMENT '部門Id',
                           `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '創建時間',
                           `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最近更新時間',
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `sw_sys_user_user_name_index` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='sw用戶表';

CREATE TABLE `sw_user_login` (
                                 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
                                 `user_name` varchar(100) NOT NULL DEFAULT '' COMMENT '用戶名',
                                 `token` varchar(100) NOT NULL DEFAULT '' COMMENT '登录token',
                                 `expire_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '失效时间',
                                 `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
                                 `datetime_lastchange` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新時間',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='sw用戶登录信息表';

CREATE TABLE `sw_user_role` (
                                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
                                `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用戶ID',
                                `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
                                `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '創建時間',
                                `datetime_lastchange` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最近更新時間',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';


select sm.menu_name,sm.menu_url,sp.id from sw_menu sm inner  join  sw_property sp on sm.module_no=sp.id
where sp.category= 1 and sp.id=1 and sm.status=1 and sm.parent_id=0

truncate table  sw_task;

select *
from sw_task;


select *
from sw_task_log;