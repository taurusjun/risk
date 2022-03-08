package com.mtech.risk.plugin.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是为了进行规则转换的POJO对象，文本中立，可以认为是JSON的格式，
 * 例子如下：
 * {
 *     "uuid": "96668402-87fc-11ec-a8a3-0242ac120002",
 *     "code": "rule001",
 *     "name": "rule001",
 *     "status": "online",
 *     "ruleGroupList":
 *     [
 *         {
 *             "uuid": "d483e432-87fc-11ec-a8a3-0242ac120002",
 *             "logicCode": "AND",
 *             "ruleConditionList":
 *             [
 *                 {
 *                     "uuid": "2a0699f4-87fd-11ec-a8a3-0242ac120002",
 *                     "logicCode": "OR",
 *                     "leftNode":
 *                     {
 *                         "code": "testVar001",
 *                         "identifyType": "variable",
 *                         "returnType": "Number"
 *                     },
 *                     "operator":
 *                     {
 *                         "uuid": "40d2786e-87fe-11ec-a8a3-0242ac120002",
 *                         "code": "gt",
 *                         "compareType": "Number"
 *                     },
 *                     "rightValue": "3"
 *                 }
 *             ]
 *         },
 *         {
 *             "uuid": "fa3cab3c-87fc-11ec-a8a3-0242ac120002",
 *             "logicCode": "AND",
 *             "ruleConditionList":
 *             [
 *                 {
 *                     "uuid": "029065ba-87ff-11ec-a8a3-0242ac120002",
 *                     "logicCode": "OR",
 *                     "leftNode":
 *                     {
 *                         "code": "testVar002",
 *                         "identifyType": "variable",
 *                         "returnType": "String"
 *                     },
 *                     "operator":
 *                     {
 *                         "uuid": "277a1ec0-87ff-11ec-a8a3-0242ac120002",
 *                         "code": "contains",
 *                         "compareType": "String"
 *                     },
 *                     "rightValue": "a"
 *                 }
 *             ]
 *         }
 *     ]
 * }
 *
 */
@Data
public class RuleObject {
    String uuid;
    String code;
    String name;
    String status;
    List<RuleGroupObject> ruleGroupList = new ArrayList<>();
    List<RuleActionObject> ruleActionList = new ArrayList<>();

}
