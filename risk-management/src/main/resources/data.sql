INSERT INTO employee ( email, firstname, lastname)
VALUES ( 'first@example.com','padmaja','krishnakumar'),
( 'second@example.com','vikram','sun'),
( 'third@example.com','krita','vagula'),
( 'fourth@example.com','santha','subbu kumar'),
( 'fifth@example.com','sita','sundar rajan');

INSERT INTO event ( uuid, code, description, status, json_properties)
VALUES ( 'aa65f68e-978a-45d9-9a88-8959b3850d81','payment','payment','online','{"amount": "金额"}');

INSERT INTO sub_event ( uuid, parent_uuid, code, description, status, json_properties)
VALUES ( '8b9a92a6-87f3-11ec-a8a3-0242ac120002','aa65f68e-978a-45d9-9a88-8959b3850d81','payment_with_card','payment with card','online','');

INSERT INTO rule ( uuid, name, code, categoryId, description, status)
VALUES ( '96668402-87fc-11ec-a8a3-0242ac120002','rule001','rule001',1,'测试规则001','online');

-- group 1
INSERT INTO rule_group ( uuid, rule_uuid)
VALUES ( 'd483e432-87fc-11ec-a8a3-0242ac120002','96668402-87fc-11ec-a8a3-0242ac120002');

INSERT INTO rule_condition ( uuid, rule_group_uuid, left_id, operator_uuid, right_value)
VALUES ( '2a0699f4-87fd-11ec-a8a3-0242ac120002','d483e432-87fc-11ec-a8a3-0242ac120002',1,'40d2786e-87fe-11ec-a8a3-0242ac120002','3');

INSERT INTO rule_condition_element ( id, code, name, type, description)
VALUES ( '1','testVar001','测试变量001','Number','测试变量001测试用');

INSERT INTO rule_condition_operator ( uuid, code, name, type, description)
VALUES ( '40d2786e-87fe-11ec-a8a3-0242ac120002','gt','大于','Number','数值测试用');

INSERT INTO rule_condition_operator_script ( operator_uuid, operator_code, dialect, script, description)
VALUES ( '40d2786e-87fe-11ec-a8a3-0242ac120002','gt','java','    boolean compare(Element left, Element right){
        return  left.compare(right);
    }
','大于比较符号的java脚本');

-- group 2
INSERT INTO rule_group ( uuid, rule_uuid)
VALUES ( 'fa3cab3c-87fc-11ec-a8a3-0242ac120002','96668402-87fc-11ec-a8a3-0242ac120002');

INSERT INTO rule_condition ( uuid, rule_group_uuid, left_id, operator_uuid, right_value)
VALUES ( '029065ba-87ff-11ec-a8a3-0242ac120002','fa3cab3c-87fc-11ec-a8a3-0242ac120002',2,'40d2786e-87fe-11ec-a8a3-0242ac120002','a');

INSERT INTO rule_condition_element ( id, code, name, type, description)
VALUES ( '2','testVar002','测试变量002','String','测试变量002测试用');

INSERT INTO rule_condition_operator ( uuid, code, name, type, description)
VALUES ( '277a1ec0-87ff-11ec-a8a3-0242ac120002','contains','包含','String','字符串测试用');

INSERT INTO rule_condition_operator_script ( operator_uuid, operator_code, dialect, script, description)
VALUES ( '277a1ec0-87ff-11ec-a8a3-0242ac120002','contains','java','    boolean compare(Element left, Element right){
        return  left.contains(right);
    }
','包含符号的java脚本');

