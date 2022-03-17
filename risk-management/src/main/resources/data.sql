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

------------------------ strategy 1 -------------------
-- strategy
INSERT INTO strategy ( uuid, code, description)
values('d68c07d6-a3af-11ec-b909-0242ac120002', 'strategy_001', '');

-- strategy_node
---- start
INSERT INTO strategy_node ( uuid, code, description, type, weight, rule_uuid, result, strategy_uuid)
values('f049ff84-a3af-11ec-b909-0242ac120002', 'start_node_001', '', 'start', 0, null, null, 'd68c07d6-a3af-11ec-b909-0242ac120002');
---- common
INSERT INTO strategy_node ( uuid, code, description, type, weight, rule_uuid, result, strategy_uuid)
values('82d63dad-d7d7-4c55-a7c6-211cfc4e67a6', 'common_node_001', '', 'common', 100, '96668402-87fc-11ec-a8a3-0242ac120002', null, 'd68c07d6-a3af-11ec-b909-0242ac120002');
---- result
INSERT INTO strategy_node ( uuid, code, description, type, weight, rule_uuid, result, strategy_uuid)
values('319bff6b-164c-43dd-a40e-1ecd0e49d43a', 'result_node_001', '', 'result', 0, null, 'reject', 'd68c07d6-a3af-11ec-b909-0242ac120002');

-- strategy_connect
--- start->common
INSERT INTO strategy_connect ( uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic)
values('6a089acb-935e-444c-8da9-8130139a91cf', 'f049ff84-a3af-11ec-b909-0242ac120002', 'start_node_001', '82d63dad-d7d7-4c55-a7c6-211cfc4e67a6', 'common_node_001', 'Any');
--- if Y, common->result
INSERT INTO strategy_connect ( uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic)
values('bbe59ac5-132a-4d3b-b787-a4f3bc7da9c3', '82d63dad-d7d7-4c55-a7c6-211cfc4e67a6', 'common_node_001', '319bff6b-164c-43dd-a40e-1ecd0e49d43a', 'result_node_001','Y');
--- if N, common->start_node_002
INSERT INTO strategy_connect ( uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic)
values('57412e6a-9d53-48f6-9380-55eae3fff8cf', '82d63dad-d7d7-4c55-a7c6-211cfc4e67a6', 'common_node_001', '62f420fb-02e7-47c7-803a-6f4b51f34c01', 'start_node_002','N');

------------------------ strategy 2 -------------------
-- strategy
INSERT INTO strategy ( uuid, code, description)
values('dc564013-5f16-4ff6-84ed-41f56c1bcaed', 'strategy_002', '');

-- strategy_node
---- start
INSERT INTO strategy_node ( uuid, code, description, type, weight, rule_uuid, result, strategy_uuid)
values('62f420fb-02e7-47c7-803a-6f4b51f34c01', 'start_node_002', '', 'start', 0, null, null, 'dc564013-5f16-4ff6-84ed-41f56c1bcaed');
---- common
INSERT INTO strategy_node ( uuid, code, description, type, weight, rule_uuid, result, strategy_uuid)
values('8bce9a9a-e6ce-4cd6-8bbc-1057f6f3ffdf', 'common_node_002', '', 'common', 100, '96668402-87fc-11ec-a8a3-0242ac120002', null, 'dc564013-5f16-4ff6-84ed-41f56c1bcaed');
---- result 1
INSERT INTO strategy_node ( uuid, code, description, type, weight, rule_uuid, result, strategy_uuid)
values('e289d258-4a07-404a-b65e-3ca99ab6513c', 'result_node_0021', '', 'result', 0, null, 'reject', 'dc564013-5f16-4ff6-84ed-41f56c1bcaed');
---- result 2
INSERT INTO strategy_node ( uuid, code, description, type, weight, rule_uuid, result, strategy_uuid)
values('48768ba8-e1a3-4558-a0b0-a7fc7a7e0923', 'result_node_0022', '', 'result', 0, null, 'accept', 'dc564013-5f16-4ff6-84ed-41f56c1bcaed');

-- strategy_connect
--- start->common
INSERT INTO strategy_connect ( uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic)
values('dd43ee7a-5e6d-44ee-90dd-50d8eae02519', '62f420fb-02e7-47c7-803a-6f4b51f34c01', 'start_node_002', '8bce9a9a-e6ce-4cd6-8bbc-1057f6f3ffdf', 'common_node_002', 'Any');
--- common->result 1
INSERT INTO strategy_connect ( uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic)
values('c116c66a-a8cd-4091-955c-840e3ca5cb96', '8bce9a9a-e6ce-4cd6-8bbc-1057f6f3ffdf', 'common_node_002', 'e289d258-4a07-404a-b65e-3ca99ab6513c', 'result_node_0021','Y');
--- common->result 2
INSERT INTO strategy_connect ( uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic)
values('15b6b899-2dca-47ab-9482-af0af317446d', '8bce9a9a-e6ce-4cd6-8bbc-1057f6f3ffdf', 'common_node_002', '48768ba8-e1a3-4558-a0b0-a7fc7a7e0923', 'result_node_0022','N');
