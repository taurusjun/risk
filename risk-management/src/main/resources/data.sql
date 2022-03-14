INSERT INTO event ( uuid, code, description, status, json_properties)
VALUES ( 'aa65f68e-978a-45d9-9a88-8959b3850d81','payment','payment','online','{"amount": "金额"}');

INSERT INTO sub_event ( uuid, parent_uuid, code, description, status, json_properties)
VALUES ( '8b9a92a6-87f3-11ec-a8a3-0242ac120002','aa65f68e-978a-45d9-9a88-8959b3850d81','payment_with_card','payment with card','online','');

INSERT INTO rule ( uuid, name, code, categoryId, description, status, version)
VALUES ( '96668402-87fc-11ec-a8a3-0242ac120002','rule001','rule001',1,'测试规则001','online', 1);

-- group 1
INSERT INTO rule_group ( uuid, rule_uuid)
VALUES ( 'd483e432-87fc-11ec-a8a3-0242ac120002','96668402-87fc-11ec-a8a3-0242ac120002');

INSERT INTO rule_condition ( uuid, rule_group_uuid, left_id, operator_code, right_value)
VALUES ( '2a0699f4-87fd-11ec-a8a3-0242ac120002','d483e432-87fc-11ec-a8a3-0242ac120002',1,'gt','0');

INSERT INTO rule_condition_element ( id, code, name, return_type, description)
VALUES ( '1','testVar001','测试变量001','Number','测试变量001测试用');

INSERT INTO rule_condition_operator ( code, name, compare_type, description)
VALUES ( 'gt','大于','Number','数值测试用');

-- group 2
INSERT INTO rule_group ( uuid, rule_uuid)
VALUES ( 'fa3cab3c-87fc-11ec-a8a3-0242ac120002','96668402-87fc-11ec-a8a3-0242ac120002');

-- condition 2-1
INSERT INTO rule_condition ( uuid, rule_group_uuid, left_id, operator_code, right_value)
VALUES ( '029065ba-87ff-11ec-a8a3-0242ac120002','fa3cab3c-87fc-11ec-a8a3-0242ac120002',2,'contains','a');

INSERT INTO rule_condition_element ( id, code, name, return_type, description)
VALUES ( '2','testVar002','测试变量002','List_String','测试变量002测试用');

INSERT INTO rule_condition_operator ( code, name, compare_type, description)
VALUES ('contains','包含','String','字符串测试用');

-- condition 2-2
INSERT INTO rule_condition ( uuid, rule_group_uuid, left_id, operator_code, right_value)
VALUES ( '21cfe2de-8e39-4b7c-9219-1d67b18bd7d9','fa3cab3c-87fc-11ec-a8a3-0242ac120002',3,'lt','99');

INSERT INTO rule_condition_element ( id, code, name, return_type, description)
VALUES ( '3','testVar003','测试变量003','Number','测试变量003测试用');

INSERT INTO rule_condition_operator ( code, name, compare_type, description)
VALUES ( 'lt','小于','Number','数值测试用');

-- action_dict
INSERT INTO action_dict ( code, name, params_type)
VALUES ( 'addTag','加入标签','List');

-- tag_dict
INSERT INTO tag_dict ( code, description) VALUES ( 'highRisk','高风险');
INSERT INTO tag_dict ( code, description) VALUES ( 'ATO','账号被盗风险');
INSERT INTO tag_dict ( code, description) VALUES ( 'safeEnv','安全环境');

-- rule action
INSERT INTO rule_action ( uuid, flag, rule_uuid, action_code, params_value, extra_map)
VALUES ( '66243293-43d3-4af4-a9d9-026abb5af9ae','Y','96668402-87fc-11ec-a8a3-0242ac120002','addTag','["highRisk","ATO"]',null);

------- mock compiled script -----
INSERT INTO rule_compiled_script(rule_uuid, language, dialect, script, version)
VALUES ('96668402-87fc-11ec-a8a3-0242ac120002', 'java', 'MVEL', 'boolean rule001= ( ruleConditionCalculator.calc("testVar001","gt","0",["leftReturnType":"Number","leftIdentifyType":"variable","operatorCode":"gt"]) ) && ( ruleConditionCalculator.calc("testVar002","contains","a",["leftReturnType":"List_String","leftIdentifyType":"variable","operatorCode":"contains"]) || ruleConditionCalculator.calc("testVar003","lt","99",["leftReturnType":"Number","leftIdentifyType":"variable","operatorCode":"lt"]) ) ;if(rule001){actionExecutor.exe("addTag","[\"highRisk\",\"ATO\"]",["rawActionExtraMap":"null"]);}else{}', 1);

-- strategy
INSERT INTO strategy ( uuid, code, description, start_node_uuid, create_time, update_time)
values('d68c07d6-a3af-11ec-b909-0242ac120002', 'strategy_001', '', 'f049ff84-a3af-11ec-b909-0242ac120002', now(), now());

-- strategy_node
---- start
INSERT INTO strategy_node ( uuid, code, description, type, weight, rule_uuid, result, strategy_uuid, create_time, update_time)
values('f049ff84-a3af-11ec-b909-0242ac120002', 'start_node_001', '', 'start', null, null, null, 'd68c07d6-a3af-11ec-b909-0242ac120002', now(), now());
---- common
INSERT INTO strategy_node ( uuid, code, description, type, weight, rule_uuid, result, strategy_uuid, create_time, update_time)
values('82d63dad-d7d7-4c55-a7c6-211cfc4e67a6', 'common_node_001', '', 'common', 100, '96668402-87fc-11ec-a8a3-0242ac120002', null, 'd68c07d6-a3af-11ec-b909-0242ac120002', now(), now());
---- result
INSERT INTO strategy_node ( uuid, code, description, type, weight, rule_uuid, result, strategy_uuid, create_time, update_time)
values('319bff6b-164c-43dd-a40e-1ecd0e49d43a', 'result_node_001', '', 'result', null, null, 'reject', 'd68c07d6-a3af-11ec-b909-0242ac120002', now(), now());

-- strategy_connect
--- start->common
INSERT INTO strategy_connect ( uuid, from_node_uuid, to_node_uuid, logic, create_time, update_time)
values('6a089acb-935e-444c-8da9-8130139a91cf', 'f049ff84-a3af-11ec-b909-0242ac120002', '82d63dad-d7d7-4c55-a7c6-211cfc4e67a6', 'Any', now(), now());
--- common->result
INSERT INTO strategy_connect ( uuid, from_node_uuid, to_node_uuid, logic, create_time, update_time)
values('bbe59ac5-132a-4d3b-b787-a4f3bc7da9c3', '82d63dad-d7d7-4c55-a7c6-211cfc4e67a6', '319bff6b-164c-43dd-a40e-1ecd0e49d43a', 'Y', now(), now());
