package com.mtech.risk.management.service

import com.mtech.risk.base.model.Event
import com.mtech.risk.base.model.EventContext
import com.mtech.risk.dataio.model.Rule
import com.mtech.risk.dataio.model.RuleCompiledScript
import com.mtech.risk.dataio.service.RuleService
import com.mtech.risk.management.bff.model.RuleVO
import com.mtech.risk.management.utils.Convertor
import com.mtech.risk.plugin.model.*
import com.mtech.risk.plugin.service.RiskRuleScriptExecutor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
open class RuleDataMngService(@Autowired private val ruleService: RuleService,
                              @Autowired private val riskRuleScriptExecutor: RiskRuleScriptExecutor,
                              @Autowired private val transactionTemplate: TransactionTemplate) {

    fun ruleVOQuery(uuid: String):RuleVO?{
        val rule = ruleService.getRuleLogic(uuid) ?: return null
        val ruleVO = Convertor.convertToUIVO(rule)
        return ruleVO
    }
    /**
     * Cascade rule update
     */
    fun ruleUpdate(ruleVO: RuleVO) {
        val rule = Convertor.convertToDomainModel(ruleVO)
        ///////
        transactionTemplate.execute {
            var currentVersion = ruleService.getRuleVersion(ruleVO.uuid)
            if(ruleVO.version==null || ruleVO.version != currentVersion){
                throw RuntimeException("Rule version not capable: current version is $currentVersion, update version is ${ruleVO.version}")
            }
            rule.version++
            ruleService.updateRuleCascade(rule)
        }
        this.reCompileAndSaveRule(ruleVO.uuid, false)
    }

    /**
     * Cascade rule insert
     */
    fun ruleInsert(ruleVO: RuleVO) {
        ruleVO.uuid = UUID.randomUUID().toString()
        val rule = Convertor.convertToDomainModel(ruleVO)

        ///////
        transactionTemplate.execute {
            ruleService.insertNewRuleCascade(rule)
        }
        this.reCompileAndSaveRule(ruleVO.uuid, true)
    }

    /**
     * Compile and save compiled rule
     * @uuid rule uuid
     * @insertOrUpdate: true for insert, false for update
     */
    private fun reCompileAndSaveRule(uuid: String, insertOrUpdate: Boolean){
        val rule: Rule? = ruleService.getCompleteRule(uuid)
        if(rule!=null){
            val ruleObj: RuleObject = Convertor.convertRuleToRuleObject(rule)
            val script = riskRuleScriptExecutor.compile(ruleObj);
            val ruleCompiledScript = RuleCompiledScript(
                0, ruleObj.uuid, "java", "MVEL",script,rule.version
            );
            if(insertOrUpdate){
                ruleService.insertRuleCompiledScript(ruleCompiledScript)
            }else{
                ruleService.updateRuleCompiledScript(ruleCompiledScript)
            }
        }else{
            throw RuntimeException("Rule not exits for the uuid = $uuid")
        }
    }

    fun mockExe(uuid:String):String? {
        val mockEvent = Event(
            "payment",
            "支付",
            ConcurrentHashMap()
        );
        val eventContext = EventContext(
            mockEvent,
            ConcurrentHashMap()
        );
        riskRuleScriptExecutor.execute(eventContext, uuid)
        return "finish"
    }

    fun compileScript(uuid:String):String?{
        val rule: Rule? = ruleService.getCompleteRule(uuid)
        if(rule!=null){
            val ruleObj: RuleObject = Convertor.convertRuleToRuleObject(rule)
            val script = riskRuleScriptExecutor.compile(ruleObj);
    //            val ruleCompiledScript = RuleCompiledScript(
    //                0, ruleObj.uuid, "java", "MVEL",script,1
    //            );
    //            ruleService.insertRuleCompiledScript(ruleCompiledScript)
            return  script;
        }else{
            return null;
        }
    }

}