DROP TABLE IF EXISTS rule;
CREATE TABLE rule
(
    id  INTEGER PRIMARY KEY auto_increment,
    uuid     VARCHAR(128) NOT NULL COMMENT 'uuid',
    name     VARCHAR(128),
    code     VARCHAR(128),
    categoryId INTEGER,
    description VARCHAR(256),
    status VARCHAR(64),
    version INTEGER NOT NULL COMMENT 'version',
    UNIQUE (uuid),
    UNIQUE (code)
);

DROP TABLE IF EXISTS rule_group;
CREATE TABLE rule_group
(
    id  INTEGER PRIMARY KEY auto_increment,
    uuid     VARCHAR(128) NOT NULL COMMENT 'uuid',
    rule_uuid     VARCHAR(128) NOT NULL COMMENT 'rule uuid',
    logic_code     VARCHAR(128) NOT NULL DEFAULT 'AND' COMMENT 'and/or, default is and',
    UNIQUE (uuid),
    FOREIGN KEY (rule_uuid) REFERENCES rule(uuid) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS rule_condition;
CREATE TABLE rule_condition
(
    id  INTEGER PRIMARY KEY auto_increment,
    uuid     VARCHAR(128) NOT NULL COMMENT 'uuid',
    rule_group_uuid     VARCHAR(128) NOT NULL COMMENT 'rule uuid',
    logic_code     VARCHAR(128) NOT NULL DEFAULT 'OR' COMMENT 'and/or, default is or',
    left_id  INTEGER NOT NULL COMMENT 'left element',
    operator_code  VARCHAR(128) NOT NULL COMMENT 'operator code',
    right_value  VARCHAR(128) NOT NULL COMMENT 'value',
    UNIQUE (uuid),
    FOREIGN KEY (rule_group_uuid) REFERENCES rule_group(uuid) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS rule_condition_element;
CREATE TABLE rule_condition_element
(
    id  INTEGER PRIMARY KEY auto_increment,
    code     VARCHAR(128) NOT NULL COMMENT 'code',
    name     VARCHAR(128) COMMENT 'name',
    identify_type   VARCHAR(128) DEFAULT 'variable' NOT NULL COMMENT '自身识别类型，默认为变量',
    return_type     VARCHAR(128) NOT NULL COMMENT 'Number/String/Boolean',
    description VARCHAR(256),
    UNIQUE (code)
);

DROP TABLE IF EXISTS rule_condition_operator;
CREATE TABLE rule_condition_operator
(
    id  INTEGER PRIMARY KEY auto_increment,
    code     VARCHAR(128) NOT NULL COMMENT 'code must be unique',
    name     VARCHAR(128) COMMENT 'name',
    compare_type     VARCHAR(128) NOT NULL COMMENT 'Number/String/Boolean',
    description VARCHAR(256),
    UNIQUE(code)
);

DROP TABLE IF EXISTS action_dict;
CREATE TABLE action_dict
(
    id  INTEGER PRIMARY KEY auto_increment,
    code     VARCHAR(128) NOT NULL COMMENT 'code must be unique',
    name     VARCHAR(128) NOT NULL COMMENT 'name',
    params_type     VARCHAR(128) NOT NULL COMMENT 'String/List/Number/Boolean',
    UNIQUE(code)
);

DROP TABLE IF EXISTS tag_dict;
CREATE TABLE tag_dict
(
    id  INTEGER PRIMARY KEY auto_increment,
    code     VARCHAR(128) NOT NULL COMMENT 'code must be unique',
    description   VARCHAR(256) NOT NULL COMMENT 'description',
    UNIQUE(code)
);

DROP TABLE IF EXISTS rule_action;
CREATE TABLE rule_action
(
    id  INTEGER PRIMARY KEY auto_increment,
    uuid     VARCHAR(128) NOT NULL COMMENT 'uuid',
    flag     VARCHAR(8) NOT NULL COMMENT 'Y/N, Y: 命中规则；N：没命中规则',
    rule_uuid     VARCHAR(128) COMMENT 'rule uuid',
    action_code     VARCHAR(128) NOT NULL COMMENT 'refer to action code',
    params_value     VARCHAR(1024) COMMENT 'input params jsonify string',
    extra_map     VARCHAR(1024) COMMENT 'extraMap in JSON format for future extension',
    UNIQUE(uuid),
    UNIQUE KEY `rule_action_union` (rule_uuid, action_code, flag)
);

DROP TABLE IF EXISTS rule_compiled_script;
CREATE TABLE rule_compiled_script
(
    id  INTEGER PRIMARY KEY auto_increment,
    rule_uuid  VARCHAR(128) NOT NULL COMMENT 'rule uuid',
    language   VARCHAR(128) NOT NULL DEFAULT 'java' COMMENT 'language to interoperate operator, e.g java',
    dialect    VARCHAR(128) NOT NULL DEFAULT 'MVEL' COMMENT 'dialect to interoperate operator, e.g MVEL',
    script     VARCHAR(65535) NOT NULL COMMENT '转换后的脚本',
    version    INTEGER NOT NULL COMMENT 'version',
    UNIQUE(rule_uuid)
);

DROP TABLE IF EXISTS event;
CREATE TABLE event
(
    id  INTEGER PRIMARY KEY auto_increment,
    uuid     VARCHAR(128) NOT NULL COMMENT 'uuid',
    code     VARCHAR(128) NOT NULL COMMENT 'code',
    description VARCHAR(256),
    status VARCHAR(64) NOT NULL COMMENT 'online/offline/deleted/wait',
    json_properties text,
    UNIQUE (code),
    UNIQUE (uuid)
);

DROP TABLE IF EXISTS sub_event;
CREATE TABLE sub_event
(
    id  INTEGER PRIMARY KEY auto_increment,
    uuid     VARCHAR(128) NOT NULL COMMENT 'uuid',
    parent_uuid     VARCHAR(128) NOT NULL COMMENT 'parent event uuid',
    code     VARCHAR(128) NOT NULL COMMENT 'code',
    description VARCHAR(256),
    status VARCHAR(64) NOT NULL COMMENT 'online/offline/deleted/wait',
    json_properties text COMMENT '合并并且覆盖父事件里的json的key',
    UNIQUE (code),
    UNIQUE (uuid)
);


DROP TABLE IF EXISTS rule_event;
CREATE TABLE rule_event
(
    id         INTEGER PRIMARY KEY auto_increment,
    rule_uuid  VARCHAR(128) NOT NULL COMMENT 'rule uuid',
    event_uuid VARCHAR(128) NOT NULL COMMENT 'event uuid'
);